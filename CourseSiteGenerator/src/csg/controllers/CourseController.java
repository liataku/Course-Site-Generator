/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import coursesitegenerator.CourseSiteGeneratorAPP;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_EXIST_MESSAGE;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_EXIST_TITLE;
import csg.data.CSGData;
import csg.data.CourseData;
import csg.data.RecitationData;
import csg.data.Recitations;
import csg.data.SitePages;
import csg.workspace.CourseTab;
import csg.workspace.RecitationTab;
import csg.workspace.WorkSpace;
import djf.controller.AppFileController;
import static djf.settings.AppPropertyType.EXPORT_WORK_TITLE;
import static djf.settings.AppPropertyType.LOAD_ERROR_MESSAGE;
import static djf.settings.AppPropertyType.LOAD_ERROR_TITLE;
import static djf.settings.AppPropertyType.SAVE_WORK_TITLE;
import static djf.settings.AppPropertyType.WORK_FILE_EXT;
import static djf.settings.AppPropertyType.WORK_FILE_EXT_DESC;
import static djf.settings.AppStartupConstants.PATH_WORK;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import jtps.jTPS;
import org.apache.commons.io.FileUtils;
import properties_manager.PropertiesManager;

/**
 *
 * @author patrickboucicault
 */
public class CourseController {
    
    
     CourseSiteGeneratorAPP app;
    
    static jTPS jtps = new jTPS();
    
    CourseTab tab;
    PropertiesManager props = PropertiesManager.getPropertiesManager();
    CourseData courseData;
    SitePages sitePage;
    File tempDirectory;
    
    File exportDirectory;
    
    WorkSpace workspace; 
    
    String indexHtml = "index.html";
    String indexHtm = "index.htm";
    
    String syllabusHtml = "syllabus.html";
    String syllabusHtm = "syllabus.htm";
    
    String hwsHtml = "hws.html";
    String hwsHtm = "hws.htm";
    
    String projectsHtml = "projects.html";
    String projectsHtm = "projects.htm";
    
    String scheduleHtml = "schedule.html";
    String scheduleHtm = "schedule.htm";
    
    
         Boolean isProject = false;
         Boolean isSchedule = false;
         Boolean isHw = false;
         Boolean isSyllabus = false;
         Boolean isIndex = false;
         Boolean isDirectoryValid = false;
    
    
    public CourseController(CourseSiteGeneratorAPP initApp,CourseTab initTab) {
        // KEEP THIS FOR LATER
        app = initApp;
        tab = initTab;
        
        workspace = (WorkSpace) app.getWorkspaceComponent();
   
    }
    
    
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }
    
    
    public void handleSelectTemplateDirectory(){
         DirectoryChooser fc = new DirectoryChooser();
         
         exportDirectory = fc.showDialog(app.getGUI().getWindow());
         
         String[] directoryList = exportDirectory.list();
         
          SitePages sitePage1; 
          SitePages sitePage2;  
          SitePages sitePage3; 
          SitePages sitePage4;
          SitePages sitePage5; 
          
          TableView courseTable = tab.getCourseTable();
          CSGData csgData = (CSGData) app.getDataComponent();
          CourseData data = csgData.getCourseData();
          ObservableList<SitePages> sitePages = data.getSiteTableList();
                        
   
         
         for(int i = 0; i < directoryList.length;i++){
             
            // System.out.println("The list: "+directoryList[i]);
             
             if(directoryList[i].equals(projectsHtml) || directoryList[i].equals(projectsHtm)){
                 isProject = true;
                 sitePage5 = new SitePages("Projects","projects.html","ProjectsBuilder.js",false);
                 sitePages.add(sitePage5);
                    
             }
             else if(directoryList[i].equals(hwsHtml) || directoryList[i].equals(hwsHtm)){
                 isHw = true;
                 sitePage4 = new SitePages("HWs","hws.html","HWsBuilder.js",false);
                 sitePages.add(sitePage4);
             }
             else if(directoryList[i].equals(syllabusHtml) || directoryList[i].equals(syllabusHtm)){
                 isSyllabus = true;
                 sitePage2 = new SitePages("Syllabus","syllabus.html","SyllabusBuilder.js",false);
                 sitePages.add(sitePage2);
             }
             else if(directoryList[i].equals(indexHtml) || directoryList[i].equals(indexHtm)){
                 isIndex = true;
                 sitePage1 = new SitePages("Home","index.html","HomeBuilder.js",false);
                 sitePages.add(sitePage1);
                 //System.out.println("yo");
             }
             else if(directoryList[i].equals(scheduleHtml) || directoryList[i].equals(scheduleHtm)){
                 isSchedule = true;
                 sitePage3 = new SitePages("Schedule","schedule.html","ScheduleBuilder.js",false);
                 sitePages.add(sitePage3);
             }
             else{
               // System.out.println("Else: "+ directoryList[i]); 
             }
         }
              
             if(isProject || isHw || isIndex || isSyllabus || isSchedule){
                    isDirectoryValid = true;
                    courseTable.setItems(sitePages);
                     
                     tab.getTemDirectoryLabel().setText(exportDirectory.toString());
            
             }
             else{
                     
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show("Directory Not Valid","Please choose another Directory"
                 + ". Directory must have project.html,index.html,syllabus.html,hws.html, and schedule.html");            
            }
                
                
         }
    
    
    
    public void handleChangeButton(){
        
        DirectoryChooser fc = new DirectoryChooser();
        tempDirectory = fc.showDialog(app.getGUI().getWindow());
        tab.getDirectoryLabel().setText(tempDirectory.toString());
        
    }
         
         
	 
     public void handleExport(){
       
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        File currentDir = new File(".");
        
        String path = tempDirectory.toString();
       
       // WorkSpace workspace = (WorkSpace)app.getWorkspaceComponent();
        
        //File source = new File (exportDirectory.toString());
        if(tempDirectory != null && exportDirectory != null ){
            Copy(exportDirectory,tempDirectory);
            
            try {
                System.out.println(tempDirectory.toString());
                app.getFileComponent().exportData(courseData, tempDirectory.toString());
                
             } 
            catch (IOException ex) {
           Logger.getLogger(AppFileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        else{
           
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show("Template directory or export directory is null"
                        + " destination is null","You must specify your template directory and export directory");        
        }
        /*DirectoryChooser fc = new DirectoryChooser();
	fc.setInitialDirectory(new File(PATH_WORK));
	fc.setTitle(props.getProperty(EXPORT_WORK_TITLE));
	
        
        String officePath = "..//public_html/js/";
       
        File officeJson = new File(officePath);
        File destination = fc.showDialog(app.getGUI().getWindow());
        */
     
      /* try {
         app.getFileComponent().saveData(app.getDataComponent(), officeJson.getPath());
       } catch (IOException ex) {
           Logger.getLogger(AppFileController.class.getName()).log(Level.SEVERE, null, ex);
       }*/
       
        //displayDirectoryContents(officeJson);
       
        
       
        
    
} 
    
    public static void Copy(File source, File destination){
      
        try {
            FileUtils.copyDirectory(source, destination);
        } catch (IOException ex) {
           Logger.getLogger(AppFileController.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        

      
}
       
    
    
    
    
}
