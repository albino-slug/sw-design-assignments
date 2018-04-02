package com.pingpong;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ContactPage extends Application {
    
    public static void main(String[] args) {
        launch(ContactPage.class, args);
    }
    
    Scene contactScene;
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

        contactScene = new Scene(border);
        stage.setScene(contactScene);
        stage.setResizable(false);
        stage.setTitle("Contact Page");
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