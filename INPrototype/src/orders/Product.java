package orders;


import application.ProductCellController;
import javafx.scene.Node;

/**
 * This class is a product information class, to save the product's information 
 * @author Mahran
 *
 */
public class Product {
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
	 * to save the product quantity
	 */
	private String quantity;
	/**
	 * to save the product maximum quantity
	 */
	private String maxQuan;
	/**
	 * to save the FXML node of the product
	 */
	private Node node;
	/**
	 * to save the product location
	 */
	private String Location;
	/**
	 * to save the product area
	 */
	private String area;
	/**
	 * to save the product cell controller of each product
	 */
	private  ProductCellController productCell;

	/**
	 * * constructor to initialize the data
	 * @param productName the product name
	 * @param productID the product id
	 * @param productPrice the product price
	 * @param quantity the product quantity
	 * @param maxQuan the product maximum quantity
	 * @param node the product node (FXML)
	 * @param productCell the product cell controller
	 * @param location the product location
	 * @param area the product area
	 */
	public Product(String productName, String productID, String productPrice, String quantity,String maxQuan,Node node,ProductCellController productCell,String location,String area) {
		super();
		this.productName = productName;
		this.productID = productID;
		this.productPrice = productPrice;
		this.quantity = quantity;
		this.maxQuan = maxQuan;
		this.node = node;
		this.productCell = productCell;
		this.Location = location;
		this.area = area;
	}
	/**
	 * * constructor to initialize the data without the product cell
	 * @param productName the product name
	 * @param productID the product id
	 * @param productPrice the product price
	 * @param quantity the product quantity
	 * @param maxQuan the product maximum quantity
	 * @param node the product node (FXML)
	 * @param location the product location
	 * @param area the product area
	 */
	public Product(String productName, String productID, String productPrice, String quantity,String maxQuan,Node node,String location,String area) {
		super();
		this.productName = productName;
		this.productID = productID;
		this.productPrice = productPrice;
		this.quantity = quantity;
		this.maxQuan = maxQuan;
		this.node = node;
		this.Location = location;
		this.area = area;
	}

	/**
	 * Method to get the product name
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Method to get the product id
	 * @return productID
	 */
	public String getProductID() {
		return productID;
	}

	/**
	 * Method to get the product price
	 * @return productPrice
	 */
	public String getProductPrice() {
		return productPrice;
	}

	/**
	 * Method to get the product quantity
	 * @return quantity
	 */
	public String getQuantity() {
		return quantity;
	}
	/**
	 *  Method to set the product's quantity as quantity parameter
	 * @param quantity quantity to set the product's quantity to this value
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * Method to get the maximum quantity
	 * @return maxQuan
	 */
	public String getMaxQuan() {
		return maxQuan;
	}

	/**
	 * Method to get the node of the product (FXML)
	 * @return node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * Method to get the product's cell controller
	 * @return productCell
	 */
	public ProductCellController getProductCell() {
		return productCell;
	}


	/**
	 * Method to get the product location
	 * @return location
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * Method to get the product area
	 * @return area
	 */
	public String getArea() {
		return area;
	}







	
}
