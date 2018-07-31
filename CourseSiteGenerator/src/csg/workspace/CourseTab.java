/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import coursesitegenerator.CourseSiteGeneratorAPP;
import csg.controllers.CourseController;
import csg.controllers.RecitationController;
import csg.data.SitePages;
import csg.data.TeachingAssistant;
import csg.data.Teams;
import java.awt.Font;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author patrickboucicault
 */
public class CourseTab extends Tab {
    

    
    CourseSiteGeneratorAPP app;
    RecitationController rController;
    TableColumn<SitePages,Boolean> checkBoxColumn;
    
     BorderPane course = new BorderPane();
     VBox container = new VBox();
  
     VBox topPane = new VBox();
     VBox midPane = new VBox();
     VBox bottomPane = new VBox();
     HBox midPaneContainer = new HBox();
     
     Label temDir = new Label(); 
     TableView<SitePages> courseTable;
     ObservableList<SitePages> sitePageList;
     Label directoryLabel = new Label();
     
       ComboBox comboBox1;
        
       ComboBox comboBox2;
        
        
       ComboBox comboBox3;
        
       ComboBox comboBox4; 
       
     Button temButton = new Button("Selected Template Directory");
     Button change = new Button("Change");
     
         ObservableList<String> subjectOptions = FXCollections.observableArrayList(
        "CSE",
        "AMS",
        "MAT"
    );
         
            ObservableList<String> semesterOptions = FXCollections.observableArrayList(
        "Spring",
        "Fall",
        "Summer"
    );
            
               ObservableList<String> numberOptions = FXCollections.observableArrayList(
        "114",
        "214",
        "215",
        "308",
        "219,",
        "220",
        "320",
        "211",
        "373",
        "310",
        "301"
                
    );
               
                  ObservableList<String> yearOptions = FXCollections.observableArrayList(
        "2016",
        "2017",
        "2018",
        "2019",
        "2020"
    );
                  
                                  ObservableList<String> sheetOptions = FXCollections.observableArrayList(
        "sea_wolf.css",
        "course_hompage_layout.css"
        
    );
    
    public CourseTab(CourseSiteGeneratorAPP init){
        
        app = init;
        
        CourseController controller = new CourseController(app,this);
        topPane.setStyle("-fx-background-color:  #4d84f2");
        midPane.setStyle("-fx-background-color:  #4d84f2");
        bottomPane.setStyle("-fx-background-color:  #4d84f2;");
  
        buildTop();
        buildMid();
        buildBottom();
        
        
        container.getChildren().add(topPane);
        container.getChildren().add(midPaneContainer);
        container.getChildren().add(bottomPane);
        
        
        container.setStyle("-fx-background-color: #618ee8;");
        container.setPadding(new Insets(10, 5, 10, 5));
        container.setPrefSize(5, 50);
        container.setSpacing(5);
        
        course.setCenter(container);
        course.setPrefSize(5, 50);
        
        temButton.setOnAction(e->{
            controller.handleSelectTemplateDirectory();
        });
        change.setOnAction(e->{
            controller.handleChangeButton();
        });
        
        app.getGUI().getExportButton().setOnAction(e->{
            controller.handleExport();
        });
        
        
        this.setContent(course);
        
        
        
        //controller = new recitationController()
        
    }
    
    private void buildTop(){
        
        //topPane.setStyle("-fx-background-color: #16f4e2;");
        
         //Creates all the labels needed for the top pane
        Label subject = new Label("Subject:");
        Label semester = new Label("Semester:");
        Label title = new Label("Title:");
        Label instructorName = new Label("Instructor Name:");
        Label instructorHome = new Label("Instructor Home");
        Label export = new Label("Export Dir:");
       
        Label cInfo = new Label("Course Info");
        Label number = new Label("Number:");
        Label year = new Label("Year:");
        
        
        
        
        
        
     
        comboBox1 = new ComboBox(subjectOptions);
        
        comboBox2 = new ComboBox(numberOptions);
        
        
        comboBox3 = new ComboBox(semesterOptions);
        
        comboBox4 = new ComboBox(yearOptions);
        

        HBox firstRow = new HBox();
        HBox firstRow1 = new HBox();
        HBox firstRow2 = new HBox();
         
        firstRow1.getChildren().add(subject);
        firstRow1.getChildren().add(comboBox1);
        firstRow1.setSpacing(50);
        firstRow1.setPadding(new Insets(7, 5, 0, 5));
        
        firstRow2.getChildren().add(number);
        firstRow2.getChildren().add(comboBox2);
        firstRow2.setSpacing(50);
        firstRow2.setPadding(new Insets(8, 25, 0, 5));
        
        
        firstRow.getChildren().add(firstRow1);
        firstRow.getChildren().add(firstRow2);
        
        firstRow.setPadding(new Insets(8, 0, 0, 10));
        firstRow.setSpacing(70);
        
        //*******************************************************
        HBox secondRow = new HBox();
        HBox secondRow1 = new HBox();
        HBox secondRow2 = new HBox();
        
        secondRow1.getChildren().add(semester);
        secondRow1.getChildren().add(comboBox3);
        secondRow1.setSpacing(35);
        secondRow1.setPadding(new Insets(10, 0, 15, 10));
        
        secondRow2.getChildren().add(year);
        secondRow2.getChildren().add(comboBox4);
        secondRow2.setSpacing(62);
        secondRow2.setPadding(new Insets(10, 0, 15, 10));
        
        
        secondRow.getChildren().add(secondRow1);
        secondRow.getChildren().add(secondRow2);
        
        secondRow.setPadding(new Insets(5, 10, 0, 5));
        secondRow.setSpacing(80);
        
        
        //*******************************************************
        TextField titleField = new TextField();
        TextField nameField = new TextField();
        TextField homeField = new TextField();
        
        
        
        HBox thirdRow = new HBox();
        HBox thirdRow1 = new HBox();
        
        HBox fourthRow = new HBox();
        HBox fourthRow1 = new HBox();
        
        HBox fifthRow = new HBox();
        HBox fifthRow1 = new HBox();
        
        HBox sixthRow = new HBox();
        HBox sixthRow1 = new HBox();
      
        
        //*******************************************************
        
        titleField.setMinSize(412,24);
        
        thirdRow1.getChildren().add(title);
        thirdRow1.getChildren().add(titleField);
        
        thirdRow1.setPadding(new Insets(0, 0, 0, 10));
        thirdRow1.setSpacing(67);
        
        thirdRow.getChildren().add(thirdRow1);
        
        thirdRow.setPadding(new Insets(0, 0, 8, 5 ));
        thirdRow.setSpacing(10);
        
        
        nameField.setMinSize(340,24);
        //*******************************************************
        fourthRow1.getChildren().add(instructorName);
        fourthRow1.getChildren().add(nameField);
        
        fourthRow1.setPadding(new Insets(0, 0, 5, 10));
        fourthRow1.setSpacing(67);
        
        fourthRow.getChildren().add(fourthRow1);
        
        fourthRow.setPadding(new Insets(0, 0, 5, 5));
        fourthRow.setSpacing(10);
        
        
        homeField.setMinSize(220,24);
        VBox instructorBox = new VBox();
        //*******************************************************
        fifthRow1.getChildren().add(instructorHome);
        fifthRow1.getChildren().add(homeField);
        
        fifthRow1.setPadding(new Insets(5, 0, 5, 10));
        fifthRow1.setSpacing(69);
        
        fifthRow.getChildren().add(fifthRow1);
        
        fifthRow.setPadding(new Insets(5, 0, 5, 5));
        fifthRow.setSpacing(10);
        
        instructorBox.getChildren().add(thirdRow);
        instructorBox.getChildren().add(fourthRow);
        instructorBox.getChildren().add(fifthRow);
        instructorBox.setSpacing(0);
        //********************************************************
        
        sixthRow1.getChildren().add(export);
        sixthRow1.getChildren().add(directoryLabel);
        sixthRow1.getChildren().add(change);
        
        sixthRow1.setPadding(new Insets(5, 0, 5, 15));
        sixthRow1.setSpacing(20);
        
        sixthRow.getChildren().add(sixthRow1);
        
        
        
        topPane.getChildren().add(cInfo);
        topPane.getChildren().add(firstRow);
        topPane.getChildren().add(secondRow);
        //topPane.getChildren().add(thirdRow);
        //topPane.getChildren().add(fourthRow);
        //topPane.getChildren().add(fifthRow);
        topPane.getChildren().add(instructorBox);
        topPane.getChildren().add(sixthRow);
        
        
        topPane.setPadding(new Insets(5, 0, 10, 20));
        topPane.setSpacing(-5);
        
        topPane.setPrefSize(20, 20);
        
        cInfo.setStyle("-fx-font: 20 arial;");
        subject.setStyle("-fx-font: 14 arial;");
        title.setStyle("-fx-font: 14 arial;");
        instructorName.setStyle("-fx-font: 14 arial;");
        instructorHome.setStyle("-fx-font: 14 arial;");
        export.setStyle("-fx-font: 14 arial;");
        directoryLabel.setStyle("-fx-font: 14 arial;");
        semester.setStyle("-fx-font: 14 arial;");
        number.setStyle("-fx-font: 14 arial;");
        year.setStyle("-fx-font: 14 arial;");
        
    }
    
    
    public void buildMid(){
        
        HBox midContainer = new HBox();
        //******************************************************************************************
        Label siteTemplate = new Label("Site Template");
        Label text = new Label("The selected directory should contain the full site template"
                + ", including the HTML files");
       
        Label sitePages = new Label("Site Pages:");
        
       
        
        siteTemplate.setStyle("-fx-font: 20 arial;");
        text.setStyle("-fx-font: 11 arial;");
        temDir.setStyle("-fx-font: 14 arial;");
        sitePages.setStyle("-fx-font: 14 arial;");
       
        
        
        midPane.getChildren().add(siteTemplate);
        midPane.getChildren().add(text);
        midPane.getChildren().add(temDir);
        midPane.getChildren().add(temButton);
        midPane.getChildren().add(sitePages);
        
        midPane.setPadding(new Insets(5, 20, 5, 20));
        midPane.setSpacing(20);
        
        
        
        
        courseTable = new TableView();
        courseTable.setItems(sitePageList);
        
        courseTable.setPrefSize(840, 300);
        
        checkBoxColumn = new TableColumn("Use");
        checkBoxColumn.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<SitePages,Boolean>,ObservableValue<Boolean>>()
        {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SitePages, Boolean> param)
            {   
                return param.getValue().getIsUsed();
            }   
        });

        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
        
        TableColumn<SitePages, String> navbarColumn = new TableColumn("Navbar Title");
        TableColumn<SitePages, String> fileColumn = new TableColumn("File Name");
        TableColumn<SitePages, String> scriptColumn =  new TableColumn("Script");
        //TableColumn<SitePages, String> useColumn =  new TableColumn("Use");
        


        navbarColumn.prefWidthProperty().bind(courseTable.widthProperty().multiply(0.4));
        fileColumn.prefWidthProperty().bind(courseTable.widthProperty().multiply(0.3));
        scriptColumn.prefWidthProperty().bind(courseTable.widthProperty().multiply(0.3));
        //linkColumn.prefWidthProperty().bind(courseTable.widthProperty().multiply(0.2));
        

        navbarColumn.setResizable(false);
        fileColumn.setResizable(false);
        scriptColumn.setResizable(false);
        //linkColumn.setResizable(false);
        




        navbarColumn.setCellValueFactory(
                    new PropertyValueFactory<SitePages, String>("navbar")
            );

        fileColumn.setCellValueFactory(
                    new PropertyValueFactory<SitePages, String>("fileName")
            );

        scriptColumn.setCellValueFactory(
                    new PropertyValueFactory<SitePages, String>("script")
            );

       /* linkColumn.setCellValueFactory(
                    new PropertyValueFactory<SitePages, String>("Link")
            );*/
        
       
       checkBoxColumn.setEditable(true);
        courseTable.setEditable(true);
        courseTable.getColumns().add(checkBoxColumn);
        courseTable.getColumns().add(navbarColumn);
        courseTable.getColumns().add(fileColumn);
        courseTable.getColumns().add(scriptColumn);
      //  courseTable.getColumns().add(linkColumn);
        
        midPane.setSpacing(10);
        
        midPaneContainer.getChildren().add(midPane);
        midPaneContainer.getChildren().add(courseTable);
        
        midPaneContainer.setSpacing(5);
        
       // midPaneContainer.getChildren().add(midPane);
        
  
    }
    
    public void buildBottom(){
        
          //Creates all the labels needed for the top pane
          
        Label pageStyle = new Label("Page Style");
        Label banner = new Label("Banner School Image:");
        Label left = new Label("Left Footer Image:");
        Label right = new Label("Right Footer Image");
        Label styleSheet = new Label("Stylesheet");
        Label Note = new Label("Note:");
        Label text1 = new Label("");
        Label text2 = new Label("");
        Label text3 = new Label("");
        Label noteText = new Label("New stylesheets may be placed in work/css to be selectable");
        
        Button change1 = new Button("Change");
        Button change2 = new Button("Change");
        Button change3 = new Button("Change");
 
        ComboBox comboSheet = new ComboBox(sheetOptions);
     

        HBox row1 = new HBox();
        HBox row2 = new HBox();
        HBox row3 = new HBox();
        HBox row4 = new HBox();
        HBox row5 = new HBox();
         
        row1.getChildren().add(banner);
        row1.getChildren().add(text1);
        row1.getChildren().add(change1);
        
        row1.setSpacing(40);
        row1.setPadding(new Insets(5, 20, 5, 20));
        
        
         
        row2.getChildren().add(left);
        row2.getChildren().add(text2);
        row2.getChildren().add(change2);
        
        row2.setSpacing(40);
        row2.setPadding(new Insets(5, 20, 5, 20));
        
        
        row3.getChildren().add(right);
        row3.getChildren().add(text3);
        row3.getChildren().add(change3);
        
        row3.setSpacing(40);
        row3.setPadding(new Insets(5, 20, 5, 20));
        
        
        row4.getChildren().add(styleSheet);
        row4.getChildren().add(comboSheet);
       
        
        row4.setSpacing(40);
        row4.setPadding(new Insets(5, 20, 5, 20));
        
        
        row5.getChildren().add(Note);
        row5.getChildren().add(noteText);
       
        
        row5.setSpacing(40);
        row5.setPadding(new Insets(5, 20, 5, 20));
        
        
        
        bottomPane.getChildren().add(pageStyle);
        bottomPane.getChildren().add(row1);
        bottomPane.getChildren().add(row2);
        bottomPane.getChildren().add(row3);
        bottomPane.getChildren().add(row4);
        bottomPane.getChildren().add(row5);
        
        
        
        pageStyle.setStyle("-fx-font: 20 arial;");
        banner.setStyle("-fx-font: 14 arial;");
        left.setStyle("-fx-font: 14 arial;");
        right.setStyle("-fx-font: 14 arial;");
        styleSheet.setStyle("-fx-font: 14 arial;");
        Note.setStyle("-fx-font: 14 arial;");
        
    }
    
    public Button getTemplateButton(){
        return temButton;
    }
    
    public Label getTemDirectoryLabel(){
        return temDir;
    }
    
    public TableView getCourseTable(){
        return courseTable;
    }
    
    public Button getChangeButton(){
        return change;
    }
    
    public Label getDirectoryLabel(){
        return directoryLabel;
    }
    
    public String getSubject(){
       return (String) comboBox1.getValue();
    }
    
    public String getNumber(){
       return (String) comboBox2.getValue();
    }
    
    public String getSemester(){
       return (String) comboBox3.getValue();
    }
    public String getYear(){
       return (String) comboBox4.getValue();
    }
    
    
    
}
