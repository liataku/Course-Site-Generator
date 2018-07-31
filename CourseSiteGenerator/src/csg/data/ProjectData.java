/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import coursesitegenerator.CourseSiteGeneratorAPP;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;

/**
 *
 * @author patrickboucicault
 */
public class ProjectData extends AppWorkspaceComponent implements AppDataComponent{
    
       // WE'LL NEED ACCESS TO THE APP TO NOTIFY THE GUI WHEN DATA CHANGES
    CourseSiteGeneratorAPP app;

    // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
    // DATA IN THE ROWS OF THE TABLE VIEW
    ObservableList<Students> studentTableList;
    ObservableList<Teams> teamTableList;
    
    // THIS WILL STORE ALL THE RECITATION TABLE DATA, WHICH YOU
    // SHOULD NOTE ARE StringProperty OBJECTS THAT ARE CONNECTED
    // TO UI LABELS, WHICH MEANS IF WE CHANGE VALUES IN THESE
    // PROPERTIES IT CHANGES WHAT APPEARS IN THOSE LABELS
    HashMap<String, StringProperty> recitationTable;
    
    
    
    // THESE ARE THE LANGUAGE-DEPENDENT VALUES FOR
    // THE OFFICE HOURS GRID HEADERS. NOTE THAT WE
    // LOAD THESE ONCE AND THEN HANG ON TO THEM TO
    // INITIALIZE OUR OFFICE HOURS GRID
    ArrayList<String> gridHeaders;
    
    
      public ProjectData(CourseSiteGeneratorAPP init){
        
        app = init;
        
        // CONSTRUCT THE LIST OF RECITATIONS FOR THE TABLE
        studentTableList = FXCollections.observableArrayList();
        teamTableList = FXCollections.observableArrayList();
        
        //THIS WILL STORE OUR OFFICE HOURS
        recitationTable = new HashMap();
        
        
         // THESE ARE THE LANGUAGE-DEPENDENT OFFICE HOURS GRID HEADERS
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        //ArrayList<String> timeHeaders = props.getPropertyOptionsList(CourseSiteGeneratorProp.OFFICE_HOURS_TABLE_HEADERS);
        //ArrayList<String> dowHeaders = props.getPropertyOptionsList(CourseSiteGeneratorProp.DAYS_OF_WEEK);
        gridHeaders = new ArrayList();
        //gridHeaders.addAll(timeHeaders);
        //gridHeaders.addAll(dowHeaders);
      }

      public ObservableList getStudentList(){
        return studentTableList;
    }
      
      public ObservableList getTeamList(){
        return teamTableList;
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
          studentTableList.clear();
          teamTableList.clear();
    }
    
    }
      
