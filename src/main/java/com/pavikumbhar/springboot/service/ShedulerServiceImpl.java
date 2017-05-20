/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.service;



import com.pavikumbhar.springboot.dao.ShedulerDao;
import com.pavikumbhar.springboot.entity.JobScheduler;
import java.text.ParseException;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pravinkumbhar
 *
 */
@Service
@Transactional
public class ShedulerServiceImpl implements ShedulerService {

     private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ShedulerDao shedulerDao;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public String getCronExpressionFromDB(String triggerName) {

        return shedulerDao.getCronExpressionFromDB(triggerName);

    }

    @Override
    public boolean reScheduleJob(String triggerName) {

        logger.debug("Start -inside reScheduleJob  triggerName=" + triggerName);
        boolean flag = false;
        Scheduler schedular = schedulerFactoryBean.getScheduler();
        TriggerKey triggerkey = new TriggerKey(triggerName);
        try {
            CronTriggerImpl cronTriggerImpl = (CronTriggerImpl) schedular.getTrigger(triggerkey);
            String originConExpression = cronTriggerImpl.getCronExpression();

            logger.debug("originConExpression : " + originConExpression);

            System.out.println("before ---->");
            String dbCronExpression = shedulerDao.getCronExpressionFromDB(triggerName);

            logger.debug("dbcronexpresssion is" + ":" + dbCronExpression);

            if (!originConExpression.equalsIgnoreCase(dbCronExpression)) {

                logger.debug(" expresssions are different");

                TriggerKey triggerKeyNew = new TriggerKey(triggerName);

                logger.debug("dbcronexpresssion is" + ":" + dbCronExpression);

                CronTriggerImpl cronTriggerImpl1New = (CronTriggerImpl) schedular.getTrigger(triggerKeyNew);

                cronTriggerImpl1New.setCronExpression(dbCronExpression);

                schedular.rescheduleJob(triggerKeyNew, cronTriggerImpl1New);
                flag = true;
            }

        } catch (SchedulerException e) {
            logger.debug("--inside reScheduleJob Exception---" + e.getMessage());
        } catch (ParseException e) {
            logger.debug("--inside reScheduleJob Exception---" + e.getMessage());
        } catch (Exception e) {
            logger.debug("--inside reScheduleJob Exception---" + e.getMessage());
        }

        return flag;

    }

    @Override
    public List<JobScheduler> jobSchedulerList() {
        return shedulerDao.jobSchedulerList();
    }

    @Override
    public void saveJobScheduler(JobScheduler jobScheduler) {

        shedulerDao.saveJobScheduler(jobScheduler);
    }

    @Override
    public void updateJobScheduler(JobScheduler jobScheduler) {
        shedulerDao.updateJobScheduler(jobScheduler);
    }

    @Override
    public List<JobScheduler> jobSchedulerList(int startIndex, int pageSize) {
        return shedulerDao.jobSchedulerList(startIndex, pageSize);
    }

    @Override
    public int getJobSchedulerCount() {

        return shedulerDao.getJobSchedulerCount();

    }

    @Override
    public JobScheduler jobSchedulerbyId(long jobId) {

        System.out.println("inside schedular service impl..." + jobId);
        return shedulerDao.jobSchedulerbyId(jobId);
    }

    @Override
    public JobScheduler updateCornExpByJobId(long jobId, String cornExp) {

        System.out.println("inside schedular service corn exp edit..." + jobId + cornExp);
        return shedulerDao.updateCornExpByJobId(jobId, cornExp);

    }

}
