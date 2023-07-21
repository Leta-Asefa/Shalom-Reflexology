package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CallAlert {
	
	Alert alert;
	
	
	
	CallAlert(AlertType type,String title,String header, String content ) {
		
		
		try {
			
		
		alert= new Alert(type);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.setHeaderText(header);
		
		
		alert.show();
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"CallAlery","callAlertConstructur",ex.getClass().getName());


			}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
