/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.service;




import com.pavikumbhar.springboot.dto.CornTriggerDto;
import com.pavikumbhar.springboot.model.QrtzTriggers;
import java.util.List;

/**
 *
 * @author pravinkumbhar
 */
public interface QuartzService {
    
      List<QrtzTriggers> getTriggers();
      List<CornTriggerDto> getTriggersInfo();
       
    
    
}
