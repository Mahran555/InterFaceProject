package customermethods;

import application.CatalogPageInterFaceController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;



public class Customer {
	public static String cameFrom="Location";
	public static String respond = "No";
	public static Label IDErrorEmptyCart;//added to disable error from view after adding product
	public static VBox IDMiniCartVBox;//mini cart in category page
	public static Label IDTotalPrice;//total price in category page
	public static AnchorPane IDAddingProperties;//anchor adding properties in product cell
	public static Label IDLabelAdded;//add to cart label in product cell
	public static CatalogPageInterFaceController con;//controller for catalog page (to activate search when needed "refresh")
	
	@SuppressWarnings("static-access")
	public static void help (String message) {
    	Alert alert = new Alert(AlertType.INFORMATION);//init alert message for information
    	alert.setContentText(message);//showed message
    	alert.initStyle(StageStyle.DECORATED.UNDECORATED);//style
    	alert.show();//show
	}
	
	public static void cancelOrder() {
		
	}
	@SuppressWarnings("static-access")
	public static void confirmationMessage(String message,String header,Class<?> c)//creating suitable confirmation message
    {
		
		//create buttons yes or no
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");
		Alert alert1 = new Alert(AlertType.NONE,"Confimation",yes,no);//init alert message 
		alert1.setContentText(message);//showed message
		alert1.setHeaderText(header);
		DialogPane dialogPane = alert1.getDialogPane();//setting our css file for the diaglog pane(message)
		dialogPane.getStylesheets().add(c.getResource("/cssF/myDialogs.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert1.initStyle(StageStyle.DECORATED.UNDECORATED);//style
		alert1.showAndWait().ifPresent(response->{
  		if(response == yes)//if user want to use the coupon
  			respond="yes";
  		else
  			respond = "No";
  			
		});
		
    }
	//to check legality of textfield (for example only numbers and needed length)
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
	@SuppressWarnings("static-access")
	public static void CompletionMessage(String message) {//message of completion 
		
		Alert alert = new Alert(AlertType.INFORMATION);//init alert message for information
	  	alert.setContentText(message);//showed message
	  	alert.initStyle(StageStyle.DECORATED.UNDECORATED);//style
	  	alert.showAndWait();//show and wait untill ok is pressed
	  	
	}


	
}
