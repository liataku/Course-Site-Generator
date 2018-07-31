/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import coursesitegenerator.CourseSiteGeneratorAPP;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author patrickboucicault
 */
public class CourseData extends AppWorkspaceComponent implements AppDataComponent{
    
    
    
    
    
     // WE'LL NEED ACCESS TO THE APP TO NOTIFY THE GUI WHEN DATA CHANGES
    CourseSiteGeneratorAPP app;

    // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
    // DATA IN THE ROWS OF THE TABLE VIEW
    ObservableList<SitePages> siteTableList;
    
    HashMap<String, StringProperty> officeHours;
    
    public CourseData(CourseSiteGeneratorAPP init){
        
        app = init;
        siteTableList = FXCollections.observableArrayList();
    }

    @Override
    public void resetWorkspace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ObservableList getSiteTableList(){
        return siteTableList;
    }
    
}
