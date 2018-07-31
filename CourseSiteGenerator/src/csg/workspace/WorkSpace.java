/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import coursesitegenerator.CourseSiteGeneratorAPP;
import coursesitegenerator.CourseSiteGeneratorProp;
import csg.controllers.CourseController;
import csg.controllers.RecitationController;
import csg.controllers.TAController;
import csg.data.CSGData;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.style.CsgStyle;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jtps.jTPS;
import properties_manager.PropertiesManager;

/**
 *
 * @author patrickboucicault
 */
public class WorkSpace extends AppWorkspaceComponent{
    
    // THIS PROVIDES US WITH ACCESS TO THE APP COMPONENTS
   CourseSiteGeneratorAPP app;

    // THIS PROVIDES RESPONSES TO INTERACTIONS WITH THIS WORKSPACE
    TAController controller;
    RecitationController rController;
    CourseController cController;
    
    TabPane tabPane = new TabPane();
    CSGData csgData;
    TAData taData;
    
    
    TATab taTab;
    CourseTab courseTab;
    ProjectTab projectTab;
    RecitationTab recitationTab;
    ScheduleTab scheduleTab;
   
    
    static jTPS jtps = new jTPS();
    
    public WorkSpace(CourseSiteGeneratorAPP init){
        
         app = init;
         
         taTab = new TATab(app);
         courseTab = new CourseTab(app);
         recitationTab = new RecitationTab(app);
         scheduleTab = new ScheduleTab(app);
         projectTab = new ProjectTab(app);
         csgData = (CSGData)app.getDataComponent();
         taData = csgData.getTAData();
         
       
        
         taTab.setText("TA Data");
         courseTab.setText("Course Details");
         projectTab.setText("Project Data");
         recitationTab.setText("Reciation Data");
         scheduleTab.setText("Schedule Data");
         

         taTab.setText("TA Data");
         
         
         tabPane.getTabs().add(courseTab);
         tabPane.getTabs().add(taTab);
         tabPane.getTabs().add(recitationTab);
         tabPane.getTabs().add(scheduleTab);
         tabPane.getTabs().add(projectTab);
        
       
         
         workspace = new BorderPane();
        
         
         ((BorderPane)workspace).setCenter(tabPane);       
         
         
    }
    
      
    public void initWorkSpace(){
       

         
    }
    
      public TATab getTATab(){
        return taTab;
    }
    
    public CourseTab getCourseTab(){
        return courseTab;
    }
    
    public RecitationTab getRecitationTab(){
       return recitationTab;
    }
    
    public ScheduleTab getScheduleTab(){
        return scheduleTab;
    }
    
    public ProjectTab getProjectTab(){
        return projectTab;
    }

    @Override
    public void resetWorkspace() {
        taTab.resetWorkspace();
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        taTab.reloadWorkspace(dataComponent);
    }
    
    
    @Override
    public BorderPane getWorkspace() {
        return (BorderPane) workspace;
    }
    
    public jTPS getJTPS(){
        return jtps;
    }
    
   
}



