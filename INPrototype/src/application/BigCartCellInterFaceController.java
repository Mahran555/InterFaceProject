package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BigCartCellInterFaceController  implements Initializable {
	@FXML
	private Label IDProductTotalPrice;
	@FXML
    private Label IDPrice;
    @FXML
    private ImageView IDProductImg;
    @FXML
    private Label IDProductName;
    @FXML
    private Label IDProductid;
	private String productName;
	private String productID;
	private String productPrice;
	private Image img;
	private String quantity;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


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


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductID() {
		return productID;
	}


	public void setProductID(String productID) {
		this.productID = productID;
	}


	public String getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}




}
