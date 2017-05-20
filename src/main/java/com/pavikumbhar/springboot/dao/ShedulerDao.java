/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.dao;


import com.pavikumbhar.springboot.entity.JobScheduler;
import java.util.List;

/**
 *
 * @author pravinkumbhar
 */
public interface ShedulerDao {

    String getCronExpressionFromDB(String triggerName);

    void saveJobScheduler(JobScheduler jobScheduler);

    void updateJobScheduler(JobScheduler jobScheduler);

    List<JobScheduler> jobSchedulerList();

    List<JobScheduler> jobSchedulerList(int startIndex, int pageSize);

    int getJobSchedulerCount();
    
    
    JobScheduler jobSchedulerbyId(long jobId);
    
    JobScheduler updateCornExpByJobId(long jobId, String cornExp); 

}
