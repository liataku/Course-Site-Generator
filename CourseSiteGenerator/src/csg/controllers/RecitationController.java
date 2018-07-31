/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import coursesitegenerator.CourseSiteGeneratorAPP;
import coursesitegenerator.CourseSiteGeneratorProp;
import static coursesitegenerator.CourseSiteGeneratorProp.MISSING_TA_NAME_MESSAGE;
import static coursesitegenerator.CourseSiteGeneratorProp.MISSING_TA_NAME_TITLE;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_EXIST_MESSAGE;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_EXIST_TITLE;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_TEXTFIELD_EMPTY_MESSAGE;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_TEXTFIELD_EMPTY_TITLE;
import csg.data.CSGData;
import csg.data.RecitationData;
import csg.data.Recitations;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.workspace.RecitationTab;
import csg.workspace.TATab;
import csg.workspace.WorkSpace;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import java.util.Collections;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jtps.jTPS;
import properties_manager.PropertiesManager;

/**
 *
 * @author patrickboucicault
 */
public class RecitationController {
    
    
    CourseSiteGeneratorAPP app;
    
    static jTPS jtps = new jTPS();
    
    RecitationTab tab;
    PropertiesManager props = PropertiesManager.getPropertiesManager();
    RecitationData recitationData;
    Recitations recitation;
    
    WorkSpace workspace; 
    
    
    
    //static jTPS recitationjTPS = new jTPS();
    /**
     * Constructor, note that the app must already be constructed.
     */
    public RecitationController(CourseSiteGeneratorAPP initApp,RecitationTab initTab) {
        // KEEP THIS FOR LATER
        app = initApp;
        tab = initTab;
        
        workspace = (WorkSpace) app.getWorkspaceComponent();
        
        
        
    }

    /**
     * This helper method should be called every time an edit happens.
     */
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }
    
    public void handleAddRecitation(){
       
        
             PropertiesManager props = PropertiesManager.getPropertiesManager();
            CSGData csgData = (CSGData) app.getDataComponent();
            recitationData = (RecitationData)csgData.getRecitationData();

            ObservableList<Recitations> recitationList; 
            recitationList = recitationData.getRecitationList();

            TAData taData = csgData.getTAData();

            WorkSpace workspace = (WorkSpace) app.getWorkspaceComponent();
            TableView recitationTable = workspace.getRecitationTab().getTable();
            ObservableList<TeachingAssistant> tableData = taData.getTeachingAssistants();
            TextField sectionTextField = tab.getSectionText();
            TextField instructorTextField = tab.getInstructorText();
            TextField dayTimeTextField = tab.getDayText();
            TextField locationTextField = tab.getLocationText();
         
            
             if(tab.getAddButton().getText().equals("Update")){
          
                Object selectedItem = tab.getTable().getSelectionModel().getSelectedItem();
                String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_RECITATION_BUTTON_TEXT.toString());
      
                Recitations recitations = (Recitations)selectedItem;
                
                String section = sectionTextField.getText();
                String instructor = instructorTextField.getText();
                String dayTime = dayTimeTextField.getText();
                String location = locationTextField.getText();
                
                recitationList.remove(recitations);
                 markWorkAsEdited();
                
                boolean taChosen1 = false;
                boolean taChosen2 = false;

                ComboBox TA1 = tab.getTA1();
                ComboBox TA2 = tab.getTA2();
                
                String taName1 = (String)TA1.getValue();
                String taName2 = (String)TA2.getValue();

                for(int i = 0; i < tableData.size();i++){

                    if(taName1.equals(tableData.get(i).getName())){
                        taChosen1 = true;
                    }
                }

                for(int i = 0; i < tableData.size();i++){
                    if(taName2.equals(tableData.get(i).getName())){
                        taChosen2 = true;
                    }
                }

                Recitations newRecitation;
                
                if(!taChosen1 && !taChosen2){   
                     newRecitation = new Recitations(section,instructor,dayTime,location);
                }     
                else if(taChosen1 && !taChosen2){
                     newRecitation = new Recitations(section,instructor,dayTime,location,taName1," ");
                }
                else if(!taChosen1 && taChosen2){
                     newRecitation = new Recitations(section,instructor,dayTime,location," ",taName2);
                }
                else{
                     newRecitation = new Recitations(section,instructor,dayTime,location,taName1,taName2);
                }
                
                
                recitationList.add(newRecitation);
                Collections.sort(recitationList);
                tab.getAddButton().setText(addButtonText);
                 markWorkAsEdited();
                
                
              }
             
         else{
             
            

            ComboBox TA1 = tab.getTA1();
            ComboBox TA2 = tab.getTA2();


            if(sectionTextField.getText().isEmpty() || instructorTextField.getText().isEmpty()||
                dayTimeTextField.getText().isEmpty() ||  locationTextField.getText().isEmpty() ){

                 AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                 dialog.show(props.getProperty(RECITATION_TEXTFIELD_EMPTY_TITLE), props.getProperty(RECITATION_TEXTFIELD_EMPTY_MESSAGE));

            }
            else{

                String section = sectionTextField.getText();
                String instructor = instructorTextField.getText();
                String dayTime = dayTimeTextField.getText();
                String location = locationTextField.getText();



                String taName1 = (String)TA1.getValue();
                String taName2 = (String)TA2.getValue();


                boolean taChosen1 = false;
                boolean taChosen2 = false;



                for(int i = 0; i < tableData.size();i++){

                    if(taName1.equals(tableData.get(i).getName())){
                        taChosen1 = true;
                    }
                }

                for(int i = 0; i < tableData.size();i++){
                    if(taName2.equals(tableData.get(i).getName())){
                        taChosen2 = true;
                    }
                }

                if(!taChosen1 && !taChosen2){   
                     recitation = new Recitations(section,instructor,dayTime,location);
                }     
                else if(taChosen1 && !taChosen2){
                     recitation = new Recitations(section,instructor,dayTime,location,taName1," ");
                }
                else if(!taChosen1 && taChosen2){
                     recitation = new Recitations(section,instructor,dayTime,location," ",taName2);
                }
                else{
                     recitation = new Recitations(section,instructor,dayTime,location,taName1,taName2);
                }


                if(recitationList.size() == 0){

                    recitationList.add(recitation);
                    sectionTextField.setText("");
                    instructorTextField.setText("");
                    dayTimeTextField.setText("");
                    locationTextField.setText("");
                    Collections.sort(recitationList);
                     markWorkAsEdited();


                }
                else{


                for(int i = 0; i < recitationList.size();i++){
                     //System.out.println(recitationTable.getItems().get(i).toString());
                     //System.out.println(recitationTable.getItems().get(i).toString());
                     if(recitationList.get(i).getSection().equals(section)){

                        AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                        dialog.show(props.getProperty(RECITATION_EXIST_TITLE), props.getProperty(RECITATION_EXIST_MESSAGE));
                        //i = recitationTable.getItems().size();
                        sectionTextField.setText(" ");
                        break;

                    }
                    else{
                        recitationList.add(recitation);

                        sectionTextField.setText("");
                        instructorTextField.setText("");
                        dayTimeTextField.setText("");
                        locationTextField.setText("");
                        Collections.sort(recitationList);
                         markWorkAsEdited();
                        break;
                    }
                }



            }

            }
      
                      
       
     }
    
   
    }
    
    
    public void handleRecitationClicked(){
        
        
               
         TableView taTable = tab.getTable();
         String updateButtonText = props.getProperty(CourseSiteGeneratorProp.UPDATE_RECITATION_BUTTON_TEXT.toString());
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null){
           
            Recitations recitations = (Recitations)selectedItem;
            
            String day = recitations.getDay();
            String instructor = recitations.getInstructor();
            String location = recitations.getLocation();
            String section = recitations.getSection();
            
            tab.getDayText().setText(day);
            tab.getInstructorText().setText(instructor);
            tab.getLocationText().setText(location);
            tab.getSectionText().setText(section);
            
            tab.getAddButton().setText(updateButtonText);
            
           
            
        }
        
     }
    
    public void handleRemoveRecitation(){
        
       
        
         String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_RECITATION_BUTTON_TEXT.toString());
         TableView taTable = tab.getTable();
        
         Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
         if(selectedItem != null){
             
             Recitations recitations = (Recitations)selectedItem;
             
             ObservableList<Recitations> recitationList; 
             recitationList = recitationData.getRecitationList();
             
             Alert alert = new Alert(AlertType.CONFIRMATION);
             alert.setTitle("Confirmation Dialog");
        
             alert.setContentText("Are you sure you want to delete this Recitation?");
 
             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK){
                 
                tab.getDayText().setText("");
                tab.getInstructorText().setText("");
                tab.getLocationText().setText("");
                tab.getSectionText().setText("");
                recitationList.remove(recitations);
                tab.getAddButton().setText(addButtonText);
                 Collections.sort(recitationList);
                  markWorkAsEdited();
             } 

             
         }
    }
    
    public void handleClearButtonClicked(){
        
       
        
        String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_RECITATION_BUTTON_TEXT.toString());
        
        tab.getDayText().setText("");
        tab.getInstructorText().setText("");
        tab.getLocationText().setText("");
        tab.getSectionText().setText("");
            
        tab.getSectionText().requestFocus();
        tab.getAddButton().setText(addButtonText);
            
     
    }
    
    
      public void handleUndoTransaction() {
          
        
        jtps.undoTransaction();
        markWorkAsEdited();
    }

    public void handleReDoTransaction() {
        
        jtps.doTransaction();
        markWorkAsEdited();
    }
}
