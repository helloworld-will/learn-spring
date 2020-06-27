package or.wr.spring.rabbitmq.receive;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class DirectReceive {

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
            //绑定交换机、队列、绑定关系，如rabbitmq服务中已经存在，以下三行可以省略采用默认策略
/*            channel.exchangeDeclare("directExchangeTest","direct",true);
            channel.queueDeclare("directQueue",true, false, false, null);
            channel.queueBind("directQueue", "directExchangeTest", "directRoutingKey");*/

            channel.basicConsume("directQueue",true, new DefaultConsumer(channel){

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

    }
}
