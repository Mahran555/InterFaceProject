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


public class CustomerPageInterFaceController extends Application implements Initializable{

	@FXML
	private Label IDCustomerType;
	@FXML
    private Label IDCustomerStatues;
    @FXML
    private Label IDCustomerid;
    @FXML
    private Label IDNumOrders;
    @FXML
    private Label IDnamelabel1;
    @FXML
    private ImageView IDcartimg1;
    @FXML
    private ImageView IDsmanageordersimg1;
    @FXML
    private ImageView IDstartorderimg1;
    @FXML 
    private Pane zr2;
    @FXML
    private Button IDcart;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button manageO1;
    @FXML
    private Button startO1;
    
    //private HashMap<String,ArrayList<String> > customerInfo = new HashMap<String, ArrayList<String>>();

	Stage stage;
	Parent root;
	@Override
	public void start(Stage primaryStage) {
		
		try {

			stage = primaryStage;
			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","Customerinterface.css");

			
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		setNameLabel("hasan");//set the name
			setLabelsDetails("1","Approved","C01","Subscriber");//set customer details
		
	}
	public void clsoe(MouseEvent event) throws Exception
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
		
	  }

    public void logout(ActionEvent event) throws Exception
   {
 	   
 			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 			root=CommonMethods.switchScene(getClass(),stage, "LoginHomePage.fxml","Logininterface.css");
	 
   }

	public void help(MouseEvent event) throws Exception{
  
    	Customer.help("This is a Customer homepage:\n"
    			+ "\nPress Start Order to create a new order"
    			+ "\nPress Manage Orders to check your previous Orders"
    			+ "\nPress Cart to go to your Current Cart(saved)"
    			+ "\nPress Logout to logout from your current page");

    }
    public void setNameLabel(String name)//to set the name label after welcome back
    {
    	IDnamelabel1.setText(name);
    	Order.customerName = name;
    }
	public void setLabelsDetails(String orderNumbers,String status,String cID,String type){//to set Customer details
		Order.customerID=cID;
		Order.customerType=type;
		IDNumOrders.setText("Orders: "+orderNumbers);
		IDCustomerStatues.setText("Status: "+status);
		IDCustomerid.setText("Customer ID: "+cID);
		IDCustomerType.setText("Type: "+type);
		
		
	}
	public void startOrder(ActionEvent event) throws Exception
	{

		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root=CommonMethods.switchScene(getClass(),stage, "StartOrderInterFace.fxml","StartOrderInterFace.css");
	
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void myCart(ActionEvent event) throws Exception
	{
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root=CommonMethods.switchScene(getClass(),stage, "CartPage.fxml","CartPage.css");
	}
	public void manageOrders(ActionEvent event) throws Exception{
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root=CommonMethods.switchScene(getClass(),stage, "ManageOrdersPage.fxml","ManageOrdersPage.css");
	}
}
