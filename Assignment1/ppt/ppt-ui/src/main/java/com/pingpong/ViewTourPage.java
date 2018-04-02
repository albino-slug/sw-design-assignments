package com.pingpong;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class ViewTourPage extends Application {
    
    public static void main(String[] args) {
    	launch(ViewTourPage.class, args);
    }
    
    
    Scene viewScene;
    Stage stage;
    Startpoint sp = new Startpoint();
    
    @Override 
    public void start(Stage s) {
    	this.stage = s;
        BorderPane border = new BorderPane();
        
        HBox hbox = sp.addHBox();
        border.setTop(hbox);
        border.setLeft(sp.addVBox());
        hbox.setAlignment(Pos.CENTER);
        sp.addStackPane(hbox); 
        border.setCenter(addGridPane());
        border.setRight(sp.addFlowPane());
        viewScene = new Scene(border);
        stage.setScene(viewScene);
        stage.setResizable(false);
        stage.setTitle("View Tournaments Page");
        stage.show();
    }

    private GridPane addGridPane() {

        GridPane grid = createGrid(1, 10);
        
        Label t1 = new Label("       Previous Tournaments");
        Label t2 = new Label("Ongoing Tournaments        ");
        grid.add(t1, 0,  0); 
        grid.add(t2, 1,  0);
        
        ListTournaments lt = new ListTournaments();
        lt.listTournaments();
        int i = 1, j = 1;
        ArrayList<Button> tourButtons = new ArrayList<Button>();
        ViewTourMatches vtm = new ViewTourMatches();
        for (int index = 0; index < lt.getTourNames().size(); index++){
        	String name = lt.getTourNames().get(index);
        	String status = lt.getTourStatuses().get(index);
        	Button b = new Button(name.toString());
        	b.setStyle(sp.buttonStyle("#000000", "#000000"));
        	GridPane.setFillWidth(b, true);
        	GridPane.setFillHeight(b, true);
            
            if (status.equals("previous")){
            	grid.add(b, 0, i);
            	i += 1;
            }
            else{
            	grid.add(b, 1, j);
            	j += 1;
            }
//            
            b.setOnAction(e -> vtm.getTour(stage, name.toString()));
        	tourButtons.add(b);
        }
                
        Button home = new Button("Home");
        home.setStyle(sp.buttonStyle("#ff5400", "#be1d00"));
        home.setOnAction(e -> sp.start(stage));
        
        grid.add(home, 0, 9);
        
        return grid;
    }
    
    private GridPane createGrid(int numCols, int numRows){
    	GridPane grid = new GridPane();
        for (int row = 0 ; row < numRows ; row++ ){
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < numCols; col++ ) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        grid.setStyle(sp.gridStyle("#cccccc"));
        return grid;
    }
    
}