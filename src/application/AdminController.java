package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminController {
	
	@FXML
	private AnchorPane contentArea;
	
	
public void BackToLoginPage(ActionEvent e) {
		
		try {
			Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
			Parent root=FXMLLoader.load(getClass().getResource("/application/LoginFxml.fxml"));
			Scene scene= new Scene(root);
			stage.setScene(scene);
			stage.show();
		
		
		
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
catch (Exception ex) {
	
	new CallAlert(AlertType.WARNING,"AdminController","backToLoginPageMethod",ex.getClass().getName());

	
}
	}

public void currentPatient(ActionEvent e) {
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/CurrentPatient.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
    	
    	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
catch (Exception ex) {

new CallAlert(AlertType.WARNING,"AdminController","currentPatienMethod",ex.getClass().getName());


}
    	
    }
    
public void dailyIncome(ActionEvent e) {
	try {
		Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/DailyIncome.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
catch (Exception ex) {

new CallAlert(AlertType.WARNING,"AdminController","dailyMethod",ex.getClass().getName());


}
    }

public void employeesInformation(ActionEvent e) {
	try {
		Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/Employee'sInformation.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"AdminController","employeesInformationMethod",ex.getClass().getName());


		}
	
}


public void howManyCustomersAreToday(ActionEvent e) {
	Parent root;
	try {
		 root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/HowManyCustomersAreToday.fxml"));
		 contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
			
	
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}
	
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"AdminController","howManyCustomersAreTodayMethod",ex.getClass().getName());


		}
	
}

public void monthlyIncome (ActionEvent e) {
	
	try {
		Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/MonthlyIncome.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}catch (Exception ex) {

new CallAlert(AlertType.WARNING,"AdminController","monthlyMethod",ex.getClass().getName());


}
}

public void patientsInformation(ActionEvent e) {
	
	try {
		Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/PatientsInformation.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}catch (Exception ex) {

new CallAlert(AlertType.WARNING,"AdminController","patientsInformationMethod",ex.getClass().getName());


}
	
}

public void paySalary(ActionEvent e) {
	try {
		Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/PaySalary.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}catch (Exception ex) {

new CallAlert(AlertType.WARNING,"AdminController","paySalaryMethod",ex.getClass().getName());


}
	
}

public void addConstants(ActionEvent e) {
	
	
	try {

		Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/AddConstants.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	
	
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}catch (Exception ex) {

new CallAlert(AlertType.WARNING,"AdminController","addConstantsMethod",ex.getClass().getName());


}
}

public void bankEntryAnalysis() {
	

	try {

		Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/BankEntryAnalysis.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	
	
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"AdminController","bankEntryAnalysisMethod",ex.getClass().getName());


		}
	
}



















}
