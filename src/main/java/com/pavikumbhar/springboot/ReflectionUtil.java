/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 *
 * @author pravinkumbhar
 */
public class ReflectionUtil {
    
 
    private final Logger logger = LoggerFactory.getLogger(getClass());
      /**
     *  pavi kumbhar
     * @param <T>
     * @param objectFromDB
     * @param objectToUpdate
     * @return 
     */
    public  <T> T updateChanges(T objectFromDB, T objectToUpdate) {
        logger.debug(" updateChanges  in Start");
        System.err.println("In  updateChanges ");
        try {
            java.lang.reflect.Field[] fields = objectFromDB.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                {
                //System.err.println("field.getModifiers() :  "+field.getModifiers());

                    if ((field.getModifiers() & java.lang.reflect.Modifier.FINAL) != java.lang.reflect.Modifier.FINAL) // serialVersionUID issue
                    {
                        if (field.get(objectToUpdate) != null) {
                            field.set(objectFromDB, field.get(objectToUpdate));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.debug(" updateChanges  catch bolock " + e.getMessage());
        }
        System.err.println("In  updateChange End ");
        logger.debug(" updateChanges  in End");
        return objectFromDB;
    }
    
    public  boolean isPublicStaticFinal(Field field) {
        int modifiers = field.getModifiers();
        return (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers));
    }
    
}
