package or.wr.spring.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class RabbitMQProducerTest {

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

            //该方法可以再rabbit中创建队列
            channel.queueDeclare("myQueue",true, false, false, null);
            String message = "this is a message of rabbitmq";

            channel.basicPublish("","myQueue", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("send successfully");
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if (channel != null){
                channel.close();
            }
            connection.close();
        }
    }
}
