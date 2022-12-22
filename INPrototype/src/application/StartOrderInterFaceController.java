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
import javafx.stage.Stage;

/**
 * 
 * This Class is a Location Page Controller the page after pressing on StartOrder for StartOrderInterFace.fxml ,it runs all the methods 
 * that functions the choice of the area and location that the customer's chooses (of the machine) 
 * Customer chooses area and location from two combo boxes
 * Area Box
 * Location Box
 * Note: this class uses help from Customer and Order class to set and get some functionality 
 * @author Mahran
 *
 */
public class StartOrderInterFaceController extends Application implements Initializable{

	/**
	 * to save and show the stage
	 */
	Stage stage;
	/**
	 * to save and the root
	 */
	Parent root;
	/**
	 * array of strings to save the Areas 
	 */
	private String[] areas= {"Haifa","Shefa-Amer","Karmiel","Tel-Aviv"};
	/**
	 * map that helps save a group of locations for specific area
	 */
	private HashMap<String,ArrayList<String> > locationsByArea = new HashMap<String, ArrayList<String>>();
	/**
	 * Label that shows error message
	 */
	@FXML
	private Label IDErrorLabel;
    /**
     * ComboBox to save/show Areas to choose from
     */
    @FXML
    private ComboBox<String> IDLoactionBox;
    /**
     * ComboBox to save/show Locations of the area to choose from
     */
    @FXML
    private ComboBox<String> IDAreaBox;
    /**
     * ImageView for help icon that can be clicked
     */
    @FXML
    private ImageView IDhelp;

  
    
	/**
	 *Method to start the primary scene and set the stage 
	 *used only for starting from customer page nothing before 
	 *could be deleted
	 */
	public void start(Stage primaryStage) {
		
		try {
			
			stage= primaryStage;
			root=CommonMethods.switchScene(getClass(),stage, "StartOrderInterFace.fxml","StartOrderInterFace.css");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *initialize the Area and locations details
	 *set for every area the locations that have
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		IDAreaBox.getItems().addAll(areas);
		locationsByArea.put("Haifa", new ArrayList<>(Arrays.asList("the Baha'i Gardens","Down Town","Maritime Museum","Haifa-University")));
		locationsByArea.put("Shefa-Amer", new ArrayList<>(Arrays.asList("Old City","Down Town","The Castle","Alna'ma")));
		locationsByArea.put("Karmiel", new ArrayList<>(Arrays.asList("Ort Braude","Down Town","North","Park")));
		locationsByArea.put("Tel-Aviv", new ArrayList<>(Arrays.asList("Jaffa","Yemenite Quarter","Tel Aviv Museum","Tel-Aviv University")));
		IDLoactionBox.setPromptText("Choose Location");
		IDAreaBox.setOnAction(this::getLocation);
	}
	/**
	 * Method for closing the application, the window of the application would be closed
	 * @param event event of the X icon clicked
	 * @throws Exception Exception will be thrown if an error occurs
	 */
	public void clsoe(MouseEvent event) throws Exception 
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
		
	  }
	
	/**
	 * Method for going back to the previous page in this case the customer page
	 * @param event event of the arrow (back) icon clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
	 */
	public void back(MouseEvent event) throws Exception 
	  {
	
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","Customerinterface.css");
		
	  }

	/**
	 * Method for clicking the help icon ,a windows will show with a message and explain the scene/page
	 * @param event event of the help icon clicked the scene/page and what every button do
	 * @throws Exception Exception will be thrown if an error occurs from Customer class 
	 */
	public void help(MouseEvent event) throws Exception{

    	Customer.help("You must choose Area (city) and Location of the machine you want:\n");
    }

	/**
	 * Method for implementing the locations of the area that customer chooses
	 * gets the value from area ComboBox and implements the locations that area 
	 * with help from the HashMap (locationsByArea) key
	 * @param event event of the choosing from ComboBox (choose area) 
	 */
	public void getLocation(ActionEvent event)
	{
		
		String location = IDAreaBox.getValue();
		IDLoactionBox.getItems().clear();
		
		if(location.equals("Haifa"))
		{
			IDLoactionBox.getItems().addAll(locationsByArea.get("Haifa"));
		}
		else if(location.equals("Shefa-Amer"))
		{
			IDLoactionBox.getItems().addAll(locationsByArea.get("Shefa-Amer"));
			
		}
		else if(location.equals("Karmiel"))
		{
			IDLoactionBox.getItems().addAll(locationsByArea.get("Karmiel"));
		}
		else if(location.equals("Tel-Aviv"))
		{
			IDLoactionBox.getItems().addAll(locationsByArea.get("Tel-Aviv"));
		}
		
		
	}
    /**
     * Method for moving forward to the next page "Catalog Page" after choosing location and area, also checks 
     * legality of the choices for example if you didn't choose it will throw an error message
     * through the error label 
     * also saves the customer choice in Customer class
     * @param event event of the the arrow (forward) icon clicked
     * @throws Exception Exception will be thrown if an error occurs when switching the stage 
     */
    public void next(MouseEvent event) throws Exception{
    	IDErrorLabel.setText("");
    	if(IDLoactionBox.getValue()==null||IDAreaBox.getValue()==null) {
    	IDErrorLabel.setText("You must Choose area and location");
    	}
    	else {
    		Customer.cameFrom="Location";//startOrder page
    		Order.area=IDAreaBox.getValue();
    		Order.location = IDLoactionBox.getValue();
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 			root=CommonMethods.switchScene(getClass(),stage, "CatalogPage.fxml","OrderCatalogPage.css");
    		
    	}
    }


}
