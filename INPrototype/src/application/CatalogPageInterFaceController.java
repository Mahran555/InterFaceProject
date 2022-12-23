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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;


/**
 * This Class is a Catalog Page Controller for CatalogPage.fxml ,it runs all the methods
 * that functions the choice of the customer such as 
 * the category choice
 * the filter choice
 * the search bar (by name) 
 * This class also uses other classes and other controllers to to help with the stage/scene
 * Classes such as :
 * ProductCellController
 * Note: this class uses help from Customer and Order class to set and get some functionalities 
 * @author Mahran
 *
 */

public class CatalogPageInterFaceController extends Application implements Initializable {

	/**
	 * ArrayList of strings to save the products name in the cart
	 */
	private ArrayList<String> productsInCart = new ArrayList<>() ;
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
	private int size=3;
	/**
	 * To get all Informations from the data base and saving them in arrays of strings 
	 */
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
	private String[] DBOnsale = {"1"};
	private String DBDisccount = "20";
		/**
		 * Label that shows area and location 
		 */
		@FXML
		private Label IDDownPageLocationAndArea;
		/**
		 * Label that shows error message
		 */
		@FXML
		private Label IDErrorEmptyCart;
		/**
		 * Label that shows Product not found
		 */
		@FXML
    	private Label IDProductNotFound;
		/**
		 * Button for cancel order
		 */
		@FXML
    	private Button IDCancelOrderBtn;
	    /**
	     * Label that shows Total price
	     */
	    @FXML
	    private Label IDTotalPrice;
		/**
		 * Button for Complete Order 
		 */
		@FXML
		private Button IDCompleteOrderBtn;
		/**
		 * ComboBox to save/show Categories to choose from
		 */
		@FXML
	    private ComboBox<String> IDCat;
	    /**
	     * ImageView to show what page customer came from
	     */
	    @FXML
	    private ImageView IDLocationStep;
	    /**
	     * TextField to search products by name
	     */
	    @FXML
	    private TextField IDSearch;
	    /**
	     * Label to show title of the page
	     */
	    @FXML
	    private Label IDTitle;
	    /**
	     * ComboBox to save/show Filter to choose from
	     */
	    @FXML
	    private ComboBox<String> IDFilter;
	    /**
	     * VBox to save/show the products for the customer 
	     */
	    @FXML
	    private VBox IDvbox;

	    /**
	     * VBox to save/show the products in Mini Cart for the customer 
	     */
	    @FXML
	    private VBox IDMiniCartVBox;
	    
	/**
	 *initialize the current path that customer took
	 *initialize all the details that needed in the helper classes 
	 *and in the current class
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Customer.IDLocationStep = IDLocationStep;
		Customer.checkCustomerPath();
		Order.loadLastCart=0;
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
		productsViewByCategoryAndFilter(null);
		IDCat.setOnAction(this::productsViewByCategoryAndFilter);
		IDFilter.setOnAction(this::productsViewByCategoryAndFilter);
		isInCart();
		}
	/**
	 *Method to start the primary scene and set the stage 
	 *used only for starting from customer page nothing before 
	 *could be deleted
	 */
	@Override
	public void start(Stage primaryStage) {

		
		try {

			stage= primaryStage;
			root=CommonMethods.switchScene(getClass(),stage, "CatalogPage.fxml","OrderCatalogPage.css");
			

		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	 * Method for going back to the previous page in this case the Location Page
	 * @param event event if the the arrow (back) icon clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
	 */
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
	/**
	 * Method for clicking the help icon ,a windows will show with a message and explain the scene/page
	 * @param event event of the help icon clicked the scene/page and what every button do
	 * @throws Exception Exception will be thrown if an error occurs from Customer class 
	 */
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

	/**
	 * Method to check and show the needed products according to category and filter or name
	 * and location and area, in case there is no product found for these category or filter or name
	 * a label would show that there is no products found
	 * @param event event of the Category or Filter Changed icon clicked
	 * flag inner boolean variable to check if there is products to help with 
	 * product not found label
	 */
	public void productsViewByCategoryAndFilter(ActionEvent event) { 
		
		Boolean flag = false;
		IDProductNotFound.setVisible(false);
		IDErrorEmptyCart.setVisible(false);
		IDvbox.getChildren().clear();
		for(int i=0;i<size;i++) 	
			if(!DBQuantities[i].equals("0")&&DBArea[i].equals(Order.area)&&DBLocation[i].equals(Order.location)) 
				{
					checkCondition(i);
					flag=true;
				}
		if(flag==false)
			IDProductNotFound.setVisible(true);
		
	}
	/**
	 * Method to check specific conditions of each product
	 * and if the conditions matches with the category or search choice 
	 * View method would be called
	 * @param i i is an index parameter of each product in Data Base Array of strings 
	 */
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
	/**
	 * Method for auto save cart that checks if there is products in the cart in each entry of Catalog Page
	 * and adds them if needed
	 */
	private void isInCart() {
		Customer.IDMiniCartVBox = IDMiniCartVBox;
		
		if(Order.productsInOrder!=null) 
		{
		for(int i=0 ;i<Order.productsInOrder.size();i++) 
		{	
			if(Order.productsInOrder.get(i).getArea().equals(Order.area)&&Order.productsInOrder.get(i).getLocation().equals(Order.location))
				Order.productsInCart.add(Order.productsInOrder.get(i).getProductCell().getProductName());
			
		
		}	
		for(int i=0 ;i<Order.productsInOrder.size();i++) 
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
	/**
	 * Method to create a cell and object for each product with its information and shows it in the stage/scene
	 * it also acknowledge if there is a discount and sets in the product cell and object 
	 * @param name name of the product
	 * @param id id of the product
	 * @param cate category of the product
	 * @param price price of the product
	 * @param spec specification to help with filtering 
	 * @param maxquantity max quantity of the product
	 */
	private void view(String name,String id,String cate,String price,String spec,String maxquantity) {
		IDErrorEmptyCart.setVisible(false);
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
		product.setData(name, id, cate,price,spec,maxquantity);
		for(int i=0 ; i<DBOnsale.length;i++)
			if(DBOnsale[i].equals(id))
				product.setProductDiscount(DBDisccount);
				
		IDvbox.getChildren().add(node);
		
		if(Order.productsInOrder!=null&&Order.checkProductInArray(name))
		{
			product.getAddingProperties().setVisible(false);
			product.getLabelAdded().setVisible(true);
		}
		


	}

	
	/**
	 * Method that checks and view according to what the customer searched in the search bar(for product's name)
	 * and clicked on the search icon,in case the search bar is empty and search icon clicked all the products
	 * would be shown(normally ,nothing to search for)
	 * @param event event of clicking search icon 
	 * @throws Exception Exception will be thrown if an error occurs
	 */
	public void searchByName(MouseEvent event) throws Exception //search icon pressed 
	  {
		IDvbox.getChildren().clear();//clear products view from scroll pane
		for(int i=0;i<size;i++) {	
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
	/**
	 * Method that checks and view according to what the customer searched in the search bar(for product's name)
	 * and clicked on the Enter key ,in case the search bar is empty and search icon clicked all the products
	 * would be shown(normally ,nothing to search for)
	 * @param event event of clicking Enter key in search bar
	 * @throws Exception Exception will be thrown if an error occurs
	 */
	public void searchByEnterKey(KeyEvent event)throws Exception //Enter Key pressed for search
	{
		IDvbox.getChildren().clear();//clear products view from scroll pane
		
		if(event.getCode()==KeyCode.ENTER) {
			for(int i=0;i<size;i++) {	
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
	/**
	 * Method that takes the customer to the next step in completing the order the Receiving Method Page
	 * in case clicked without an actual order a label error would be thrown
	 * @param event event of the Complete Order button clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
	 */
	public void CompleteOrder(ActionEvent event) throws Exception
	   {
		   if(Order.sumPrices==0) {
			   
			 
			   IDErrorEmptyCart.setVisible(true);
			   
		   }
		   else 
		   {
			   Order.orderPrice=Order.sumPrices+"";
			   Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			   root=CommonMethods.switchScene(getClass(),stage, "RecievingOptions.fxml","RecievingOptions.css");
		   }
	    }


}

