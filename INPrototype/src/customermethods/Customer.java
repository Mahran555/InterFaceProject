package customermethods;

import application.CatalogPageInterFaceController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;



/**
 * This class is a Customer information class, to save the Customer information 
 * connects between classes to get and set information in case its needed 
 * @author Mahran
 *
 */
public class Customer {
	/**
	 * to save the where the customer came from
	 */
	public static String cameFrom="Location";
	/**
	 * to save the customer's response to the confrimation message
	 */
	public static String respond = "No";
	/**
	 * Label that shows error message
	 * in this case to disable error from view after adding product
	 */
	public static Label IDErrorEmptyCart;
	/**
	 * VBox to save/show the products in Mini Cart for the customer in the catalog page
	 */
	public static VBox IDMiniCartVBox;
	/**
	 * Label to show total price in category page
	 */
	public static Label IDTotalPrice;
	/**
	 * AnchoPane to save adding properties AnchorPane from product cell
	 */
	public static AnchorPane IDAddingProperties;
	/**
	 * Label to show added to cart in product cell
	 */
	public static Label IDLabelAdded;
	/**
	 * to save the controller for catalog page (to activate search when needed "refresh")
	 */
	public static CatalogPageInterFaceController con;
	/**
	 * ImageView to show what page customer came from
	 */
	public static ImageView IDLocationStep;
	/**
	 * Image to help change the image path (top bar) according to the path the the customer took
	 */
	private static Image img;
	/**
	 * Static Method to set and shows a help message (for question mark "help" icon) as the message parameter that was sent
	 * @param message
	 */
	@SuppressWarnings("static-access")
	public static void help (String message) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText(message);
    	alert.initStyle(StageStyle.DECORATED.UNDECORATED);
    	alert.show();
	}
	

	/**
	 * Static Method to create a suitable confirmation message and sets it 
	 * as sent in message parameter and header and shows it
	 * and set the response of the customer to the choice that he made
	 * @param message
	 * @param header
	 * @param c
	 */
	@SuppressWarnings("static-access")
	public static void confirmationMessage(String message,String header,Class<?> c)
    {
		
		//create buttons yes or no
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");
		Alert alert1 = new Alert(AlertType.NONE,"Confimation",yes,no);
		alert1.setContentText(message);//showed message
		alert1.setHeaderText(header);
		DialogPane dialogPane = alert1.getDialogPane();
		dialogPane.getStylesheets().add(c.getResource("/cssF/myDialogs.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert1.initStyle(StageStyle.DECORATED.UNDECORATED);
		alert1.showAndWait().ifPresent(response->{
  		if(response == yes)
  			respond="yes";
  		else
  			respond = "No";
  			
		});
		
    }
	/**
	 * Static Method to check the TextField legality such as :
	 * the input are only numbers
	 * the input length is less or equals to limit parameter
	 * @param tFID the TextField 
	 * @param limit the length limit
	 */
	public static void textLegality(TextField tFID , int limit) {
		tFID.textProperty().addListener(new ChangeListener<String>() {//only numbers are allowed
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue,String newValue) {
		        
		    	if (!newValue.matches("\\d*")) 
		    		tFID.setText(newValue.replaceAll("[^\\d]", ""));//if not a number don't change
		    	else if (tFID.getText().length()>limit)
		    	{
		    		tFID.setText(tFID.getText().substring(0, limit));
		    	}
		       
		    	}
		    });
	}
	/**
	 * Static Method to show the completion message that was sent as a parameter
	 * @param message message to show 
	 */
	@SuppressWarnings("static-access")
	public static void CompletionMessage(String message) { 
		
		Alert alert = new Alert(AlertType.INFORMATION);
	  	alert.setContentText(message);
	  	alert.initStyle(StageStyle.DECORATED.UNDECORATED);
	  	alert.showAndWait();
	  	
	}
	/**
	 * Method to set path image according to the path that the customer took
	 */
	public static void checkCustomerPath() {
		if(Customer.cameFrom.equals("MyCart"))
		{
			img = new Image("/icons/shopping-cart.png");
			IDLocationStep.setImage(img);
		}
		else if(Customer.cameFrom.equals("Location"))
		{
			img = new Image("/icons/map.png");
			IDLocationStep.setImage(img);
		}
		
	}


	
}
