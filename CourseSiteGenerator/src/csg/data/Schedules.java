/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author patrickboucicault
 */
public class Schedules <E extends Comparable<E>> implements Comparable<E>{
    
    private final StringProperty type;
    private final StringProperty date;
    private final StringProperty title;
    private final StringProperty topic;
    private final StringProperty link;
    private final StringProperty criteria;
    private final StringProperty time;
    
   


    /**
     * Constructor initializes both the TA name and email.
     */
    public Schedules(String initType, String initDate,String initTitle, String initTopic) {
        
        type = new SimpleStringProperty(initType);
        date = new SimpleStringProperty(initDate);
        title = new SimpleStringProperty(initTitle);
        topic = new SimpleStringProperty(initTopic);
        link = new SimpleStringProperty();
        criteria = new SimpleStringProperty();
        time = new SimpleStringProperty();
    }
    
    
    
    public String getType(){
        return type.get();
    }
    public String getDate(){
        return date.get();
    }
    public String getTitle(){
        return title.get();
    }
    public String getTopic(){
        return topic.get();
    }
     public String getLink(){
        return link.get();
    }
    public String getCriteria(){
        return criteria.get();
    }
    public String getTime(){
        return time.get();
    }
    
    
    
    public void setLink(String t){
         link.set(t);
    }
    public void setCriteria(String t){
         criteria.set(t);
    }
    public void setTime(String t){
         time.set(t);
    }

   

    @Override
    public int compareTo(E otherSchedule) {
       return getDate().compareTo(((Schedules)otherSchedule).getDate());
    }
}
