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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ManageOrdersInterFaceController extends Application implements Initializable {
	Stage stage;
	Parent root;
	private String DBOrderStatus = "Completed"; 
    @FXML
    private TableView<TableRow> IDTableView;
    @FXML
    private TableColumn<TableRow, HBox> IDAction;
    @FXML
    private TableColumn<TableRow, Integer> IDOrderid;
    @FXML
    private ScrollPane IDScrollPane;
    @FXML
    private TableColumn<TableRow, String> IDTotalPrice;
    @FXML
    private ImageView IDhelp;
    @FXML
    private TableColumn<TableRow, String> IDproducts;
    @FXML
    private Pane zr2;
    HBox actionHbox ;
    
    ObservableList<TableRow> list;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		IDOrderid.setCellValueFactory(new PropertyValueFactory<TableRow, Integer>("OrderID"));
		IDTotalPrice.setCellValueFactory(new PropertyValueFactory<TableRow, String>("totalPrice"));
		IDproducts.setCellValueFactory(new PropertyValueFactory<TableRow, String>("products"));
		IDAction.setCellValueFactory(new PropertyValueFactory<TableRow, HBox>("buttonsHbox"));
		TableRow first = new TableRow();
	    list = IDTableView.getItems();
		first.setOrderID(0);
		first.setTotalPrice("12"+"$");
		first.setProducts("Sprite\nElit-Bar\n");
		if(DBOrderStatus.equals("NotCompleted")) 
		{
		actionHbox =new HBox(createCancelButton(first));
		actionHbox.setSpacing(20);
		actionHbox.setAlignment(Pos.CENTER);
		
		}
		else {
			actionHbox =new HBox(createCompleteLabel());
			actionHbox.setAlignment(Pos.CENTER);
		}
		first.setButtonsHbox(actionHbox);
		list.add(first);
		IDTableView.setItems(list);
	}

	private Label createCompleteLabel() {
		Label complete = new Label();
		complete.setText("Completed");
		complete.setStyle("-fx-font-size: 15px;"+"-fx-text-fill: #90BB14;"+"-fx-font-weight:bold;");
		return complete;
	}
	private Button createCancelButton(TableRow first)
    {
    	Button cancelBtn=new Button("Cancel");
    	
    	cancelBtn.requestFocus();
    	cancelBtn.setOnMouseClicked(event ->  
    	{

    		list.remove(first);
    	} );
 
    	cancelBtn.setStyle("-fx-background-radius: 50;"+"-fx-background-color: #90BB14;"+"-fx-font-weight:bold;"+"-fx-font-size: 12px;"
    	+"-fx-effect: dropshadow( three-pass-box , #A2A09F, 13, 0 , 7 , 7 );"+"-fx-text-fill: white;");
    	
        return cancelBtn;
    }
	
	/*
	private Button createContinueButton(TableRow first)
    {
    	Button continueBtn=new Button("Continue");
    	
    	continueBtn.requestFocus();
    	continueBtn.setOnMouseClicked(event ->  
    	{
    		build();
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		root=CommonMethods.switchScene(getClass(),stage, "CartPage.fxml","CartPage.css");
    	} );
 
    	
    	continueBtn.setStyle("-fx-background-radius: 50;"+"-fx-background-color: #90BB14;"+"-fx-font-weight:bold;"+"-fx-font-size: 12px;"
    	+"-fx-effect: dropshadow( three-pass-box , #A2A09F, 13, 0 , 7 , 7 );"+"-fx-text-fill: white;");
    	
        return continueBtn;
    }
	private void build() {
		Order.orderID = 1;
		Order.area = "Haifa";
		Order.location = "Haifa-University";
		Order.productsFromMnager.add("Sprite");
		Node node = null;
		FXMLLoader fXLoader = new FXMLLoader();
		fXLoader.setLocation(getClass().getResource("/FXMLs/ProductCell.fxml"));
	
		
		
		try {
			
			node = fXLoader.load();
			
		} catch (IOException e) {
			System.out.println("Exception in view - product Cell");
			e.printStackTrace();
		}
		ProductCellController product= fXLoader.getController();
		product.setData("Sprite", "1", "Soft-Drinks","1","Others","10");
		product.setQuantity("2");

		try {
			product.AddToCart(null);
		} catch (Exception e) {
			System.out.println("AddToCart In ManageOrders failed");
			e.printStackTrace();
		}
		
	}
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

	public void back(MouseEvent event) throws Exception // close window
	  {
	
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root=CommonMethods.switchScene(getClass(),stage, "CustomerPage.fxml","Customerinterface.css");
		
	  }
    public void help(MouseEvent event) throws Exception{//to the throw a message when "question" pressed

    	Customer.help("This is your auto saved Cart Page\n"
    			+ "Press Complete Order tp complete this order\n"
    			+ "Press Edit Order to Edit this order\n"
    			+ "Press Cancel Order to cancel this order");//showed message
    			
    }
    public void clsoe(MouseEvent event) throws Exception // close window
	  {
		stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
		
	  }
    

	
    public class TableRow {
		

		private int OrderID;
		private String totalPrice;
	    private String products;
	    private HBox buttonsHbox;

	    
	    public TableRow()
	    {
	    	
	    }
	    
	    public TableRow(int orderID, String totalPrice, String products,HBox buttonsHbox,Button cancelButton ,Button continueButton) {
			super();
			this.OrderID = orderID;
			this.totalPrice = totalPrice;
			this.products = products;
			this.buttonsHbox = buttonsHbox;
			
		}

	    public int getOrderID() {
			return OrderID;
		}

		public void setOrderID(int orderID) {
			OrderID = orderID;
		}

		public String getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getProducts() {
			return products;
		}

		public void setProducts(String products) {
			this.products = products;
		}

		public HBox getButtonsHbox() {
			return buttonsHbox;
		}

		public void setButtonsHbox(HBox buttonsHbox) {
			this.buttonsHbox = buttonsHbox;
		}

	

		    
	}
}
