package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import customermethods.CommonMethods;
import customermethods.Customer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This Class is a Manage Orders Page Controller for ManageOrderPage.fxml ,it runs all the methods
 * that functions to show the orders(also the their status) of the customer and the ability to cancel an order
 * Note: this class uses help from Customer and Order class to set and get some functionalities 
 * @author Mahran
 *
 */
public class ManageOrdersInterFaceController extends Application implements Initializable {
	/**
	 * to save and show the stage
	 */
	Stage stage;
	/**
	 * to save and the root
	 */
	Parent root;
	/**
	 * To get all Informations from the data base and saving them in arrays of strings 
	 */
	private String[] DBOrderProducts = {"Sprite\nElit-Bar","Sprite"};
	private String[] DBOrderPrice = {"10","1","5"};
	private String[] DBOrderId = {"1","2"};
	private String[] DBOrderStatus = {"Completed","NotCompleted"}; 
    /**
     * TableView to save and show the orders
     */
    @FXML
    private TableView<TableRow> IDTableView;
    /**
     * TableColumn for Buttons
     */
    @FXML
    private TableColumn<TableRow, HBox> IDAction;
    /**
     * TableColumn for the orders id
     */
    @FXML
    private TableColumn<TableRow, Integer> IDOrderid;
    /**
     * TableColumn for the orders price
     */
    @FXML
    private TableColumn<TableRow, String> IDTotalPrice;
    /**
     * TableColumn for the orders products
     */
    @FXML
    private TableColumn<TableRow, String> IDproducts;
    /**
     * VBox to save/show the Buttons 
     */
    private HBox actionHbox ;
    
    /**
     * Observable list for TableView rows
     */
    ObservableList<TableRow> list;
    
	/**
	 *initialize all the details that needed in the helper classes 
	 *and in the current class
	 *initialize the table view and every row/order in it
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		IDOrderid.setCellValueFactory(new PropertyValueFactory<TableRow, Integer>("OrderID"));
		IDTotalPrice.setCellValueFactory(new PropertyValueFactory<TableRow, String>("totalPrice"));
		IDproducts.setCellValueFactory(new PropertyValueFactory<TableRow, String>("products"));
		IDAction.setCellValueFactory(new PropertyValueFactory<TableRow, HBox>("buttonsHbox"));
		
	    list = IDTableView.getItems();
	    for(int i = 0 ; i<2;i++) 
	    {
	    	
	    TableRow save = new TableRow();
	    save.setOrderID(Integer.parseInt(DBOrderId[i]));
	    save.setTotalPrice(DBOrderPrice[i]+"$");
	    save.setProducts(DBOrderProducts[i]);
		if(DBOrderStatus[i].equals("NotCompleted")) 
		{
		actionHbox =new HBox(createCancelButton(save));
		actionHbox.setSpacing(20);
		actionHbox.setAlignment(Pos.CENTER);
		
		}
		else {
			actionHbox =new HBox(createCompleteLabel());
			actionHbox.setAlignment(Pos.CENTER);
		}
		save.setButtonsHbox(actionHbox);
		list.add(save);
		IDTableView.setItems(list);
	    }
	}

	/**
	 *Method to start the primary scene and set the stage 
	 *used only for starting from customer page nothing before 
	 *could be deleted
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			stage= primaryStage;
			root=CommonMethods.switchScene(getClass(),stage, "ManageOrdersPage.fxml","ManageOrdersPage.css");

		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to create and connect label to the row of the table view(order) "complete" ,in case the order is completed
	 * @return complete(label)
	 */
	private Label createCompleteLabel() {
		Label complete = new Label();
		complete.setText("Completed");
		complete.setStyle("-fx-font-size: 15px;"+"-fx-text-fill: #90BB14;"+"-fx-font-weight:bold;");
		return complete;
	}
	/**
	 * Method to create and connect a cancel button for the order (the row in that table view) in case 
	 * that order isn't completed
	 * @param row row that needs to be connected to the cancel button in the table view 
	 * @return
	 */
	private Button createCancelButton(TableRow row)
    {
    	Button cancelBtn=new Button("Cancel");
    	
    	cancelBtn.requestFocus();
    	cancelBtn.setOnMouseClicked(event ->  
    	{

    		list.remove(row);
    	} );
 
    	cancelBtn.setStyle("-fx-background-radius: 50;"+"-fx-background-color: #90BB14;"+"-fx-font-weight:bold;"+"-fx-font-size: 12px;"
    	+"-fx-effect: dropshadow( three-pass-box , #A2A09F, 13, 0 , 7 , 7 );"+"-fx-text-fill: white;");
    	
        return cancelBtn;
    }
	

	/**
	 * Method for going back to the previous page in this case the Customer Home Page
	 * @param event event if the the arrow (back) icon clicked
	 * @throws Exception Exception will be thrown if an error occurs when switching the stage 
	 */
	public void back(MouseEvent event) throws Exception // close window
	  {
	
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","Customerinterface.css");
		
	  }
    /**
	 * Method for clicking the help icon ,a windows will show with a message and explain the scene/page
	 * @param event event of the help icon clicked the scene/page and what every button do
	 * @throws Exception Exception will be thrown if an error occurs from Customer class 
     */
    public void help(MouseEvent event) throws Exception{//to the throw a message when "question" pressed

    	Customer.help("This is your auto saved Cart Page\n"
    			+ "Press Complete Order tp complete this order\n"
    			+ "Press Edit Order to Edit this order\n"
    			+ "Press Cancel Order to cancel this order");//showed message
    			
    }
    /**
	 * Method for closing the application, the window of the application would be closed
	 * @param event event of the X icon clicked
	 * @throws Exception Exception will be thrown if an error occurs
     */
    public void clsoe(MouseEvent event) throws Exception // close window
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
		
	  }
    

	
    /**
     * Inner class to help build the rows of the order's table view
     * @author Mahran
     *
     */
    public class TableRow {
		

		private int OrderID;
		private String totalPrice;
	    private String products;
	    private HBox buttonsHbox;

	    
	    /**
	     * default constructor
	     */
	    public TableRow()
	    {
	    	
	    }
	    
	    /**
	     * constructor to initialize the data
	     * @param orderID id of the order
	     * @param totalPrice total price of the order
	     * @param products products in that order
	     * @param buttonsHbox HBox to contain the Buttons
	     */
	    public TableRow(int orderID, String totalPrice, String products,HBox buttonsHbox) {
			super();
			this.OrderID = orderID;
			this.totalPrice = totalPrice;
			this.products = products;
			this.buttonsHbox = buttonsHbox;
			
		}

	    /**
	     * Method to get the order id
	     * @return OrderID
	     */
	    public int getOrderID() {
			return OrderID;
		}

		/**
		 * Method to set the order id 
		 * @param orderID OrderID to set the order id to this value
		 */
		public void setOrderID(int orderID) {
			OrderID = orderID;
		}

		/**
		 * Method to get the total price
		 * @return totalPrice
		 */
		public String getTotalPrice() {
			return totalPrice;
		}

		/**
		 * Method to set the total price of the order
		 * @param totalPrice totalPrice to set the order total price to this value
		 */
		public void setTotalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
		}

		/**
		 * Method to get the products in the order 
		 * @return products
		 */
		public String getProducts() {
			return products;
		}

		/**
		 * Method to set the products in the order
		 * @param products products to set the order products to this value
		 */
		public void setProducts(String products) {
			this.products = products;
		}
		
		/**
		 * Method to get HBox
		 * @return buttonsHbox
		 */
		public HBox getButtonsHbox() {
			return buttonsHbox;
		}

		/**
		 * Method to set the the order's HBox
		 * @param buttonsHbox buttonsHbox to set the order HBox to this value
		 */
		public void setButtonsHbox(HBox buttonsHbox) {
			this.buttonsHbox = buttonsHbox;
		}
		    
	}
}
