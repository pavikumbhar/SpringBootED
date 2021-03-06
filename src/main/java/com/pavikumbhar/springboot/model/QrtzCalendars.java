package com.pavikumbhar.springboot.model;
// Generated 21 Oct, 2016 3:00:38 PM by Hibernate Tools 4.3.1


import java.sql.Blob;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * QrtzCalendars generated by hbm2java
 */
@Entity
@Table(name="QRTZ_CALENDARS")
public class QrtzCalendars  implements java.io.Serializable {


     private QrtzCalendarsId id;
     private Blob calendar;

    public QrtzCalendars() {
    }

    public QrtzCalendars(QrtzCalendarsId id, Blob calendar) {
       this.id = id;
       this.calendar = calendar;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="schedName", column=@Column(name="SCHED_NAME", nullable=false, length=120) ), 
        @AttributeOverride(name="calendarName", column=@Column(name="CALENDAR_NAME", nullable=false, length=200) ) } )
    public QrtzCalendarsId getId() {
        return this.id;
    }
    
    public void setId(QrtzCalendarsId id) {
        this.id = id;
    }

    
    @Column(name="CALENDAR", nullable=false)
    public Blob getCalendar() {
        return this.calendar;
    }
    
    public void setCalendar(Blob calendar) {
        this.calendar = calendar;
    }




}


