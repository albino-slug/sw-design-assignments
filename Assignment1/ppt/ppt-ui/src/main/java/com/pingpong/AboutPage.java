package com.pingpong;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AboutPage extends Application {
    
    public static void main(String[] args) {
        launch(AboutPage.class, args);
    }
    
    Scene aboutScene;
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

        aboutScene = new Scene(border);
        stage.setScene(aboutScene);
        stage.setResizable(false);
        stage.setTitle("About Page");
        stage.show();
    }
    
    private GridPane addGridPane() {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle(sp.gridStyle("#F07B69"));
        
        Button home = new Button("Home");
        home.setStyle(sp.buttonStyle("#ff5400", "#be1d00"));
        Startpoint sp = new Startpoint();
        home.setOnAction(e -> sp.start(stage));
        
        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.getChildren().addAll(home);
        
        return grid;
    }
}