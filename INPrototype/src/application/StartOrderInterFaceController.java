package application;
import orders.Order;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import customermethods.CommonMethods;
import customermethods.Customer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartOrderInterFaceController extends Application implements Initializable{

	Stage stage;
	Parent root;
	private String[] locations= {"Haifa","Shefa-Amer","Karmiel","Tel-Aviv"};
	private HashMap<String,ArrayList<String> > areaByLocation = new HashMap<String, ArrayList<String>>();
	//names flipped between area and location
	@FXML
	private Label IDErrorLabel;
    @FXML
    private ComboBox<String> IDAreaBox;
    @FXML
    private ComboBox<String> IDLoactionBox;
    @FXML
    private ImageView IDhelp;
    @FXML
    private Pane zr2;
    @FXML
    private ImageView IDLocationIMG;
  
    
	public void start(Stage primaryStage) {
		
		try {
			
			stage= primaryStage;
			root=CommonMethods.switchScene(getClass(),stage, "StartOrderInterFace.fxml","StartOrderInterFace.css");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void clsoe(MouseEvent event) throws Exception // close window
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
		
	  }
	public void back(MouseEvent event) throws Exception // close window
	  {
	
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","Customerinterface.css");
		
	  }

	public void help(MouseEvent event) throws Exception{//to the throw a message when "question" pressed

    	Customer.help("You must choose Area (city) and Location of the machine you want:\n");//showed message
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {//intilaize the data into the box and the hash map
		IDLoactionBox.getItems().addAll(locations);//set the location to to location box
		areaByLocation.put("Haifa", new ArrayList<>(Arrays.asList("the Baha'i Gardens","Down Town","Maritime Museum","Haifa-University")));
		areaByLocation.put("Shefa-Amer", new ArrayList<>(Arrays.asList("Old City","Down Town","The Castle","Alna'ma")));
		areaByLocation.put("Karmiel", new ArrayList<>(Arrays.asList("Ort Braude","Down Town","North","Park")));
		areaByLocation.put("Tel-Aviv", new ArrayList<>(Arrays.asList("Jaffa","Yemenite Quarter","Tel Aviv Museum","Tel-Aviv University")));
		IDLoactionBox.setOnAction(this::getLocation);
		IDAreaBox.setPromptText("Choose Location");
	}
	public void getLocation(ActionEvent event)
	{
		IDAreaBox.setPromptText("Choose Location");
		String location = IDLoactionBox.getValue();
		IDAreaBox.getItems().clear();
		
		//IDAreaBox.setAccessibleText("Choose Area");
		if(location.equals("Haifa"))
		{
			IDAreaBox.getItems().addAll(areaByLocation.get("Haifa"));
		}
		else if(location.equals("Shefa-Amer"))
		{
			IDAreaBox.getItems().addAll(areaByLocation.get("Shefa-Amer"));
		}
		else if(location.equals("Karmiel"))
		{
			IDAreaBox.getItems().addAll(areaByLocation.get("Karmiel"));
		}
		else if(location.equals("Tel-Aviv"))
		{
			IDAreaBox.getItems().addAll(areaByLocation.get("Tel-Aviv"));
		}
		
	}
    public void next(MouseEvent event) throws Exception{
    	IDErrorLabel.setText("");
    	if(IDAreaBox.getValue()==null||IDLoactionBox.getValue()==null) {
    	IDErrorLabel.setText("You must Choose area and location");
    	}
    	else {
    		Customer.cameFrom="Location";//startOrder page
    		Order.area=IDLoactionBox.getValue();
    		Order.location = IDAreaBox.getValue();
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 			root=CommonMethods.switchScene(getClass(),stage, "CatalogPage.fxml","OrderCatalogPage.css");
    		
    	}
    }
	public static void main(String[] args) {
		launch(args);
	}

}
