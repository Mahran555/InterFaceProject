package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import customermethods.Customer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import orders.Order;

/**
 * This Class is a Product Cell Controller for ProductCell.fxml,it runs all the methods 
 * that functions the choices of quantities or add to cart button for each product.
 * for each product that is showing in the catalog page there is a product cell that have
 * been created
 * Note: this class uses help from Customer and Order class to set and get some functionalities 
 * @author Mahran
 *
 */
public class ProductCellController  implements Initializable {
	
	/**
	 * to save the maximum quantity of the product
	 */
	private int maxQuantity;
	/**
	 * to save the current quantity of the product
	 */
	private int quantity;
	/**
	 * to save the image of the product
	 */
	private Image img;
	/**
	 * to save the id of the product
	 */
	private String productID;
	/**
	 * to save the name of the product
	 */
	private String productName;
	/**
	 * to save the price of the product
	 */
	private String productPrice;
	/**
	 * to save the discount of the product (in case there is a discount)
	 */
	private String productDiscount;
    /**
     * Label to show to discount value
     */
    @FXML
    private Label IDDiscount;
	/**
	 * ImageView with a sale image for the product that have discount
	 */
	@FXML
	private ImageView IDSaleOn;
	/**
	 * Label to show that this product is added to the cart
	 */
	@FXML
    private Label IDLabelAdded;
	/**
	 * Button for adding that product to the cart
	 */
	@FXML
    private Button IDAddToCartBtn;
    /**
     * Label to show the product category 
     */
    @FXML
    private Label IDProductCatagory;
    /**
     * Label to show the product name
     */
    @FXML
    private Label IDProductName;
    /**
     * Label to show the product id
     */
    @FXML
    private Label IDProductid;
    /**
     * TextField to get and show the quantities of the product 
     */
    @FXML
    private TextField IDQuantity;
    /**
     * ImageView to set the image of the product
     */
    @FXML
    private ImageView IDproductImg;
    /**
     * Label to show the price of the product
     */
    @FXML
    private Label IDPrice;
    /**
     * Label to show Specification of the product
     */
    @FXML
    private Label IDspecification;
    /**
     * AnchorPane that contains the adding buttons and properties
     */
    @FXML
    private AnchorPane IDAddingProperties;


	
	/**
	 *initialize IDLabelAdded and IDAddingProperties
	 *initialize textQuantityCheck
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Customer.IDLabelAdded = IDLabelAdded;
		Customer.IDAddingProperties = IDAddingProperties;
		textQuantityCheck(IDQuantity);

	
	}
	
	/**
	 * Method to set the data for the this product (class/object)
	 * @param name name of the product
	 * @param id id of the product
	 * @param catagory category of the product
	 * @param price price of the product
	 * @param specification specification of the product
	 * @param maxquantity maximum quantity of the product
	 */
	public void setData(String name,String id,String category,String price,String specification,String maxquantity) {
		IDSaleOn.setVisible(false);
		IDDiscount.setVisible(false);
		this.img = new Image("/Products/"+id+".png"); 
		this.productName = name;
		this.productID = id;
		
		this.productPrice = price;
		IDproductImg.setImage(img);
		IDProductName.setText("Name: "+name);
		IDProductid.setText("ID: "+id);
		IDProductCatagory.setText("Category: "+category);
		IDPrice.setText(""+price+"$");
		IDspecification.setText(""+"specification: "+specification);
		this.maxQuantity=Integer.parseInt(maxquantity);
		
	}
	/**
	 * Method that increase the quantity and shows the changed quantity  
	 * in case the increase arrow icon is clicked
	 * in case it reaches the maximum quantity it doesn't increase
	 * 
	 * @param event event if clicking the increase arrow icon
	 * @throws Exception Exception will be thrown if an error occurs
	 */
	public void increaseQuantity(MouseEvent event)throws Exception
	{
		if(IDQuantity.getText().equals(""))
		{
			IDQuantity.setText("0");
		}
		quantity = Integer.parseInt(IDQuantity.getText().toString());
		if(quantity >= this.maxQuantity)
			IDQuantity.setText(this.maxQuantity+"");
		else {
			IDQuantity.setText(++quantity+"");
		}
		
	}
	/**
	 * Method that decrease the quantity and shows the changed quantity
	 * in case the decrease arrow icon is clicked
	 *  in case it reaches the minimum quantity it doesn't decrease
	 * @param event event if clicking the decrease arrow icon
	 * @throws Exception Exception will be thrown if an error occurs
	 */
	public void decreaseQuantity(MouseEvent event)throws Exception
	{
		
		if(IDQuantity.getText().equals(""))
		{
			IDQuantity.setText("0");
		}
		quantity = Integer.parseInt(IDQuantity.getText().toString());
		if(quantity  == 1)
			IDQuantity.setText(quantity+"");
		else 
			IDQuantity.setText(--quantity+"");
		
	}

	/**
	 * Method for adding product to the Mini cart (on the side of the screen),
	 * in case add to cart is clicked a Mini cart cell would be created according to
	 * this product and the information in it then it would be added and implemented in
	 * the stage/scene of the catalog page
	 * @param event event of clicking Add To Cart Button
	 * @throws Exception Exception will be thrown if an error occurs
	 */
	public void AddToCart(ActionEvent event) throws Exception{
		if(Customer.IDErrorEmptyCart!=null)
		Customer.IDErrorEmptyCart.setVisible(false);
		Node node = null;
		FXMLLoader fXLoader = new FXMLLoader();
		fXLoader.setLocation(getClass().getResource("/FXMLs/MiniCartSidePage.fxml"));
		try {
			node = fXLoader.load();
			
		} catch (IOException e) {
			System.out.println("Exception in view - Mini Cart Cell");
			e.printStackTrace();
		}
		if(getQuantity().equals(""))
			setQuantity("1");
		
		
		MiniCartSideController productcart= fXLoader.getController();
		if(!Order.productsInCart.contains(productName))
			{	
			
			getAddingProperties().setVisible(false);
				getLabelAdded().setVisible(true);
				Order.productsInCart.add(productName);
				productcart.setData(productName, productID,productPrice,getIDQuantity().getText(),getMaxQuantity(),node);
				Order.setCartProduct(productName, productID, productPrice,getQuantity(),getMaxQuantity(),node,this,Order.location,Order.area);
				if(Order.loadLastCart!=1)
				Customer.IDMiniCartVBox.getChildren().add(node);
				Order.sumPrices +=productcart.getSum();
				if(Order.loadLastCart!=1)
				Customer.IDTotalPrice.setText("Toltal Price: "+String.format("%.2f",Order.sumPrices)+"$");
					
			}
		else if(Order.productsInCart.contains(productName))
		{
				Customer.IDMiniCartVBox.getChildren().add(Order.productsInOrder.get(Order.i).getNode());
				Customer.IDTotalPrice.setText("Toltal Price: "+Order.sumPrices+"$");
		}

	}
	
	/**
	 * Method to check legality of the input in the quantity Text Field 
	 * legality such as 
	 * only numbers and no empty field 
	 * maximum and minimum quantities 
	 * @param IDQuantity IDQuantity TextField 
	 */
	private void textQuantityCheck(TextField IDQuantity )
	{
		IDQuantity.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue,String newValue) {
		        
		    	if (!newValue.matches("\\d*")) 
		        	IDQuantity.setText(newValue.replaceAll("[^\\d]", ""));
		        	
		        else
		        {

		        	IDQuantity.setOnKeyPressed(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
							
							if(event.getCode()==KeyCode.ENTER) 
							{
								if(IDQuantity.getText().equals("0")||IDQuantity.getText().equals("")||IDQuantity.getText().length()<1)
									IDQuantity.setText("1");
								else if(Integer.parseInt(IDQuantity.getText())>=maxQuantity)
									IDQuantity.setText(maxQuantity+"");

							}
					}});
		        
		        }}
		    });
	}
	
	/**
	 * Method that return the TextField of IDQuantity of the product cell
	 * @return return the TextField of IDQuantity of the product cell
	 */
	public TextField getIDQuantity() {
		return IDQuantity;
	}
	/**
	 * Method that sets the TextField of IDQuantity of the product cell
	 * @param iDQuantity sets the TextField of IDQuantity of the product cell
	 */
	public void setIDQuantity(TextField iDQuantity) {
		IDQuantity = iDQuantity;
	}
	/**
	 * Method that return the quantity number of product
	 * @return the quantity as a number
	 */
	public int getQuantityint() {
		return this.quantity;
	}
	/**
	 * Method that return the quantity of the product as string
	 * @return the quantity as a string
	 */
	public String getQuantity() {
		return IDQuantity.getText();
	}
	/**
	 * Method that set the quantity TextField using the num parameter
	 * @param num number that represent the quantity that needs to be set
	 */
	public void setQuantity(String num) {
		IDQuantity.setText(num+"");
	}
	/**
	 * Method that set the maximum quantity using the num parameter
	 * @param num number that represent the maximum quantity that needs to be set
	 */
	public void setMaxQuantity(String num) {
		maxQuantity=Integer.parseInt(num);
	}
	/**
	 * Method to get the maximum quantity as string
	 * @return return the maximum quantity of the product as a string
	 */
	public String getMaxQuantity() {
		return maxQuantity+"";
	}

    /**
     * Method that returns the AnchorPane id of all adding properties
     * @return the IDAddingPropertie 
     */
    public AnchorPane getAddingProperties() {
    	
    	return this.IDAddingProperties;
    }
 
	/**
	 * Method to get the product name
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * Method to get the IDLabelAdded
	 * @return IDLabelAdded
	 */
	public Label getLabelAdded() {
		return this.IDLabelAdded;
	}
	
    /**
     * Method to get the product discount
     * @return productDiscount
     */
    public String getProductDiscount() {
		return productDiscount;
	}
    
	/**
	 * Method to set the product discount and change the price according to the discount
	 * @param productDiscount productDiscount to get the discount 
	 */
	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
		if(productDiscount!="0") 
		{
			IDDiscount.setText("-"+productDiscount+"%");
			IDDiscount.setVisible(true);
			IDSaleOn.setVisible(true);
			double temp;
			temp = Double.parseDouble(this.productPrice)-Double.parseDouble(this.productPrice)*Double.parseDouble(this.productDiscount)/100;
			this.productPrice = String.format("%.2f",temp) + "";
			IDPrice.setText(""+this.productPrice+"$");
			
		}
	}
}
