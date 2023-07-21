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

public class ReceptionController {
	
	@FXML
	private AnchorPane contentArea;
	
	

	public void backToLoginPage(ActionEvent e) {
		
		try {
			Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/LoginFxml.fxml"));
			Scene scene= new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"PaySalaryController","backToLoginPageMethod",ex.getClass().getName());


		}
		
	}
	
	public void acceptPayment(ActionEvent e) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/ReceptionFxml/AcceptPayment.fxml"));
			 
			 contentArea.getChildren().removeAll();
			 contentArea.getChildren().setAll(root);
		
		
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PaySalaryController","acceptPaymentMethod",ex.getClass().getName());


			}
	}
	
	
	
public void addNewPatient(ActionEvent e) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/application/ReceptionFxml/AddNewPatient.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PaySalaryController","addNewPatientMethod",ex.getClass().getName());


			}
	}


public void enterPatients(ActionEvent e) {
	
	try {
		Parent root=FXMLLoader.load(getClass().getResource("/application/ReceptionFxml/EnterPatients.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"PaySalaryController","enterPatientsMethod",ex.getClass().getName());


		}
}



public void reflexology(ActionEvent e) {
	try {
		Parent root=FXMLLoader.load(getClass().getResource("/application/ReceptionFxml/Reflexology.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"PaySalaryController","refelxologyMethod",ex.getClass().getName());


		}
	
	
}


public void withdrawMoney(ActionEvent e) {
	try {
		Parent root=FXMLLoader.load(getClass().getResource("/application/ReceptionFxml/WithdrawMoney.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"PaySalaryController","withdrawMoneyMethod",ex.getClass().getName());


		}
	
}

@FXML public void activatePatient() {
	
	try {
		Parent root= FXMLLoader.load(getClass().getResource("/application/ReceptionFxml/ActivatePatient.fxml"));
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"PaySalaryController","activatePatientMethod",ex.getClass().getName());


		}
}

@FXML public void bankEntryAnalysisForReception() {
	
	try {
		Parent root=FXMLLoader.load(getClass().getResource("/application/ReceptionFxml/BankEntryAnalysisForReception.fxml"));
	
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll(root);
	
	
	
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"PaySalaryController","bankEntryAnalysisForReceptionMethod",ex.getClass().getName());


		}
	
	
	
}




	
}
