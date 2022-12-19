package orders;

import java.util.ArrayList;
import application.ProductCellController;
import javafx.scene.Node;


public class Order {
public static int orderID=0;
public static ArrayList<Product> productsInOrder = new ArrayList<>();//array to save products we have in an order
public static Delivery deliveryInfo ;//to save delivery information
public static String customerName;//to save the customer name of that order
public static String customerID;//to save the customer ID of that order
public static String customerType="Subscriber";//to save the customer type of that order
public static String area;//order area
public static String location;//order location in the area
public static String orderPrice="0";//sum prices in String 
public static double sumPrices = 0;//sum prices in double value 
public static String methodOfDelivery;

public static ArrayList<String> productsInCart ;//to save products names in that order

public static int i=0;//to follow after indexes in products in order array

	public static void setCartProduct(String productName,String productID,String productPrice,String quantity,String maxquantity,Node node,ProductCellController temp,String location,String area) {
		
		productsInOrder.add(new Product(productName,productID,productPrice,quantity,maxquantity,node,temp,location,area));
		
		
		}
	public static void removeCartProduct (String productName) {//remove all product from products array
		
		for(int i=0 ; i<productsInOrder.size();i++)
			if(productsInOrder.get(i).getProductName().equals(productName)) {
				productsInOrder.remove(i);
			}
		}
		
	public static void updateProductQuantity (String productName,String Quantity) {//update specific product in the product array and change his quantity
		
		for(int i=0 ; i<productsInOrder.size();i++)
			if(productsInOrder.get(i).getProductName().equals(productName)) {
				productsInOrder.get(i).setQuantity(Quantity);
			}
		}
	public static boolean checkProductInArray(String productName) {//check if the products array contains that product (productname)
		
		for(int i=0 ; i<productsInOrder.size();i++)
			if(productsInOrder.get(i).getProductName().equals(productName)) {
				return true;
			}
		return false;
		}
	public static void setDelivery(String firstName, String lastName, String phoneNumber, String houseNumber, String street) {//save delivery infromation
		deliveryInfo=new Delivery(firstName,lastName,phoneNumber,houseNumber,street);
	}
	public static void clearOrder() {
	
		productsInOrder.clear();

		productsInCart.clear();
	
		i = 0;
		if(deliveryInfo!=null)
			deliveryInfo.clear(); 
		customerName=null;
		customerID=null;
		customerType=null;
		area=null;
		location=null;
		orderPrice=null;
		sumPrices = 0;
	}
}