/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pravinkumbhar
 */
@Controller
public class HolidayController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping(value = {"/holiday"}, method = RequestMethod.GET)
    public String holiday(Model model) {

        return "holiday";
    }

}
