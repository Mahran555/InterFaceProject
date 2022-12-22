package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import orders.Order;

/**
 * This Class is a Payment Page Controller for PaymentPage.fxml ,it runs all the methods
 * that functions the choice of Paying for the order by the customer
 * the choices for subscriber are
 * Normal Payment (credit card)
 * Subscription Payment(monthly)
 * there is only one choice for normal customer to pay with credit card(normal payment)
 * Note: this class uses help from Customer and Order class to set and get some functionalities 
 * @author Mahran
 *
 */
public class PaymentController extends Application implements Initializable  {

	/**
	 * ArrayList to save the coupons of the customer
	 */
	private ArrayList<String> coupons = new ArrayList<>(Arrays.asList("None","Gift-10","Gift-20"));
	/**
	 * to save the final price
	 */
	private String finalPrice;
    /**
     * to save and show the stage
     */
    Stage stage;
    /**
     * to save and the root
     */
    Parent root;
    /**
     * ImageView to show what page customer came from
     */
    @FXML
    private ImageView IDLocationStep;
    /**
     * ImageView to show v mark after completing the payment
     */
    @FXML
    private ImageView IDCompleteMark;
    /**
     * RadioButton to select subscription payment option
     */
    @FXML
    private RadioButton IDSubPayment;
    /**
     * Label to show to total price
     */
    @FXML
    private Label IDTotalPrice;
    /**
     * Label that shows error message
     */
    @FXML
    private Label IDErrorLabel;
    /**
     * ComboBox to save/show coupons to choose from
     */
    @FXML
    private ComboBox<String> IDCoupon;
    /**
     * Button for cancel order
     */
    @FXML
    private Button IDCancel;
    /**
     * Button to the complete order(confirm payment) 
     */
    @FXML
    private Button IDConfirm;
    /**
     * TextField to get the customer's credit card number
     */
    @FXML
    private TextField IDCreditCard;
    /**
     * TextField to get the customer's credit card CVV
     */
    @FXML
    private TextField IDCvv;
    /**
     * TextField to get the customer's credit card expiry Month
     */
    @FXML
    private TextField IDDateMonth;
    /**
     * TextField to get the customer's credit card expiry year
     */
    @FXML
    private TextField IDDateYear;
    /**
     * TextField to get the Holder's name
     */
    @FXML
    private TextField IDHolderName;
    /**
     * Label to show page title
     */
    @FXML
    private Label IDTitle;
    /**
     * Button to use coupon
     */
    @FXML
    private Button IDUseCoupon;
    /**
	 *initialize labels and text fields
	 *initialize the current path that customer took
	 *initialize all the details that needed in the helper classes 
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Customer.IDLocationStep = IDLocationStep;
    	Customer.checkCustomerPath();
		IDErrorLabel.setVisible(false);
		IDSubPayment.setVisible(true);
		checkCustomer();
		IDCoupon.getItems().addAll(coupons);
		IDErrorLabel.setText("All fields with * mark must be filled correctly\n");
		IDTotalPrice.setText("Toltal Price: "+Order.orderPrice+"$");
			Customer.textLegality(IDCreditCard,16);
			Customer.textLegality(IDDateMonth,2);
			Customer.textLegality(IDDateYear,4);
			Customer.textLegality(IDCvv,3);
			IDCoupon.setOnAction(this::chooseCoupon);

		
	}
    /**
     *Method to start the primary scene and set the stage 
	 *used only for starting from customer page nothing before 
	 *could be deleted
     */
    @Override
	public void start(Stage primaryStage) {
    	stage= primaryStage;
		root=CommonMethods.switchScene(getClass(),stage, "PaymentPage.fxml","PaymentPage.css");

	}

    /**
     * Method to choose coupon from the coupon Combo Box and and calculate the final price after the coupon
     * and shows it on the screen
     * @param event if a coupon is choosed from the coupon Combo Box
     */
    public void chooseCoupon(ActionEvent event) {
    	if(IDCoupon.getValue()=="None")
    	{
    		finalPrice = Order.orderPrice;
			IDTotalPrice.setText("Toltal Price: "+finalPrice+"$");
    	}
    	if(IDCoupon.getValue()!="None"&&IDCoupon.getValue()!=null)
    		{
    			finalPrice = Double.parseDouble(Order.orderPrice)-Double.parseDouble(Order.orderPrice)*(CouponDiscount()/100)+"";
    			IDTotalPrice.setText("Toltal Price: "+finalPrice+"$");
    		}
    }
    /**
     * Method to activate the coupon and implemented on final price
     * in case customer chooses the coupon and choose to use it a message 
     * of confirmation would show to confirm his decision then it will remove the coupon 
     * from the coupon ComboBox
     * @param event event if use button is clicked
     */
    public void getCoupon(ActionEvent event) {
    	
    	if(IDCoupon.getValue()!="None"&&IDCoupon.getValue()!=null)
    	{
    		Customer.confirmationMessage("Do you want to use this coupon?\n\n","Coupon\n",getClass());
	  		if(Customer.respond == "yes")
	  		{
	  			String coupon = IDCoupon.getValue();
		    	if(Order.orderPrice!=null)
		    		Order.orderPrice = Double.parseDouble(Order.orderPrice)-Double.parseDouble(Order.orderPrice)*(CouponDiscount()/100)+"";
		    	IDTotalPrice.setText("Toltal Price: "+Order.orderPrice+"$");
		    	IDCoupon.getItems().remove(coupon);
	  		}
    		
    		
    		
	    	
    	}
    }
    
    /**
     * Method that gets the customer's coupon and returns the discount as a double 
     * @return discountNum
     */
    private double CouponDiscount() 
    {
    	String coupon = IDCoupon.getValue();
    	String discount[]=coupon.split("-");
    	double discountNum=Double.parseDouble(discount[1]);
    	return discountNum;
    }

	/**
	 * Method to check if the customer is a subscriber or a regular customer
	 */
	public void checkCustomer() {
		
		if(Order.customerType.equals("Subscriber")) {
			IDSubPayment.setVisible(true);
		}
		else 
			IDSubPayment.setVisible(false);
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
	
	/**
	 * Method for clicking the help icon ,a windows will show with a message and explain the scene/page
	 * @param event event of the help icon clicked the scene/page and what every button do
	 * @throws Exception Exception will be thrown if an error occurs from Customer class 
	 */
	public void help(MouseEvent event) throws Exception{
  	
	Customer.help("All Fields with * are necessary fill \n"
  			+ "Press Coupon and choose one if you have\n"
  			+ "Press confirm to submit your Order\n"
  			+ "Press Cancel Order to Cancel your order\n"
  			+ "Press <- to go back to the recieving options Page(if you want to change the method)\n" );

  }
	
	/**
	 * Method for going back to the previous page in this case the Receiving Options Page
	 * @param event event if the the arrow (back) icon clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
	 */
	public void back(MouseEvent event) throws Exception // close window
	  {
		
		
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root=CommonMethods.switchScene(getClass(),stage, "RecievingOptions.fxml","RecievingOptions.css");
		
	  }
	
	
	
	/**
	 * Method to confirm payment and checks the customer's payment information legality 
	 * in case the customer is a subscriber he can pay by credit option (monthly payment)
	 * after the customer confirms the payment it takes him back to his home page(switch scenes/stage)
	 * @param event event if the confirm Button is clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
	 */
	public void Confirm(ActionEvent event) throws Exception{
		
		if(Order.customerType.equals("Subscriber")&&IDSubPayment.isSelected())
		{
			Customer.confirmationMessage("Your total price is \n"+Order.orderPrice+"$ Do you want to proceed","Payment\n",getClass());
			if(Customer.respond=="yes")
			{
				IDCompleteMark.setVisible(true);
				IDErrorLabel.setVisible(false);
				Customer.CompletionMessage("Thank you\n Payment Confirmed ,your order has been submited \n"
			  			+ "an email would be send to you with the receipt\n");
				
				Order.clearOrder();
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  		root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","CustomerInterface.css");
			}
		}
		else if(checkEmptyFields(IDHolderName,IDCreditCard,IDDateMonth,IDDateYear,null)||IDCvv.getText()==null ||IDCvv.getText().length()!=3
			||checkEmptyFields(IDHolderName,IDCreditCard,IDDateMonth,IDDateYear,null)||IDCvv.equals("")||IDDateMonth.getText().length()!=2
			||IDDateYear.getText().length()!=4||IDCreditCard.getText().length()!=16) 
		{
			clearFields();
		}
		else
		{
			Customer.confirmationMessage("Your total prive is \n"+Order.orderPrice+" ,Do you want to proceed","Payment\n",getClass());
			if(Customer.respond=="yes")
			{
				IDCompleteMark.setVisible(true);
				IDErrorLabel.setVisible(false);
				Customer.CompletionMessage("Thank you\n Payment Confirmed ,your order has been submited \n"
			  			+ "an email would be send to you with the receipt\n");
				Order.clearOrder();
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  		root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","CustomerInterface.css");
			}
		}
				
	}
	/**
	 * Method to check if there is an empty field empty fields in any TextField given as a parameter
	 * true in case there is an empty field and false if there isn't
	 * @param IDFN first name text field
	 * @param IDLN last name text field
	 * @param IDPN phone number text field
	 * @param IDHN house number text field
	 * @param check another parameter if needed
	 * @return returns true in case there is an empty field and false if there isn't
	 */
	private boolean checkEmptyFields(TextField IDFN,TextField IDLN,TextField IDPN,TextField IDHN,String check) {
		if(IDFN.getText().equals(check)||IDLN.getText().equals(check)||IDPN.getText().equals(check)||IDHN.getText().equals(check))
			return true;
		return false;
	}
	
	/**
	 * Method that cancel the order in case Cancel Order button is clicked
	 * the method would show a message of confirmation for the customer 
	 * and ask confirmation from the customer, in case the answer is yes 
	 * the stage goes back to the Customer Page 
	 * @param event event of the Cancel Order button clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
	 */
	public void CancelOrder(ActionEvent event) throws Exception{//cancel order
		Customer.confirmationMessage("Do you want to cancel this order\n","Cancel Order\n",getClass());
		if(Customer.respond == "yes")
		{
			Order.clearOrder();//clear order of everything
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","CustomerInterface.css");
		}
	}

	/**
	 * Method to clear every field (TextField)
	 */
	private void clearFields() {
		IDHolderName.clear();
		IDCreditCard.clear();
		IDDateMonth.clear();
		IDDateYear.clear();
		IDCvv.clear();
		IDErrorLabel.setVisible(true);
	}

	



}
