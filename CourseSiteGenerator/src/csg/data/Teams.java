/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ColorPicker;

/**
 *
 * @author patrickboucicault
 */
public class Teams <E extends Comparable<E>> implements Comparable<E>  {
    
    private  StringProperty name;
    private  StringProperty color;
    private  StringProperty textColor;
    private  StringProperty link;
    private double red = 0;
    private double green = 0;
    private double blue = 0;
   


    /**
     * Constructor initializes both the TA name and email.
     */
    public Teams(String initName, String initColor,String initTextColor, String initLink) {
        
        name = new SimpleStringProperty(initName);
        color = new SimpleStringProperty(initColor);
        textColor = new SimpleStringProperty(initTextColor);
        link = new SimpleStringProperty(initLink);
  
    }
    
    
    
    public String getName(){
        return name.get();
    }
 
    public void setName(String nam){
        name.setValue(nam);
    }
    
      public void setColor(String col){
        color.setValue(col);
    }
      
      public void setTextColor(String col){
        textColor.setValue(col);
    }
        
      public void setLink(String lin){
        link.setValue(lin);
    }
    
    public String getColor(){
        return color.get();
    }
    public String getTextColor(){
        return textColor.get();
    }
    public String getLink(){
        return link.get();
    }

    @Override
    public int compareTo(E otherTeam) {
         return getName().compareTo(((Teams)otherTeam).getName());
    }
    
    public void setBlue(double b){
        blue = b;
    }
    public void setGreen(double b){
        green = b;
    }
    public void setRed(double b){
        red = b;
    }
    
    
    public double getBlue(){
        return blue;
    }
    public double getGreen(){
        return green;
    }
    public double getRed(){
       return red;
    }
    
}
