package or.wr.springbootrabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RabbitSendService {

    @Resource
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String message) {
        amqpTemplate.convertAndSend("directExchange", "directBinding", message);
    }

    // 前提：交换机确保存在，即消费者启动时是否有创建交换机，无则需要创建
    public void sendMessageByFanout(String message) {
        amqpTemplate.convertAndSend("fanoutExchange", "", message);
    }

    // 前提：交换机确保存在，即消费者启动时是否有创建交换机，无则需要创建
    public void sendMessageByTopic(String message, String key) {
        amqpTemplate.convertAndSend("topicExchange", key, message);
    }
}
