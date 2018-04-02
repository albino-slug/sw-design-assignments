package com.pingpong;

import java.util.ArrayList;

import com.pingpong.model.Match;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewTourMatches extends Application {
    
    public static void main(String[] args) {
        launch(ViewTourMatches.class, args);
    }
    
    Stage 		stage;
    Scene 		viewMatchesScene;
    Startpoint 	sp = new Startpoint();
    TournamentDisplay td;
    ArrayList<String> usernamesP1Q, usernamesP2Q, usernamesP1S, usernamesP2S, usernamesP1F, usernamesP2F;
    ArrayList<Integer> scoresP1Q, scoresP2Q, scoresP1S, scoresP2S, scoresP1F, scoresP2F;
    
    public void getTour(Stage s, String tourName){
    	td = new TournamentDisplay(tourName);
    	td.getTournament();
    	System.out.println(td.getTour().toString());
    	usernamesP1Q = td.getP1Names("quarterfinals");
    	usernamesP2Q = td.getP2Names("quarterfinals");
    	scoresP1Q = td.getP1Scores("quarterfinals");
    	scoresP2Q = td.getP2Scores("quarterfinals");
    	
    	usernamesP1S = td.getP1Names("semifinals");
    	usernamesP2S = td.getP2Names("semifinals");
    	scoresP1S = td.getP1Scores("semifinals");
    	scoresP2S = td.getP2Scores("semifinals");
    	
    	usernamesP1F = td.getP1Names("finals");
    	usernamesP2F = td.getP2Names("finals");
    	scoresP1F = td.getP1Scores("finals");
    	scoresP2F = td.getP2Scores("finals");
    	
    	start(s);
    }
    
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

        viewMatchesScene = new Scene(border);
        stage.setScene(viewMatchesScene);
        stage.setResizable(false);
        stage.setTitle("View Matches For Tournament");
        stage.show();
    }

    private GridPane addGridPane() {

        GridPane grid = createGrid(12, 10);
        
        Label quarterfinals = new Label("quarterfinals");
        Label semifinals	= new Label("  semifinals  ");
        Label finals		= new Label("     finals    ");
        quarterfinals.setTextFill(Color.RED);
        semifinals.setTextFill(Color.RED);
        finals.setTextFill(Color.RED);
        grid.add(quarterfinals, 4, 0); 
        grid.add(semifinals, 4, 3);
        grid.add(finals, 4, 6);
        
        int c = 0;
        for (int i = 0; i < usernamesP1Q.size(); i++){
        	 Label p1 	= new Label(usernamesP1Q.get(i));
             Label p2	= new Label(usernamesP2Q.get(i));
             Label s1 	= new Label(scoresP1Q.get(i).toString());
             Label s2	= new Label(scoresP2Q.get(i).toString());
             
             grid.add(p1, c, 1); 
             grid.add(p2, c, 2);
             grid.add(s1, c+1, 1);
             grid.add(s2, c+1, 2);
             
             c+=3;
        }
        
        c = 3;
        for (int i = 0; i < usernamesP1S.size(); i++){
        	 Label p1 	= new Label(usernamesP1S.get(i));
             Label p2	= new Label(usernamesP2S.get(i));
             Label s1 	= new Label(scoresP1S.get(i).toString());
             Label s2	= new Label(scoresP2S.get(i).toString());
             
             grid.add(p1, c, 4); 
             grid.add(p2, c, 5);
             grid.add(s1, c+1, 4);
             grid.add(s2, c+1, 5);
             
             c+=3;
        }
        
        c = 4;
        for (int i = 0; i < usernamesP1F.size(); i++){
       	 Label p1 	= new Label(usernamesP1F.get(i));
            Label p2	= new Label(usernamesP2F.get(i));
            Label s1 	= new Label(scoresP1F.get(i).toString());
            Label s2	= new Label(scoresP2F.get(i).toString());
            
            grid.add(p1, c, 7); 
            grid.add(p2, c, 8);
            grid.add(s1, c+1, 7);
            grid.add(s2, c+1, 8);
            
            c+=3;
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