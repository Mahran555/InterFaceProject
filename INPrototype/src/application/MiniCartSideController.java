package application;

import java.net.URL;
import java.util.ResourceBundle;

import customermethods.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import orders.Order;


public class MiniCartSideController  implements Initializable {
	 @FXML
	    private ImageView IDDecrease;
	    @FXML
	    private ImageView IDIncrease;
		@FXML
    	private Button IDCancel;
	    @FXML
	    private ImageView IDProductImg;
	    @FXML
	    private Label IDProductName;
	    @FXML
	    private TextField IDProductQuantity;
	    @FXML
	    private Label IDProductPrice;
		private String productName;
		private String productID;
		private String productPrice;
		private int quantity;
		private int maxQuantity;
		private double sum=0;
		private Node node=null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
	}


	public ImageView getIncreaseImg()
	{
		return this.IDIncrease;
	}
	public ImageView getDecreaseImg()
	{
		return this.IDDecrease;
	}
	public void setData(String name,String id,String price,String quan,String maxQuan,Node node) {
		this.productID = id;
		Image img = new Image("/Products/"+productID+".png"); 
		this.productName = name;
		this.productPrice = price;
		this.quantity =Integer.parseInt(quan);
		this.maxQuantity = Integer.parseInt(maxQuan);
		this.sum = Double.parseDouble(quan)*Double.parseDouble(price);
		IDProductImg.setImage(img);
		IDProductName.setText(name);
		IDProductQuantity.setText(""+quan);
		IDProductPrice.setText(Double.parseDouble(price)*Integer.parseInt(quan)+"$");
		this.node = node;
		
	}
	public Node getNode() {
		return this.node;
	}
	public String getName() {
		return productName;
		
	}
	
	public String getPrice() {
		return productPrice;
	}
	public void setQuantity() {
		int temp = Integer.parseInt(IDProductQuantity.getText());
		this.quantity = ++temp;
		IDProductQuantity.setText(this.quantity+"");
		this.sum+=temp;
	}



	public String getQuantity() {//return the quantity
		return IDProductQuantity.getText();
	}
	public int getQuantityNum() {
		return quantity;
	}
	public String getMaxQuantity() {//return the quantity
		return maxQuantity+"";
	}
	public void setQuantity(int num) {//set the quantity
		quantity = num;

	}
	
	
	public void setNode(Node node) {//set the quantity
		this.node=node;
	}
    public Button getCancelBtn() {
    	
    	return this.IDCancel;
    }
    public TextField getQuantityTextField() {
    	return IDProductQuantity;
    	
    }
    public Double getSum() {
    	return this.sum;
    }
    public void setSum(double num) {
    	this.sum = num;
    }
	public void increaseQuantity(MouseEvent event)throws Exception
	{

		if(getQuantityTextField().getText().equals(""))//if quantity field is empty set it to 0
		{
			getQuantityTextField().setText("0");
		}
		setQuantity(Integer.parseInt(getQuantityTextField().getText())); //save quantity
		if(getQuantityNum() >= Integer.parseInt(getMaxQuantity()))//check if we reached max quantity
			getQuantityTextField().setText(getMaxQuantity()+"");
		else {
			int temp=getQuantityNum();
			++temp;
			Order.updateProductQuantity(getName(),temp+"");
			setQuantity(temp);
			getQuantityTextField().setText(temp+"");//increase quantity
			Order.sumPrices+=1;
			setSum(Order.sumPrices);///////////////////////
			Customer.IDTotalPrice.setText("Toltal Price: "+Order.sumPrices+"$");
		}
	}
		
	
	public void decreaseQuantity(MouseEvent event)throws Exception
	{
		
		if(getQuantityTextField().getText().equals(""))//if quantity field is empty set it to 0
		{
			getQuantityTextField().setText("0");
		}
		setQuantity(Integer.parseInt(getQuantityTextField().getText())); //save quantity
		if(getQuantityNum()  == 1)//check if we reached min quantity
			getQuantityTextField().setText(getQuantityNum()+"");
		else {
			int temp=getQuantityNum();
			--temp;
			Order.updateProductQuantity(getName(),temp+"");
			setQuantity(temp);
			getQuantityTextField().setText(temp+"");//decrease quantity
			Order.sumPrices-=1;
			setSum(Order.sumPrices);
			Customer.IDTotalPrice.setText("Toltal Price: "+Order.sumPrices+"$");
			
		}
		
	}
	public void RemoveProduct(MouseEvent event)throws Exception
	{
		
		Customer.IDMiniCartVBox.getChildren().remove(getNode());//remove from minicart vbox
		Order.productsInCart.remove(getName());//remove name from the array of products in cart
		Order.removeCartProduct(getName());
		Order.sumPrices -= getQuantityNum()*Double.parseDouble(getPrice());//remove the sum from the total sum
		Customer.IDTotalPrice.setText("Toltal Price: "+Order.sumPrices+"$");//set the new value of total sum after updating
		Customer.IDAddingProperties.setVisible(true);//return adding propirties to product cell
		Customer.IDLabelAdded.setVisible(false);//hide/remove label added product to cart
		
		try {
			
			Customer.con.searchByName(null);
			
		} catch (Exception e) {
			System.out.println("Error in Cancel Button - SearchByName\n");
			e.printStackTrace();
		}
		
	}
}
