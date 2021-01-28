import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class sendEmail {
    Properties props;
    Session session;
    public sendEmail() {
        props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("snmp.notifier@gmail.com",
                                "snmpmonitor");
                    }
                });

    }

    public void run(String to_mail,String content){

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("snmp.notifier@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress.parse(to_mail);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("SNMP-Notifier - Urgent");//Assunto
            message.setText(content);
            /**Método para enviar a mensagem criada*/
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
