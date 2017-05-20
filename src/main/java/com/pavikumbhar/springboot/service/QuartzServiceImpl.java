/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.service;



import com.pavikumbhar.springboot.util.TimestampConvertor;
import com.pavikumbhar.springboot.dao.QuartzDao;
import com.pavikumbhar.springboot.dto.CornTriggerDto;
import com.pavikumbhar.springboot.model.QrtzTriggers;
import java.util.ArrayList;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pravinkumbhar
 */
@Service
public class QuartzServiceImpl implements QuartzService {

     private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuartzDao quartzDao;

    @Autowired
    private TimestampConvertor timestampConvertor;
    
//    @Autowired
//    private ConExpressionUtil conExpressionUtil;


    @Transactional(readOnly = true)
    @Override
    public List<QrtzTriggers> getTriggers() {
        return quartzDao.getTriggers();

    }

    @Transactional(readOnly = true)
    @Override
    public List<CornTriggerDto> getTriggersInfo() {

        List<CornTriggerDto> cornTriggerDtos = new ArrayList<CornTriggerDto>();
        List<QrtzTriggers> triggers = quartzDao.getTriggers();
        for (QrtzTriggers trigger : triggers) {
            CornTriggerDto cornTriggerDto = new CornTriggerDto();
            cornTriggerDto.setSchedName(trigger.getId().getTriggerGroup());
            cornTriggerDto.setTriggerName(trigger.getId().getTriggerName());
            cornTriggerDto.setTriggerGroup(trigger.getId().getTriggerGroup());
            cornTriggerDto.setCronExpression(trigger.getQrtzCronTriggers().getCronExpression());
            cornTriggerDto.setTimeZoneId(trigger.getQrtzCronTriggers().getTimeZoneId());
            cornTriggerDto.setPrevFireTime(timestampConvertor.convertLongTimeSatmpToDate(trigger.getPrevFireTime()));
            cornTriggerDto.setNextFireTime(timestampConvertor.convertLongTimeSatmpToDate(trigger.getNextFireTime()));
            cornTriggerDto.setStartTime(timestampConvertor.convertLongTimeSatmpToDate(trigger.getStartTime()));
            cornTriggerDto.setEndTime(timestampConvertor.convertLongTimeSatmpToDate(trigger.getEndTime()));
            cornTriggerDto.setTriggerState(trigger.getTriggerState());
            String description="";
//             boolean validExpression = conExpressionUtil.isValidExpression(trigger.getQrtzCronTriggers().getCronExpression());
//        if (validExpression) {
//            description = conExpressionUtil.getconExpressionDescriptor(trigger.getQrtzCronTriggers().getCronExpression());
//        }
        cornTriggerDto.setDescription(description);
            cornTriggerDtos.add(cornTriggerDto);

        }

        return cornTriggerDtos;
    }

}
