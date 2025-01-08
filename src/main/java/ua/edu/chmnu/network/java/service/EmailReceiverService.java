package ua.edu.chmnu.network.java.service;

import org.springframework.boot.web.servlet.server.Session;

import com.sun.mail.*;
import java.util.Properties;

public class EmailReceiverService {

    private String host = "imap.gmail.com";
    private String username = "email";
    private String password = "password";

    public void checkInbox() {
        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            Session emailSession = Session.getInstance(properties);

            Store store = emailSession.getStore("imaps");
            store.connect(host, username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                if (!message.isSet(Flags.Flag.SEEN)) {
                    String from = ((InternetAddress) message.getFrom()[0]).getAddress();
                    String subject = message.getSubject();
                    System.out.println("New email received:");
                    System.out.println("From: " + from);
                    System.out.println("Subject: " + subject);
                    System.out.println();

                    message.setFlag(Flags.Flag.SEEN, true);
                }
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
