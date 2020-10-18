package or.wr.springbootrabbitmqconsumer.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RabbitReceiveService {

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 单次接收消息
     */
    public void singleReceiveMessage() {
        String message = (String) amqpTemplate.receiveAndConvert("directQueue");
        System.out.println(message);
    }

    /**
     * RabbitListener注解会随项目的启动，自动监听接收消息，只有当消息正常处理才会将在队列中移除
     * 监听单个队列
     *
     * @param message RabbitListener监听到的消息
     */
    @RabbitListener(queues = "directQueue")
    public void directReceive(String message) {
        System.out.println("异步监听接收的消息：" + message);
    }

    // 两个fanout监听器都会到 producer发送的消息
    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(), // 随机生成队列名，默认随consumer关闭删除，也可以指定
                            exchange = @Exchange(name = "fanoutExchange", type = "fanout"))
            })
    public void fanoutReceive01(String message) {
        System.out.println("fanoutReceive01 异步监听fanout 交换机，接收的消息：" + message);
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(), // 随机生成队列名，默认随consumer关闭删除，也可以指定
                            exchange = @Exchange(name = "fanoutExchange", type = "fanout"))
            }
    )
    public void fanoutReceive02(String message) {
        System.out.println("fanoutReceive02 异步监听fanout 交换机，接收的消息：" + message);
    }


    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue("topic-one"),
                            key = "aa",
                            exchange = @Exchange(name = "topicExchange", type = "topic"))
            }
    )
    public void topicReceiveOne(String message) {
        System.out.println("topicReceiveOne 异步监听 aa 交换机，接收的消息：" + message);
    }


    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue("topic-two"),
                            key = "aa.*",
                            exchange = @Exchange(name = "topicExchange", type = "topic"))
            }
    )
    public void topicReceiveTwo(String message) {
        System.out.println("topicReceiveTwo 异步监听 aa.*交换机，接收的消息：" + message);
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue("topic-three"),
                            key = "aa.#",
                            exchange = @Exchange(name = "topicExchange", type = "topic"))
            }
    )
    public void topicReceiveThree(String message) {
        System.out.println("topicReceiveThree 异步监听aa.#交换机，接收的消息：" + message);
    }

}
