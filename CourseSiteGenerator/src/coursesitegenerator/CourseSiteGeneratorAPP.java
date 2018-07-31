package coursesitegenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import csg.data.CSGData;
import csg.data.TAData;
import csg.file.CourseFile;
import csg.file.TAFile;
import csg.style.CsgStyle;
import csg.workspace.WorkSpace;
import djf.AppTemplate;
import java.util.Locale;
import static javafx.application.Application.launch;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author patrickboucicault
 */
public class CourseSiteGeneratorAPP extends AppTemplate{
    
    
    
  

    @Override
    public void buildAppComponentsHook() {
         // CONSTRUCT ALL FOUR COMPONENTS. NOTE THAT FOR THIS APP
        // THE WORKSPACE NEEDS THE DATA COMPONENT TO EXIST ALREADY
        // WHEN IT IS CONSTRUCTED, SO BE CAREFUL OF THE ORDER
      //  dataComponent = new TAData(this);
      
        dataComponent = new CSGData(this);
        workspaceComponent = new WorkSpace(this);
        fileComponent = new TAFile(this);
        styleComponent = new CsgStyle(this);
        
        
    }
    
              /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
	launch(args);
       
    }
    
}
    

