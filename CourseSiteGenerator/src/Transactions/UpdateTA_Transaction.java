/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transactions;


import coursesitegenerator.CourseSiteGeneratorAPP;
import csg.controllers.TAController;
import jtps.jTPS_Transaction;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.workspace.TATab;


/**
 *
 * @author khurr
 */
public class UpdateTA_Transaction implements jTPS_Transaction {

    private String oldName;
    private String newName;
    private String oldEmail;
    private String newEmail;
    private TAData taData;
    private TeachingAssistant ta;
    private CourseSiteGeneratorAPP app; 
    private TATab tab;
    
    public UpdateTA_Transaction(String orgName, String name, String orgEmail, String email, TAData data, CourseSiteGeneratorAPP taApp, TATab tab) {
        oldName = orgName;
        newName = name;
        oldEmail = orgEmail;
        newEmail = email;
        taData = data;
        ta = data.getTA(orgName);
        app=taApp; 
        this.tab=tab; 
    }

    @Override
    public void doTransaction() {  //Control Y 
        System.out.println("updateTA doTransaction ");
        taData.getTA(oldName).setName(newName);
        TAController controller = new TAController(app,tab);
        controller.handleUpdateTaGrid(oldName, newName);
        ta.setName(newName);                        // MOVED TO TRANSACTION CASE 
        ta.setEmail(newEmail);
        tab.getNameTextField().setText(newName);
        tab.getEmailTextField().setText(newEmail);
       // transWorkspace.taTable.refresh();

    }

    @Override
    public void undoTransaction() {  //Control Z 
        System.out.println("updateTA undoTransaction ");
        taData.getTA(newName).setName(oldName);
        TAController controller = new TAController(app,tab);
        controller.handleUpdateTaGrid(newName, oldName);
        ta.setName(oldName);        // MOVED TO TRANSACTION CASE 
        ta.setEmail(oldEmail);
        tab.getNameTextField().setText(oldName);
        tab.getEmailTextField().setText(oldEmail);
        

    }

}
