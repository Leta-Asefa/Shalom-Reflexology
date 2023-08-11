package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class BankReception implements Initializable{

	@FXML TextField referenceTextField;
	@FXML ComboBox<String> bankComboBox;
	@FXML Button enterButton;
	private ShalomDAO dao;
	private ObservableList<String> bankList;
	@FXML DatePicker datePicker;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
		
		
		dao= new ShalomDAO();
		bankList=dao.getBanksName();
		bankComboBox.setItems(bankList);
		bankComboBox.setEditable(true);
		
		
		referenceTextField.textProperty().addListener( new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				
				if(newValue.matches("[a-zA-z0-9]{0,40}")) {
					referenceTextField.setText(newValue);
					
				}
				else {
					referenceTextField.setText(oldValue);

					
				}
				
				
				
			}
			
		});
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"BankReception","initializeMethod",ex.getClass().getName());


			}
		
	}

	@FXML public void enter() {
		
		try {
		
		if(!referenceTextField.getText().equals("") && bankComboBox.getValue()!=null && datePicker.getValue()!=null ) {
			
			
			
			String date=datePicker.getValue().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
			
			int total=dao.getDailyTotalIncome(date);
			int mb=dao.getDailyTotalMB(date);
			int withdrawal=dao.getDailyWithdrawal(date);
			int bank=dao.getDailyTotalCash(date)-dao.getDailyWithdrawal(date);
			//int net=total - (bank+withdrawal+mb);
			
			dao.insertIntoBankAnalysis(total, withdrawal,bank ,"",mb ,referenceTextField.getText() +" - "+bankComboBox.getValue() , date);
			
			
			enterButton.setDisable(true);
			new CallAlert(AlertType.INFORMATION,"","","saved successfully !");

			
		}else {
			
			new CallAlert(AlertType.WARNING,"","","please, fulfill all neccessary datas !");
		}
		
		
		
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"BankReception","enterMethod",ex.getClass().getName());


			}
		
		
	}












}
