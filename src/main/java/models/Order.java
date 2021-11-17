package models;

import javax.persistence.*;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hse_id")
    private String hse_Id;
    @Column(name = "request_text")
    private String request_text;
    @Column(name = "type")
    private String type;

    public Order() {
    }

    public Order(String hseId, String request_text, String type) {
        this.hse_Id = hseId;
        this.request_text = request_text;
        this.type = type;
    }

    public Order parseMessageToOrder(Message m) throws MessagingException, IOException {
        int nose, tail;
        String hseId, request_text, type;
        String message = getTextFromMessage(m);
//  models.Order number
        nose = message.indexOf("сервису № ") + 10;
        tail = nose + 6;
        hseId = message.substring(nose, tail);
//  models.Order type
        type = "bpm";
//  models.Order description
        nose = message.indexOf("Описание вопроса:") + 17;
        tail = message.indexOf("Приложение:", nose) - 4;
        request_text = message.substring(nose, tail);
        System.out.println();
        return new Order(hseId, request_text, type);
    }

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHse_Id() {
        return hse_Id;
    }

    public void setHse_Id(String hse_Id) {
        this.hse_Id = hse_Id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return request_text;
    }

    public void setDescription(String request_text) {
        this.request_text = request_text;
    }
}
