package orders;

import java.util.ArrayList;
import application.ProductCellController;
import javafx.scene.Node;


/**
 * This class is a order information class, to save the order information 
 * and uses other helper classes and connects between classes to get and set information
 * in case its needed 
 * @author Mahran
 *
 */
public class Order {
/**
 * to save the order id
 */
public static int orderID=0;
/**
 * to save the order size
 */
public static int orderSize=3;
/**
 * to identify the loaded cart in order
 */
public static int loadLastCart=1;
/**
 * array to save products we have in an order
 */
public static ArrayList<Product> productsInOrder = new ArrayList<>();
/**
 * to save delivery information
 */
public static Delivery deliveryInfo ;
/**
 * to save the customer name of that order
 */
public static String customerName;
/**
 * to save the customer ID of that order
 */
public static String customerID;
/**
 * to save the customer type of that order
 */
public static String customerType="Subscriber";
/**
 * to save the order area
 */
public static String area;
/**
 * to save the order location in the area
 */
public static String location;
/**
 * to save order price
 */
public static String orderPrice="0";
/**
 * to save sum prices in double value 
 */
public static double sumPrices = 0;
/**
 * to save the delivery method that the customer chosed
 */
public static String methodOfDelivery;

/**
 * array to save products names in that order
 */
public static ArrayList<String> productsInCart ;

/**
 * to follow after indexes in products in order array
 */
public static int i=0;

	/**
	 * Static Method to create an new product and set all the given data 
	 * with the product cell controller
	 * @param productName product name
	 * @param productID product id
	 * @param productPrice product price
	 * @param quantity product quantity
	 * @param maxquantity product maximum quantity
	 * @param node product node(FXML)
	 * @param controller product cell Controller
	 * @param location product location
	 * @param area product area
	 */
	public static void setCartProduct(String productName,String productID,String productPrice,String quantity,String maxquantity,Node node,ProductCellController controller,String location,String area) {
		
		productsInOrder.add(new Product(productName,productID,productPrice,quantity,maxquantity,node,controller,location,area));
		
		
		}
	/**
	 * Static Method to create an new product and set all the given data
	 * without the product cell controller
	 * @param productName product name
	 * @param productID product id
	 * @param productPrice product price
	 * @param quantity product quantity
	 * @param maxquantity product maximum quantity
	 * @param node product node(FXML)
	 * @param location product location
	 * @param area product area
	 */
	public static void setCartProduct2(String productName,String productID,String productPrice,String quantity,String maxquantity,Node node,String location,String area) {
		
		productsInOrder.add(new Product(productName,productID,productPrice,quantity,maxquantity,node,location,area));
		
		
		}
	/**
	 * Static Method to remove product with the productName(parameter) from products array
	 * @param productName the product name
	 */
	public static void removeCartProduct (String productName) {
		
		for(int i=0 ; i<productsInOrder.size();i++)
			if(productsInOrder.get(i).getProductName().equals(productName)) {
				productsInOrder.remove(i);
			}
		}
		
	/**
	 * Static Method to search for specific product with the productName(parameter)
	 * in the product array and change/update his quantity with the Quantity(parameter) 
	 * @param productName the product name
	 * @param Quantity the product quantity
	 */
	public static void updateProductQuantity (String productName,String Quantity) {
		
		for(int i=0 ; i<productsInOrder.size();i++)
			if(productsInOrder.get(i).getProductName().equals(productName)) {
				productsInOrder.get(i).setQuantity(Quantity);
			}
		}
	/**
	 * Static Method to check if the products array contains that product (productName)
	 * @param productName the product name
	 * @return True if contains that product (productName) else false
	 */
	public static boolean checkProductInArray(String productName) {
		
		for(int i=0 ; i<productsInOrder.size();i++)
			if(productsInOrder.get(i).getProductName().equals(productName)) 
				return true;
		return false;
		}
	/**
	 * Static Method to set the delivery information of the order
	 * @param firstName first name of the customer
	 * @param lastName last name of the customer
	 * @param phoneNumber phone number of the customer
	 * @param houseNumber house number of the customer
	 * @param street street address of the customer
	 */
	public static void setDelivery(String firstName, String lastName, String phoneNumber, String houseNumber, String street) {
		deliveryInfo=new Delivery(firstName,lastName,phoneNumber,houseNumber,street);
	}
	
	/**
	 * Static Method to clear the order
	 */
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