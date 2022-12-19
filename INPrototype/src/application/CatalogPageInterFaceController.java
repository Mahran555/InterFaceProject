package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import customermethods.CommonMethods;
import customermethods.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import orders.Order;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;




public class CatalogPageInterFaceController extends Application implements Initializable {

	private ArrayList<String> productsInCart = new ArrayList<>() ;
	Stage stage;
	Parent root;
	private Image img;
	private int size=3;
	private String[] category= {"All","Chips","Soft-Drinks","Choclate-Bars","jelly-Sweets","Others"};
	private String[] filter= {"All","Healthy","Low-Sugar","Big-Size","Normal-Size","Others"};
	private String[] DBName = {"Sprite","Elit Bar","Yoguta"};
	private String[] DBId = {"1","2","3"};
	private String[] DBCategory = {"Soft-Drinks","Choclate-Bars","jelly-Sweets"};
	private String[] DBPrice = {"1","2","0.5"};
	private String[] DBspecification = {"Others","Big-Size","Normal-Size"};
	private String[] DBQuantities = {"10","1","3"};
	private String[] DBArea = {"Haifa","Haifa","Haifa"};
	private String[] DBLocation = {"Haifa-University","Haifa-University","Haifa-University"};
	public double sumPrices = 0;
		@FXML
		private Label IDDownPageLocationAndArea;
		@FXML
		private Label IDErrorEmptyCart;
		@FXML
    	private Label IDProductNotFound;
		@FXML
    	private Button IDCancelOrderBtn;
	    @FXML
	    private Label IDTotalPrice;
		@FXML
		private Button IDCompleteOrderBtn;
		@FXML
	    private ComboBox<String> IDCat;
	    @FXML
	    private ImageView IDLocationStep;
	    @FXML
	    private ImageView IDMethodsStep3;
	    @FXML
	    private Label IDMyCart;
	    @FXML
	    private ImageView IDOrderCatalagStep2;
	    @FXML
	    private ImageView IDPaymentStep4;
	    @FXML
	    private TextField IDSearch;
	    @FXML
	    private Label IDTitle;
	    @FXML
	    private ComboBox<String> IDFilter;
	    @FXML
	    private ImageView IDhelp;
	    @FXML
	    private VBox IDvbox;
	    @FXML
	    private Pane zr2;
	    @FXML
	    private ScrollPane IDScrollPane;
	    @FXML
	    private ImageView IDCatImg;
	    @FXML
	    private ImageView IDFilterImg;
	    @FXML
	    private ImageView IDGoSearchImg;
	    @FXML
	    private ImageView IDSearchDataImg;
	    @FXML
	    private VBox IDMiniCartVBox;
	    @FXML
	    private ScrollPane IDScrollPaneMiniCart;
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//adding to combo box the value we want
		checkCustomerPath();
		Customer.con=this;
		IDDownPageLocationAndArea.setText(Order.area+", "+Order.location);
		Customer.IDErrorEmptyCart = IDErrorEmptyCart;
		IDErrorEmptyCart.setText("You can't Complete Order\n      with an empty cart");
		IDErrorEmptyCart.setTranslateX(65);
		Customer.IDMiniCartVBox = IDMiniCartVBox;
		Customer.IDTotalPrice=IDTotalPrice;
		Order.productsInCart = productsInCart;
		IDCat.getItems().addAll(category);
		IDFilter.getItems().addAll(filter);
		IDSearch.setText(null);
		IDErrorEmptyCart.setVisible(false);
		productsViewByCategoryAndFilter(null);//lunch as first time to load every thing 
		IDCat.setOnAction(this::productsViewByCategoryAndFilter);//if customer changed category
		IDFilter.setOnAction(this::productsViewByCategoryAndFilter);//if customer changed Filter
		isInCart();
		}
	@Override
	public void start(Stage primaryStage) {

		
		try {

			stage= primaryStage;
			root=CommonMethods.switchScene(getClass(),stage, "CatalogPage.fxml","OrderCatalogPage.css");
			

		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	public void clsoe(MouseEvent event) throws Exception // close window
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
		
	  }
	public void back(MouseEvent event) throws Exception // close window
	  {

		productsInCart.clear();
		if(Customer.cameFrom.equals("MyCart"))
		{
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 			root=CommonMethods.switchScene(getClass(),stage, "CartPage.fxml","CartPage.css");
		}
		else if(Customer.cameFrom.equals("Location"))
		{
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 			root=CommonMethods.switchScene(getClass(),stage, "StartOrderInterFace.fxml","StartOrderInterFace.css");
		}
			
	
	  }
	public void help(MouseEvent event) throws Exception{//to the throw a message when "question" pressed
  	Customer.help("You can change your product view with Filter or Category\n"
  			+ "(by default all category and all types would be shown)\n"
  			+ "Choose product\n"
  			+ "Choose quantity (1 by default)\n"
  			+ "Press Add To Cart\n"
  			+ "Press Cancel to canel Order\n"
  			+ "press <- to go back to location Page (you can change Location)\n"
  			+ "Press Complete Order to move to the next page\n");

  }
	public static void main(String[] args) {
		launch(args);
	}


	public void productsViewByCategoryAndFilter(ActionEvent event) {//to show the needed products according to category and filter or name  
		
		Boolean flag = false;//flag to check if there is products
		IDProductNotFound.setVisible(false);
		IDErrorEmptyCart.setVisible(false);
		IDvbox.getChildren().clear();//clear products view from scroll pane
		for(int i=0;i<size;i++) 	//check and view according to what the customer wants
			//reveal the products in that location and check their condition
			if(!DBQuantities[i].equals("0")&&DBArea[i].equals(Order.area)&&DBLocation[i].equals(Order.location)) 
				{
					checkCondition(i);
					flag=true;//there is at least one product found
				}
		if(flag==false)//no products found
			IDProductNotFound.setVisible(true);//set not found visible
		
	}
	private void checkCondition(int i)
	{
		
		if(IDCat.getValue()==null  && IDFilter.getValue()==null )
			view(DBName[i],DBId[i],DBCategory[i],DBPrice[i],DBspecification[i],DBQuantities[i]);
		else if(IDCat.getValue()=="All" && IDFilter.getValue()=="All"||IDCat.getValue()=="All" && IDFilter.getValue()==null || IDCat.getValue()==null && IDFilter.getValue()=="All")
			view(DBName[i],DBId[i],DBCategory[i],DBPrice[i],DBspecification[i],DBQuantities[i]);
		else if(IDCat.getValue()==DBCategory[i] && IDFilter.getValue()==DBspecification[i])
			view(DBName[i],DBId[i],DBCategory[i],DBPrice[i],DBspecification[i],DBQuantities[i]);
		else if(IDCat.getValue()=="All" && IDFilter.getValue()==DBspecification[i]||IDCat.getValue()==null && IDFilter.getValue()==DBspecification[i])
			view(DBName[i],DBId[i],DBCategory[i],DBPrice[i],DBspecification[i],DBQuantities[i]);
		else if(IDCat.getValue()==DBCategory[i] && IDFilter.getValue()=="All"||IDCat.getValue()==DBCategory[i]&& IDFilter.getValue()==null)
			view(DBName[i],DBId[i],DBCategory[i],DBPrice[i],DBspecification[i],DBQuantities[i]);

		
	}
	private void isInCart() {//for auto save cart
		
		if(Order.productsInOrder!=null) //check if there is products in product array
		{
		for(int i=0 ;i<Order.productsInOrder.size();i++) //enter the names of the products only if the location is correct
		{	
			if(Order.productsInOrder.get(i).getArea().equals(Order.area)&&Order.productsInOrder.get(i).getLocation().equals(Order.location))
				Order.productsInCart.add(Order.productsInOrder.get(i).getProductCell().getProductName());
			
		
		}	
		for(int i=0 ;i<Order.productsInOrder.size();i++) //add the products to the mini cart view
		{
			try {
				if(Order.productsInOrder.get(i).getArea().equals(Order.area)&&Order.productsInOrder.get(i).getLocation().equals(Order.location))
				{
					Order.i=i;
					Order.productsInOrder.get(i).getProductCell().AddToCart(null);
				}
				
				
			} 
			catch (Exception e) 
			{
				System.out.println("Catched isIncart\n");
				e.printStackTrace();
			}
			
		
		}
	
		}
	}
	private void view(String name,String id,String cate,String price,String spec,String quan) {
		IDErrorEmptyCart.setVisible(false);//remove error label from view
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
		product.setData(name, id, cate,price,spec,quan);
		IDvbox.getChildren().add(node);
		
		if(Order.productsInOrder!=null&&Order.checkProductInArray(name))
		{
			product.getAddingProperties().setVisible(false);
			product.getLabelAdded().setVisible(true);
		}
		
		

	}

	
	public void searchByName(MouseEvent event) throws Exception //search icon pressed 
	  {
		IDvbox.getChildren().clear();//clear products view from scroll pane
		for(int i=0;i<size;i++) {	//check and view according to what the customer wants
			if(IDSearch.getText()==null )
			{
			
				IDProductNotFound.setVisible(false);
				view(DBName[i],DBId[i],DBCategory[i],DBPrice[i],DBspecification[i],DBQuantities[i]);
				
				
			}
			else if(IDSearch.getText().equals(DBName[i]))
				{
				
				IDProductNotFound.setVisible(false);
				view(DBName[i],DBId[i],DBCategory[i],DBPrice[i],DBspecification[i],DBQuantities[i]);
			
				break;
				
				}
			else {
			
				IDProductNotFound.setVisible(true);
			}
			
		}
		
		IDSearch.clear();
		IDSearch.setText(null);
		


		
	  }
	public void searchByEnterKey(KeyEvent event)throws Exception //Enter Key pressed for search
	{
		IDvbox.getChildren().clear();//clear products view from scroll pane
		
		if(event.getCode()==KeyCode.ENTER) {
			for(int i=0;i<size;i++) {	//check and view according to what the customer wants
				if(IDSearch.getText()==null )
				{
				
					IDProductNotFound.setVisible(false);
					view(DBName[i],DBId[i],DBCategory[i],DBPrice[i],DBspecification[i],DBQuantities[i]);
					
					
				}
				else if(IDSearch.getText().equals(DBName[i]))
					{
					IDProductNotFound.setVisible(false);
					view(DBName[i],DBId[i],DBCategory[i],DBPrice[i],DBspecification[i],DBQuantities[i]);
					
					break;
					
					}
				else {
				
					IDProductNotFound.setVisible(true);
				}
				
			}
			
			IDSearch.clear();
			IDSearch.setText(null);
			
		
		}
	}
	 public void cancelOrder(ActionEvent event) throws Exception
	 {
		Customer.confirmationMessage("Do you want to cancel this order\n","Cancel Order\n",getClass());
		if(Customer.respond == "yes")
		{
			Order.clearOrder();
			IDvbox.getChildren().clear();
		 	IDMiniCartVBox.getChildren().clear();
		 	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 	root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","CustomerInterface.css");
		}
	 }
	   public void CompleteOrder(ActionEvent event) throws Exception
	   {
		   if(Order.sumPrices==0) {
			   
			 
			   IDErrorEmptyCart.setVisible(true);
			   
		   }
		   else 
		   {
			   Order.orderPrice=sumPrices+"";
			   Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			   root=CommonMethods.switchScene(getClass(),stage, "RecievingOptions.fxml","RecievingOptions.css");
		   }
	    }


}

