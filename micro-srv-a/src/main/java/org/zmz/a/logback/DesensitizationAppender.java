package org.zmz.a.logback;

import ch.qos.logback.classic.spi.LoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class DesensitizationAppender {
    /**
     * LoggingEvent的属性 - message
     * 格式化前的日志信息，如log.info("your name : {}", "柳成荫")
     * message就是"your name : {}"
     */
    private static final String MESSAGE = "message";
    /**
     * LoggingEvent的属性 - formattedMessage
     * 格式化后的日志信息，如log.info("your name : {}", "柳成荫")
     * formattedMessage就是"your name : 柳成荫"
     */
    private static final String FORMATTED_MESSAGE = "formattedMessage";

    public void operation(LoggingEvent event) {
        // event.getArgumentArray() - 获取日志中的参数数组
        // 如：log.info("your name : {}, your id : {}", "柳成荫", 11)
        // event.getArgumentArray() => ["柳成荫",11]
        if (event.getArgumentArray() != null) {
            // 获取格式化后的Message
            String eventFormattedMessage = event.getFormattedMessage();
            DesensitizationUtil util = new DesensitizationUtil();
            // 获取替换后的日志信息
            String changeMessage = util.customChange(eventFormattedMessage);
            if (!(null == changeMessage || changeMessage.isEmpty())) {
                try {
                    // 利用反射的方式，将替换后的日志设置到原event对象中去
                    Class<? extends LoggingEvent> eventClass = event.getClass();
                    // 保险起见，将message和formattedMessage都替换了
                    Field message = eventClass.getDeclaredField(MESSAGE);
                    message.setAccessible(true);
                    message.set(event, changeMessage);
                    Field formattedMessage = eventClass.getDeclaredField(FORMATTED_MESSAGE);
                    formattedMessage.setAccessible(true);
                    formattedMessage.set(event, changeMessage);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    log.error("反射异常", e);
                }
            }

        }
    }
}