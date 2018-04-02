package com.pingpong;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SignupPage extends Application {
    
    public static void main(String[] args) {
        launch(SignupPage.class, args);
    }
    
    Scene signupScene;
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

        signupScene = new Scene(border);
        stage.setScene(signupScene);
        stage.setResizable(false);
        stage.setTitle("Sign Up");
        stage.show();
    }

    private GridPane addGridPane() {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle(sp.gridStyle("#cccccc"));
        
        Button home = new Button("Home");
        home.setStyle(sp.buttonStyle("#ff5400", "#be1d00"));
        Startpoint sp = new Startpoint();
        home.setOnAction(e -> sp.start(stage));
        
        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.getChildren().addAll(home);
        
        return grid;
    }
}