package or.wr.spring.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class TopicExchangeTest {

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
            //topic , 此处不需要声明队列，以及队列绑定关系，在消费者中已经完成

            channel.exchangeDeclare("topicExchangeTest","topic",true);

            String message01 = "this is a message01 by topic exchange";
            String message02 = "this is a message02 by topic exchange";
            String message03 = "this is a message03 by topic exchange";

            channel.basicPublish("topicExchangeTest","routing",null, message01.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish("topicExchangeTest","routing.Key",null, message02.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish("topicExchangeTest","routing.key.test",null, message03.getBytes(StandardCharsets.UTF_8));
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

