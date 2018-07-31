/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import coursesitegenerator.CourseSiteGeneratorAPP;
import static csg.data.TAData.MAX_END_HOUR;
import static csg.data.TAData.MIN_START_HOUR;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import jtps.jTPS;

/**
 *
 * @author patrickboucicault
 */
public class CSGData extends AppWorkspaceComponent implements AppDataComponent{

    ProjectData project;
    CourseData course;
    RecitationData recitation;
    ScheduleData schedule;
    TAData ta;
    
    CourseSiteGeneratorAPP app;
    
    jTPS jtps = new jTPS();
    public CSGData(CourseSiteGeneratorAPP init){
        
        app = init;
        
        ta = new TAData(app);
        recitation = new RecitationData(app);
        project = new ProjectData(app);
        course = new CourseData(app);
        schedule = new ScheduleData(app);
        
        
    }
    
    
    
    public ProjectData getProjectData(){
        return project;
    }
    public CourseData getCourseData(){
        return course;
    }
    public RecitationData getRecitationData(){
        return recitation;
    }
    public ScheduleData getScheduleData(){
        return schedule;
    }
    public TAData getTAData(){
        return ta;
    }
    
        
    public jTPS getjTPS(){
        return jtps;
    }
    
    @Override
    public void resetWorkspace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public void resetData() {
        
       ta.resetData();
       schedule.resetData();
       recitation.resetData();
       project.resetData();
    }
    
}
