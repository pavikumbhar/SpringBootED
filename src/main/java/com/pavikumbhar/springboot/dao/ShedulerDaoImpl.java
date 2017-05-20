/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.dao;


import com.pavikumbhar.springboot.ReflectionUtil;
import com.pavikumbhar.springboot.entity.JobScheduler;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pravinkumbhar
 */
@Repository
public class ShedulerDaoImpl implements ShedulerDao {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param triggerName
     * @return
     */
    @Override
    public String getCronExpressionFromDB(String triggerName) {

        logger.debug("Inside getCronExpressionFromDB Start triggerName = " + triggerName);
        String cronExpression = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("Select cronExpression from JobScheduler where triggerName=:triggerName");
            query.setString("triggerName", triggerName);
            cronExpression = (String) query.uniqueResult();

        } catch (Exception e) {
            logger.debug("--inside getCronExpressionFromDB Exception---" + e.getMessage());
            e.printStackTrace(System.out);
        }

        logger.debug("Inside getCronExpressionFromDB End");

        return cronExpression;

    }

    @Override
    public void saveJobScheduler(JobScheduler jobScheduler) {

        try {
            logger.debug("Inside saveJobScheduler start");

            Long jobId = (Long) sessionFactory.getCurrentSession().save(jobScheduler);
            logger.debug("End saveJobScheduler ");
        } catch (Exception e) {
            logger.debug("Exception saveJobScheduler " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void updateJobScheduler(JobScheduler jobScheduler) {

        try {
            logger.debug("Inside updateJobScheduler start");

            JobScheduler jobSchedulerFromDB = (JobScheduler) sessionFactory.getCurrentSession().get(JobScheduler.class, jobScheduler.getJobId());

            ReflectionUtil reflectionUtil = new ReflectionUtil();
            //null checkm and set 
            JobScheduler updaterJobScheduler = reflectionUtil.updateChanges(jobSchedulerFromDB, jobScheduler);

            sessionFactory.getCurrentSession().update(updaterJobScheduler);
            logger.debug("End updateJobScheduler ");
        } catch (Exception e) {
            logger.debug("Exception updateJobScheduler " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    @Override
    public List<JobScheduler> jobSchedulerList() {

        List<JobScheduler> jobSchedulers = new ArrayList<JobScheduler>();
        try {
            logger.debug("Inside jobSchedulerList start");

            jobSchedulers = sessionFactory.getCurrentSession()
                    .createQuery("FROM JobScheduler ")
                    .list();
            logger.debug("End jobSchedulerList ");
        } catch (Exception e) {
            logger.debug("Exception jobSchedulerList " + e.getMessage());
            e.printStackTrace(System.out);
        }
        return jobSchedulers;
    }

    @Override
    public List<JobScheduler> jobSchedulerList(int startIndex, int pageSize) {
        return sessionFactory.getCurrentSession().createQuery("from JobScheduler ")
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .list();
    }

    @Override
    public int getJobSchedulerCount() {
        int count = 0;
        try {
            count = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from JobScheduler").uniqueResult()).intValue();
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
        return count;
    }

    @Override
    public JobScheduler jobSchedulerbyId(long jobId) {

        //System.out.println("inside job dao query..............."+jobId);
        JobScheduler jobscheduler = new JobScheduler();
        try {
            logger.debug("Inside jobScheduler start");

            jobscheduler = (JobScheduler) sessionFactory.getCurrentSession().get(JobScheduler.class, jobId);

            logger.debug("End jobSchedulerList ");
        } catch (Exception e) {
            logger.debug("Exception jobSchedulerList " + e.getMessage());
            e.printStackTrace(System.out);
        }

        System.out.println("outside job dao query..............." + jobscheduler);

        return jobscheduler;
    }

    @Override
    public JobScheduler updateCornExpByJobId(long jobId, String cornExp) {

        JobScheduler jobscheduler = new JobScheduler();
        try {
            logger.debug("Inside jobScheduler start");

            jobscheduler = (JobScheduler) sessionFactory.getCurrentSession().get(JobScheduler.class, jobId);

            jobscheduler.setCronExpression(cornExp);

            logger.debug("End jobSchedulerList ");
        } catch (Exception e) {
            logger.debug("Exception jobSchedulerList " + e.getMessage());
            e.printStackTrace(System.out);
        }

        System.out.println("outside job dao query..............." + jobscheduler);

        return jobscheduler;
    }

}
