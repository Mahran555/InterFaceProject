package application;
	

import customermethods.CommonMethods;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


 


public class ConnectionPageInterFaceController extends Application {

	private double xOffset = 0;
	private double yOffset = 0;
	Stage stage;
	Parent root;

    @FXML
    private Button conBtn;
    @FXML
    private TextField textField1;

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		try {
			stage= primaryStage;
			FXMLLoader loader = new FXMLLoader();//load fxml file
			loader.setLocation(getClass().getResource("/FXMLs/ConnectionPage.fxml"));
			root =(Parent)loader.load();//implement file and load to the root
			stage.setTitle("Connectoion page");//title
			stage.initStyle(StageStyle.DECORATED.UNDECORATED);//decorate as an app
			//click and drag functionality for window
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY();
				}
				
			});
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					stage.setX(event.getScreenX()-xOffset);
					stage.setY(event.getScreenY()-yOffset);
				}
				
			});
			/////////////
			Scene scene = new Scene(root);//create scene as the root
			Image icon = new Image("icons/icon.png");//implement the icon
			scene.getStylesheets().add(getClass().getResource("/cssF/ConnectionInterface.css").toExternalForm());//implement css design
			stage.setScene(scene);//set the stage for the new scene
			stage.getIcons().add(icon);//set icon 
			stage.show();//show scene on stage
	

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void connect(ActionEvent event) throws Exception
	   {
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root=CommonMethods.switchScene(getClass(),stage, "LoginHomePage.fxml","Logininterface.css");
		 
	   }
	
	public void clsoe(MouseEvent event) throws Exception // close window
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
		
	  }
	
	public static void main(String[] args) {
		launch(args);
	}
}
