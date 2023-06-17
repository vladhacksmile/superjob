package com.vladhacksmile.searchjob.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

//@Configuration
//@EnableAutoConfiguration
public class QuartzConfig {

    Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        logger.info("Hello world from Spring...");
    }

//    @Bean
//    public SpringBeanJobFactory springBeanJobFactory() {
//        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
//        logger.debug("Configuring Job factory");
//
//        jobFactory.setApplicationContext(applicationContext);
//        return jobFactory;
//    }
//
//    @Bean
//    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job, SpringBeanJobFactory springBeanJobFactory) {
//        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
//
//        logger.debug("Setting the Scheduler up");
//        schedulerFactory.setJobFactory(springBeanJobFactory());
//        schedulerFactory.setJobDetails(job);
//        schedulerFactory.setTriggers(trigger);
//
//        return schedulerFactory;
//    }
//
//    @Bean
//    public JobDetailFactoryBean jobDetail() {
//        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
//        jobDetailFactory.setJobClass(AsyncScheduling.class);
//        jobDetailFactory.setName("Qrtz_Job_Detail");
//        jobDetailFactory.setDescription("Invoke Sample Job service...");
//        jobDetailFactory.setDurability(true);
//        return jobDetailFactory;
//    }
//
//    @Bean
//    public CronTriggerFactoryBean trigger(JobDetail job) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
//        trigger.setJobDetail(job);
//
//        String cronExpression = "0 0 10 * * ?";
//        trigger.setCronExpression(cronExpression);
//
//        return trigger;
//    }
}