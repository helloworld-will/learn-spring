package or.wr.springrabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;


@RestController
public class TestController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("sendTest")
    public String sendTest(String message){
        String ms = LocalDate.now().toString()+ " "+ LocalTime.now().toString();
        System.out.println(ms);
        rabbitTemplate.convertAndSend(rabbitTemplate.getExchange(), rabbitTemplate.getRoutingKey(),
                ms);
        System.out.println(message + ms);
        return message + ms;
    }

    @RequestMapping("test")
    public String test01(){
        return "ok";
    }

}
