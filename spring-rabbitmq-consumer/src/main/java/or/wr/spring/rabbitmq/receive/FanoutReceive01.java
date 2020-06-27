package or.wr.spring.rabbitmq.receive;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class FanoutReceive01 {

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
            //fanout 由于接收机制，需要先启动消费者，并在此创建交换机等
            channel.exchangeDeclare("fanoutExchangeTest","fanout",true);
            //随机创建队列名，
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, "fanoutExchangeTest","");

            channel.basicConsume(queueName,true, new DefaultConsumer(channel){

                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                {
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println("消费者 01 ："+message);
                }

            });


        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

