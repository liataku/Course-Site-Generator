/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transactions;

import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.workspace.TATab;
import csg.workspace.WorkSpace;

/**
 *
 * @author khurr
 */
public class UpdateTA_EmailOnly_Transaction implements jTPS_Transaction{
    
    private String orgName;
    
    private String orgEmail;
    private String newEmail;
    private TAData taData;
    private TATab tab;
   public  UpdateTA_EmailOnly_Transaction(String oldName, String oldEmail, String email, TAData data, TATab tab )
    {
        orgName=oldName;
        orgEmail=oldEmail;
        newEmail=email;
        taData=data; 
        this.tab=tab;
    }
    

    @Override
    public void doTransaction() {  //Control Y 
        TableView taTable = tab.getTATable();
        TeachingAssistant ta=taData.getTA(orgName); 
        ta.setEmail(newEmail);
        tab.getEmailTextField().setText(newEmail);
        taTable.refresh();
        
        //data.getTA(orgName).setEmail(email);
        //tasetEmail(email);
                
        
    }

    @Override
    public void undoTransaction() {  // COntrol Z 
         TableView taTable = tab.getTATable();
        TeachingAssistant ta=taData.getTA(orgName);
        ta.setEmail(orgEmail);
        tab.getEmailTextField().setText(orgEmail);
        taTable.refresh();
        
        
    }
    
}
