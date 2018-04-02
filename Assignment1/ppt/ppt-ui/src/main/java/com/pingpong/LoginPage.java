package com.pingpong;

import com.pingpong.validation.LoginValidator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage extends Application {
    
    public static void main(String[] args) {
        launch(LoginPage.class, args);
    }
    
    Scene loginScene;
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

        loginScene = new Scene(border);
        stage.setScene(loginScene);
        stage.setResizable(false);
        stage.setTitle("Log In");
        stage.show();
    }

    private GridPane addGridPane() {

        GridPane grid = new GridPane();
        grid.setStyle(sp.gridStyle("#cccccc"));
        
        ColumnConstraints columnOneConstraints = new ColumnConstraints(70, 70, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        ColumnConstraints columnTwoConstrains = new ColumnConstraints(270,270, Double.MAX_VALUE);
        //columnTwoConstrains.setHgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
        addUIControls(grid);
        
        return grid;
    }
    
    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("LOG IN");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        gridPane.add(headerLabel, 1,2);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("username: ");
        gridPane.add(nameLabel, 0,3);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,3);
        
     // Add Password Label
        Label passwordLabel = new Label("password: ");
        gridPane.add(passwordLabel, 0, 4);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 4);

        Button submitButton = new Button("Log in");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
        
        final Text actiontarget = new Text();
        gridPane.add(actiontarget, 1, 6);
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	String username = nameField.getText();
            	String password = passwordField.getText();
            	
            	LoginValidator lv = new LoginValidator();
            	if (lv.inexistentUsername(username)){
            		actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Inexistent Username");
            	}
            	else if(lv.wrongPassword(username, password)){
            		actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Wrong Password");
            	}
            	else{
            		sp.start(stage);
            	}
                
            }
        });
    }
}