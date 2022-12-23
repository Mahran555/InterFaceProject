package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * This Class is a Big Cart Cell Controller for BigCartCell.fxml
 * this method saves in a cell current products in your cart(auto saved)
 * it mainly used by other classes to create the cart cells 
 * Note: this class uses help from Customer and Order class to set and get some functionalities 
 * @author Mahran
 *
 */
public class BigCartCellInterFaceController  {
	/**
	 * Label to show to total price of the product cell
	 */
	@FXML
	private Label IDProductTotalPrice;
	/**
	 * Label to show the price of one unit of the product
	 */
	@FXML
    private Label IDPrice;
    /**
     * ImageView to set the image of the product
     */
    @FXML
    private ImageView IDProductImg;
    /**
     * Label to show the name of the product
     */
    @FXML
    private Label IDProductName;
    /**
     * Label to show the id of the product
     */
    @FXML
    private Label IDProductid;
	/**
	 * to save the product name
	 */
	private String productName;
	/**
	 * to save the product id
	 */
	private String productID;
	/**
	 * to save the product price
	 */
	private String productPrice;
	/**
	 * to save the image of the product
	 */
	private Image img;
	/**
	 * to save the product quantity
	 */
	private String quantity;
	

	/**
	 * Method to set the data for the this product (class/object)
	 * @param name name of the product
	 * @param id id of the product
	 * @param price price of the product
	 * @param quantity quantity of the product
	 */
	public void setData(String name,String id,String price,String quantity) {
		
		this.img = new Image("/Products/"+id+".png"); 
		this.productName = name;
		this.productID = id;
		this.productPrice = price;
		this.quantity = quantity;
		IDProductImg.setImage(img);
		IDProductName.setText("Name: "+name);
		IDProductid.setText("ID: "+id);
		IDPrice.setText(""+price+"$"+" x "+quantity);
		IDProductTotalPrice.setText("Total Price: "+Double.parseDouble(quantity)*Double.parseDouble(price)+"$");		
	}


	/**
	 * Method to get the product name
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Method to get the Product id
	 * @return productID
	 */
	public String getProductID() {
		return productID;
	}
	/**
	 * Method to get product price
	 * @return productPrice
	 */
	public String getProductPrice() {
		return productPrice;
	}

	/**
	 * Method to get product quantity
	 * @return quantity
	 */
	public String getQuantity() {
		return quantity;
	}







}
