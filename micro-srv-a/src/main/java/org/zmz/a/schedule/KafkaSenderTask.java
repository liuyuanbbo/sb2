package org.zmz.a.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zmz.common.Message;
import org.zmz.a.kafka.KafkaSender;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试定时任务，往队列里写数据
 */
@Component
@ConditionalOnExpression("${kafka.schedule.sender.task.enable:true}")
@Slf4j
public class KafkaSenderTask {

    private KafkaSender kafkaSender;
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        log.info(">>>>>>>>>>>> KafkaSenderTask Bean条件装配成功 >>>>>>>>>>>>>>");
    }

    @Autowired
    public void setKafkaSender(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

    // 用于计数
    private static final AtomicInteger count = new AtomicInteger(0);

    // 发生文本消息
    @Scheduled(fixedRate = 2000)
    public void sendTextMessage() {
        int num = count.getAndIncrement();
        String message = "[" + num + "] " + Thread.currentThread().getName()
                + " - 我是生产者，我现在正在生产文本消息！现在的时间是：" + formatter.format(LocalDateTime.now());
        kafkaSender.sendMessage("mytopic02.string", message);
    }

    // 发生JSON消息
    @Scheduled(fixedRate = 3000)
    public void sendObjMessage() throws JsonProcessingException {
        int num = count.getAndIncrement();
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg("[" + num + "]" + UUID.randomUUID());
        message.setSendTime(LocalDate.now());
        kafkaSender.sendMessage("mytopic02.message", "mytopic02.key", objectMapper.writeValueAsString(message));
    }

}