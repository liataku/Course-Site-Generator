/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import coursesitegenerator.CourseSiteGeneratorAPP;
import csg.controllers.ScheduleController;
import csg.data.CSGData;
import csg.data.Recitations;
import csg.data.ScheduleData;
import csg.data.Schedules;
import csg.data.TeachingAssistant;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author patrickboucicault
 */
public class ScheduleTab extends Tab{
    
    CourseSiteGeneratorAPP app;
    
    VBox top = new VBox();
    VBox bottom = new VBox();
    VBox container = new VBox();
    VBox tableBox = new VBox();
    VBox midContainer = new VBox();
    
    HBox topContainer = new HBox();
    HBox left = new HBox();
    HBox right = new HBox();
    
    DatePicker mondayPicker = new DatePicker();
    DatePicker fridayPicker = new DatePicker();
    
    DatePicker bottomDate = new DatePicker();
    
    boolean isMonday = false;
    boolean isFriday = false;
    boolean mondayIsValid = false;
    boolean fridayIsValid = false;
    Button addButton = new Button("Add Schedule");
    Button clearButton = new Button("Clear");
    Button deleteButton = new Button("-");
    
    
    TextField timeField = new TextField();
    TextField titleField = new TextField();
    TextField topicField = new TextField();
    TextField linkField = new TextField();
    TextField criteriaField = new TextField();
    
    TableView<Schedules> scheduleTable;
    
    ComboBox comboBox;
    
    //TableView<Schedules> taTable;
    
    public ScheduleTab(CourseSiteGeneratorAPP init){
        
        app = init;
        
        
        
        CSGData csgData = (CSGData)app.getDataComponent();
        ScheduleData scheduleData = csgData.getScheduleData();
        ObservableList scheduleList = scheduleData.getScheduleList();
        scheduleTable = new TableView();
        scheduleTable.setItems(scheduleList);
        
        buildTop();
        buildMid();
        buildBottom();
        
        //mondayPicker.setOnAction(value);
        
        container.setPadding(new Insets(10, 5, 10, 5));
        container.setStyle("-fx-background-color: #4286f4;");
       
        container.getChildren().add(top);
        
        
        
        container.getChildren().add(midContainer);
        
        container.getChildren().add(bottom);
        
        
        ScheduleController controller = new ScheduleController(app,this);
        
        mondayPicker.setOnAction(e->{
            controller.handleMondayPicker();
        });
        
        fridayPicker.setOnAction(e->{
            controller.handleFridayPicker();
        });
        this.setContent(container);
        
        bottomDate.setOnAction(e->{
            controller.handleDatePicker();
        });
        
        addButton.setOnAction(e->{
            controller.handleAddSchedule();
        });
        
        scheduleTable.setOnMouseClicked(e->{
            controller.handleScheduleClicked();
        });
        
        clearButton.setOnAction(e->{
            controller.handleClearButton();
        });
        
        deleteButton.setOnAction(e->{
            controller.handleRemoveSchedule();
        });
        scheduleTable.setOnKeyPressed(e->{
            
             if(e.getCode() == (KeyCode.BACK_SPACE)){
               
                controller.handleRemoveSchedule();
            }
        });
        
        
        
    }
    
    public void buildTop(){
        
    top.setStyle("-fx-background-color: #6d9ded;");
        

    Label starting = new Label("Starting Monday:");
    Label ending = new Label("Ending Friday:");
    
    Label calendarB = new Label("Calendar Boundries");
    calendarB.setStyle("-fx-font: 14 arial;");
    
    left.getChildren().add(starting);
    left.getChildren().add(mondayPicker);
    left.setSpacing(10);
    
    right.getChildren().add(ending);
    right.getChildren().add(fridayPicker);
    right.setSpacing(10);
    
    topContainer.getChildren().add(left);
    topContainer.getChildren().add(right);
    topContainer.setSpacing(30);
    
    topContainer.setPadding(new Insets(10, 5, 10, 5));
    
    top.getChildren().add(calendarB);
    top.getChildren().add(topContainer);
    top.setPadding(new Insets(10, 5, 10, 5));
    
    
    }
    
    public void buildMid(){
        
    
   
    TableColumn<Schedules, String> typeColumn = new TableColumn("Type");
    TableColumn<Schedules, String> dateColumn = new TableColumn("Date");
    TableColumn<Schedules, String> titleColumn =  new TableColumn("Title");
    TableColumn<Schedules, String> topicColumn =  new TableColumn("Topic");
   
    
    
    typeColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(0.2));
    dateColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(0.2));
    titleColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(0.3));
    topicColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(0.3));
    
    typeColumn.setResizable(false);
    dateColumn.setResizable(false);
    titleColumn.setResizable(false);
    topicColumn.setResizable(false);
   
    
    
   
    
    typeColumn.setCellValueFactory(
                new PropertyValueFactory<Schedules, String>("Type")
        );
    
    dateColumn.setCellValueFactory(
                new PropertyValueFactory<Schedules, String>("Date")
        );
    
    titleColumn.setCellValueFactory(
                new PropertyValueFactory<Schedules, String>("Title")
        );
    
    topicColumn.setCellValueFactory(
                new PropertyValueFactory<Schedules, String>("Topic")
        );
    
    
    scheduleTable.getColumns().add(typeColumn);
    scheduleTable.getColumns().add(dateColumn);
    scheduleTable.getColumns().add(titleColumn);
    scheduleTable.getColumns().add(topicColumn);
    
     

    
    
    
     Label scheduleText = new Label("Schedule");
     scheduleText.setStyle("-fx-font: 22 arial;");
        
     Label scheduleItems = new Label("Schedule Items");
     scheduleItems.setStyle("-fx-font: 15 arial;");
        
     
        
     HBox midTop = new HBox();
        
     midTop.getChildren().add(scheduleItems);
     midTop.getChildren().add(deleteButton);
     
     tableBox.getChildren().add(scheduleTable);
     
     
     midContainer.getChildren().add(midTop);
     midContainer.getChildren().add(tableBox);
     
     
    
    
    
    
  }
    
    
    public void buildBottom(){
        
        Label addEdit = new Label("Add/Edit");
        
        Label type = new Label("Type:");
        Label date = new Label("Date:");
        Label time = new Label("Time:");
        Label title = new Label("Title:");
        Label topic = new Label("Topic");
        Label link = new Label("Link:");
        Label criteria = new Label("Criteria:");
        
     
        
         timeField.setMinSize(200,20);
         titleField.setMinSize(200,20);
         topicField.setMinSize(200,20);
         linkField.setMinSize(200,20);
         criteriaField.setMinSize(200,20);
        
        HBox row1 = new HBox();
        HBox row2 = new HBox();
        HBox row3= new HBox();
        HBox row4 = new HBox();
        HBox row5 = new HBox();
        HBox row6 = new HBox();
        HBox row7 = new HBox();
        HBox row8 = new HBox();
        
        ObservableList<String> options = 
    FXCollections.observableArrayList(
        "",
        "Holiday",
        "Lecture",
        "Hws",
        "Recitation"
                
    );
        
        

        
        
        
       comboBox = new ComboBox(options);
        
 
        row1.getChildren().add(type);
        row1.getChildren().add(comboBox);
        row1.setSpacing(81);
        
        row2.getChildren().add(date);
        row2.getChildren().add(bottomDate);
        row2.setSpacing(81);
        
        row3.getChildren().add(time);
        row3.getChildren().add(timeField);
        row3.setSpacing(81);
        
        
        row4.getChildren().add(title);
        row4.getChildren().add(titleField);
        row4.setSpacing(73);
        
        row5.getChildren().add(topic);
        row5.getChildren().add(topicField);
        row5.setSpacing(81);
        
        row6.getChildren().add(link);
        row6.getChildren().add(linkField);
        row6.setSpacing(81);
        
        row7.getChildren().add(criteria);
        row7.getChildren().add(criteriaField);
        row7.setSpacing(50);
        
        row8.getChildren().add(addButton);
        row8.getChildren().add(clearButton);
        row8.setSpacing(20);
        
        bottom.getChildren().add(addEdit);
        bottom.getChildren().add(row1);
        bottom.getChildren().add(row2);
        bottom.getChildren().add(row3);
        bottom.getChildren().add(row4);
        bottom.getChildren().add(row5);
        bottom.getChildren().add(row6);
        bottom.getChildren().add(row7);
        bottom.getChildren().add(row8);
        
        bottom.setSpacing(5);
             
        
    }
    
     /*if(mondayPicker.getValue().getDayOfWeek().getValue() == 1){
        System.out.println("ot");
    }*/
    
 
    
 
    public TextField getTimeField(){
        return timeField;
    }
    
    public TextField getTitleField(){
        return titleField;
    }
    
    public TextField getTopicField(){
        return topicField;
    }
    
    public TextField getLinkField(){
        return linkField;
    }
    
    public TextField getCriteriaield(){
        return criteriaField;
    }
    public DatePicker getMondayPicker(){
        return mondayPicker;
    }
    
     public DatePicker getFridayPicker(){
        return fridayPicker;
    }
     
    public Boolean getIsMonday(){
        return isMonday;
    }
    public Boolean getIsFriday(){
        return isFriday;
    }
    
    public void setMonday(boolean t){
        isMonday = t;
    }
    
    public void setFriday(boolean t){
        isFriday = t;
    }
    
    public DatePicker getBottomDate(){
        return bottomDate;
    }
     
    
    public void setMondayIsValid(boolean t){
        mondayIsValid = t;
    }
    
    public void setFridayIsValid(boolean t){
        fridayIsValid = t;
    }
    
    public boolean getIsDateVaid(){
        return mondayIsValid && fridayIsValid;
    }
     
      
    
    public Button getAddButton(){
        return addButton;
    }
    
    public Button getClearButton(){
        return clearButton;
    }
    
    public ComboBox getTypeBox(){
        return comboBox;
    }
    
    public TableView getTable(){
        return scheduleTable;
    }
    
  
    
    
   
    
}