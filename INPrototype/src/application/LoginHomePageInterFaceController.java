package application;
	
import java.util.ArrayList;
import java.util.Arrays;

import customermethods.CommonMethods;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;






public class LoginHomePageInterFaceController extends Application {

		Stage stage;
		Parent root;
	    @FXML
	    private Label IDErrorLabel;
		@FXML
		private Label IDwelcome1;
		@FXML
	    private AnchorPane backGround1;
	    @FXML
	    private Pane info1;
	    @FXML
	    private ImageView lockIMG;
	    @FXML
	    private ImageView userIMG;
	    @FXML
	    private ImageView userNameIMG;
	    @FXML
	    private Button loginBtn;
	    @FXML
	    private ImageView logoIMG1;
	    @FXML
	    private PasswordField passwordID;
	    @FXML
	    private TextField usernameID;
	   
	    private ArrayList<String> DBUsername = new ArrayList<>(Arrays.asList("hasan","lena"));;
	    private ArrayList<String> DBPassword=new ArrayList<>(Arrays.asList("123456","123456789")); 
	    //private ArrayList<String> DBRole=new ArrayList<>(Arrays.asList("Customer","ceo")); ;
  
	@Override
	public void start(Stage primaryStage) {
		try {
		
		stage= primaryStage;
		root=CommonMethods.switchScene(getClass(),stage, "LoginHomePage.fxml","Logininterface.css");
		System.out.println("1");
		

	} catch(Exception e) {
		e.printStackTrace();
	}

	}
	
	    public void login(ActionEvent event) throws Exception
	   {
	    	
	    	//checking if user is database and his password is correct
	 	    if(DBUsername.contains(usernameID.getText()) && DBPassword.get(DBUsername.indexOf(usernameID.getText())).equals(passwordID.getText())) 
	 	    {
	 	    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","Customerinterface.css");
	 			
	 	   }
	 	   else
	 	   {
	 		  passwordID.setText(""); 
	 		  IDErrorLabel.setText("");
	 		  IDErrorLabel.setText("      Wrong Username or Password\nPlease Enter Username and Password");
	 		   
	 	   }
		 
	   }
	    public String getUserName()
	    {
	    	return usernameID.getText().toString();
	    }
	    public String getPasswordName()
	    {
	    	return passwordID.getText().toString();
	    }
		public void clsoe(MouseEvent event) throws Exception
		  {
			stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
			stage.close();
			
		  }


	public static void main(String[] args) {
		launch(args);
	}


}
