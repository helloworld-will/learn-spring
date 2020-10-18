package or.wr.springbootrabbitmqconsumer;

import or.wr.springbootrabbitmqconsumer.service.RabbitReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Application.class, args);

/*        RabbitReceiveService rabbitReceiveService = (RabbitReceiveService) ac.getBean("rabbitReceiveService");

        rabbitReceiveService.singleReceiveMessage();*/
    }
}
