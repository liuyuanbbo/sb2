package org.zmz.a.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

@Component
@Slf4j
public class KafkaSender {

    @Resource
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送消息
     *
     * @param topic   主题名称
     * @param message 消息
     */
    public void sendMessage(String topic, String message) {
        ListenableFuture future = kafkaTemplate.send(topic, message);
        future.addCallback(
                o -> log.info("消息发送成功：" + message),
                err -> log.error("消息发送失败：" + message)
        );
    }

    /**
     * 发送消息
     *
     * @param topic   主题名称
     * @param key     键
     * @param message 消息
     */
    public void sendMessage(String topic, String key, String message) {
        ListenableFuture future = kafkaTemplate.send(topic, key, message);
        future.addCallback(
                o -> log.info("消息发送成功：" + message),
                err -> log.error("消息发送失败：" + message)
        );
    }

}