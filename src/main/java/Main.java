import services.OrderService;
import services.ReadEmail;

import javax.mail.Message;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        boolean run = true;
        OrderService orderService = new OrderService();
        ReadEmail email = new ReadEmail();
        Message[] messages = email.messages;
        int inBoxSize = messages.length;

        do {
            if (inBoxSize == 0) {
                return;
            } else {


            }

            Thread.sleep(1 * 60 * 1000);
        } while (run);

    }
}
