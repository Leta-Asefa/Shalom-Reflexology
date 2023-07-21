package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;



public class HowManyCustomersAreTodayController implements Initializable {

	@FXML
	private Label rxLabel,thLabel,maLabel;
	@FXML
	private DatePicker datePicker;
	
	private ShalomDAO dao;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
		
		dao= new ShalomDAO();
		String date=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		rxLabel.setText(String.valueOf(dao.howManyReflexology(date)));
		maLabel.setText(String.valueOf(dao.howManyMassages(date)));
		thLabel.setText(String.valueOf(dao.howManyTherapies(date)));
		
		datePicker.setEditable(false);
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"HowManyCustomersAreTodayController","initializeMethod",ex.getClass().getName());


			}
	}
	
	
	
	
	
	
	
	
	
	@FXML public void search(ActionEvent event) {
		
		try {
		
		if(datePicker.getValue()!=null) {
		String date=datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
		rxLabel.setText(String.valueOf(dao.howManyReflexology(date)));
		maLabel.setText(String.valueOf(dao.howManyMassages(date)));
		thLabel.setText(String.valueOf(dao.howManyTherapies(date)));
			
		
	
		}
		else {
			
			
		}
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"HowManyCustomersAreTodayController","searchMethod",ex.getClass().getName());


			}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
