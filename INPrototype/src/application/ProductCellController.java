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
import javafx.scene.layout.Pane;
import orders.Order;

public class ProductCellController  implements Initializable {
	
	private int maxQuantity;
	private int quantity;
	private Image img;
	private String productID;
	private String productName;
	private String productPrice;

    @FXML
    private Label IDLabelAdded;
	@FXML
    private Button IDAddToCartBtn;
    @FXML
    private Label IDProductCatagory;
    @FXML
    private Label IDProductName;
    @FXML
    private Label IDProductid;
    @FXML
    private TextField IDQuantity;
    @FXML
    private Pane IDpane;
    @FXML
    private ImageView IDproductImg;
    @FXML
    private Label IDPrice;
    @FXML
    private Label IDspecification;
    @FXML
    private AnchorPane IDAddingProperties;


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Customer.IDLabelAdded = IDLabelAdded;//init to Order
		Customer.IDAddingProperties = IDAddingProperties;//init to Order
		IDQuantity.textProperty().addListener(new ChangeListener<String>() {//only numbers are allowed
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue,String newValue) {
		        
		    	if (!newValue.matches("\\d*")) 
		        	IDQuantity.setText(newValue.replaceAll("[^\\d]", ""));//if not a number don't change
		        	
		       
		        else//if its a number
		        {
		        	
		        	
		        		
		        	IDQuantity.setOnKeyPressed(new EventHandler<KeyEvent>() {//check if enter pressed 

					@Override
					public void handle(KeyEvent event) {
							
							if(event.getCode()==KeyCode.ENTER) 
							{//check if it is legal and change the value accordingly
								if(IDQuantity.getText().equals("0")||IDQuantity.getText().equals("")||IDQuantity.getText().length()<1)
									IDQuantity.setText("1");
								else if(Integer.parseInt(IDQuantity.getText())>=maxQuantity)
									IDQuantity.setText(maxQuantity+"");
								
								
						
							}
				
			
					}});
		        
		        }}
		    });

	
	}
	//save data 
	public void setData(String name,String id,String catagory,String price,String specification,String quan) {
		this.img = new Image("/Products/"+id+".png"); 
		this.productName = name;
		this.productID = id;
		this.productPrice = price;
		IDproductImg.setImage(img);
		IDProductName.setText("Name: "+name);
		IDProductid.setText("ID: "+id);
		IDProductCatagory.setText("Catagory: "+catagory);
		IDPrice.setText(""+price+"$");
		IDspecification.setText(""+"specification: "+specification);
		this.maxQuantity=Integer.parseInt(quan);
		
	}
	public void increaseQuantity(MouseEvent event)throws Exception
	{
		if(IDQuantity.getText().equals(""))//if quantity field is empty set it to 0
		{
			IDQuantity.setText("0");
		}
		quantity = Integer.parseInt(IDQuantity.getText().toString());//save quantity
		if(quantity >= this.maxQuantity)//check if we reached max quantity
			IDQuantity.setText(this.maxQuantity+"");
		else {
			IDQuantity.setText(++quantity+"");//increase quantity
		}
		
	}
	public void decreaseQuantity(MouseEvent event)throws Exception
	{
		
		if(IDQuantity.getText().equals(""))//if quantity field is empty set it to 0
		{
			IDQuantity.setText("0");
		}
		quantity = Integer.parseInt(IDQuantity.getText().toString());//save quantity
		if(quantity  == 1)//check if we reached min quantity
			IDQuantity.setText(quantity+"");
		else 
			IDQuantity.setText(--quantity+"");//decrease quantity
		
	}


	

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
		if(getQuantity().equals(""))//if textfield of quantity in cell is empty and added to cart fix it to 1
			setQuantity("1");
		
		
		MiniCartSideController productcart= fXLoader.getController();
		if(!Order.productsInCart.contains(productName))
			{	
			
			getAddingProperties().setVisible(false);//disable(remove) all adding properties from product cell
				getLabelAdded().setVisible(true);//adding label "product is added"
				Order.productsInCart.add(productName);//add name to cart array(to know it exists)
				//set the data we need into the mini cart cell
				productcart.setData(productName, productID,productPrice,getIDQuantity().getText(),getMaxQuantity(),node);//quan = maxquantity
				Order.setCartProduct(productName, productID, productPrice,getQuantity(),getMaxQuantity(),node,this,Order.location,Order.area);//!!!
				if(Order.loadLastCart!=1)
				Customer.IDMiniCartVBox.getChildren().add(node);//add the node to the mini cart (to the scene)
				Order.sumPrices +=productcart.getSum();
				if(Order.loadLastCart!=1)
				Customer.IDTotalPrice.setText("Toltal Price: "+Order.sumPrices+"$");
					
			}
		else if(Order.productsInCart.contains(productName))
		{
				Customer.IDMiniCartVBox.getChildren().add(Order.productsInOrder.get(Order.i).getNode());
				Customer.IDTotalPrice.setText("Toltal Price: "+Order.sumPrices+"$");
		}

	

		
	}
	public TextField getIDQuantity() {
		return IDQuantity;
	}
	public void setIDQuantity(TextField iDQuantity) {
		IDQuantity = iDQuantity;
	}
	public int getQuantityint() {
		return this.quantity;
	}
	public String getQuantity() {//return the quantity
		return IDQuantity.getText();
	}
	public void setQuantity(String num) {//set the quantity
		IDQuantity.setText(num+"");
	}
	public void setMaxQuantity(String num) {//set the quantity
		maxQuantity=Integer.parseInt(num);
	}
	public String getMaxQuantity() {//return the quantity
		return maxQuantity+"";
	}
    public Button getAddToCartBtn() {
    	
    	return this.IDAddToCartBtn;
    }
    public AnchorPane getAddingProperties() {
    	
    	return this.IDAddingProperties;
    }
 
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public Label getLabelAdded() {
		return this.IDLabelAdded;
	}
}
