package or.wr.spring.rabbitmq.exchange;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class FanoutExchangeTest {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        try {
            //fanout , 此处不需要声明队列，以及队列绑定关系，在消费者中已经完成

            channel.exchangeDeclare("fanoutExchangeTest","fanout",true);

            String message = "this is a message by fanout exchange";

            channel.basicPublish("fanoutExchangeTest","",null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("send successfully!!!");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (channel != null){
                channel.close();
            }
            connection.close();
        }

    }
}

