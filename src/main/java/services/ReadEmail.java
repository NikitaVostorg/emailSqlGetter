package services;

import javax.mail.*;
import java.util.Properties;

public class ReadEmail {

    String IMAP_AUTH_EMAIL = "hsebpmorders@gmail.com";
    String IMAP_AUTH_PWD = "hseworkmail";
    String IMAP_Server = "imap.gmail.com";
    String IMAP_Port = "993";
    public Message[] messages;

    public ReadEmail() {
//        messages = this.ReadEmails();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Message[] ReadEmails() {
        Properties properties = new Properties();
        properties.put("mail.debug", "false");
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.imap.port", IMAP_Port);

        Authenticator auth = new EmailAuthenticator(IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);
        Session session = Session.getDefaultInstance(properties, auth);
        session.setDebug(false);

        try {
            Store store = session.getStore();

            // Подключение к почтовому серверу
            store.connect(IMAP_Server, IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);

            // Папка входящих сообщений
            Folder inbox = store.getFolder("INBOX");

            // Открываем папку в режиме только для чтения
            inbox.open(Folder.READ_ONLY);

            return messages = inbox.getMessages();

        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
        return messages;
    }

//    public String services.ReadEmail() {
//        Properties properties = new Properties();
//        properties.put("mail.debug", "false");
//        properties.put("mail.store.protocol", "imaps");
//        properties.put("mail.imap.ssl.enable", "true");
//        properties.put("mail.imap.port", IMAP_Port);
//
//        String messageText = "------------------------------------------------------";
//
//        Authenticator auth = new services.EmailAuthenticator(IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);
//        Session session = Session.getDefaultInstance(properties, auth);
//        session.setDebug(false);
//
//        try {
//            Store store = session.getStore();
//
//            // Подключение к почтовому серверу
//            store.connect(IMAP_Server, IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);
//
//            // Папка входящих сообщений
//            Folder inbox = store.getFolder("INBOX");
//
//            // Открываем папку в режиме только для чтения
//            inbox.open(Folder.READ_ONLY);
//
//            System.out.println("Количество сообщений : " + inbox.getMessageCount());
//            if (inbox.getMessageCount() == 0)
//                return "000000000000000000000000000000000000";
//            // Последнее сообщение; первое сообщение под номером 1
//            Message message = inbox.getMessage(inbox.getMessageCount());
//            Multipart mp = (Multipart) message.getContent();
//            // Вывод содержимого в консоль
//            for (int i = 0; i < mp.getCount(); i++) {
//                BodyPart bp = mp.getBodyPart(i);
//                if (bp.getFileName() == null) {
//                    messageText = bp.getContent().toString();
//                    System.out.println("    " + i + ". сообщение : '" + bp.getContent() + "'");
//                } else {
//                    System.out.println("    " + i + ". файл : '" + bp.getFileName() + "'");
//                }
//            }
//        } catch (MessagingException | IOException e) {
//            System.err.println(e.getMessage());
//        }
//        return messageText;
//    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


}
