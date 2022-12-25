package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import customermethods.CommonMethods;
import customermethods.Customer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import orders.Order;

/**
 * This Class is a Cart Page Controller for CartPage.fxml ,it runs all the methods
 * that functions the choice of the customer such as 
 * edit order
 * cancel order
 * complete order
 * This class also uses other classes and other controllers to to help with the stage/scene
 * Classes such as :
 * BigCartCellInterFaceConrtoller
 * to help build the big cart cell
 * Note: this class uses help from Customer and Order class to set and get some functionalities 
 * @author Mahran
 *
 */
public class CartPageInterFaceController extends Application implements Initializable {
	/**
	 * to save and show the stage
	 */
	Stage stage;
	/**
	 * to save and the root
	 */
	Parent root;
	/**
	 * to save the size of columns information in the data base
	 */
	private int DBSize = 2;
	/**
	 * To get all Informations from the data base and saving them in arrays of strings 
	 */
	private String[] DBName = {"Sprite","Elit Bar","Yoguta"};
	private String[] DBId = {"1","11","12"};
	private String[] DBCategory = {"Soft-Drinks","Choclate-Bars","jelly-Sweets"};
	private String[] DBPrice = {"1","2","0.5"};
	private String[] DBspecification = {"Others","Big-Size","Normal-Size"};
	private String[] DBMaxQuantities = {"10","1","3"};
	private String[] DBQuantities = {"2","1","3"};
	private String[] DBArea = {"Haifa","Haifa","Haifa"};
	private String[] DBLocation = {"Haifa-University","Haifa-University","Haifa-University"};
	private String[] DBOnsale = {"1","2"};
	private String DBDisccount = "20";
	/**
	 * Label to show the cart's price
	 */
	@FXML
	private Label IDTotalCartPrice;
    /**
     * Label to show error "Empty cart"
     */
    @FXML
    private Label IDErrorEmptyCart;
	/**
	 * Label to show to the area and location of the cart
	 */
	@FXML
	private Label IDDownPageAreaAndLocation;
	/**
	 * Button to edit the order cart
	 */
	@FXML
	private Button IDEditBtn;
	/**
	 * Button to cancel the order in the cart
	 */
	@FXML
	private Button IDCancelOrderBtn;
	/**
	 * Button to complete the order in the cart
	 */
	@FXML
	private Button IDCompleteOrderBtn;
	/**
	 * VBox to save/show the products in the cart
	 */
	@FXML
	private VBox IDvbox;


	/**
	 *initialize the product in the cart
	 *initialize the saved cart if it exists
	 *initialize all the details that needed in the helper classes 
	 *and in the current class
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(Order.productsInCart==null)
			Order.productsInCart=new ArrayList<>();
		IDErrorEmptyCart.setText("Cart is empty , Start new order\n");
		IDErrorEmptyCart.setTranslateX(-65);
		IDErrorEmptyCart.setVisible(false);
		if(Order.loadLastCart ==1)
			buildLastSavedCart();
		checkAndRelease();
		
	}
	/**
	 *Method to start the primary scene and set the stage 
	 *used only for starting from customer page nothing before 
	 *could be deleted
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage= primaryStage;
		root=CommonMethods.switchScene(getClass(),stage, "CartPage.fxml","CartPage.css");
		
	}
	
	/**
	 * Method to build/load the products cell for saved cart 
	 * and sets it as loaded
	 */
	@SuppressWarnings("unused")
	private void buildLastSavedCart() {
		Order.area = DBArea[0];
		Order.location = DBLocation[0];
		
		for(int i =0 ;i< DBSize ; i++) 
		{
		Node node = null;
		FXMLLoader fXLoader = new FXMLLoader();
		fXLoader.setLocation(getClass().getResource("/FXMLs/ProductCell.fxml"));
	
		try {
			
			node = fXLoader.load();
			
		} catch (IOException e) {
			System.out.println("Exception in view - product Cell");
			e.printStackTrace();
		}
		ProductCellController product= fXLoader.getController();
		product.setData(DBName[i], DBId[i], DBCategory[i],DBPrice[i],DBspecification[i],DBMaxQuantities[i]);
		product.setQuantity(DBQuantities[i]);
		for(int j=0 ; j<DBOnsale.length;j++)
			if(DBOnsale[j].equals(DBId[i]))
				product.setProductDiscount(DBDisccount);
		try {
			product.AddToCart(null);
		} catch (Exception e) {
			System.out.println("AddToCart In CartPage failed");
			e.printStackTrace();
		}
		}
		Order.loadLastCart = 0;
	}

	/**
	 * Method to set location of the order and call for the building of the cart cells
	 */
	private void checkAndRelease() {
		for(int i=0;i<Order.productsInOrder.size();i++)
		{
			if(i==0)
				IDDownPageAreaAndLocation.setText(Order.productsInOrder.get(i).getArea()+", "+Order.productsInOrder.get(i).getLocation());
			view(Order.productsInOrder.get(i).getProductName(),Order.productsInOrder.get(i).getProductID(),Order.productsInOrder.get(i).getProductPrice(),Order.productsInOrder.get(i).getQuantity());
			IDTotalCartPrice.setText("Cart Total Price: "+Order.sumPrices+"$");
		}
	}
	/**
	 * Method to build a cart cell
	 * @param name name of the product
	 * @param id id of the product
	 * @param price price of the product
	 * @param quantity quantity of the product
	 */
	private void view(String name,String id,String price,String quantity) {
		
		Node node = null;
		FXMLLoader fXLoader = new FXMLLoader();
		fXLoader.setLocation(getClass().getResource("/FXMLs/BigCartCell.fxml"));
		try {
			
			node = fXLoader.load();
			
		} catch (IOException e) {
			System.out.println("Exception in view - Big Cart Cell");
			e.printStackTrace();
		}
		BigCartCellInterFaceController product= fXLoader.getController();
		product.setData(name, id,price,quantity);
		IDvbox.getChildren().add(node);
	
	}

	
	/**
	 * Method for going back to the previous page in this case the Cart Page "MyCart"
	 * @param event event if the the arrow (back) icon clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
	 */
	public void back(MouseEvent event) throws Exception // close window
	  {
	
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","Customerinterface.css");
		
	  }
	/**
	 * Method for clicking the help icon ,a windows will show with a message and explain the scene/page
	 * @param event event of the help icon clicked the scene/page and what every button do
	 * @throws Exception Exception will be thrown if an error occurs from Customer class 
	 */
	public void help(MouseEvent event) throws Exception{//to the throw a message when "question" pressed

    	Customer.help("This is your auto saved Cart Page\n"
    			+ "Press Complete Order tp complete this order\n"
    			+ "Press Edit Order to Edit this order\n"
    			+ "Press Cancel Order to cancel this order");//showed message
    			
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
	 * Method that cancel the order in case Cancel Order button is clicked
	 * the method would show a message of confirmation for the customer 
	 * and ask confirmation from the customer, in case the answer is yes 
	 * the stage goes back to the Customer Page 
	 * @param event event of the Cancel Order button clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
	 */
	public void cancelOrder(ActionEvent event) throws Exception
	 {
		 IDErrorEmptyCart.setVisible(false);
		   if(Order.productsInOrder.isEmpty()) {
			   
			 
			   IDErrorEmptyCart.setVisible(true);
			   
		   }
		   else {
			Customer.confirmationMessage("Do you want to cancel this order\n","Cancel Order\n",getClass());
			if(Customer.respond == "yes")
			{
				Order.loadLastCart = 0;
				Order.clearOrder();
				IDvbox.getChildren().clear();
			 	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 	root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","CustomerInterface.css");
			}
		 }
	 }


		 /**
		  * Method that takes the customer to the next step in completing the order the Receiving Method Page
		  * in case clicked without an actual order in the cart a label error would be thrown
		  * @param event event of the Complete Order button clicked
		  * @throws Exception Exception will be thrown if an error occurs when switching the stage 
		 */
		public void CompleteOrder(ActionEvent event) throws Exception
		   {
			 IDErrorEmptyCart.setVisible(false);
			   if(Order.productsInOrder.isEmpty()) {
				   
				 
				   IDErrorEmptyCart.setVisible(true);
				   
			   }
			   else 
			   {
				   Order.loadLastCart = 0;
				   Customer.cameFrom="MyCart";
				   Order.orderPrice=Order.sumPrices+"";
				   Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				   root=CommonMethods.switchScene(getClass(),stage, "RecievingOptions.fxml","RecievingOptions.css");
			   }
		    }
		 
		/**
		 * Method that edit the order in case Edit Order button is clicked
		 * the method checks if the cart is empty ,in case its not is saves 
		 * the path that customer comes from "MyCart" and changes the stage/page 
		 * to the catalog page so the customer can edit his order
		 * @param event event if Edit Order Button is clicked
		 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
		 */
		public void editOrder(ActionEvent event) throws Exception
		 {
			 
			 IDErrorEmptyCart.setVisible(false);
			   if(Order.productsInOrder.isEmpty()) {
				   
					 
				   IDErrorEmptyCart.setVisible(true);
				   
			   }
			   else 
			   {
				   Customer.cameFrom="MyCart";
				   Order.orderPrice=Order.sumPrices+"";
				   Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				   root=CommonMethods.switchScene(getClass(),stage, "CatalogPage.fxml","OrderCatalogPage.css");
			   }
			 
		 }

}
