/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import coursesitegenerator.CourseSiteGeneratorAPP;
import csg.controllers.ProjectController;
import csg.data.CSGData;
import csg.data.ProjectData;
import csg.data.RecitationData;
import csg.data.Recitations;
import csg.data.Students;
import csg.data.Teams;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
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
public class ProjectTab extends Tab{
    
    CourseSiteGeneratorAPP app;
    
    PropertiesManager props = PropertiesManager.getPropertiesManager();
 
    
 
    TableView<Teams> projectTable = new TableView();
    TableView<Students> studentTable = new TableView();
     
    
    VBox projects = new VBox();
    VBox top = new VBox();
    HBox team = new HBox();
    HBox tableBox = new HBox();
    VBox bottomOfTop = new VBox();
    HBox nameField = new HBox();
    HBox colorHolder = new HBox();
    HBox leftColor = new HBox();
    HBox rightColor = new HBox();
    HBox linkField = new HBox();
    HBox buttons = new HBox();
    
    
    VBox bottomBox = new VBox();
  
    
     Button addTeamButton = new Button("Add Team");
     Button clearTeamButton = new Button("Clear");
     
     Button addStudentButton = new Button("Add Student");
     Button clearStudentButton = new Button("Clear");
     
     TextField nameTextField = new TextField();
     TextField linkTextField = new TextField();
        
        
           
    ColorPicker one = new ColorPicker();
    ColorPicker two = new ColorPicker();
        
    TextField firstNameTextField = new TextField();
    TextField lastNameTextField = new TextField();
    ComboBox teamComboBox = new ComboBox();
    TextField roleTextField = new TextField();
    
    Button deleteTeamButton = new Button("-");
    Button deleteStudentButton = new Button("-");
    
    
    public ProjectTab(CourseSiteGeneratorAPP init){
        
        app = init;
        
        Label topProject = new Label("Projects");
        topProject.setStyle("-fx-font: 30 arial;");
        
        CSGData csgData = (CSGData) app.getDataComponent();
        ProjectData projectData = (ProjectData)csgData.getProjectData();
    
        ObservableList<Students> studentList = projectData.getStudentList(); 
        ObservableList<Teams> teamList = projectData.getTeamList();
        
        studentTable.setItems(studentList);
        projectTable.setItems(teamList);
        teamComboBox.getItems().add(" ");
        teamComboBox.setValue("");
        for(int i = 0; i < teamList.size();i++){
            
            teamComboBox.getItems().add(teamList.get(i).getName());
        }
        
        
        buildTop();
        buildBottom();
        
        projects.getChildren().add(topProject);
        projects.getChildren().add(top);
        projects.getChildren().add(bottomBox);
        
        
        ProjectController controller = new ProjectController(app,this);
        
        addStudentButton.setOnAction(e ->{
            controller.handleAddStudent();
        });
        
        studentTable.setOnMouseClicked(e->{
            controller.handleStudentClicked();
        });
        
        clearStudentButton.setOnAction(e->{
            controller.handleStudentClearButtonClicked();
        });
        
        deleteStudentButton.setOnAction(e ->{
             
            controller.handleRemoveStudent();
        });
        
        studentTable.setOnKeyPressed(e ->{
            
            if(e.getCode() == (KeyCode.BACK_SPACE)){
               
                controller.handleRemoveStudent();
            }
        });
        
        addTeamButton.setOnAction(e->{
            controller.handleAddTeam();
        });
        
        projectTable.setOnMouseClicked(e->{
            controller.handleTeamClicked();
        });
        
        clearTeamButton.setOnAction(e->{
            controller.handleTeamClearButtonClicked();
        });
        
        deleteTeamButton.setOnAction(e ->{
             
            controller.handleRemoveTeam();
        });
        
        projectTable.setOnKeyPressed(e ->{
            
            if(e.getCode() == (KeyCode.BACK_SPACE)){
               
                controller.handleRemoveTeam();
            }
        });
        this.setContent(projects);
        
    }
    
    public void buildTop(){
        
        
        Label teamText = new Label("Teams");
        teamText.setStyle("-fx-font: 12 arial;");
        team.getChildren().add(teamText);
        team.getChildren().add(deleteTeamButton);
        
        Label nameLabel = new Label("Name:");
        Label colorLabel = new Label("Color:");
        Label linkLabel = new Label("Link:");
        Label colorLabel2 = new Label("Text Color");
        
       
        
        
     
        
        one.setPrefSize(100, 40);
        
       

        TableColumn<Teams, String> nameColumn = new TableColumn("Name");
        TableColumn<Teams, String> colorColumn = new TableColumn("Color");
        TableColumn<Teams, String> textColorColumn =  new TableColumn("Text Color");
        TableColumn<Teams, String> linkColumn =  new TableColumn("Link");
        
        projectTable.setPrefSize(150, 400);

        nameColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.2));
        colorColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.2));
        textColorColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.3));
        linkColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.3));
        

        nameColumn.setResizable(false);
        colorColumn.setResizable(false);
        textColorColumn.setResizable(false);
        linkColumn.setResizable(false);
        




        nameColumn.setCellValueFactory(
                    new PropertyValueFactory<Teams, String>("name")
            );

        colorColumn.setCellValueFactory(
                    new PropertyValueFactory<Teams, String>("color")
            );

        textColorColumn.setCellValueFactory(
                    new PropertyValueFactory<Teams, String>("textColor")
            );

        linkColumn.setCellValueFactory(
                    new PropertyValueFactory<Teams, String>("link")
            );
        
        projectTable.getColumns().add(nameColumn);
        projectTable.getColumns().add(colorColumn);
        projectTable.getColumns().add(textColorColumn);
        projectTable.getColumns().add(linkColumn);
        
        tableBox.getChildren().add(projectTable);
       
        Label addEdit = new Label("Add/Edit");
        
       
        
        nameField.getChildren().add(nameLabel);
        nameField.getChildren().add(nameTextField);
        
       
        leftColor.getChildren().add(colorLabel);
        leftColor.getChildren().add(one);
        
        rightColor.getChildren().add(colorLabel2);
        rightColor.getChildren().add(two);
        
        linkField.getChildren().add(linkLabel);
        linkField.getChildren().add(linkTextField);
        
        colorHolder.getChildren().add(leftColor);
        colorHolder.getChildren().add(rightColor);
        
        buttons.getChildren().add(addTeamButton);
        buttons.getChildren().add(clearTeamButton);
        
        bottomOfTop.getChildren().add(nameField);
        bottomOfTop.getChildren().add(colorHolder);
        bottomOfTop.getChildren().add(linkField);
        bottomOfTop.getChildren().add(buttons);
        
        bottomOfTop.setSpacing(5);
        
        top.getChildren().add(team);
        top.getChildren().add(projectTable);
        top.getChildren().add(addEdit);
        top.getChildren().add(bottomOfTop);
        top.setSpacing(5);
    }
    
    
    
    
    
    
    private void buildBottom(){
        
        
        HBox studentBox = new HBox();
        HBox studentTableBox = new HBox();
        VBox infoFieldsBox = new VBox();
        HBox firstBox = new HBox();
        HBox lastBox = new HBox();
        HBox teamFieldBox = new HBox();
        HBox roleBox = new HBox();
        HBox bottomButtonsBox = new HBox();
        
        
        
        Label studentText = new Label("Students");
        studentText.setStyle("-fx-font: 12 arial;");
        
        studentBox.getChildren().add(studentText);
        studentBox.getChildren().add(deleteStudentButton);
        
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label bottomTeamLabel = new Label("Team:");
        Label roleLabel = new Label("Role");
        

        //studentTable.setPrefSize(30, 10);

        TableColumn<Students, String> firstNameColumn = new TableColumn("First Name");
        TableColumn<Students, String> lastNameColumn = new TableColumn("Last Name");
        TableColumn<Students, String> teamColumn =  new TableColumn("Team");
        TableColumn<Students, String> roleColumn =  new TableColumn("Role");
        


        firstNameColumn.prefWidthProperty().bind(studentTable.widthProperty().multiply(0.3));
        lastNameColumn.prefWidthProperty().bind(studentTable.widthProperty().multiply(0.3));
        teamColumn.prefWidthProperty().bind(studentTable.widthProperty().multiply(0.3));
        roleColumn.prefWidthProperty().bind(studentTable.widthProperty().multiply(0.1));
        

        firstNameColumn.setResizable(false);
        lastNameColumn.setResizable(false);
        teamColumn.setResizable(false);
        roleColumn.setResizable(false);
        




        firstNameColumn.setCellValueFactory(
                    new PropertyValueFactory<Students, String>("firstName")
            );

        lastNameColumn.setCellValueFactory(
                    new PropertyValueFactory<Students, String>("lastName")
            );

        teamColumn.setCellValueFactory(
                    new PropertyValueFactory<Students, String>("Team")
            );

        roleColumn.setCellValueFactory(
                    new PropertyValueFactory<Students, String>("Role")
            );
        
        studentTable.getColumns().add(firstNameColumn);
        studentTable.getColumns().add(lastNameColumn);
        studentTable.getColumns().add(teamColumn);
        studentTable.getColumns().add(roleColumn);
        
       
       
        
        //studentTableBox.getChildren().add(studentTable);
               
        Label addEdit = new Label("Add/Edit");
        
        firstBox.getChildren().add(firstNameLabel);
        firstBox.getChildren().add(firstNameTextField);
        
        
        lastBox.getChildren().add(lastNameLabel);
        lastBox.getChildren().add(lastNameTextField);
        
        teamFieldBox.getChildren().add(bottomTeamLabel);
        teamFieldBox.getChildren().add(teamComboBox);
        
        roleBox.getChildren().add(roleLabel);
        roleBox.getChildren().add(roleTextField);
        
        bottomButtonsBox.getChildren().add(addStudentButton);
        bottomButtonsBox.getChildren().add(clearStudentButton);
        
        
        
      
        
        
        Label addEditText = new Label("Add/Edit:");
        
        
        
        infoFieldsBox.getChildren().add(firstBox);
        infoFieldsBox.getChildren().add(lastBox);
        infoFieldsBox.getChildren().add(teamFieldBox);
        infoFieldsBox.getChildren().add(roleBox);
        infoFieldsBox.getChildren().add(bottomButtonsBox);
        
        
        
       
        bottomBox.getChildren().add(studentBox);
        bottomBox.getChildren().add(studentTable);
        bottomBox.getChildren().add(addEditText);
        bottomBox.getChildren().add(infoFieldsBox);
        bottomBox.setPadding(new Insets(5, 10, 5, 10));
        
    }
    
    
    public Button getTeamClearButton(){
       return clearTeamButton;
   }
   
    public Button getAddTeamButton(){
       return addTeamButton;
   }
    
     public Button getClearStudentButton(){
       return clearStudentButton;
   }
   
    public Button getAddStudentButton(){
       return addStudentButton;
   }
   
   public TextField getNameText(){
       return nameTextField;
   }
   public TextField linkTextField(){
       return linkTextField;
   }
    public ColorPicker getColorPickerOne(){
       return one;
   }
    public ColorPicker getColorPickerTwo(){
       return two;
   }
    public TextField getFirstNameText(){
       return firstNameTextField;
    }
    public TextField getLastNameTextField(){
       return lastNameTextField;
   }
    public ComboBox getTeamComboBox(){
       return teamComboBox;
   }
    
    public TextField getRoleTextField(){
       return roleTextField;
    }
    
    public Button getDeleteTeamButton(){
        return deleteTeamButton;
    }
    
     public Button getDeleteStudentButton(){
        return deleteStudentButton;
    }
     
     public TableView getStudentTable(){
         
         return studentTable;
     }
    
      public TableView getTeamTable(){
         
         return projectTable;
     }

      public double getRedOne(Teams team){
          return one.getValue().getRed();
      }
      public double getBlueOne(Teams team){
          return one.getValue().getBlue();
      }
      public double getGreenOne(Teams team){
          return one.getValue().getGreen();
      }
      
      
      
      public double getRedTwo(Teams team){
          return two.getValue().getRed();
      }
      public double getBlueTwo(Teams team){
          return two.getValue().getBlue();
      }
      public double getGreenTwo(Teams team){
          return two.getValue().getGreen();
      }
    
}
