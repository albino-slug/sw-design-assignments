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

public class Startpoint extends Application {
    
    public static void main(String[] args) {
        launch(Startpoint.class, args);
    }
    
    Scene scene;
    Stage stage;
    
    @Override
    public void start(Stage s) {
    	this.stage = s;
        BorderPane border = new BorderPane();
        
        HBox hbox = addHBox();
        border.setTop(hbox);
        border.setLeft(addVBox());
        hbox.setAlignment(Pos.CENTER);
        
        addStackPane(hbox);     
        border.setCenter(addGridPane());
        border.setRight(addFlowPane());

        scene = new Scene(border);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Welcome Page");
        stage.show();
    }

    public HBox addHBox() {

        HBox hbox = new HBox();
       // hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(40);
        hbox.setStyle("-fx-background-color: #3F3E46;");

        Button about = new Button("About");
        about.setStyle(buttonStyle("#ff5400", "#be1d00"));
        AboutPage ap = new AboutPage();
        about.setOnAction(e -> ap.start(stage));

        Button contact = new Button("Contact");
        contact.setStyle(buttonStyle("#ff5400", "#be1d00"));
        ContactPage cp = new ContactPage();
        contact.setOnAction(e -> cp.start(stage));
        
        Button login = new Button("Log in");
        login.setStyle(buttonStyle("#ff5400", "#be1d00"));
        LoginPage lp = new LoginPage();
        login.setOnAction(e -> lp.start(stage));
        
        Button signup = new Button("Signup");
        signup.setStyle(buttonStyle("#ff5400", "#be1d00"));
        SignupPage sp = new SignupPage();
        signup.setOnAction(e -> sp.start(stage));
        
        Button view = new Button("View Tournaments");
        view.setStyle(buttonStyle("#ff5400", "#be1d00"));
        ViewTourPage vtp = new ViewTourPage();
        view.setOnAction(e -> vtp.start(stage));
        
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(about, contact, view, login, signup);
        
        return hbox;
    }
    
    public VBox addVBox() {
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);
        vbox.setStyle("-fx-font-size: 8pt; -fx-background-color: #3F3E46;");
        vbox.setMaxWidth(100);

        Text title = new Text("Useful Links:");
        vbox.getChildren().add(title);
        title.setStyle("-fx-font-weight: bold");
        
        Hyperlink options[] = new Hyperlink[] {
            new Hyperlink("How to create a tournament"),
            new Hyperlink("How to create a match in an existing tournament"),
            new Hyperlink("How to add your score to a match"),
            new Hyperlink("FAQ")};

        for (int i=0; i<4; i++) {
        	options[i].setWrapText(true);
        	options[i].setStyle("-fx-text-fill: #F24900;");
            vbox.getChildren().add(options[i]);
        }
        
        Text title2 = new Text("Find us on:");
        vbox.getChildren().add(title2);
        title2.setStyle("-fx-font-weight: bolder");
        
        Hyperlink options2[] = new Hyperlink[] {
            new Hyperlink("Facebook"),
            new Hyperlink("Ping-Pong World")};

        for (int i=0; i<2; i++) {
            options2[i].setWrapText(true);
            options2[i].setStyle("-fx-text-fill: #F24900;");
            vbox.getChildren().add(options2[i]);
        }
        
        return vbox;
    }

    public void addStackPane(HBox hb) {

        StackPane stack = new StackPane();
        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
            new Stop[]{
            new Stop(0,Color.web("#4977A3")),
            new Stop(0.5, Color.web("#B0C6DA")),
            new Stop(1,Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);
        
        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0")); 
        
        stack.getChildren().addAll(helpIcon, helpText);
        stack.setAlignment(Pos.CENTER_RIGHT);
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));
        
        hb.getChildren().add(stack);
        HBox.setHgrow(stack, Priority.ALWAYS);
                
    }

    private GridPane addGridPane() {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle(
	        "-fx-background-color: #F07B69;"
	        + "-fx-background-image:"
	        + "url('https://cdn.dribbble.com/users/730703/screenshots/3707872/peter-greenwood-folio-illustration-table-tennis-d_1x.jpg');"
	        + "-fx-background-repeat: stretch;"
	        + "-fx-background-size: 400 312;"
	        + "-fx-background-position: center center;"
	        + "-fx-font-size: 8pt;");
        
        return grid;
    }

    public FlowPane addFlowPane() {

        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(0, 5, 0, 5));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setStyle("-fx-background-color: #F07B69;");
        flow.setMaxWidth(80);

        ImageView pages[] = new ImageView[4];
        for (int i=0; i<3; i++) {
            pages[i] = new ImageView(
                    new Image(Startpoint.class.getResourceAsStream("" + (i+1) + ".jpg")));
            pages[i].setFitWidth(80);
            pages[i].setFitHeight(80);
            flow.getChildren().add(pages[i]);
        }
        
        pages[3] = new ImageView(
                new Image(Startpoint.class.getResourceAsStream("4.jpg")));
        pages[3].setFitWidth(80);
        pages[3].setFitHeight(67);
        flow.getChildren().add(pages[3]);

        return flow;
    }
    
    String buttonStyle(String color1, String color2){
    	return "-fx-background-color: linear-gradient("
    			+ color1 + "," + color2 + ");"
        		+ "-fx-background-radius: 30;"
        		+ "-fx-background-insets: 0;"
        		+ "-fx-text-fill: white;"
        		+ "-fx-font-size: 8pt;";
    }
    
    String gridStyle(String color){
    	return "-fx-background-color: "
    			+ color + ";"
    	        + "-fx-background-repeat: stretch;"
    	        + "-fx-background-size: 400 312;"
    	        + "-fx-background-position: center center;"
    	        + "-fx-font-size: 8pt;";
    }
}