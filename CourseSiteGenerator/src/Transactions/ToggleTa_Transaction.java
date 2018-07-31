/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transactions;

import coursesitegenerator.CourseSiteGeneratorAPP;
import csg.data.CSGData;
import javafx.scene.layout.Pane;
import jtps.jTPS_Transaction;
import csg.data.TAData;
import csg.data.TeachingAssistant;

/**
 *
 * @author khurr
 */
public class ToggleTa_Transaction implements jTPS_Transaction {

    private Object selectedItem;
    private CourseSiteGeneratorAPP app;
    private Pane pane;

    /**
     *
     * @param selectedItem
     * @param workspace
     * @param app
     * @param pane
     */
    public ToggleTa_Transaction(Object selectedItem, CourseSiteGeneratorAPP app, Pane pane) {
        this.selectedItem = selectedItem;
        this.app = app;
        this.pane = pane;
    }

    @Override
    public void doTransaction() {
        TeachingAssistant ta = (TeachingAssistant) selectedItem;
        String taName = ta.getName();
        CSGData csgData = (CSGData) app.getDataComponent();
        TAData taData = (TAData) csgData.getTAData();
        String cellKey = pane.getId();

        // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
        taData.toggleTAOfficeHours(cellKey, taName);

    }

    @Override
    public void undoTransaction() {
        TeachingAssistant ta = (TeachingAssistant) selectedItem;
        String taName = ta.getName();
        CSGData csgData = (CSGData) app.getDataComponent();
        TAData taData = (TAData) csgData.getTAData();
        String cellKey = pane.getId();

        // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
        taData.toggleTAOfficeHours(cellKey, taName);

    }

}
