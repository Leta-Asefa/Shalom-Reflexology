package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;

public class AddNewPatientController implements Initializable {

	@FXML
	private TextField phoneTextField,ageTextField,nameTextField;
	@FXML
	private RadioButton maleButton,femaleButton;
	
	@FXML
	private Button addButton;
	@FXML private ImageView image;
	@FXML private Label label;
	@FXML private TextField cardTextField;

	 private ShalomDAO dao;
	@FXML private RadioButton mbButton;
	@FXML private  RadioButton cashButton;
	@FXML  private TextField referenceTextField;
	@FXML  private Label referenceLabel;
	@FXML private ComboBox<String> bankComboBox;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		
	dao=new ShalomDAO();
	femaleButton.setSelected(true);
	cashButton.setSelected(true);
	bankComboBox.setItems(dao.getBanksName());

	inputValidation();
	
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddNewPatientController","initializeMethod",e.getClass().getName());

			
		}
	
	}
	
	
	
	private void inputValidation() {
		
		try {
	
		phoneTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[0-9]{0,10}")) {
				phoneTextField.setText(newValue);
			} else {
				phoneTextField.setText(oldValue);
					}
			}
			
		});

		ageTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[0-9]{0,2}")) {
				ageTextField.setText(newValue);
			} else {
				ageTextField.setText(oldValue);
					}
			}
			
		});

		
		cardTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[0-9]{0,4}")) {
				cardTextField.setText(newValue);
			} else {
				cardTextField.setText(oldValue);
					}
			}
			
		});
		
		
		referenceTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-z0-9]{0,40}")) {
				referenceTextField.setText(newValue);
			} else {
				referenceTextField.setText(oldValue);
					}
			}
			
		});
		
		
		
		
		nameTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-Z\s]{0,15}")) {
				nameTextField.setText(newValue);
			} else {
				nameTextField.setText(oldValue);
					}
			}
			
		});
		
		
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddNewPatientController","inputValidationMethod",e.getClass().getName());

			
		}
	}



	public void add(ActionEvent e) {
		
		try {
			
			
			
			if(cashButton.isSelected()) {
				

				if( (!(phoneTextField.getText().equals("")) && !(ageTextField.getText().equals("")) && !(nameTextField.getText().equals("")) && !(cardTextField.getText().equals("")))) {
					
					int phone=Integer.parseInt(phoneTextField.getText());
					int age=Integer.parseInt(ageTextField.getText());
					String name=nameTextField.getText();
				
				
					
					
				if(maleButton.isSelected() ) {
					dao.addNewPatient(phone, name, age, 'M', "", "", "","");
					dao.takeFirstAttendance(phone, 'E', "firsta");
					dao.recieveCardPayment(phone, Integer.parseInt(cardTextField.getText()), 'C',LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString(),"" );
				
					addButton.setDisable(true);
					image.setVisible(true);
					label.setVisible(true);
				}
				
			

				else if(femaleButton.isSelected()) {
					
					dao.addNewPatient(phone, name, age, 'F', "", "", "","");
					dao.takeFirstAttendance(phone, 'E', "firsta");
					dao.recieveCardPayment(phone, Integer.parseInt(cardTextField.getText()), 'C',LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString(),"" );
				
					addButton.setDisable(true);
					image.setVisible(true);
					label.setVisible(true);
				
				}
				
				
				
				
				
				}
				
				else {
					
					new CallAlert(AlertType.WARNING,"","","Please, fulfil all necessary datas ! ");
				}
				
				
				
				
			}else if(mbButton.isSelected()) {
				
				
				
				
				if( !phoneTextField.getText().equals("") && !ageTextField.getText().equals("") && !nameTextField.getText().equals("") && !cardTextField.getText().equals("")  && bankComboBox.getValue()!=null && !referenceTextField.getText().equals(""))  {
					
					int phone=Integer.parseInt(phoneTextField.getText());
					int age=Integer.parseInt(ageTextField.getText());
					String name=nameTextField.getText();
				
				
					
				
				if(maleButton.isSelected() ) {
					dao.addNewPatient(phone, name, age, 'M', "", "", "","");
					dao.takeFirstAttendance(phone, 'E', "firsta");
					dao.recieveCardPayment(phone, Integer.parseInt(cardTextField.getText()), 'M',LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString(),referenceTextField.getText()+" - "+bankComboBox.getValue() );
					

					addButton.setDisable(true);
					image.setVisible(true);
					label.setVisible(true);
					
				}

				
				 if(femaleButton.isSelected() ) {
					
					dao.addNewPatient(phone, name, age, 'F', "", "", "","");
					dao.takeFirstAttendance(phone, 'E', "firsta");
					dao.recieveCardPayment(phone, Integer.parseInt(cardTextField.getText()), 'M',LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString(),referenceTextField.getText()+" - "+bankComboBox.getValue()  );
				

					addButton.setDisable(true);
					image.setVisible(true);
					label.setVisible(true);
					
				}
				
				
				}
				
				else {
					
					new CallAlert(AlertType.WARNING,"","","Please, fulfil all necessary datas ! ");
				}
				
			}
			
			
			
			
			
			
		
		
		}
		catch(NumberFormatException ex) {
			new CallAlert(AlertType.WARNING,"","","Please, insert the phone number in this format ( 0987654321 ) ");

		}
	catch (Exception ex) {
		
		new CallAlert(AlertType.WARNING,"AddNewPatientController","addMethod",ex.getClass().getName());

		
	}
	}



	@FXML public void selectPaymentMethod() {
		try {
		
		if (mbButton.isSelected()) {
			referenceTextField.setVisible(true);
			referenceLabel.setVisible(true);
			bankComboBox.setVisible(true);
			
			
		} else if(cashButton.isSelected()) {

			referenceTextField.setVisible(false);
			referenceLabel.setVisible(false);
			bankComboBox.setVisible(false);

		}
		
		
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddNewPatientController","selectPaymentMethod",e.getClass().getName());

			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
