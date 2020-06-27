package or.wr.spring.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class DirectExchangeTest {

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
            //绑定交换机
            channel.exchangeDeclare("directExchangeTest","direct",true);
            //该方法可以在rabbit中创建队列
            channel.queueDeclare("directQueue",true, false, false, null);

            channel.queueBind("directQueue", "directExchangeTest", "directRoutingKey");
            String message = "this is a message of rabbitmq and the type of exchange is direct ";

            channel.basicPublish("directExchangeTest","directRoutingKey", null, message.getBytes(StandardCharsets.UTF_8));
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
