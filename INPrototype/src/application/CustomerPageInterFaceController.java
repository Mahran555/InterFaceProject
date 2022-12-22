package application;

import java.net.URL;
import java.util.ResourceBundle;

import customermethods.CommonMethods;
import customermethods.Customer;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import orders.Order;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

/**
 * This Class is a Customer Page Controller for CustomerPage.fxml ,it runs all the methods 
 * that functions the choices of the customer :
 * to Start Order 
 * to manage Orders
 * to MyCart
 * @author Mahran
 *
 */
public class CustomerPageInterFaceController extends Application implements Initializable{
	
	/**
	 * Label that shows the customer's type {subscriber,regular}
	 */
	@FXML
	private Label IDCustomerType;
	/**
	 * Label that shows the customer's statues {approved , suspended..}
	 */
	@FXML
    private Label IDCustomerStatues;
    /**
     * Label that shows the customer's id
     */
    @FXML
    private Label IDCustomerid;
    /**
     * Label that shows the customer's number of orders that he made
     */
    @FXML
    private Label IDNumOrders;
    /**
     * Label that shows the customer's name
     */
    @FXML
    private Label IDnamelabel1;
    /**
     * ImageView that contains a small cart icon for decoration 
     */
    @FXML
    private ImageView IDcartimg1;
    /**
     * ImageView that contains a small managing kit icon for decoration 
     */
    @FXML
    private ImageView IDsmanageordersimg1;
    /**
     * ImageView that contains a small starting choice icon for decoration 
     */
    @FXML
    private ImageView IDstartorderimg1;
    /**
     * a white container to contain objects {buttons ,images,..} and decoration 
     */
    @FXML 
    private Pane zr2;
    /**
     * Button to MyCart
     */
    @FXML
    private Button IDcart;
    /**
     * Button to Logout
     */
    @FXML
    private Button logoutBtn;
    /**
     * Button to Manage Orders
     */
    @FXML
    private Button manageO1;
    /**
     * Button to Start Order
     */
    @FXML
    private Button startO1;
    
  

	/**
	 * to save and show the stage
	 */
	Stage stage;
	/**
	 * to save and the root
	 */
	Parent root;
	/**
	 *Method to start the primary scene and set the stage 
	 *used only for starting from customer page nothing before 
	 *could be deleted
	 */
	@Override
	public void start(Stage primaryStage) {
		
		try {

			stage = primaryStage;
			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","Customerinterface.css");

			
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *initialize the customer details
	 *set the name
	 *set customer details
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		setNameLabel("hasan");
			setLabelsDetails("1","Approved","C01","Subscriber");
		
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
     * Method for logging out from customer page , switches stage to the login page
     * @param event event of the logout Button clicked
     * @throws Exception Exception will be thrown if an error occurs when switching the stage 
     */
    public void logout(ActionEvent event) throws Exception
   {
 	   
 			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 			root=CommonMethods.switchScene(getClass(),stage, "LoginHomePage.fxml","Logininterface.css");
	 
   }

	/**
	 * Method for clicking the help icon ,a windows will show with a message and explain the scene/page
	 * @param event event of the help icon clicked the scene/page and what every button do
	 * @throws Exception Exception will be thrown if an error occurs from Customer class 
	 */
	public void help(MouseEvent event) throws Exception{
  
    	Customer.help("This is a Customer homepage:\n"
    			+ "\nPress Start Order to create a new order"
    			+ "\nPress Manage Orders to check your previous Orders"
    			+ "\nPress Cart to go to your Current Cart(saved)"
    			+ "\nPress Logout to logout from your current page");

    }
 
    /**
     * Method to set the name label after welcome back label
     * @param name name of the customer
     */
    private void setNameLabel(String name)
    {
    	IDnamelabel1.setText(name);
    	Order.customerName = name;
    }
	/**
	 * Method to set/show customer details on his page 
	 * @param orderNumbers the number of orders that the customer made
	 * @param status the status of the customer in the system
	 * @param cID the cutsomer's id
	 * @param type the customer's type
	 */
	public void setLabelsDetails(String orderNumbers,String status,String cID,String type){//to set Customer details
		Order.customerID=cID;
		Order.customerType=type;
		IDNumOrders.setText("Orders: "+orderNumbers);
		IDCustomerStatues.setText("Status: "+status);
		IDCustomerid.setText("Customer ID: "+cID);
		IDCustomerType.setText("Type: "+type);
		
		
	}
	/**
	 * Method that changes stages when Start Order button is clicked
	 * @param event event of the Start Order Button clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage
	 */
	public void startOrder(ActionEvent event) throws Exception
	{

		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root=CommonMethods.switchScene(getClass(),stage, "StartOrderInterFace.fxml","StartOrderInterFace.css");
	
	}


	/**
	 * Method that changes stages when My Cart button is clicked
	 * @param event event of the My Cart Button clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage
	 */
	public void myCart(ActionEvent event) throws Exception
	{
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root=CommonMethods.switchScene(getClass(),stage, "CartPage.fxml","CartPage.css");
	}
	/**
	 * Method that changed stages when Manage Orders button is clicked
	 * @param event event of the Manage Orders Button clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage
	 */
	public void manageOrders(ActionEvent event) throws Exception{
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root=CommonMethods.switchScene(getClass(),stage, "ManageOrdersPage.fxml","ManageOrdersPage.css");
	}
}
