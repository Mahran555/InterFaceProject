package application;


import customermethods.Customer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import orders.Order;


/**
 * This Class is a Mini Cart Cell Controller for MiniCartSide.fxml,it runs all the methods 
 * that functions the choices of quantities for each product.
 * for each product that add to cart button clicked in the Product cell there is a mini cart cell that 
 * will be created
 * Note: this class uses help from Customer and Order class to set and get some functionalities 
 * @author Mahran
 *
 */
public class MiniCartSideController   {


		/**
		 * Button to remove/cancel product from the cart
		 */
		@FXML
    	private Button IDCancel;
	    /**
	     * ImageView to set the image of the product
	     */
	    @FXML
	    private ImageView IDProductImg;
	    /**
	     * Label to show the product name
	     */
	    @FXML
	    private Label IDProductName;
	    /**
	     * Label to show the product quantity
	     */
	    @FXML
	    private TextField IDProductQuantity;
	    /**
	     * Label to show the product price
	     */
	    @FXML
	    private Label IDProductPrice;
		/**
		 * to save the name of the product
		 */
		private String productName;
		/**
		 * to save the id of the product
		 */
		private String productID;
		/**
		 * to save the price of the product
		 */
		private String productPrice;
		/**
		 * to save the quantity of the product
		 */
		private int quantity;
		/**
		 * to save the maximum quantity of the product
		 */
		private int maxQuantity;
		/**
		 * to save the sum 
		 */
		private double sum=0;
		/**
		 * to save the node
		 */
		private Node node=null;
	

	/**
	 * Method to set the data for the this product (class/object)
	 * @param name name of the product
	 * @param id id of the product
	 * @param price price of the product
	 * @param quantity quantity of the product
	 * @param maxQuan maximum quantity of the product
	 * @param node node (fxml) of the product
	 */
	public void setData(String name,String id,String price,String quantity,String maxQuan,Node node) {
		this.productID = id;
		Image img = new Image("/Products/"+productID+".png"); 
		this.productName = name;
		this.productPrice = price;
		this.quantity =Integer.parseInt(quantity);
		this.maxQuantity = Integer.parseInt(maxQuan);
		this.sum = Double.parseDouble(quantity)*Double.parseDouble(price);
		IDProductImg.setImage(img);
		IDProductName.setText(name);
		IDProductQuantity.setText(""+quantity);
		IDProductPrice.setText(String.format("%.2f",Double.parseDouble(price)*Double.parseDouble(quantity))+"$");
		this.node = node;
		
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

		if(getQuantityTextField().getText().equals(""))
		{
			getQuantityTextField().setText("0");
		}
		setQuantity(Integer.parseInt(getQuantityTextField().getText())); 
		if(getQuantityNum() >= Integer.parseInt(getMaxQuantity()))
			getQuantityTextField().setText(getMaxQuantity()+"");
		else {
			int temp=getQuantityNum();
			++temp;
			Order.updateProductQuantity(getName(),temp+"");
			setQuantity(temp);
			getQuantityTextField().setText(temp+"");
			Order.sumPrices+=1.0*Double.parseDouble(this.productPrice);
			setSum(Order.sumPrices);
			Customer.IDTotalPrice.setText("Toltal Price: "+String.format("%.2f",Order.sumPrices)+"$");
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
		
		if(getQuantityTextField().getText().equals(""))
		{
			getQuantityTextField().setText("0");
		}
		setQuantity(Integer.parseInt(getQuantityTextField().getText())); 
		if(getQuantityNum()  == 1)
			getQuantityTextField().setText(getQuantityNum()+"");
		else {
			int temp=getQuantityNum();
			--temp;
			Order.updateProductQuantity(getName(),temp+"");
			setQuantity(temp);
			getQuantityTextField().setText(temp+"");
			Order.sumPrices-=1.0*Double.parseDouble(this.productPrice);;
			setSum(Order.sumPrices);
			Customer.IDTotalPrice.setText("Toltal Price: "+String.format("%.2f",Order.sumPrices)+"$");
			
		}
		
	}
	/**
	 * Method to remove product from the mini cart and update the cart price
	 * after removing the product (total price of the order)
	 * @param event event on clicking the X in the product cell
	 * @throws Exception Exception will be thrown if an error occurs
	 */
	public void RemoveProduct(MouseEvent event)throws Exception
	{
		
		Customer.IDMiniCartVBox.getChildren().remove(getNode());
		Order.productsInCart.remove(getName());
		Order.removeCartProduct(getName());
		Order.sumPrices -= Double.valueOf(getQuantityNum())*Double.parseDouble(getPrice());
		Customer.IDTotalPrice.setText("Toltal Price: "+String.format("%.2f",Order.sumPrices)+"$");
		Customer.IDAddingProperties.setVisible(true);
		Customer.IDLabelAdded.setVisible(false);
		
		try {
			
			Customer.con.searchByName(null);
			
		} catch (Exception e) {
			System.out.println("Error in Cancel Button - SearchByName\n");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to get the saved node of the product
	 * @return node
	 */
	public Node getNode() {
		return this.node;
	}
	/**
	 * Method to get the product name
	 * @return productName
	 */
	public String getName() {
		return productName;
		
	}
	
	/**
	 * Method to get the product price
	 * @return productPrice
	 */
	public String getPrice() {
		return productPrice;
	}
	/**
	 * Method to increase and set quantity
	 */
	public void setQuantity() {
		int temp = Integer.parseInt(IDProductQuantity.getText());
		this.quantity = ++temp;
		IDProductQuantity.setText(this.quantity+"");
		this.sum+=temp;
	}


	/**
	 * Method to get quantity as a number(int)
	 * @return quantity
	 */
	public int getQuantityNum() {
		return quantity;
	}
	/**
	 * Method to get maximum quantity as a string
	 * @returnmaxQuantity
	 */
	public String getMaxQuantity() {
		return maxQuantity+"";
	}
	/**
	 * Method to set the given parameter as the quantity
	 * @param num a parameter to set the quantity to
	 */
	public void setQuantity(int num) {//set the quantity
		quantity = num;

	}
	
	
	/**
	 * Method to set the node with the given node
	 * @param node node to set the class node 
	 */
	public void setNode(Node node) {
		this.node=node;
	}
    /**
     * Method to get the Cancel Button 
     * @return IDCancel
     */
    public Button getCancelBtn() {
    	
    	return this.IDCancel;
    }
    /**
     * Method to get the quantity TextField
     * @return IDProductQuantity
     */
    public TextField getQuantityTextField() {
    	return IDProductQuantity;
    	
    }
    /**
     * Method to get the sum
     * @return sum
     */
    public Double getSum() {
    	return this.sum;
    }
    /**
     * Method to set the sum as the given parameter
     * @param num parameter to set the sum to
     */
    public void setSum(double num) {
    	this.sum = num;
    }
}
