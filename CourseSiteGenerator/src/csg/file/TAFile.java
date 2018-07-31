/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.file;

import coursesitegenerator.CourseSiteGeneratorAPP;
import csg.data.CSGData;
import csg.data.CourseData;
import csg.data.ProjectData;
import csg.data.RecitationData;
import csg.data.Recitations;
import csg.data.ScheduleData;
import csg.data.Schedules;
import csg.data.Students;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.data.Teams;
import csg.workspace.CourseTab;
import csg.workspace.WorkSpace;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author patrickboucicault
 */
public class TAFile implements AppFileComponent{
    
      // THIS IS THE APP ITSELF
   CourseSiteGeneratorAPP app;
    
    // THESE ARE USED FOR IDENTIFYING JSON TYPES
   
    //TAData
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    static final String JSON_NAME = "name";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_GRAD_TAS = "grad_tas";
    static final String JSON_EMAIL = "email";
    
    //Course
    static final String JSON_Course_Subject = "subject";
    static final String JSON_Course_Number = "number";
    static final String JSON_Course_Semester = "semester";
    static final String JSON_Course_Year = "year";
    static final String JSON_Course_Title = "title";
    static final String JSON_Course_Instructor_Name = "Instructor Name";
    static final String JSON_Course_Instructor_Home = "Instructor Home";
    static final String JSON_Export_Dir = "Export Dir";
    
    static final String JSON_Course_Use = "Use";
    static final String JSON_Course_Navbar_Title = "Navbar Title";
    static final String JSON_Course_File_Name = "File Name";
    static final String JSON_Course_Script = "Script";
    static final String JSON_Course_StyleSheet = "StyleSheet";
    
    //Recitation
    static final String JSON_Recitation_Section = "section";
    static final String JSON_Recitation_Instructor = "instructor";
    static final String JSON_Recitation_DayTime = "day_time";
    static final String JSON_Recitation_Location= "location";
    static final String JSON_Recitation_TA1= "ta_1";
    static final String JSON_Recitation_TA2 = "ta_2";
    static final String JSON_Recitation_Data = "recitations";
    
    
    //Schedule
    static final String JSON_Schedule_Type = "Type";
    static final String JSON_Schedule_Date = "Date";
    static final String JSON_Schedule_Title = "Schedule_Title";
    static final String JSON_Schedule_Topic = "Topic";
    static final String JSON_Schedule_Link = "Link";
    static final String JSON_Schedule_Criteria = "Criteria";
    static final String JSON_Schedule_Time = "Time";
    static final String JSON_Schedule_Data = "ScheduleData";
    
    //Project
    static final String JSON_Projects_Name = "Project_Name";
    static final String JSON_Projects_Color = "Project_Color"; 
    static final String JSON_Projects_Text_Color = "Project_Text_Color";
    static final String JSON_Projects_Link = "Project_Link";
    static final String JSON_Projects_First_Name = "Project_First_Name";
    static final String JSON_Projects_Last_Name = "Project_Last_Name";
    static final String JSON_Projects_Role = "Project_Role";
    static final String JSON_Projects_Team_List = "Project_Team_List";
    static final String JSON_Student_Data = "Student_Data";
    static final String JSON_Team_Data = "Team_Data";
    
    //Team
    static final String JSON_Team_Name = "name";         
    static final String JSON_Color_Red = "red"; 
    static final String JSON_Color_Green = "green";
    static final String JSON_Color_Blue = "blue";
    static final String JSON_Team = "teams";
    
    
    //Students
    static final String JSON_Student_Last_Name = "lastName";         
    static final String JSON_Student_First_Name = "firstName"; 
    static final String JSON_Student_Team = "team";
    static final String JSON_Studentr_Role = "role";
    static final String JSON_Students = "students";
    
    public TAFile(CourseSiteGeneratorAPP initApp) {
        app = initApp;
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	// CLEAR THE OLD DATA OUT
	CSGData csgData = (CSGData)app.getDataComponent();
	TAData dataManager = (TAData)csgData.getTAData();
        ProjectData projectDataManager = csgData.getProjectData();
        ScheduleData scheduleDataManager = csgData.getScheduleData();
        RecitationData recitationDataManager = csgData.getRecitationData();
        CourseData courseDataManager = csgData.getCourseData();

        

	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);

	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);  
        dataManager.initHours(startHour, endHour);

        // NOW RELOAD THE WORKSPACE WITH THE LOADED DATA
        //app.getWorkspaceComponent().reloadWorkspace(app.getDataComponent());

        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
           
            dataManager.addTA(name, email);
        }

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            dataManager.addOfficeHoursReservation(day, time, name);
        }
        
        
        
        
        
        
          // Load All Recitations
        JsonArray jsonRecitationArray = json.getJsonArray(JSON_Recitation_Data);
        for (int i = 0; i < jsonRecitationArray.size(); i++) {
            JsonObject jsonRecitation = jsonRecitationArray.getJsonObject(i);
            String section = jsonRecitation.getString(JSON_Recitation_Section);
            String instructor = jsonRecitation.getString(JSON_Recitation_Instructor);
            String day = jsonRecitation.getString(JSON_Recitation_DayTime);
            String location = jsonRecitation.getString(JSON_Recitation_Location);
            String ta1 = jsonRecitation.getString(JSON_Recitation_TA1);
            String ta2 = jsonRecitation.getString(JSON_Recitation_TA2);
            Recitations recitation = new Recitations(section,instructor,day,location,ta1,ta2);
            
            recitationDataManager.getRecitationList().add(recitation);
            
            
        }
        
       
          // Load All Schedules
        JsonArray jsonScheduleArray = json.getJsonArray(JSON_Schedule_Data);
        for (int i = 0; i < jsonScheduleArray.size(); i++) {
            JsonObject jsonSchedule = jsonScheduleArray.getJsonObject(i);
            String date = jsonSchedule.getString(JSON_Schedule_Date);
            String link = jsonSchedule.getString(JSON_Schedule_Link);
            String time = jsonSchedule.getString(JSON_Schedule_Time);
            String title = jsonSchedule.getString(JSON_Schedule_Title);
            String topic = jsonSchedule.getString(JSON_Schedule_Topic);
            String type = jsonSchedule.getString(JSON_Schedule_Type);
            String criteria = jsonSchedule.getString(JSON_Schedule_Criteria);
            Schedules schedule = new Schedules(type,date,title,topic);
            schedule.setCriteria(criteria);
            schedule.setLink(link);
            schedule.setTime(time);
            scheduleDataManager.getScheduleList().add(schedule);
            
        }  
        
        

           // Load All Team
        JsonArray jsonTeamArray = json.getJsonArray(JSON_Team_Data);
        for (int i = 0; i < jsonTeamArray.size(); i++) {
            JsonObject jsonTeam = jsonTeamArray.getJsonObject(i);
            String name = jsonTeam.getString(JSON_Projects_Name);
            String color = jsonTeam.getString(JSON_Projects_Color);
            String textColor = jsonTeam.getString(JSON_Projects_Text_Color);
            String link = jsonTeam.getString(JSON_Projects_Link);
            
            Teams team = new Teams(name,color,textColor,link);
            projectDataManager.getTeamList().add(team);
               
        }

           // Load All Students
        JsonArray jsonStudentArray = json.getJsonArray(JSON_Student_Data);
        for (int i = 0; i < jsonStudentArray.size(); i++) {
            JsonObject jsonStudent = jsonStudentArray.getJsonObject(i);
            String firstName = jsonStudent.getString(JSON_Projects_First_Name);
            String lastName = jsonStudent.getString(JSON_Projects_Last_Name);
            String team = jsonStudent.getString(JSON_Projects_Team_List);
            String role = jsonStudent.getString(JSON_Projects_Role);
            
            Students student = new Students(firstName,lastName,team,role);
        
            projectDataManager.getStudentList().add(student);
               
        } 
 
    }
    
    
      // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
        private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
     }
    
    
       
      
   

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
        CSGData csgData = (CSGData)app.getDataComponent();
	TAData dataManager = (TAData)csgData.getTAData();
        ProjectData projectDataManager = csgData.getProjectData();
        ScheduleData scheduleDataManager = csgData.getScheduleData();
        RecitationData recitationDataManager = csgData.getRecitationData();
        CourseData courseDataManager = csgData.getCourseData();
       
	// NOW BUILD THE TA JSON OBJCTS TO SAVE
	JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	    
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_GRAD_TAS, ta.getGrad().get()).build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();

	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        

        // NOW BUILD THE Recitaition JSON OBJCTS TO SAVE
	JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitations> recs = recitationDataManager.getRecitationList();
	for (Recitations rec : recs) {	    
	    JsonObject recitationJson = Json.createObjectBuilder()
		    .add(JSON_Recitation_Section, rec.getSection())
		    .add(JSON_Recitation_Instructor, rec.getInstructor())
                    .add(JSON_Recitation_DayTime,rec.getDay())
                    .add(JSON_Recitation_Location, rec.getLocation())
                    .add(JSON_Recitation_TA1,rec.getTA1())
                    .add(JSON_Recitation_TA2,rec.getTA2()).build();
	    recitationArrayBuilder.add(recitationJson);
	}
	JsonArray recitationsArray = recitationArrayBuilder.build();
        

        // NOW BUILD THE Schedule JSON OBJCTS TO SAVE
	JsonArrayBuilder scheduleArrayBuilder = Json.createArrayBuilder();
	ObservableList<Schedules> schedule = scheduleDataManager.getScheduleList();
	for (Schedules sch : schedule) {	    
	    JsonObject scheduleJson = Json.createObjectBuilder()
		    .add(JSON_Schedule_Criteria, sch.getCriteria())
		    .add(JSON_Schedule_Date, sch.getDate())
                    .add(JSON_Schedule_Link,sch.getLink())
                    .add(JSON_Schedule_Time, sch.getTime())
                    .add(JSON_Schedule_Title,sch.getTitle())
                    .add(JSON_Schedule_Topic,sch.getTopic())
                    .add(JSON_Schedule_Type,sch.getType()).build(); 
                     scheduleArrayBuilder.add(scheduleJson);
	}
	JsonArray schdulesArray = scheduleArrayBuilder.build();

      // NOW BUILD THE Team JSON OBJCTS TO SAVE
	JsonArrayBuilder teamArrayBuilder = Json.createArrayBuilder();
	ObservableList<Teams> team = projectDataManager.getTeamList();
	for (Teams tem : team) {	    
	    JsonObject teamJson = Json.createObjectBuilder()
		    .add(JSON_Projects_Color, tem.getColor())
		    .add(JSON_Projects_Link, tem.getLink())
                    .add(JSON_Projects_Name,tem.getName())
                    .add(JSON_Projects_Text_Color, tem.getTextColor()).build();

                     teamArrayBuilder.add(teamJson);
	}
	JsonArray teamArray = teamArrayBuilder.build();
        
        
        
        // NOW BUILD THE Students JSON OBJCTS TO SAVE
	JsonArrayBuilder studentArrayBuilder = Json.createArrayBuilder();
	ObservableList<Students> student = projectDataManager.getStudentList();
	for (Students stu : student) {	    
	    JsonObject studentJson = Json.createObjectBuilder()
		    .add(JSON_Projects_First_Name, stu.getFirstName())
		    .add(JSON_Projects_Last_Name,stu.getLastName())
                    .add(JSON_Projects_Role,stu.getRole())
                    .add(JSON_Projects_Team_List, stu.getTeam()).build();

                     studentArrayBuilder.add(studentJson);
	}
	JsonArray studentArray = studentArrayBuilder.build();
        
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
                .add(JSON_Recitation_Data, recitationsArray)
                .add(JSON_Schedule_Data,schdulesArray)
                .add(JSON_Team_Data,teamArray)
                .add(JSON_Student_Data,studentArray).build();
              
        
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    // IMPORTING/EXPORTING DATA IS USED WHEN WE READ/WRITE DATA IN AN
    // ADDITIONAL FORMAT USEFUL FOR ANOTHER PURPOSE, LIKE ANOTHER APPLICATION

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        // GET THE DATA
	CSGData csgData = (CSGData)app.getDataComponent();
	TAData dataManager = (TAData)csgData.getTAData();
        ProjectData projectDataManager = csgData.getProjectData();
        ScheduleData scheduleDataManager = csgData.getScheduleData();
        RecitationData recitationDataManager = csgData.getRecitationData();
        CourseData courseDataManager = csgData.getCourseData();
        WorkSpace workspace = (WorkSpace) app.getWorkspaceComponent();
        CourseTab courseTab = workspace.getCourseTab();
	// NOW BUILD THE TA JSON OBJCTS TO SAVE
	JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	    
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail()).build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();

	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
		.build();
        

        
         // NOW BUILD THE Recitaition JSON OBJCTS TO SAVE
	JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitations> recs = recitationDataManager.getRecitationList();
	for (Recitations rec : recs) {	    
	    JsonObject recitationJson = Json.createObjectBuilder()
		    .add(JSON_Recitation_Section, rec.getSection() + "("+rec.getInstructor()+")")
		    //.add(JSON_Recitation_Instructor, rec.getInstructor())
                    .add(JSON_Recitation_DayTime,rec.getDay())
                    .add(JSON_Recitation_Location, rec.getLocation())
                    .add(JSON_Recitation_TA1,rec.getTA1())
                    .add(JSON_Recitation_TA2,rec.getTA2()).build();
	    recitationArrayBuilder.add(recitationJson);
	}
	JsonArray recitationsArray = recitationArrayBuilder.build();
        

        // NOW BUILD THE Schedule JSON OBJCTS TO SAVE
	JsonArrayBuilder scheduleArrayBuilder = Json.createArrayBuilder();
	ObservableList<Schedules> schedule = scheduleDataManager.getScheduleList();
	for (Schedules sch : schedule) {	    
	    JsonObject scheduleJson = Json.createObjectBuilder()
		    .add(JSON_Schedule_Criteria, sch.getCriteria())
		    .add(JSON_Schedule_Date, sch.getDate())
                    .add(JSON_Schedule_Link,sch.getLink())
                    .add(JSON_Schedule_Time, sch.getTime())
                    .add(JSON_Schedule_Title,sch.getTitle())
                    .add(JSON_Schedule_Topic,sch.getTopic())
                    .add(JSON_Schedule_Type,sch.getType()).build(); 
                     scheduleArrayBuilder.add(scheduleJson);
	}
	JsonArray schdulesArray = scheduleArrayBuilder.build();

        
        
        
         //Team
  
        
      // NOW BUILD THE Team JSON OBJCTS TO SAVE
	JsonArrayBuilder teamArrayBuilder = Json.createArrayBuilder();
	ObservableList<Teams> team = projectDataManager.getTeamList();
	for (Teams tem : team) {	    
	    JsonObject teamJson = Json.createObjectBuilder()
		    .add(JSON_Team_Name, tem.getColor())
		    .add(JSON_Color_Red, tem.getRed())
                    .add(JSON_Color_Green,tem.getGreen())
                    .add(JSON_Color_Blue, tem.getBlue()).build();

                     teamArrayBuilder.add(teamJson);
	}
	JsonArray teamArray = teamArrayBuilder.build();
        
        
        
        // NOW BUILD THE Students JSON OBJCTS TO SAVE
	JsonArrayBuilder studentArrayBuilder = Json.createArrayBuilder();
	ObservableList<Students> student = projectDataManager.getStudentList();
	for (Students stu : student) {	    
	    JsonObject studentJson = Json.createObjectBuilder()
		    .add(JSON_Student_Last_Name, stu.getLastName())
		    .add(JSON_Student_First_Name,stu.getFirstName())
                    .add(JSON_Student_Team,stu.getTeam())
                    .add(JSON_Studentr_Role, stu.getRole()).build();

                     studentArrayBuilder.add(studentJson);
	}
        
        
       
        
        
        
        
        JsonArrayBuilder projectArrayBuilder = Json.createArrayBuilder();
	//ObservableList<Students> student = projectDataManager.getStudentList();
	for (Students stu : student) {	    
	    JsonObject studentJson = Json.createObjectBuilder()
		    .add("semester", courseTab.getSemester() +" " +courseTab.getYear())
		    .add("projects",studentArrayBuilder)
                    .add(JSON_Student_Team,stu.getTeam())
                    .add(JSON_Studentr_Role, stu.getRole()).build();

                     studentArrayBuilder.add(studentJson);
	}
	JsonArray studentArray = studentArrayBuilder.build();
        
        
        JsonObject dataManagerRecitation = Json.createObjectBuilder()
		.add(JSON_Recitation_Data, recitationsArray).build();
        
        JsonObject dataManagerSchedules = Json.createObjectBuilder()
		.add(JSON_Schedule_Data,schdulesArray).build();
        
        JsonObject dataManagerTeams = Json.createObjectBuilder()
		.add(JSON_Team,teamArray).add(JSON_Students,studentArray).build();
        
              
   
        
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath+"/js"+"/OfficeHoursGridData.json");
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath+"/js"+"/OfficeHoursGridData.json");
	pw.write(prettyPrinted);
	pw.close();
        
//*******************************************************************************
        properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	writerFactory = Json.createWriterFactory(properties);
	sw = new StringWriter();
        jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerRecitation);
	jsonWriter.close();

	os = new FileOutputStream(filePath+"/js"+"/RecitationsData.json");
	jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerRecitation);
	prettyPrinted = sw.toString();
	pw = new PrintWriter(filePath+"/js"+"/RecitationsData.json");
	pw.write(prettyPrinted);
	pw.close();
 //*******************************************************************************       
        properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	writerFactory = Json.createWriterFactory(properties);
	sw = new StringWriter();
        jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerSchedules);
	jsonWriter.close();
        
	os = new FileOutputStream(filePath+"/js"+"/ScheduleData.json");
	jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerSchedules);
	prettyPrinted = sw.toString();
	pw = new PrintWriter(filePath+"/js"+"/ScheduleData.json");
	pw.write(prettyPrinted);
	pw.close();
        
     //*******************************************************************************   
        properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	writerFactory = Json.createWriterFactory(properties);
	sw = new StringWriter();
        jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerTeams);
	jsonWriter.close();

	
	os = new FileOutputStream(filePath+"/js"+"/TeamsAndStudents.json");
	jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerTeams);
	prettyPrinted = sw.toString();
	pw = new PrintWriter(filePath+"/js"+"/TeamsAndStudents.json");
	pw.write(prettyPrinted);
	pw.close();
        
   //*******************************************************************************     
        properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	writerFactory = Json.createWriterFactory(properties);
	sw = new StringWriter();
        jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerTeams);
	jsonWriter.close();


	os = new FileOutputStream(filePath+"/js"+"/ProjectsData.json");
        System.out.println(filePath+"/js"+"/ProjectsData.json");
	jsonFileWriter = Json.createWriter(os);
	//jsonFileWriter.writeObject(dataManagerProjects);
	prettyPrinted = sw.toString();
	pw = new PrintWriter(filePath+"/js"+"/ProjectsData.json");
	pw.write(prettyPrinted);
	pw.close();
  
    
    }
    
}
