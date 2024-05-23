package org.zmz.b.portal.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.zmz.common.Message;

import java.util.Optional;

@Component
@Slf4j
@ConditionalOnExpression("${kafka.schedule.receiver.enable:true}")
public class KafkaReceiver {

    ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 使用KafkaListener配置消费者监听的队列
     *
     * @param record 消息对象
     */
    @KafkaListener(topics = "mytopic02.string")
    public void listen1(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            String message = kafkaMessage.get().toString();
            String str = Thread.currentThread().getName() + " - 消费者1 - topic = " + record.topic() +
                    ", key = " + record.key() + ", offset = " + record.offset() + ", message = " + message;
            log.info(str);
        }
    }

    /**
     * 接收JSON
     */
    @KafkaListener(topics = "mytopic02.message")
    public void listen2(ConsumerRecord<?, ?> record) throws JsonProcessingException {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            String json = kafkaMessage.get().toString();
            // 转换为对象
            Message message = objectMapper.readValue(json, Message.class);

            String str = Thread.currentThread().getName() + " - 消费者2 - topic = " + record.topic()
                    + ", key = " + record.key() + ", offset = " + record.offset() + ", message = " + message;
            log.info(str);
        }
    }

}