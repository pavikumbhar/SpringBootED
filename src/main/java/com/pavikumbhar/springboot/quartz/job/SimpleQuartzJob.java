/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.quartz.job;

import javax.annotation.PostConstruct;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
@DisallowConcurrentExecution
public class SimpleQuartzJob implements Job {
    
    
     @PostConstruct
    public void init() {
         System.out.println("########################%% initialized.");
    }

    private String firstName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
         System.err.println("###Job invoked at : " + new java.util.Date());
        System.out.println("My job is running with " + firstName + ' '
                + age);

    }

}
