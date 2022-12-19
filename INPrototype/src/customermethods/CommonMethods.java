package customermethods;

import java.io.IOException;

import application.LoginHomePageInterFaceController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class CommonMethods {
	private static double xOffset = 0;
	private static double yOffset = 0; 
	public static Parent switchScene(Class<?> c,Stage stage,String newPageName,String cssFileName)
	{
		stage.close();
		Parent root=null;
		Scene scene;
		String filename = "/FXMLs/"+newPageName;
		String filecss = "/CssF/"+cssFileName;
		FXMLLoader loader = new FXMLLoader(c.getResource(filename));
	    try {
			root = loader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("im at exception");
			System.out.println("filename="+filename);
			e.printStackTrace();
		}
		scene = new Scene(root);
		scene.getStylesheets().add(c.getResource(filecss).toExternalForm());
		stage.setScene(scene);
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
			
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX()-xOffset);
				stage.setY(event.getScreenY()-yOffset);
			}
			
		});
		stage.show();
		return root;
	}
	
	public static Parent switchScene(Class<?> c,Stage stage,String newPageName)
	{
		Parent root=null;
		Scene scene;
		String filename = "/FXMLs/"+newPageName;
		FXMLLoader loader = new FXMLLoader(c.getResource(filename));
	    try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root);
		stage.setScene(scene);
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
			
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX()-xOffset);
				stage.setY(event.getScreenY()-yOffset);
			}
			
		});
		stage.show();
		return root;
		
	}
	public static LoginHomePageInterFaceController getController(LoginHomePageInterFaceController c,String page)
	{
		System.out.println(c);
		String filename = "/FXMLs/"+page;
		FXMLLoader loader = new FXMLLoader(c.getClass().getResource(filename));
		c=loader.getController();
		return c;
	}

}
