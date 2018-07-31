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
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_TEXTFIELD_EMPTY_MESSAGE;
import static coursesitegenerator.CourseSiteGeneratorProp.RECITATION_TEXTFIELD_EMPTY_TITLE;
import csg.data.CSGData;
import csg.data.RecitationData;
import csg.data.Recitations;
import csg.data.ScheduleData;
import csg.data.Schedules;
import csg.data.TAData;
import csg.workspace.RecitationTab;
import csg.workspace.ScheduleTab;
import csg.workspace.WorkSpace;
import djf.ui.AppMessageDialogSingleton;
import java.util.Collections;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import properties_manager.PropertiesManager;

/**
 *
 * @author patrickboucicault
 */
public class ScheduleController {
 
    CourseSiteGeneratorAPP app;
    
    ScheduleTab tab;
    PropertiesManager props = PropertiesManager.getPropertiesManager();
    ScheduleData scheduleData;
    Schedules schedule;
    
    WorkSpace workspace; 
    
    String mondayYear = "";
    String mondayMonth="";
    String mondayDay = "";
    
    String fridayYear = "";
    String fridayMonth="";
    String fridayDay = "";
    
    
     public ScheduleController(CourseSiteGeneratorAPP initApp,ScheduleTab initTab) {
        // KEEP THIS FOR LATER
        app = initApp;
        tab = initTab;
        
        workspace = (WorkSpace) app.getWorkspaceComponent();
   
    }
     
     
    public void handleMondayPicker(){
       
        DatePicker picker = tab.getMondayPicker();
        String date = picker.getValue().toString();
        
        String [] monthDayYear = date.split("-");
        
         mondayYear = monthDayYear[0];
         mondayMonth = monthDayYear[1];
         mondayDay = monthDayYear[2];
        
        String dayOfWeek = picker.getValue().getDayOfWeek().toString();
        
        if(!dayOfWeek.equals("MONDAY")){
            
           AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
           dialog.show("Incorrect Date","Date must be on a Monday");
           tab.setMonday(false);
        }
        else{
            tab.setMonday(true);
        }
       
    }
   
   
   public void handleFridayPicker(){
       
       
       DatePicker picker = tab.getFridayPicker();
       String date = picker.getValue().toString();
        
       String [] monthDayYear = date.split("-");
        
        fridayYear = monthDayYear[0];
        fridayMonth = monthDayYear[1];
        fridayDay = monthDayYear[2];
        
       String dayOfWeek = picker.getValue().getDayOfWeek().toString();
        
        if(!dayOfWeek.equals("FRIDAY")){
            
           AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
           dialog.show("Incorrect Date","Date must be on a Friday");
           tab.setFriday(false);
           
        }
        else{
            tab.setFriday(true);
        }
       
       
     
   }
     
   
      public void handleDatePicker(){
          
        DatePicker picker = tab.getBottomDate();
        String date = picker.getValue().toString();
        
        String [] monthDayYear = date.split("-");
        
        String year = monthDayYear[0];
        String month = monthDayYear[1];
        String day = monthDayYear[2];
        
       // System.out.println("Date:"+year+"/"+month+"/"+day);
        //System.out.println("Calandar:"+mondayYear+"/"+mondayMonth+"/"+mondayDay);
        
        if(Integer.parseInt(year) > Integer.parseInt(mondayYear)){
            tab.setMondayIsValid(true);
        }   
        else if(Integer.parseInt(year) == Integer.parseInt(mondayYear)){
            
            if(Integer.parseInt(month) > Integer.parseInt(mondayMonth)){
                tab.setMondayIsValid(true);
  
            }
            
            else if(Integer.parseInt(month) == Integer.parseInt(mondayMonth)){
                
                if(Integer.parseInt(day) >= Integer.parseInt(mondayDay)){
                     tab.setMondayIsValid(true);
                    
                }
                
                else{
                    
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show("Incorrect Date","Date must be after Monday boundry");
                    tab.setMondayIsValid(false);
                     
                }
                
            }
            
            else{
                 AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show("Incorrect Date","Date must be after Monday boundry");
                tab.setMondayIsValid(false);
            }
                
               
        }
        
        
        else{
            
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Incorrect Date","Date must be after Monday boundry");
            tab.setMondayIsValid(false);
        }
        
        
            picker = tab.getBottomDate();
            date = picker.getValue().toString();
        
            monthDayYear = date.split("-");
        
            year = monthDayYear[0];
            month = monthDayYear[1];
            day = monthDayYear[2];
        
        
        
       // System.out.println(year+month+day);
        if(Integer.parseInt(year) < Integer.parseInt(fridayYear)){
            tab.setFridayIsValid(true);
        }   
        else if(Integer.parseInt(year) == Integer.parseInt(fridayYear)){
            
            if(Integer.parseInt(month) < Integer.parseInt(fridayMonth)){
                tab.setFridayIsValid(true);
  
            }
            
            else if(Integer.parseInt(month) == Integer.parseInt(fridayMonth)){
                
                if(Integer.parseInt(day) <= Integer.parseInt(fridayDay)){
                     tab.setFridayIsValid(true);
                    
                }
                
                else{
                    
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show("Incorrect Date","Date must be after Friday boundry");
                    tab.setFridayIsValid(false);
                     
                }
                
            }
            
            else{
               
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show("Incorrect Date","Date must be after Friday boundry");
                tab.setFridayIsValid(false);
            }
                
                
        }
        
        
        else{
            
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Incorrect Date","Date must be after Friday boundry");
            tab.setFridayIsValid(false);
        }
      }
      
      public void handleAddSchedule(){
        
          System.out.println(tab.getIsDateVaid());
          DatePicker dateField = tab.getBottomDate();
          ComboBox typeField = tab.getTypeBox();
          TextField timeField = tab.getTimeField();
          TextField titleField = tab.getTitleField();
          TextField topicField = tab.getTopicField();
          TextField linkField = tab.getLinkField();
          TextField criteriaField = tab.getCriteriaield();
          
          Button addButton = tab.getAddButton();
          Button clearButton = tab.getClearButton();
          
          PropertiesManager props = PropertiesManager.getPropertiesManager();
          CSGData csgData = (CSGData) app.getDataComponent();
          scheduleData = (ScheduleData)csgData.getScheduleData();

          ObservableList<Schedules> scheduleList; 
          scheduleList = scheduleData.getScheduleList();

         

          WorkSpace workspace = (WorkSpace) app.getWorkspaceComponent();
          TableView scheduleTable = workspace.getScheduleTab().getTable();
          
          if(tab.getAddButton().getText().equals("Update")){
              
              if(tab.getIsDateVaid()){
                  
                  Schedules schedule = (Schedules) scheduleTable.getSelectionModel().getSelectedItem();
                  
                  
                  
                 
                  String date = tab.getBottomDate().getValue().toString();
                  String type = (String) typeField.getValue();
                  String time = timeField.getText();
                  String topic = topicField.getText();
                  String link =linkField.getText();
                  String criteria = criteriaField.getText();
                  String title = titleField.getText();
                  
                  
                  Schedules newSchedule;
                  
                  
                  newSchedule = new Schedules(type,date,title,topic);
                  newSchedule.setCriteria(criteria);
                  newSchedule.setLink(link);
                  newSchedule.setTime(time);
                  scheduleList.remove(schedule);
                  scheduleList.add(newSchedule);
                  tab.getAddButton().setText("Add Schedule");
                  
                  
                  tab.getTimeField().setText("");
                  tab.getLinkField().setText("");
                  tab.getTitleField().setText("");
                  tab.getCriteriaield().setText("");
                  tab.getTopicField().setText("");
                  tab.getTypeBox().setValue("");
                  
                  
              }
              
          }
          else{

              if(tab.getIsDateVaid()){
                  
                  Schedules schedule;
                  //System.out.println("fdslg");
                  String date = tab.getBottomDate().getValue().toString();
                  //System.out.println(typeField.getValue());
                  String type = (String) typeField.getValue();
                  String time = timeField.getText();
                  String topic = topicField.getText();
                  String link =linkField.getText();
                  String criteria = criteriaField.getText();
                  String title = titleField.getText();
                  
                  schedule = new Schedules(type,date,title,topic);
                  schedule.setCriteria(criteria);
                  schedule.setLink(link);
                  schedule.setTime(time);
                  scheduleList.add(schedule);
                  
                  
                  tab.getTimeField().setText("");
                  tab.getLinkField().setText("");
                  tab.getTitleField().setText("");
                  tab.getCriteriaield().setText("");
                  tab.getTopicField().setText("");
                  tab.getTypeBox().setValue("");
              }
              
              else{
                  AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                 dialog.show(props.getProperty(RECITATION_TEXTFIELD_EMPTY_TITLE), props.getProperty(RECITATION_TEXTFIELD_EMPTY_MESSAGE));
              }
          }
        
      }
      
      public void handleScheduleClicked(){
          
          
         TableView taTable = tab.getTable();
         String updateButtonText = props.getProperty(CourseSiteGeneratorProp.UPDATE_RECITATION_BUTTON_TEXT.toString());
        
        // IS A TA SELECTED IN THE TABLE?
         Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null){
           
            Schedules schedule = (Schedules)selectedItem;
            
            String date = schedule.getDate();
            String title = schedule.getTitle();
            String topic = schedule.getTopic();
            String type = schedule.getType();
            String link = schedule.getLink();
            String criteria = schedule.getCriteria();
            String time = schedule.getTime();
            
            tab.getTimeField().setText(time);
            tab.getLinkField().setText(link);
            tab.getTitleField().setText(title);
            tab.getCriteriaield().setText(criteria);
            tab.getTopicField().setText(topic);
            tab.getTypeBox().setValue(type);
            tab.getAddButton().setText("Update");
            
      }
   }
      
    public void handleClearButton(){
        
        
        
          DatePicker dateField = tab.getBottomDate();
          ComboBox typeField = tab.getTypeBox();
          TextField timeField = tab.getTimeField();
          TextField titleField = tab.getTitleField();
          TextField topicField = tab.getTopicField();
          TextField linkField = tab.getLinkField();
          TextField criteriaField = tab.getCriteriaield();
          
          Button addButton = tab.getAddButton();
         
          
          PropertiesManager props = PropertiesManager.getPropertiesManager();
          CSGData csgData = (CSGData) app.getDataComponent();
          scheduleData = (ScheduleData)csgData.getScheduleData();

          ObservableList<Schedules> scheduleList; 
          scheduleList = scheduleData.getScheduleList();

          typeField.setValue("");
          titleField.setText("");
          timeField.setText("");
          topicField.setText("");
          linkField.setText("");
          criteriaField.setText("");
         // dateField.setValue(LocalDate.);
          
          addButton.setText("Add Schedule");
          
    }
      
    
    
    public void handleRemoveSchedule(){
        
         TableView taTable = tab.getTable();
        
         Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
         if(selectedItem != null){
             
             Schedules schedule = (Schedules)selectedItem;
             
             ObservableList<Schedules> scheduleList; 
             scheduleList = scheduleData.getScheduleList();
             
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation Dialog");
        
             alert.setContentText("Are you sure you want to delete this Schedule?");
 
             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK){
                 
                 ComboBox typeField = tab.getTypeBox();
                 TextField timeField = tab.getTimeField();
                 TextField titleField = tab.getTitleField();
                 TextField topicField = tab.getTopicField();
                 TextField linkField = tab.getLinkField();
                 TextField criteriaField = tab.getCriteriaield();
                
                 typeField.setValue("");
                 titleField.setText("");
                 timeField.setText("");
                 topicField.setText("");
                 linkField.setText("");
                 criteriaField.setText("");
                 scheduleList.remove(schedule);
                 tab.getAddButton().setText("Add Schedule");
                 Collections.sort(scheduleList);
        
             } 

             
         }
    }
     
     
}
