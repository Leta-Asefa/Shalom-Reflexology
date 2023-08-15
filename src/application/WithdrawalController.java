package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class WithdrawalController implements Initializable{

	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private TextArea reasonTextArea;
	@FXML
	private TextField amountTextField;
	@FXML
	private Button withdrawButton;
	@FXML
	private ImageView image;
	
	
	private ShalomDAO dao;
	private ObservableList<String> list;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		
		dao=new ShalomDAO();
		list=dao.getAllEmployeeName();
		comboBox.setItems(list);
		comboBox.setEditable(true);
		inputValidation();
		
		}catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"WithrdrawalController","initializeMethod",ex.getClass().getName());


			}
	}
	
	
	
	
	
	
	private void inputValidation() {
		
		try {
		
		amountTextField.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (newValue.matches("[0-9-]{0,6}")) {
					amountTextField.setText(newValue);
				} else {
					amountTextField.setText(oldValue);
						}
				}
				
			});


		reasonTextArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-Z\s]{0,29}")) {
				reasonTextArea.setText(newValue);
			} else {
				reasonTextArea.setText(oldValue);
					}
			}
			
		});
		
		
		}catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"WithrdrawalController","inputValidationMethod",ex.getClass().getName());


			}
	}






	public void withdraw(ActionEvent e) {
		
		try {
		
		if(!(comboBox.getValue()==null) && !(amountTextField.getText().equals("")) && 
				!(reasonTextArea.getText().equals(""))	) {
		
		dao.insertIntoWithdrawal( comboBox.getValue(), Integer.parseInt(amountTextField.getText()),
				reasonTextArea.getText(), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		withdrawButton.setDisable(true);
		image.setVisible(true);
		
		}
		else {
			
			new CallAlert(AlertType.WARNING,"","","Please, fulfil all necessary datas ! ");
	
		}
		
		}catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"WithrdrawalController","withdrawMethod",ex.getClass().getName());


			}
	}

}
