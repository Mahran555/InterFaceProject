package orders;


import application.ProductCellController;
import javafx.scene.Node;

public class Product {
	private String productName;//to save name
	private String productID;//to save id
	private String productPrice;//to save product's price
	private String quantity;//to save quantity
	private String maxQuan;//to save max quantity
	private Node node;//to save the fx node
	private String Location;//to save product location
	private String area;//to save product area
	private  ProductCellController productCell;

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

	public String getMaxQuan() {
		return maxQuan;
	}

	public void setMaxQuan(String maxQuan) {
		this.maxQuan = maxQuan;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public ProductCellController getProductCell() {
		return productCell;
	}

	public void setProductCell(ProductCellController productCell) {
		this.productCell = productCell;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}





	
}
