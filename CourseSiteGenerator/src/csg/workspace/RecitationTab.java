/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import coursesitegenerator.CourseSiteGeneratorAPP;
import coursesitegenerator.CourseSiteGeneratorProp;
import csg.controllers.RecitationController;
import csg.data.CSGData;
import csg.data.RecitationData;
import csg.data.Recitations;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author patrickboucicault
 */


public class RecitationTab extends Tab{
    
    
    CourseSiteGeneratorAPP app;
    CSGData csgData;
    RecitationData data;
    
    VBox container = new VBox();
    VBox bottom = new VBox();
    VBox top = new VBox();
    
    
   TextField sectionText = new TextField();
   TextField instructorText = new TextField();
   TextField dayText = new TextField();
   TextField locationText = new TextField();
   
   ObservableList<TeachingAssistant> TAtableData;
   ObservableList<Recitations> recitationTableList;
   RecitationData recitationTableData;
   
    ComboBox TA1 = new ComboBox();
    ComboBox TA2 = new ComboBox();
    
    
        
    TableView<Recitations> recitationTable;
    
    RecitationController controller;
    
     Button add = new Button();
     Button clear = new Button();
     Button deleteKey = new Button("-");
     
     PropertiesManager props = PropertiesManager.getPropertiesManager();
    
    public RecitationTab(CourseSiteGeneratorAPP init){
        
        app = init;
        
        csgData = (CSGData) app.getDataComponent();
        data = csgData.getRecitationData();
        
        TA1.getItems().add(" ");
        TA2.getItems().add(" ");
        TA1.setValue(" ");
        TA2.setValue(" ");
        TAData taData = csgData.getTAData();
        recitationTableData = (RecitationData)csgData.getRecitationData();
        recitationTableList = recitationTableData.getRecitationList();
        
         String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_RECITATION_BUTTON_TEXT.toString());
         String clearButtonText = props.getProperty(CourseSiteGeneratorProp.CLEAR_BUTTON_TEXT.toString());
         String updateButtonText = props.getProperty(CourseSiteGeneratorProp.UPDATE_RECITATION_BUTTON_TEXT.toString());
        
         add.setText(addButtonText);
         clear.setText(clearButtonText);
         
        TAtableData = taData.getTeachingAssistants();
        
        TA1.getItems().add("");
        TA2.getItems().add("");
        
        for (int i = 1; i < TAtableData.size(); i++) {
            
            TA1.getItems().add(TAtableData.get(i).getName());
            TA2.getItems().add(TAtableData.get(i).getName());
        }
        
        HBox first = new HBox();
        
        Label recitations = new Label("Recitations");
        
        recitations.setStyle("-fx-font: 30 arial;");
        first.setSpacing(10);
        
        first.getChildren().add(recitations);
        first.getChildren().add(deleteKey);
        
        buildTop();
        buildBottom();
        
       
        
        container.setStyle("-fx-background-color: #4286f4;");
        container.getChildren().add(first);
        container.getChildren().add(top);
        container.getChildren().add(bottom);
        
        
        
        

        WorkSpace workspace = (WorkSpace) app.getWorkspaceComponent();
        
        this.setContent(container); 
        
        controller = new RecitationController(app,this);
        
        add.setOnAction(e->{
            
            controller.handleAddRecitation();
        });
        
        recitationTable.setOnMouseClicked(e ->{
            
            controller.handleRecitationClicked();
        });
        
        clear.setOnAction(e ->{
                
            controller.handleClearButtonClicked();
        });
        
        deleteKey.setOnAction(e ->{
             
            controller.handleRemoveRecitation();
        });
        
        recitationTable.setOnKeyPressed(e ->{
            
            if(e.getCode() == (KeyCode.BACK_SPACE)){
               
                controller.handleRemoveRecitation();
            }
        });
        
       
        
        
        
        
        
        
       
    }
    
     public void setTAList(){
         setTAList(TA1,TA2,TAtableData);
     } 
     
     private void setTAList(ComboBox TA1,ComboBox TA2,ObservableList<TeachingAssistant> tableData ){
   
         TA1.getItems().clear();
         TA2.getItems().clear();
         TA1.getItems().add(" ");
         TA2.getItems().add(" ");
         TA1.setValue(" ");
         TA2.setValue(" ");
        
        for (int i = 0; i < tableData.size(); i++) {
            
            TA1.getItems().addAll(tableData.get(i).getName());
            TA2.getItems().addAll(tableData.get(i).getName());
            
           

        }
      
        
    }
    
    public void buildTop(){ 
        
    recitationTable = new TableView();
    recitationTable.setItems(recitationTableList);
   
    TableColumn<Recitations, String> sectionColumn = new TableColumn("Section");
    TableColumn<Recitations, String> instructorColumn = new TableColumn("Instructor");
    TableColumn<Recitations, String> dayColumn =  new TableColumn("Day/Time");
    TableColumn<Recitations, String> locationColumn =  new TableColumn("Location");
    TableColumn<Recitations, String> TAColumn1 =  new TableColumn("TA");
    TableColumn<Recitations, String> TAColumn2 =  new TableColumn("TA");
    
    
    
    sectionColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(0.1));
    instructorColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(0.1));
    dayColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(0.3));
    locationColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(0.2));
    TAColumn1.prefWidthProperty().bind(recitationTable.widthProperty().multiply(0.15));
    TAColumn2.prefWidthProperty().bind(recitationTable.widthProperty().multiply(0.15));

    sectionColumn.setResizable(false);
    instructorColumn.setResizable(false);
    dayColumn.setResizable(false);
    locationColumn.setResizable(false);
    TAColumn1.setResizable(false);
    TAColumn2.setResizable(false);
    

    sectionColumn.setCellValueFactory(
                new PropertyValueFactory<Recitations, String>("section")
        );
    
    instructorColumn.setCellValueFactory(
                new PropertyValueFactory<Recitations, String>("instructor")
        );
    
    dayColumn.setCellValueFactory(
                new PropertyValueFactory<Recitations, String>("day")
        );
    
    locationColumn.setCellValueFactory(
                new PropertyValueFactory<Recitations, String>("location")
        );
    TAColumn1.setCellValueFactory(
                new PropertyValueFactory<Recitations, String>("TA1")
        );
    
    TAColumn2.setCellValueFactory(
                new PropertyValueFactory<Recitations, String>("TA2")
        );
    
    recitationTable.getColumns().add(sectionColumn);
    recitationTable.getColumns().add(instructorColumn);
    recitationTable.getColumns().add(dayColumn);
    recitationTable.getColumns().add(locationColumn);
    recitationTable.getColumns().add(TAColumn1);
    recitationTable.getColumns().add(TAColumn2);
     

    top.getChildren().add(recitationTable);
    
  }
    
    public void buildBottom(){

        Label addEdit = new Label("Add/Edit");
        addEdit.setStyle("-fx-font: 18 arial;");
        
 
        HBox row1 = new HBox();
        HBox row2 = new HBox();
        HBox row3 = new HBox();
        HBox row4 = new HBox();
        HBox row5 = new HBox();
        HBox row6 = new HBox();
        HBox row7 = new HBox();
        
        row1.setSpacing(58);
        row2.setSpacing(35);
        row3.setSpacing(50);
        row4.setSpacing(50);
        row5.setSpacing(50);
        row6.setSpacing(50);
        row7.setSpacing(50);
        
        Label section = new Label("Section:");
        Label instructor = new Label("Instructor:");
        Label day = new Label("Day/Time:");
        Label location = new Label("Location:");
        Label supervisingTA1 = new Label("Supervising TA:");
        Label supervisingTA2 = new Label("Supervising TA:");
        
    
        
        
         sectionText.setMinSize(160,20);
         instructorText.setMinSize(160,20);
         dayText.setMinSize(160,20);
         locationText.setMinSize(160,20);
        
        row1.getChildren().add(section);
        row1.getChildren().add(sectionText);
        
        row2.getChildren().add(instructor);
        row2.getChildren().add(instructorText);
        
        row3.getChildren().add(day);
        row3.getChildren().add(dayText);
        
        row4.getChildren().add(location);
        row4.getChildren().add(locationText);
        
        row5.getChildren().add(supervisingTA1);
        row5.getChildren().add(TA1);
        
        row6.getChildren().add(supervisingTA2);
        row6.getChildren().add(TA2);
        
        row7.getChildren().add(add);
        row7.getChildren().add(clear);
        
        bottom.setStyle("-fx-background-color: #6d9ded;");
        bottom.getChildren().add(addEdit);
        
        bottom.getChildren().add(row1);
        bottom.getChildren().add(row2);
        bottom.getChildren().add(row3);
        bottom.getChildren().add(row4);
        bottom.getChildren().add(row5);
        bottom.getChildren().add(row6);
        bottom.getChildren().add(row7);
        
        bottom.setPadding(new Insets(5, 20, 5, 10));
        
    }
    
    
    
   public RecitationController getRecitationController(){
       return controller;
   }
    
   public Button getClearButton(){
       return clear;
   }
   
    public Button getAddButton(){
       return add;
   }
   
   public TextField getInstructorText(){
       return instructorText;
   }
   public TextField getDayText(){
       return dayText;
   }
    public TextField getLocationText(){
       return locationText;
   }
    public TextField getSectionText(){
       return sectionText;
   }
    public TableView getTable(){
       return recitationTable;
    }
    public ComboBox getTA1(){
       return TA1;
   }
    public ComboBox getTA2(){
       return TA2;
   }
}
