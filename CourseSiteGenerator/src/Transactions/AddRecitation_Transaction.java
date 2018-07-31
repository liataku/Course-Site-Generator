/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transactions;

import coursesitegenerator.CourseSiteGeneratorAPP;
import csg.data.CSGData;
import csg.data.RecitationData;
import csg.data.Recitations;
import csg.data.TAData;
import csg.workspace.RecitationTab;
import csg.workspace.WorkSpace;
import java.util.Collections;
import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;

/**
 *
 * @author patrickboucicault
 */
public class AddRecitation_Transaction implements jTPS_Transaction{

    private Recitations recitation;
    private CourseSiteGeneratorAPP app;
    
    public AddRecitation_Transaction(CourseSiteGeneratorAPP init, Recitations selectedItem){
        recitation = selectedItem;
        app = init;
    }
    
    @Override
    public void doTransaction() {
        
        //Recitations recitation = (Recitations) selectedItem;
        
    }

    @Override
    public void undoTransaction() {

         CSGData csgData = (CSGData) app.getDataComponent();
         RecitationData recitationData = (RecitationData)csgData.getRecitationData();

         ObservableList<Recitations> recitationList; 
         recitationList = recitationData.getRecitationList();

         WorkSpace workspace = (WorkSpace) app.getWorkspaceComponent();
         RecitationTab tab = workspace.getRecitationTab();
         
         //workspace.getRecitationTab().getRecitationController().handleRemoveRecitation();
            tab.getDayText().setText("");
            tab.getInstructorText().setText("");
            tab.getLocationText().setText("");
            tab.getSectionText().setText("");
            recitationList.remove(recitation);
            tab.getAddButton().setText("Add Recitation");
            Collections.sort(recitationList);
         
         
         
    }
    
}
