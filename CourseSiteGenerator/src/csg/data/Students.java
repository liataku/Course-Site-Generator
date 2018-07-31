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
public class Students<E extends Comparable<E>> implements Comparable<E>  {
    
    
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty team;
    private final StringProperty role;
   


    /**
     * Constructor initializes both the TA name and email.
     */
    public Students(String initFirst, String initLast,String initRole,String initTeam) {
        
        firstName = new SimpleStringProperty(initFirst);
        lastName = new SimpleStringProperty(initLast);
        team = new SimpleStringProperty(initTeam);
        role = new SimpleStringProperty(initRole);
  
    }
    
    
    
    public String getFirstName(){
        return firstName.get();
    }
    public String getLastName(){
        return lastName.get();
    }
    public String getTeam(){
        return team.get();
    }
    public String getRole(){
        return role.get();
    }

    @Override
    public int compareTo(E otherStudent) {
        return getFirstName().compareTo(((Students)otherStudent).getFirstName());
    }
}
