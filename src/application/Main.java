package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			 
			Parent root =FXMLLoader.load(getClass().getResource("LoginFxml.fxml"));
			Scene scene = new Scene(root,1400,750);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			Image icon=new Image (getClass().getResource("Logo.jpg").toURI().toString());
			stage.getIcons().add(icon);
			stage.show();
			stage.centerOnScreen();
			
			
		} 
	
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"MainClass","initializeMethod",ex.getClass().getName());


		}
	}

	public static void main(String[] args) {
		
		try {
		launch(args);
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"HowManyCustomersAreTodayController","initializeMethod",ex.getClass().getName());


			}
	}
}
