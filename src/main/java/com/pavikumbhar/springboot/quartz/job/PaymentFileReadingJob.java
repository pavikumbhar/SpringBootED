/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.quartz.job;


import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author pravinkumbhar
 */
@Component("paymentFileReadingJobBean")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
@DisallowConcurrentExecution
public class PaymentFileReadingJob extends QuartzJobBean {
  
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("####PaymentFileReadingJob");
        
        System.err.println("###Job invoked at : " + new java.util.Date());
        try {
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        
    }
    
}
