package com.pavikumbhar.springboot.web;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */







import com.pavikumbhar.springboot.dto.CornTriggerDto;
import com.pavikumbhar.springboot.entity.JobScheduler;
import com.pavikumbhar.springboot.service.QuartzService;
import com.pavikumbhar.springboot.service.ShedulerService;

import java.util.List;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pavikumbhar
 */


@Controller
public class IndexController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
   
    @Autowired
    private  QuartzService quartzService;
    
     @Autowired
    private ShedulerService shedulerService;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index(Model model) {

        return "index";
    }

    @RequestMapping(value = {"/in"}, method = RequestMethod.GET)
    @ResponseBody
    public List<CornTriggerDto> Quartz (Model model) {

        return quartzService.getTriggersInfo();
    }

      @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        
        List<CornTriggerDto> triggers = quartzService.getTriggersInfo();
        model.addAttribute("triggers", triggers);
        
        List<JobScheduler> jobSchedulerList = shedulerService.jobSchedulerList();
        
        model.addAttribute("jobSchedulerList", jobSchedulerList);

      

        return "home";
    }

    @RequestMapping(value = {"/quartz"}, method = RequestMethod.GET)
    public String quartz(Model model) {

        List<CornTriggerDto> triggers = quartzService.getTriggersInfo();
        model.addAttribute("triggers", triggers);

        System.out.println("com.icici.pgc.controller.IndexController.quartz()" + triggers);

        return "quartz";
    }

}
