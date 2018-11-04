package de.mieterBewertung;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class EMailUtil {

    public static void sendMail(String resourceFile, String emailFrom, String emailTo, String subject, String text) throws MessagingException, IOException {
        Properties props = System.getProperties();
        props.load(EMailUtil.class.getClassLoader().getResourceAsStream(resourceFile));

        Session session = Session.getInstance(props, null);

        MimeMessage msg = new MimeMessage(session);
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(emailFrom, "NoReply-JD"));

        msg.setReplyTo(InternetAddress.parse(emailFrom, false));

        msg.setSubject(subject, "UTF-8");

        msg.setText(text, "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
        Transport.send(msg);
    }
}
