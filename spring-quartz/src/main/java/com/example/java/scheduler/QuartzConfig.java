//package com.example.java.scheduler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.SpringBeanJobFactory;
//
//@Slf4j
//@Configuration
//public class QuartzConfig {
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//
//    @Bean(name="testFooJob")
//    public JobDetail fooJob() {
//        return JobBuilder
//                .newJob(FooJob.class)
//                .withIdentity(FooJob.identity)
//                .storeDurably()
//                .build();
//    }
//
//    @Bean
//    public Trigger testQuartzTrigger1(@Qualifier("testFooJob") JobDetail job) {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(5)
//                .repeatForever();
////        return TriggerBuilder.newTrigger().forJob(fooJob())
//        return TriggerBuilder.newTrigger().forJob(job)
//                .withIdentity(FooJob.identity)
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//
//
//    @Bean
//    public SpringBeanJobFactory springBeanJobFactory() {
//        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
//        jobFactory.setApplicationContext(applicationContext);
//        return jobFactory;
//    }
//
//}
