/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author patrickboucicault
 */
public class Recitations<E extends Comparable<E>> implements Comparable<E>{

    
    private final StringProperty section;
    private final StringProperty instructor;
    private final StringProperty dayTime;
    private final StringProperty location;
    
    private StringProperty TA1 = new SimpleStringProperty("");
    private StringProperty TA2 = new SimpleStringProperty("");


    /**
     * Constructor initializes both the TA name and email.
     */
    public Recitations(String initSection, String initInstructor,String initDay, String initLocation,
            String initTA1, String initTA2) {
        
        section = new SimpleStringProperty(initSection);
        instructor = new SimpleStringProperty(initInstructor);
        dayTime = new SimpleStringProperty(initDay);
        location = new SimpleStringProperty(initLocation);
        TA1 = new SimpleStringProperty(initTA1);
        TA2 = new SimpleStringProperty(initTA2);
   
    }
    
  
    public Recitations(String initSection, String initInstructor,String initDay, String initLocation) {
        
        section = new SimpleStringProperty(initSection);
        instructor = new SimpleStringProperty(initInstructor);
        dayTime = new SimpleStringProperty(initDay);
        location = new SimpleStringProperty(initLocation);
 
    }
    
    
    public String getSection(){
        return section.get();
    }
    public String getInstructor(){
        return instructor.get();
    }
    public String getDay(){
        return dayTime.get();
    }
    public String getLocation(){
        return location.get();
    }
    public String getTA1(){
        return TA1.get();
    }
    public String getTA2(){
        return TA2.get();
    }
    @Override
    public String toString(){
        
        return getSection(); 
                
    }

    @Override
    public int compareTo(E otherRecitation) {
        return getSection().compareTo(((Recitations)otherRecitation).getSection());
    
    }   
}
