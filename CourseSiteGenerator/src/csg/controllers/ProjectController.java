/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import coursesitegenerator.CourseSiteGeneratorAPP;
import coursesitegenerator.CourseSiteGeneratorProp;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_EXIST_MESSAGE;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_EXIST_TITLE;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_TEXTFIELD_EMPTY_MESSAGE;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_TEXTFIELD_EMPTY_TITLE;
import csg.data.CSGData;
import csg.data.ProjectData;
import csg.data.RecitationData;
import csg.data.Recitations;
import csg.data.Students;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.data.Teams;
import csg.workspace.ProjectTab;
import csg.workspace.RecitationTab;
import csg.workspace.WorkSpace;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jtps.jTPS;
import properties_manager.PropertiesManager;

/**
 *
 * @author patrickboucicault
 */
public class ProjectController {
    
     CourseSiteGeneratorAPP app;
    
    static jTPS jTPS = new jTPS();
    
    ProjectTab tab;
    PropertiesManager props = PropertiesManager.getPropertiesManager();
    ProjectData projectData;
    //ProjectData studentData;
    HashMap<String,ColorPicker> colorPickerOne = new HashMap<String,ColorPicker>();
    HashMap<String,ColorPicker> colorPickerTwo = new HashMap<String,ColorPicker>();
    
    ComboBox teamComboBox;
    
    
    static jTPS projectjTPS = new jTPS();
    /**
     * Constructor, note that the app must already be constructed.
     */
    public ProjectController(CourseSiteGeneratorAPP initApp,ProjectTab initTab) {
        // KEEP THIS FOR LATER
        app = initApp;
        tab = initTab;
        
        teamComboBox = tab.getTeamComboBox();
        
    }

    /**
     * This helper method should be called every time an edit happens.
     */
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }
    
    public void handleAddStudent(){
        
        
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            CSGData csgData = (CSGData) app.getDataComponent();
            projectData = (ProjectData)csgData.getProjectData();

            ObservableList<Students> studentList; 
            studentList = projectData.getStudentList();
            
  

            WorkSpace workspace = (WorkSpace) app.getWorkspaceComponent();
            TableView studentTable = workspace.getProjectTab().getStudentTable();
          
            TextField firstNameTextField = tab.getFirstNameText();
            TextField lastNameTextField = tab.getLastNameTextField();
            ComboBox teamComboBox = tab.getTeamComboBox();
            TextField roleTextField = tab.getRoleTextField();
            
             if(tab.getAddStudentButton().getText().equals("Update Student")){
             
                Object selectedItem = tab.getStudentTable().getSelectionModel().getSelectedItem();
                String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_RECITATION_BUTTON_TEXT.toString());
      
                Students student = (Students)selectedItem;
                
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String teamSelection = (String) teamComboBox.getValue();
                String role = roleTextField.getText();
                
                
                
            

                Students newStudent;
                
                if(teamSelection.isEmpty()){
                    
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show(props.getProperty(RECITATION_TEXTFIELD_EMPTY_TITLE), props.getProperty(RECITATION_TEXTFIELD_EMPTY_MESSAGE));
                    
                }
               
                else{
                    studentList.remove(student);
                
                     newStudent = new Students(firstName,lastName,role,teamSelection);
                     studentList.add(newStudent);
                      markWorkAsEdited();
                }
                

                tab.getAddStudentButton().setText(addButtonText);
                Collections.sort(studentList);
                
                
              }
             
            else{
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String teamSelection = (String) teamComboBox.getValue();
                String role = roleTextField.getText();
                
                if(firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()||
                    roleTextField.getText().isEmpty() || teamSelection.isEmpty()){

                     AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                     dialog.show(props.getProperty(RECITATION_TEXTFIELD_EMPTY_TITLE), props.getProperty(RECITATION_TEXTFIELD_EMPTY_MESSAGE));

            }
            else{

               /* String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String teamSelection = (String) teamComboBox.getValue();
                String role = roleTextField.getText();
                */

               boolean studentChosen = true;

                Students newStudent;
                
                if(!studentChosen){   
                     newStudent = new Students(firstName,lastName,role," ");
                }     
              
                else{
                     newStudent = new Students(firstName,lastName,role,teamSelection);
                }
                


                if(studentList.size() == 0){
                    
                     studentList.add(newStudent);
                     tab.getAddStudentButton().setText("Add Student");
                     firstNameTextField.setText("");
                     lastNameTextField.setText("");
                     roleTextField.setText("");
                     Collections.sort(studentList);
                      markWorkAsEdited();
                    


                }
                else{


                for(int i = 0; i < studentList.size();i++){
                     
                     if(studentList.get(i).getFirstName().equals(newStudent.getFirstName() ) &&
                          studentList.get(i).getLastName().equals(newStudent.getLastName()  )){

                        AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                        dialog.show(props.getProperty(RECITATION_EXIST_TITLE), props.getProperty(RECITATION_EXIST_MESSAGE));
                        //i = recitationTable.getItems().size();
                         firstNameTextField.setText("");
                         lastNameTextField.setText("");
                         break;

                    }
                     else{
                         studentList.add(newStudent);
                         tab.getAddStudentButton().setText("Add Student");

                        firstNameTextField.setText("");
                        lastNameTextField.setText("");
                        roleTextField.setText("");
                        Collections.sort(studentList);
                         markWorkAsEdited();
                       // Collections.sort(recitationList);
                        break;
                    }
                }

            }

           }
           
       
     }

    }
    
    
    public void handleStudentClicked(){
        
         TableView taTable = tab.getStudentTable();
         String updateButtonText = props.getProperty(CourseSiteGeneratorProp.UPDATE_RECITATION_BUTTON_TEXT.toString());
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null){
           
            Students student = (Students)selectedItem;
            
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            String role = student.getRole();
            String team = student.getTeam();
            
            tab.getFirstNameText().setText(firstName);
            tab.getLastNameTextField().setText(lastName);
            tab.getRoleTextField().setText(role);
            //tab.getTeamComboBox().setText(team);
            
            tab.getAddStudentButton().setText("Update Student");
             markWorkAsEdited();
            
           
            
        }
        
     }
    
    public void handleRemoveStudent(){
        
         //String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_RECITATION_BUTTON_TEXT.toString());
         TableView taTable = tab.getStudentTable();
        
         Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
         if(selectedItem != null){
             
             Students students = (Students)selectedItem;
             
             ObservableList<Students> studentList; 
             studentList = projectData.getStudentList();
             
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation Dialog");
        
             alert.setContentText("Are you sure you want to delete this Student?");
 
             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK){
                 
                tab.getFirstNameText().setText("");
                tab.getLastNameTextField().setText("");
                tab.getRoleTextField().setText("");
                studentList.remove(students);
                tab.getAddStudentButton().setText("Add Student");
                Collections.sort(studentList);
                 markWorkAsEdited();
        
             } 

             
         }
    }
    
   
    
     
    public void handleStudentClearButtonClicked(){
        //String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_RECITATION_BUTTON_TEXT.toString());
        
        tab.getFirstNameText().setText("");
        tab.getLastNameTextField().setText("");
        tab.getRoleTextField().setText("");
            
        tab.getFirstNameText().requestFocus();
        tab.getAddStudentButton().setText("Add Student");
            
     
    }
    
    
     public void handleRemoveTeam(){
        
         TableView taTable = tab.getTeamTable();
        
         Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
         if(selectedItem != null){
             
             Teams teams = (Teams)selectedItem;
             
             ObservableList<Teams> teamList; 
             teamList = projectData.getTeamList();
             
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation Dialog");
        
             alert.setContentText("Are you sure you want to delete this Team?");
 
             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK){
                 
                tab.getNameText().setText("");
                tab.linkTextField().setText("");
                
                teamList.remove(teams);
                tab.getAddTeamButton().setText("Add Team");
              
                teamComboBox.getItems().clear();
                teamComboBox.getItems().add("");
                for(int i = 0; i < teamList.size();i++){
            
                    teamComboBox.getItems().add(teamList.get(i).getName());
                }
                
                Collections.sort(teamList);
                 markWorkAsEdited();
        
             } 

             
         }
    }
    
    public void handleTeamClearButtonClicked(){
        tab.getNameText().setText("");
        tab.linkTextField().setText("");
        tab.getAddTeamButton().setText("Add Team");
        tab.getNameText().requestFocus();
    }
    
    public void handleTeamClicked(){
        
        TableView teamTable = tab.getTeamTable();
         //String updateButtonText = props.getProperty(CourseSiteGeneratorProp.UPDATE_RECITATION_BUTTON_TEXT.toString());
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = teamTable.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null){
           
            Teams teams = (Teams)selectedItem;
            
            TextField teamName = tab.getNameText();
            TextField teamLink = tab.linkTextField();
            ColorPicker colorPicker1 = tab.getColorPickerOne();
            ColorPicker colorPicker2 = tab.getColorPickerTwo();
            
            
            teamName.setText(teams.getName());
            teamLink.setText(teams.getLink());
            
            
            tab.getAddTeamButton().setText("Update Team");
            
           
            
        }
        
    }
    
    public void handleAddTeam(){
        
        
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            CSGData csgData = (CSGData) app.getDataComponent();
            projectData = (ProjectData)csgData.getProjectData();

            ObservableList<Teams> teamList; 
            teamList = projectData.getTeamList();
            
  

            WorkSpace workspace = (WorkSpace) app.getWorkspaceComponent();
            TableView teamTable = workspace.getProjectTab().getTeamTable();
          
            TextField teamName = tab.getNameText();
            TextField teamLink = tab.linkTextField();
            ColorPicker colorPicker1 = tab.getColorPickerOne();
            ColorPicker colorPicker2 = tab.getColorPickerTwo();
            
            
            
            
             if(tab.getAddTeamButton().getText().equals("Update Team")){
             
                Object selectedItem = tab.getTeamTable().getSelectionModel().getSelectedItem();
                String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_RECITATION_BUTTON_TEXT.toString());
      
                Teams team = (Teams)selectedItem;
                
                String name = teamName.getText();
                String link = teamLink.getText();
                String color1 = (String) colorPicker1.getValue().toString();
                String color2 = (String) colorPicker2.getValue().toString();
                
  

                Teams newTeam;
                
              
                 if(teamName.getText().isEmpty() || teamLink.getText().isEmpty()){

                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show(props.getProperty(RECITATION_TEXTFIELD_EMPTY_TITLE), props.getProperty(RECITATION_TEXTFIELD_EMPTY_MESSAGE));

                 }
                 
                 else{
                   
                     newTeam = new Teams(name,color1,color2,link);
                     newTeam.setBlue(colorPicker1.getValue().getBlue());
                     newTeam.setRed(colorPicker1.getValue().getBlue());
                     newTeam.setGreen(colorPicker1.getValue().getGreen());
                     teamList.remove(team);
                     tab.getAddTeamButton().setText("Add Team");
                     teamList.add(newTeam);
     
                     Collections.sort(teamList);
                      markWorkAsEdited();
                     
                     teamComboBox.getItems().clear();
                     teamComboBox.getItems().add("");
                      for(int i = 0; i < teamList.size();i++){
            
                         teamComboBox.getItems().add(teamList.get(i).getName());
                      }
                     
                }
                     
                  
     
              }
    
              
             
             else{

                Object selectedItem = tab.getStudentTable().getSelectionModel().getSelectedItem();
                String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_RECITATION_BUTTON_TEXT.toString());
      
                String name = teamName.getText();
                String link = teamLink.getText();
                String color1 = (String) colorPicker1.getValue().toString();
                String color2 = (String) colorPicker2.getValue().toString();
                
            
          
            

                if(teamName.getText().isEmpty() || teamLink.getText().isEmpty()){

                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show(props.getProperty(RECITATION_TEXTFIELD_EMPTY_TITLE), props.getProperty(RECITATION_TEXTFIELD_EMPTY_MESSAGE));

                }
             
                 else{

                    Teams newTeam = new Teams(name,color1,color2,link);

                    if(teamList.size() == 0){
                        
                        newTeam.setBlue(colorPicker1.getValue().getBlue());
                        newTeam.setRed(colorPicker1.getValue().getBlue());
                        newTeam.setGreen(colorPicker1.getValue().getGreen());
                        teamList.add(newTeam);
                        
                        tab.getAddTeamButton().setText("Add Team");
                        teamName.setText("");
                        teamLink.setText("");
                        
                        
                         teamComboBox.getItems().clear();
                         teamComboBox.getItems().add("");
                         for(int i = 0; i < teamList.size();i++){
            
                             teamComboBox.getItems().add(teamList.get(i).getName());
                         }

                        Collections.sort(teamList);
                         markWorkAsEdited();

                   }
                    
                   else{


                        for(int i = 0; i < teamList.size();i++){

                            if(teamList.get(i).getName().equals(newTeam.getName()) &&
                                 teamList.get(i).getColor().equals(newTeam.getColor()  ))
                            {

                               AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                               dialog.show(props.getProperty(RECITATION_EXIST_TITLE), props.getProperty(RECITATION_EXIST_MESSAGE));
                               //i = recitationTable.getItems().size();
                                teamName.setText("");
                                teamLink.setText("");
                                break;

                            }
                            
                            else{
                               newTeam.setBlue(colorPicker1.getValue().getBlue());
                               newTeam.setRed(colorPicker1.getValue().getBlue());
                               newTeam.setGreen(colorPicker1.getValue().getGreen());
                               teamList.add(newTeam);

                               tab.getAddTeamButton().setText("Add Team");

                               teamName.setText("");
                               teamLink.setText("");
                               
                               
                                teamComboBox.getItems().clear();
                                teamComboBox.getItems().add("");
                                 for(int k = 0; k < teamList.size();k++){
            
                                     teamComboBox.getItems().add(teamList.get(k).getName());
                                 }
                              
                               Collections.sort(teamList);
                                markWorkAsEdited();

                               break;
                           }
                       }
                        
                       

                   }

              }


        }

       }

   
}
