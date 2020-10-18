package or.wr.springbootrabbitmqconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // bean的名字需要与交换机名字一致

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange", true, true);
    }

    // bean的名字需要与队列名字一致
    @Bean
    public Queue directQueue(){
        return new Queue("directQueue");
    }

    /**
     *
     * @param directQueue 队列名，使与@Bean队列的bean名字一致
     * @param directExchange 交换机名，使与@Bean队列的bean名字一致
     * @return 返回Binding
     */
    @Bean
    public Binding directBinding(Queue directQueue, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue).to(directExchange).with("directBinding");
    }

}
