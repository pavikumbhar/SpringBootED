/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springboot;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;


/**
 *
 * @author pavikumbhar
 */
//@Configuration
@PropertySource({"classpath:persistence.properties"})
public class TomcatEmbeddedServletContainerConfig {

    @Autowired
    private Environment environment;

    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatFactory() {
        return new TomcatEmbeddedServletContainerFactory() {

            @Override
            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
                    Tomcat tomcat) {
                tomcat.enableNaming();
                return super.getTomcatEmbeddedServletContainer(tomcat);
            }

            @Override
            protected void postProcessContext(Context context) {
                ContextResource resource = new ContextResource();
                resource.setName("jdbc/myDataSource");
                resource.setType(DataSource.class.getName());
                resource.setProperty("driverClassName", environment.getRequiredProperty("database.driverClass"));
                resource.setProperty("url", environment.getRequiredProperty("database.url"));
                resource.setProperty("username", environment.getRequiredProperty("database.username"));
                resource.setProperty("password", environment.getRequiredProperty("database.password"));

                context.getNamingResources().addResource(resource);
            }
        };
    }
    
    
       @Bean(destroyMethod = "")
    public DataSource dataSource() {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:comp/env/jdbc/myDataSource");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        try {
            bean.afterPropertiesSet();
        } catch (IllegalArgumentException ex) {
           
        } catch (NamingException ex) {

        }
        return (DataSource) bean.getObject();
    }

  

}
