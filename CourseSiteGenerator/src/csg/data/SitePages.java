/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author patrickboucicault
 */
public class SitePages {
    
    
    private final StringProperty navbar;
    private final StringProperty fileName;
    private final StringProperty script;
    private BooleanProperty isUsed;
   

    /**
     * Constructor initializes both the TA name and email.
     */
    public SitePages(String initNavbar, String initFileName,String initScript, boolean initUse) {
        
        navbar = new SimpleStringProperty(initNavbar);
        fileName = new SimpleStringProperty(initFileName);
        script = new SimpleStringProperty(initScript); 
        isUsed = new SimpleBooleanProperty(false);
        
    }
    
    
    public String getNavbar(){
        return navbar.getValue();
    }
    public String getFileName(){
        return fileName.getValue();
    }
    public String getScript(){
        return script.getValue();
    }

    public BooleanProperty getIsUsed(){
        return isUsed;
    }
    
    
    
}
