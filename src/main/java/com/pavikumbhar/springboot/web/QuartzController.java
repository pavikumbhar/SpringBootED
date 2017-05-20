/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.web;

import com.pavikumbhar.springboot.dto.CornTriggerDto;
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
 * @author pravinkumbhar
 */
@Controller
public class QuartzController {
    
   private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
   
  
    @Autowired
    private QuartzService quartzService;

    @Autowired
    private ShedulerService shedulerService;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/quartzList"}, method = RequestMethod.GET)
    public @ResponseBody
    List<CornTriggerDto> quartzList(Model model) {

        List<CornTriggerDto> triggers = quartzService.getTriggersInfo();
        model.addAttribute("triggers", triggers);

        System.out.println("com.icici.pgc.controller.IndexController.quartz()" + triggers);

        return triggers;
    }

   

}
