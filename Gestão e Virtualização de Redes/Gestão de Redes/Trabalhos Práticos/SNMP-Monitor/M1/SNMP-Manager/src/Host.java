public class Host {
    private String adress;
    private String port;
    private String communityTarget;

    public Host(String adress, String port, String communityTarget) {
        this.adress = adress;
        this.port = port;
        this.communityTarget = communityTarget;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCommunityTarget() {
        return communityTarget;
    }

    public void setCommunityTarget(String communityTarget) {
        this.communityTarget = communityTarget;
    }

    @Override
    public String toString() {
        return "Host{" +
                "adress='" + adress + '\'' +
                ", port='" + port + '\'' +
                ", communityTarget='" + communityTarget + '\'' +
                '}';
    }
}
