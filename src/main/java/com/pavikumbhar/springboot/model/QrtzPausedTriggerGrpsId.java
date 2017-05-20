package com.pavikumbhar.springboot.model;
// Generated 21 Oct, 2016 3:00:38 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * QrtzPausedTriggerGrpsId generated by hbm2java
 */
@Embeddable
public class QrtzPausedTriggerGrpsId  implements java.io.Serializable {


     private String schedName;
     private String triggerGroup;

    public QrtzPausedTriggerGrpsId() {
    }

    public QrtzPausedTriggerGrpsId(String schedName, String triggerGroup) {
       this.schedName = schedName;
       this.triggerGroup = triggerGroup;
    }
   


    @Column(name="SCHED_NAME", nullable=false, length=120)
    public String getSchedName() {
        return this.schedName;
    }
    
    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }


    @Column(name="TRIGGER_GROUP", nullable=false, length=200)
    public String getTriggerGroup() {
        return this.triggerGroup;
    }
    
    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof QrtzPausedTriggerGrpsId) ) return false;
		 QrtzPausedTriggerGrpsId castOther = ( QrtzPausedTriggerGrpsId ) other; 
         
		 return ( (this.getSchedName()==castOther.getSchedName()) || ( this.getSchedName()!=null && castOther.getSchedName()!=null && this.getSchedName().equals(castOther.getSchedName()) ) )
 && ( (this.getTriggerGroup()==castOther.getTriggerGroup()) || ( this.getTriggerGroup()!=null && castOther.getTriggerGroup()!=null && this.getTriggerGroup().equals(castOther.getTriggerGroup()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSchedName() == null ? 0 : this.getSchedName().hashCode() );
         result = 37 * result + ( getTriggerGroup() == null ? 0 : this.getTriggerGroup().hashCode() );
         return result;
   }   


}


