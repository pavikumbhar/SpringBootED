/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.quartz.config;

import com.pavikumbhar.springboot.quartz.job.MarchantPaymentFileGenerationJob;
import com.pavikumbhar.springboot.quartz.job.ECollectionFileReadingJob;
import com.pavikumbhar.springboot.quartz.job.PaymentFileReadingJob;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.quartz.CronTrigger;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 *
 * @author pravinkumbhar
 */
@Configuration
public class QuartzConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private DataSource dataSource;
    
    
   

//    @Autowired
//    private PlatformTransactionManager transactionManager;
//   
//    @Autowired
//    private JpaTransactionManager transactionManager;
    @Autowired
    private HibernateTransactionManager transactionManager;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        log.debug("QuartzConfig initialized.");
    }

    
    /**
    @Bean
    public JobDetailFactoryBean processSimpleJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

       
        jobDetailFactory.setJobClass(SimpleQuartzJob.class);
        jobDetailFactory.setGroup("spring4-quartz");

        jobDetailFactory.setDurability(true);
        jobDetailFactory.setDescription("This job is used for ");

        Map<String, Object> jobDataAsMap = new HashMap<String, Object>();
        jobDataAsMap.put("firstName", "pravin kumbhar");
        jobDataAsMap.put("age", "1");
        jobDetailFactory.setJobDataAsMap(jobDataAsMap);

        return jobDetailFactory;
    }

    @Bean
    public InitializingCronTrigger processSimpleTrigger() {
        // CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        InitializingCronTrigger cronTriggerFactoryBean = new InitializingCronTrigger();
        //PersistableCronTriggerFactoryBean cronTriggerFactoryBean = new PersistableCronTriggerFactoryBean();

        cronTriggerFactoryBean.setJobDetail(processSimpleJob().getObject());
        cronTriggerFactoryBean.setCronExpression("0 * * * * ?");
        cronTriggerFactoryBean.setGroup("spring4-quartz");

        cronTriggerFactoryBean.setStartDelay(0);

        return cronTriggerFactoryBean;
    }
***/
    
    
    // ############################## 1-E-Collection Start##################################### *
    
    @Bean
    public JobDetailFactoryBean eCollectionFileReadingJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

        /**
         * Reference to the job bean that will be triggered
         */
        jobDetailFactory.setJobClass(ECollectionFileReadingJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setDescription("This job is used for read the e-Collection file and insert record in Datatabase");

        return jobDetailFactory;
    }

    @Bean
    public InitializingCronTrigger eCollectionFileReadingTrigger() {
        // CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        InitializingCronTrigger cronTriggerFactoryBean = new InitializingCronTrigger();
        cronTriggerFactoryBean.setJobDetail(eCollectionFileReadingJob().getObject());
       // cronTriggerFactoryBean.setCronExpression("0 0/15 * * * ?");

        cronTriggerFactoryBean.setStartDelay(0);
        cronTriggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

        return cronTriggerFactoryBean;
    }

    // ############################## 1-E-Collection  End ##################################### *
    
    
    
    // ############################## 2- Payment File Start##################################### *
    @Bean
    public JobDetailFactoryBean paymentFileReadingJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

        /**
         * Reference to the job bean that will be triggered
         */
        jobDetailFactory.setJobClass(PaymentFileReadingJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setDescription("This job is used for read the Payment file and insert record in Datatabase");

        return jobDetailFactory;
    }

    @Bean
    public InitializingCronTrigger paymentFileReadingTrigger() {
        // CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        InitializingCronTrigger cronTriggerFactoryBean = new InitializingCronTrigger();
        cronTriggerFactoryBean.setJobDetail(paymentFileReadingJob().getObject());
      //  cronTriggerFactoryBean.setCronExpression("0 0/30 * * * ?");

        cronTriggerFactoryBean.setStartDelay(0);
        cronTriggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

        return cronTriggerFactoryBean;
    }

    // ############################## 2- Payment File  End ##################################### *
    
    
    
    
    // ############################## 3-Marchant Payment File Generation  Start##################################### *
    @Bean("marchantPaymentFileGeneration")
    public JobDetailFactoryBean marchantPaymentFileGenerationJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

        /**
         * Reference to the job bean that will be triggered
         */
        jobDetailFactory.setJobClass(MarchantPaymentFileGenerationJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setDescription("This job is used for Marchant Payment File Generation");

        return jobDetailFactory;
    }

    @Bean
    public InitializingCronTrigger marchantPaymentFileGenerationTrigger() {
        // CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        InitializingCronTrigger cronTriggerFactoryBean = new InitializingCronTrigger();
        cronTriggerFactoryBean.setJobDetail(marchantPaymentFileGenerationJob().getObject());
       // cronTriggerFactoryBean.setCronExpression("0 0/30 * * * ?");

        cronTriggerFactoryBean.setStartDelay(0);
        cronTriggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

        return cronTriggerFactoryBean;
    }

    // ############################## 3-Marchant Payment File End ##################################### *
    
    
    
    
    // ############################## 4-IPS Payment File Response Reading  Start##################################### *
    @Bean
    public JobDetailFactoryBean ipsPaymentFileResponseReadingJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

        /**
         * Reference to the job bean that will be triggered
         */
        jobDetailFactory.setJobClass(MarchantPaymentFileGenerationJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setDescription("This job is used for  IPS Payment File Response Reading ");

        return jobDetailFactory;
    }

    @Bean
    public InitializingCronTrigger ipsPaymentFileResponseReadingTrigger() {
        // CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        InitializingCronTrigger cronTriggerFactoryBean = new InitializingCronTrigger();
        cronTriggerFactoryBean.setJobDetail(ipsPaymentFileResponseReadingJob().getObject());
        //cronTriggerFactoryBean.setCronExpression("0 0/30 * * * ?");

        cronTriggerFactoryBean.setStartDelay(0);
        cronTriggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

        return cronTriggerFactoryBean;
    }

    // ############################## 4-IPS Payment File Response Reading  End ##################################### *
    
    
    
    
    // ##############################  5-MIS File Generation  Start##################################### *
    @Bean
    public JobDetailFactoryBean misFileGenerationJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

        /**
         * Reference to the job bean that will be triggered
         */
        jobDetailFactory.setJobClass(MarchantPaymentFileGenerationJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setDescription("This job is used for  IPS Payment File Response Reading ");

        return jobDetailFactory;
    }

    @Bean
    public InitializingCronTrigger misFileGenerationTrigger() {
        // CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        InitializingCronTrigger cronTriggerFactoryBean = new InitializingCronTrigger();
        cronTriggerFactoryBean.setJobDetail(misFileGenerationJob().getObject());
       // cronTriggerFactoryBean.setCronExpression("0 0/30 * * * ?");

        cronTriggerFactoryBean.setStartDelay(0);
        cronTriggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

        return cronTriggerFactoryBean;
    }

    // ##############################  5-MIS File Generation End ##################################### *
    @Bean
    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        Properties properties = null;
        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();

        } catch (IOException e) {
            log.warn("Cannot load quartz.properties.");
        }

        return properties;
    }

    @Bean
    public SchedulerFactoryBean quartzScheduler() {
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

        /**
         * Naturally, Quartz with the DB requires references to the data source
         * and transaction manager beans
         */
        quartzScheduler.setDataSource(dataSource);
        quartzScheduler.setTransactionManager(transactionManager);

        /**
         * This name is persisted as SCHED_NAME in db. for local testing could
         * change to unique name to avoid collision with dev server
         */
        quartzScheduler.setSchedulerName("pgcQuartzScheduler");

        /**
         * Will update database cron triggers to what is in this jobs file on
         * each deploy. Replaces all previous trigger and job data that was in
         * the database. YMMV
         */
        quartzScheduler.setOverwriteExistingJobs(true);

        // custom job factory of spring with DI support for @Autowired!
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        jobFactory.setIgnoredUnknownProperties("applicationContext");
        quartzScheduler.setJobFactory(jobFactory);

        quartzScheduler.setAutoStartup(true);
        quartzScheduler.setOverwriteExistingJobs(true);
        quartzScheduler.setWaitForJobsToCompleteOnShutdown(false);
        
        quartzScheduler.setApplicationContextSchedulerContextKey("applicationContext");

        quartzScheduler.setQuartzProperties(quartzProperties());

        //Trigger[] triggers = {processSimpleTrigger().getObject()};
        Trigger[] triggers = {
                                eCollectionFileReadingTrigger().getObject(),
                                paymentFileReadingTrigger().getObject(),
                                marchantPaymentFileGenerationTrigger().getObject(),
                                ipsPaymentFileResponseReadingTrigger().getObject(),
                                misFileGenerationTrigger().getObject()
                             };

        quartzScheduler.setTriggers(triggers);

        return quartzScheduler;
    }

}
