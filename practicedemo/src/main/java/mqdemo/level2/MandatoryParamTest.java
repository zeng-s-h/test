package mqdemo.level2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import mqdemo.RabbitMqConst;
import mqdemo.RabbitMqUtils;

/**
 * @author 小白i
 * @date 2020/7/7
 */
public class MandatoryParamTest {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMqConst.IP_ADDRESS);
        factory.setPort(RabbitMqConst.PORT);
        factory.setUsername(RabbitMqConst.USER_NAME);
        factory.setPassword(RabbitMqConst.PASSWORD);

        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        // 创建一个 type="direct" 、持久化的、非自动删除的交换器
        channel.exchangeDeclare(RabbitMqConst.EXCHANGE_NAME, "direct", true, false, null);
        //创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(RabbitMqConst.QUEUE_NAME, true, false, false, null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(RabbitMqConst.QUEUE_NAME, RabbitMqConst.EXCHANGE_NAME, RabbitMqConst.ROUTING_KEY);
        //发送一条持久化的消息: hello world !
        String message = "lalala ni hao ya !";
        //MessageProperties.PERSISTENT_TEXT_PLAIN:消息持久化到磁盘
        //channel.basicPublish(RabbitMqConst.EXCHANGE_NAME, RabbitMqConst.ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        channel.basicPublish(RabbitMqConst.QUEUE_NAME, "", true, MessageProperties.PERSISTENT_TEXT_PLAIN, "test".getBytes());
        //关闭资源
        RabbitMqUtils.closeResource(channel, connection);
    }

}
