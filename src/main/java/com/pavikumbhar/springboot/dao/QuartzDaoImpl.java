/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot.dao;





import com.pavikumbhar.springboot.model.QrtzTriggers;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * @author pravinkumbhar
 */
@Repository
public class QuartzDaoImpl implements QuartzDao {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<QrtzTriggers> getTriggers() {
        List<QrtzTriggers> qrtzTriggerses = null;
        logger.debug("Inside getTriggers Start");

        try {
         
            Query query = sessionFactory.getCurrentSession().createQuery("from QrtzTriggers");
            qrtzTriggerses =  query.list();
            
            
               for (QrtzTriggers qrtzTriggers : qrtzTriggerses) {
                Hibernate.initialize(qrtzTriggers.getQrtzJobDetails());

               
            }

        } catch (Exception e) {
            //logger.debug("--inside getTriggers Exception---" + e.getMessage);
             e.printStackTrace();
                   
        }

        logger.debug("Inside getTriggers End");

        return qrtzTriggerses;

    }

}
