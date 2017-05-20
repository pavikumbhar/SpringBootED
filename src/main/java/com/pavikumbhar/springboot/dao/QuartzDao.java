/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.dao;




import com.pavikumbhar.springboot.model.QrtzTriggers;
import java.util.List;

/**
 *
 * @author pravinkumbhar
 */
public interface QuartzDao {
    
    List<QrtzTriggers> getTriggers();
    
}
