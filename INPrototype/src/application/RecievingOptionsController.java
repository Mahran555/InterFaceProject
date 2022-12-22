package application;


import java.net.URL;
import java.util.ResourceBundle;

import customermethods.CommonMethods;
import customermethods.Customer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import orders.Order;

public class RecievingOptionsController extends Application implements Initializable {
    Stage stage;
    Parent root;
    @FXML
    private ImageView IDLocationStep;
    @FXML
    private Button IDCancelOrder;
    @FXML
    private Label IDErrorMustFill;
    @FXML
    private Label IDPickupText;
    @FXML
    private RadioButton IDRadioDelivery;
    @FXML
    private RadioButton IDRadioPickup; 
    @FXML
    private ImageView IDOptionImg;
    @FXML
    private Button IDCheckout;
    @FXML
    private TextField IDHouseNumber;
    @FXML
    private TextField IDStreet;
    @FXML
    private TextField IDFirstName;
    @FXML
    private TextField IDLastName;
    @FXML
    private TextField IDPhoneNumber;
    @FXML
    private AnchorPane DeliveryInfo;
    private Image img;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Customer.IDLocationStep = IDLocationStep;
		Customer.checkCustomerPath();
		Order.orderPrice = Order.sumPrices+"";
		IDErrorMustFill.setText("All fields with * mark must be filled corretly\n");
		IDErrorMustFill.setVisible(false);
		DeliveryInfo.setVisible(false);
		IDOptionImg.setVisible(false);
		IDCheckout.setVisible(false);
		Customer.textLegality(IDPhoneNumber,10);//text legality for phone number with length 10
		Customer.textLegality(IDHouseNumber,3);//text legality for house number with length 3
	}
	/**
	 *Method to start the primary scene and set the stage 
	 *used only for starting from customer page nothing before 
	 *could be deleted
	 */
	@Override
	public void start(Stage primaryStage) {
		stage= primaryStage;
		root=CommonMethods.switchScene(getClass(),stage, "RecievingOptions.fxml","RecievingOptions.css");

	}
    
	
	/**
	 * Method for closing the application, the window of the application would be closed
	 * @param event event of the X icon clicked
	 * @throws Exception Exception will be thrown if an error occurs
	 */
	public void clsoe(MouseEvent event) throws Exception // close window
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
	  }
	

	public void help(MouseEvent event) throws Exception{//to the throw a message when "question" pressed
  	
	Customer.help("pick your recieving option \n"
  			+ "Press PickUp to pick the order yourself\n"
  		   +  "Press Delivery and fill delivery information for delivery option\n"
  		   +  "Press Checkout to complete your order\n"
  		   +  "Press Cancel Order if you wish to cancel your order\n" );//showed message

  }
	
	public void back(MouseEvent event) throws Exception // close window
	  {
		
		
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root=CommonMethods.switchScene(getClass(),stage, "CatalogPage.fxml","OrderCatalogPage.css");
		
	  }
	public void PhysicalRecieving(ActionEvent event) throws Exception
	{
		IDRadioPickup.setSelected(true);//keep it selected
		IDErrorMustFill.setVisible(false);//error label
		IDCheckout.setVisible(true);//check out button show
		IDPickupText.setVisible(true);//show the text of pickup when selected
		IDPickupText.setText("You can pickup your order\n"//set text when pickup choosed
				+ "after completing your order \n"
				+ "press checkout to processed ");
		IDRadioDelivery.setSelected(false);//remove selection of delivery radio button
		//insert the needed image
		img = new Image("/icons/pickup.png");
		IDOptionImg.setImage(img);
		IDOptionImg.setVisible(true);
		DeliveryInfo.setVisible(false);//hide delivery information fields
		
	}
	
	public void DeliveryRecieving(ActionEvent event) throws Exception
	{
		IDRadioDelivery.setSelected(true);//keep it selected
		IDErrorMustFill.setVisible(false);//error label
		IDCheckout.setVisible(true);//check out button show
		IDPickupText.setVisible(false);//hide pickup text
		IDRadioPickup.setSelected(false);//remove selection of pickup radio button
		//insert the needed image
		img = new Image("/icons/delivery-man.png");
		IDOptionImg.setImage(img);
		IDOptionImg.setVisible(true);
		DeliveryInfo.setVisible(true);//show delivery information fields
		
	}
	
	public void ProcceedToPayment(ActionEvent event) throws Exception
	{
		
		if(IDRadioDelivery.isSelected()) 
		{
			
			if(checkEmptyFields(IDFirstName,IDLastName,IDPhoneNumber,IDHouseNumber,null) || checkEmptyFields(IDFirstName,IDLastName,IDPhoneNumber,IDHouseNumber,"")
					||IDPhoneNumber.getText().length()!=10 || IDHouseNumber.getText().length()!=3)
			{
				clearFields();
				
			}
			else
			{
				Order.methodOfDelivery = "Delivery";//set the method in order class as delivery
				//set delivery information
				Order.setDelivery(IDFirstName.getText(),IDLastName.getText(),IDPhoneNumber.getText(),IDHouseNumber.getText(),IDStreet.getText());
				//change to the next page
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				root=CommonMethods.switchScene(getClass(),stage, "PaymentPage.fxml","PaymentPage.css");
				
			}
		}
		else {
			Order.methodOfDelivery = "PickUp";//set the method in order class as pickup
			//change to the next page
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root=CommonMethods.switchScene(getClass(),stage, "PaymentPage.fxml","PaymentPage.css");
		}
	}
	//to check empty fields 
	private boolean checkEmptyFields(TextField IDFN,TextField IDLN,TextField IDPN,TextField IDHN,String check) {
		if(IDFN.getText().equals(check)||IDLN.getText().equals(check)||IDPN.getText().equals(check)||IDHN.getText().equals(check))
			return true;
		return false;
	}
	
	public void CancelOrder(ActionEvent event) throws Exception{//cancel order
		Customer.confirmationMessage("Do you want to cancel this order\n","Cancel Order\n",getClass());
		if(Customer.respond == "yes")
		{
		Order.clearOrder();//clear everything 
		//go to the customer homepage
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","CustomerInterface.css");
		}
	}



    private void clearFields() {
    	IDFirstName.clear();
		IDLastName.clear();
		IDPhoneNumber.clear();
		IDHouseNumber.clear();
		IDStreet.clear();
		IDErrorMustFill.setVisible(true);
    }

    public String HouseNumber() {
		return IDHouseNumber.getText();
	}

	public void setHouseNumber(TextField houseNumber) {
		IDHouseNumber = houseNumber;
	}

	public String getStreet() {
		return IDStreet.getText();
	}

	public void setStreet(TextField street) {
		IDStreet = street;
	}

	public String getFirstName() {
		return IDFirstName.getText();
	}

	public void setFirstName(TextField firstName) {
		IDFirstName = firstName;
	}

	public String getLastName() {
		return IDLastName.getText();
	}

	public void setLastName(TextField lastName) {
		IDLastName = lastName;
	}

	public String getPhoneNumber() {
		return IDPhoneNumber.getText();
	}

	public void setPhoneNumber(TextField phoneNumber) {
		IDPhoneNumber = phoneNumber;
	}
}
