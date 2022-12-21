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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import orders.Order;

public class PaymentController extends Application implements Initializable  {

	private ArrayList<String> coupons = new ArrayList<>(Arrays.asList("None","Gift-10","Gift-20"));
	private String finalPrice;
    Stage stage;
    Parent root;
    private Image img;
    @FXML
    private ImageView IDLocationStep;
    @FXML
    private ImageView IDCompleteMark;
    @FXML
    private RadioButton IDSubPayment;
    @FXML
    private Label IDTotalPrice;
    @FXML
    private Label IDErrorLabel;
    @FXML
    private ComboBox<String> IDCoupon;
    @FXML
    private Button IDCancel;

    @FXML
    private Button IDConfirm;

    @FXML
    private TextField IDCreditCard;

    @FXML
    private TextField IDCvv;

    @FXML
    private TextField IDDateMonth;

    @FXML
    private TextField IDDateYear;

    @FXML
    private TextField IDHolderName;

    @FXML
    private Label IDTitle;
    @FXML
    private Pane zr2;
    @FXML
    private Button IDUseCoupon;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	checkCustomerPath();
		IDErrorLabel.setVisible(false);
		IDSubPayment.setVisible(true);
		checkCustomer();
		IDCoupon.getItems().addAll(coupons);
		IDErrorLabel.setText("All fields with * mark must be filled correctly\n");
		IDTotalPrice.setText("Toltal Price: "+Order.orderPrice+"$");
			Customer.textLegality(IDCreditCard,16);//text legality for credit card number with length 16
			Customer.textLegality(IDDateMonth,2);//text legality for month with length 2
			Customer.textLegality(IDDateYear,4);//text legality for year with length 4
			Customer.textLegality(IDCvv,3);//text legality for cvv with length 2
			IDCoupon.setOnAction(this::chooseCoupon);

		
	}
  
	private void checkCustomerPath() {
		if(Customer.cameFrom.equals("MyCart"))
		{
			img = new Image("/icons/shopping-cart.png");
			IDLocationStep.setImage(img);
		}
		else if(Customer.cameFrom.equals("Location"))
		{
			img = new Image("/icons/map.png");
			IDLocationStep.setImage(img);
		}
		
	}
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
    //use coupon pressed
    public void getCoupon(ActionEvent event) {
    	
    	if(IDCoupon.getValue()!="None"&&IDCoupon.getValue()!=null)
    	{
    		Customer.confirmationMessage("Do you want to use this coupon?\n\n","Coupon\n",getClass());//init a message 
	  		if(Customer.respond == "yes")//if user pressed yes
	  		{
	  			//activate the coupon and remove it
	  			String coupon = IDCoupon.getValue();
		    	if(Order.orderPrice!=null)
		    		Order.orderPrice = Double.parseDouble(Order.orderPrice)-Double.parseDouble(Order.orderPrice)*(CouponDiscount()/100)+"";
		    	IDTotalPrice.setText("Toltal Price: "+Order.orderPrice+"$");
		    	IDCoupon.getItems().remove(coupon);
	  		}
    		
    		
    		
	    	
    	}
    }
    
    private double CouponDiscount() //get the discount from the coupon (number)
    {
    	String coupon = IDCoupon.getValue();
    	String discount[]=coupon.split("-");
    	double discountNum=Double.parseDouble(discount[1]);
    	return discountNum;
    }

	public void checkCustomer() {//if the customer is a subscriber then he can pay using subscribtion payment
		
		if(Order.customerType.equals("Subscriber")) {
			IDSubPayment.setVisible(true);//show subscribtion payment option
		}
		else 
			IDSubPayment.setVisible(false);//hide subscribtion payment option(not subscriber)
	}
    @Override
	public void start(Stage primaryStage) {
    	stage= primaryStage;
		root=CommonMethods.switchScene(getClass(),stage, "PaymentPage.fxml","PaymentPage.css");

	}
	
	public void clsoe(MouseEvent event) throws Exception // close window
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
	  }
	
	public void help(MouseEvent event) throws Exception{//to the throw a message when "question" pressed
  	
	Customer.help("All Fields with * are necessary fill \n"
  			+ "Press Coupon and choose one if you have\n"
  			+ "Press confirm to submit your Order\n"
  			+ "Press Cancel Order to Cancel your order\n"
  			+ "Press <- to go back to the recieving options Page(if you want to change the method)\n" );//showed message

  }
	
	public void back(MouseEvent event) throws Exception // close window
	  {
		
		
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root=CommonMethods.switchScene(getClass(),stage, "RecievingOptions.fxml","RecievingOptions.css");
		
	  }
	
	
	public void Confirm(ActionEvent event) throws Exception{//confirm button pressed(checkout)
		
		if(Order.customerType.equals("Subscriber")&&IDSubPayment.isSelected())//coustmer is subscriber and paying monthly
		{
			//completed order and switch to customer home page
			Customer.confirmationMessage("Your total price is \n"+Order.orderPrice+"$ Do you want to proceed","Payment\n",getClass());
			if(Customer.respond=="yes")
			{
				IDCompleteMark.setVisible(true);//v mark for payment
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
		else//regular customer 
		{//completed order and switch to customer home page
			Customer.confirmationMessage("Your total prive is \n"+Order.orderPrice+" ,Do you want to proceed","Payment\n",getClass());
			if(Customer.respond=="yes")
			{
				IDCompleteMark.setVisible(true);//v mark for payment
				IDErrorLabel.setVisible(false);
				Customer.CompletionMessage("Thank you\n Payment Confirmed ,your order has been submited \n"
			  			+ "an email would be send to you with the receipt\n");
				Order.clearOrder();
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  		root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","CustomerInterface.css");
			}
		}
				
	}
	private boolean checkEmptyFields(TextField IDFN,TextField IDLN,TextField IDPN,TextField IDHN,String check) {
		if(IDFN.getText().equals(check)||IDLN.getText().equals(check)||IDPN.getText().equals(check)||IDHN.getText().equals(check))
			return true;
		return false;
	}
	
	public void CancelOrder(ActionEvent event) throws Exception{//cancel order
		Customer.confirmationMessage("Do you want to cancel this order\n","Cancel Order\n",getClass());
		if(Customer.respond == "yes")
		{
			Order.clearOrder();//clear order of everything
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","CustomerInterface.css");
		}
	}

	private void clearFields() {
		IDHolderName.clear();
		IDCreditCard.clear();
		IDDateMonth.clear();
		IDDateYear.clear();
		IDCvv.clear();
		IDErrorLabel.setVisible(true);
	}

	



}
