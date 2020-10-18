package or.wr.springbootrabbitmq;

import or.wr.springbootrabbitmq.service.RabbitSendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringbootRabbitmqApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(SpringbootRabbitmqApplication.class, args);

        RabbitSendService rabbitSendService = (RabbitSendService) ac.getBean("rabbitSendService");

/*
        rabbitSendService.sendMessage("spring-boot 整合 rabbitMQ，使用direct" + LocalDateTime.now().toString());

        rabbitSendService.sendMessageByFanout("spring-boot 整合 rabbitMQ，使用fanout" + LocalDateTime.now().toString());
*/

        rabbitSendService.sendMessageByTopic("spring-boot整合rabbitMQ，使用 top-aa "
                + LocalDateTime.now().toString(), "top-aa");

        rabbitSendService.sendMessageByTopic("spring-boot整合rabbitMQ，使用 top-aa.bb "
                + LocalDateTime.now().toString(), "top-aa.bb");

        rabbitSendService.sendMessageByTopic("spring-boot整合rabbitMQ，使用 top-aa.bb.cc "
                + LocalDateTime.now().toString(), "top-aa.bb.cc");

    }

}
