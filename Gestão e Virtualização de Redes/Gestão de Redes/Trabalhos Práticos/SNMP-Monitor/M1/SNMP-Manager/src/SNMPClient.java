import java.io.IOException;
        import java.util.*;

        import org.snmp4j.CommunityTarget;
        import org.snmp4j.PDU;
        import org.snmp4j.Snmp;
        import org.snmp4j.TransportMapping;
        import org.snmp4j.event.ResponseEvent;
        import org.snmp4j.mp.SnmpConstants;
        import org.snmp4j.smi.GenericAddress;
        import org.snmp4j.smi.OID;
        import org.snmp4j.smi.OctetString;
        import org.snmp4j.smi.VariableBinding;
        import org.snmp4j.transport.DefaultUdpTransportMapping;
        import org.snmp4j.util.DefaultPDUFactory;
        import org.snmp4j.util.TreeEvent;
        import org.snmp4j.util.TreeUtils;

        import static java.lang.Integer.parseInt;

public class SNMPClient {
    private String hostAdress;
    private String hostPort;
    private String hostCommunityTarget;
    Snmp snmp = null;
    CommunityTarget target;

    //Constructor
    public SNMPClient(String address,String port,String communityTarget){
        hostAdress = address;
        hostPort   = port;
        hostCommunityTarget = communityTarget;
        target = new CommunityTarget();
        configTarget();
    }

    public SNMPClient() {
        hostAdress = "127.0.0.1";
        hostPort   = "161";
        hostCommunityTarget = "gr2020";
        target = new CommunityTarget();
        configTarget();
    }

    private void configTarget() {
        target.setCommunity(new OctetString(hostCommunityTarget));
        target.setAddress(GenericAddress.parse(hostAdress + '/' + hostPort));
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
    }

    public void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
    }



    public Map<OID, String> doWalk(String tableOid) {
        Map<OID, String> result = new TreeMap<OID, String>();
        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = treeUtils.getSubtree(target, new OID(tableOid));

        if (events == null || events.size() == 0) {
            System.out.println("Error: Unable to read table!");
            return result;
        }

        for (TreeEvent event : events) {
            if (event == null) {
                continue;
            }
            if (event.isError()) {
                System.out.println("Error: table OID [" + tableOid + "] " + event.getErrorMessage());
                continue;
            }
            VariableBinding[] varBindings = event.getVariableBindings();
            if (varBindings == null || varBindings.length == 0) {
                continue;
            }
            for (VariableBinding varBinding : varBindings) {
                if (varBinding == null) {
                    continue;
                }
                result.put(varBinding.getOid(), varBinding.getVariable().toString());
            }
        }
        return result;
    }
}