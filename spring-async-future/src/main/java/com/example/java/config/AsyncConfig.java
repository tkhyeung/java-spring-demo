package com.example.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig {

    //ThreadPoolTaskExecutor is from Spring Framework.
    //Executors.newFixedThreadPool(50) is creates a standard ThreadPoolExecutor thread pool that comes from standard Java and is available since Java 5.
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //https://stackoverflow.com/questions/17659510/core-pool-size-vs-maximum-pool-size-in-threadpoolexecutor
//        executor.setCorePoolSize(2);//when task created, 2 threads will be created and task will be added to queue
//        executor.setMaxPoolSize(2); //when queue is full, new thread will be created up to Max pool size.
//        executor.setQueueCapacity(200);
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(200);

        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Demo-Async-");
        return executor;
    }
}
