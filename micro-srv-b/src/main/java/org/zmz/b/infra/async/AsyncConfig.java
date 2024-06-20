package org.zmz.b.infra.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        int coreSize = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(coreSize << 2);

        //线程池队列大小
        executor.setQueueCapacity(100);

        //线程空闲存活时间
        executor.setKeepAliveSeconds(30);

        //线程池拒绝时交由调用线程执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //装饰线程池中的线程
        executor.setTaskDecorator(new ContextCopyingDecorator());
        executor.setThreadNamePrefix("Feign-Async-");
        executor.afterPropertiesSet();

        return executor;
    }

    static class ContextCopyingDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            try {
                //当前请求上下文
                RequestAttributes context = RequestContextHolder.currentRequestAttributes();
                //copy当前调用线程的 ThreadLocalMap 中保存的信息
                //Map<String, String> previous = MDC.getCopyOfContextMap();
                return () -> {
                    try {
                        //http request上下文塞到当前线程中
                        RequestContextHolder.setRequestAttributes(context);
                        //将调用线程的 ThreadLocalMap 塞到当前线程
                        //MDC.setContextMap(previous);
                        runnable.run();
                    } finally {
                        //clear
                        RequestContextHolder.resetRequestAttributes();
                        //MDC.clear();
                    }
                };
            } catch (IllegalStateException e) {
                return runnable;
            }
        }
    }
}
