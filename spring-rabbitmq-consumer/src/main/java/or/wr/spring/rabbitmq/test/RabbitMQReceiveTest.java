package or.wr.spring.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class RabbitMQReceiveTest {

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

            channel.queueDeclare("myQueue",true, false, false, null);

            channel.basicConsume("myQueue",true, new DefaultConsumer(channel){

                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                {
                    String message = new String(body,StandardCharsets.UTF_8);
                    System.out.println(message);
                }

            });

        }catch (IOException e){
            e.printStackTrace();
        }
        //不需要关闭链接与通道，实现实时监听接收
    }
}
