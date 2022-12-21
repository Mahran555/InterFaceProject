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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import orders.Order;

public class CartPageInterFaceController extends Application implements Initializable {
	Stage stage;
	Parent root;
	
	private String[] DBName = {"Sprite","Elit Bar","Yoguta"};
	private String[] DBId = {"1","2","3"};
	private String[] DBCategory = {"Soft-Drinks","Choclate-Bars","jelly-Sweets"};
	private String[] DBPrice = {"1","2","0.5"};
	private String[] DBspecification = {"Others","Big-Size","Normal-Size"};
	private String[] DBMaxQuantities = {"10","1","3"};
	private String[] DBQuantities = {"2","1","3"};
	private String[] DBArea = {"Haifa","Haifa","Haifa"};
	private String[] DBLocation = {"Haifa-University","Haifa-University","Haifa-University"};
	private String[] DBOnsale = {"1","3"};
	private String DBDisccount = "20";
	@FXML
	private Label IDTotalCartPrice;
    @FXML
    private Label IDErrorEmptyCart;
	@FXML
	private Label IDDownPageAreaAndLocation;
	@FXML
	private Button IDCancelEditBtn;
	@FXML
	private Button IDCancelOrderBtn;
	@FXML
	private Button IDCompleteOrderBtn;
	@FXML
	private ImageView IDLocationStep1;
	@FXML
	private ImageView IDMethodsStep3;
	@FXML
	private ImageView IDOrderCatalagStep2;
	@FXML
	private ImageView IDPaymentStep4;
	@FXML
	private ScrollPane IDScrollPane;
	@FXML
	private ImageView IDhelp;
	@FXML
	private VBox IDvbox;
	@FXML
	private Pane zr2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//to identify loaded cart in data base set value of loadLastCart in order class to 0 or 1
		
		if(Order.productsInCart==null)
			Order.productsInCart=new ArrayList<>();
		IDErrorEmptyCart.setText("Cart is empty , Start new order\n");
		IDErrorEmptyCart.setTranslateX(-65);
		IDErrorEmptyCart.setVisible(false);
		if(Order.loadLastCart ==1)
			buildLastSavedCart();
		checkAndRelease();
		
	}
	private void buildLastSavedCart() {
		//getting data from DB all products saved in the same area and location
		Order.area = DBArea[0];
		Order.location = DBLocation[0];
		
		for(int i =0 ;i< 2 ; i++) 
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

		try {
			product.AddToCart(null);
		} catch (Exception e) {
			System.out.println("AddToCart In CartPage failed");
			e.printStackTrace();
		}
		}
		Order.loadLastCart = 0;//loadded
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage= primaryStage;
		root=CommonMethods.switchScene(getClass(),stage, "CartPage.fxml","CartPage.css");
		
	}
	private void checkAndRelease() {
		for(int i=0;i<Order.productsInOrder.size();i++)
		{
			if(i==0)//only one time display the area and location because every other product from the same location
				IDDownPageAreaAndLocation.setText(Order.productsInOrder.get(i).getArea()+", "+Order.productsInOrder.get(i).getLocation());
			view(Order.productsInOrder.get(i).getProductName(),Order.productsInOrder.get(i).getProductID(),Order.productsInOrder.get(i).getProductPrice(),Order.productsInOrder.get(i).getQuantity());
			IDTotalCartPrice.setText("Cart Total Price: "+Order.sumPrices+"$");
		}
	}
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

	
	public void back(MouseEvent event) throws Exception // close window
	  {
	
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","Customerinterface.css");
		
	  }
    @SuppressWarnings("static-access")
	public void help(MouseEvent event) throws Exception{//to the throw a message when "question" pressed

    	Customer.help("This is your auto saved Cart Page\n"
    			+ "Press Complete Order tp complete this order\n"
    			+ "Press Edit Order to Edit this order\n"
    			+ "Press Cancel Order to cancel this order");//showed message
    			
    }
    public void clsoe(MouseEvent event) throws Exception // close window
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
		
	  }
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
		 
		 public void editOrder(ActionEvent event) throws Exception
		 {
			 
			 IDErrorEmptyCart.setVisible(false);
			 //&&Order.loadLastCart == 0 deleted
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
