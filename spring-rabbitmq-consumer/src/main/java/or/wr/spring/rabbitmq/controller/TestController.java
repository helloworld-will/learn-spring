package or.wr.spring.rabbitmq.controller;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("getMessage")
    public String getMessage(){

        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("127.0.0.1");



        return null;
    }
}
