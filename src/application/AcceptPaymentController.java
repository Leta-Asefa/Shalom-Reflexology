package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;

public class AcceptPaymentController implements Initializable {

	
	
	@FXML
	private Label totalLabel,creditLabel,nameLabel;
	@FXML
	private ImageView image;
	@FXML
	private Button searchButton,saveButton,Button;
	@FXML
	private TextField searchTextField,amountTextField;
	@FXML
	private RadioButton mobileBankingButton,cashButton;
	@FXML
	private CheckBox cardCheckBox;
	@FXML Label label;
	
	private ShalomDAO dao;
	@FXML private TextField referenceTextField;
	@FXML  private Label referenceLabel;
	@FXML  private DatePicker datePicker;
	@FXML  private TextField p1;
	@FXML  private TextField p5;
	@FXML private  TextField p4;
	@FXML  private TextField p3;
	@FXML private  TextField p2;
	@FXML private  TextField p6;
	@FXML  private TextField p10;
	@FXML  private TextField p9;
	@FXML  private TextField p8;
	@FXML private  TextField p7;
	@FXML  private TextField p11;
	@FXML private  TextField p15;
	@FXML private  TextField p14;
	@FXML  private TextField p13;
	@FXML  private TextField p12;
	@FXML  private CheckBox checkBox;
	@FXML private  Label l12;
	@FXML  private Label l13;
	@FXML  private Label l14;
	@FXML private  Label l15;
	@FXML private  Label l11;
	@FXML  private Label l7;
	@FXML  private Label l8;
	@FXML  private Label l9;
	@FXML private  Label l10;
	@FXML private  Label l6;
	@FXML private  Label l2;
	@FXML private  Label l3;
	@FXML private  Label l4;
	@FXML  private Label l5;
	@FXML private  Label l1;
	private String dayOfWeek=null;
	@FXML private ComboBox<String> bankComboBox;
	@FXML Label startingDateLabel;
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		try {
		
		dao= new ShalomDAO();
		inputValidation();
		cashButton.setSelected(true);
		datePicker.setEditable(false);
		bankComboBox.setItems(dao.getBanksName());
	
		}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AcceptPaymentController","initializeMethod",e.getClass().getName());

			
		}
		
		
		}
	
	
	
	
	
	
	
	private void inputValidation() {
		try {
		searchTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[0-9]{0,10}")) {
				searchTextField.setText(newValue);
			} else {
				searchTextField.setText(oldValue);
					}
			}
			
		});
		
		amountTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[0-9]{0,6}")) {
				amountTextField.setText(newValue);
			} else {
				amountTextField.setText(oldValue);
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

	
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AcceptPaymentController","inputValidationMethod",e.getClass().getName());

			
		}
		
		
		}







	public void save(ActionEvent  e) {
		
		
		try {
		int phone = 0;
		
		
		
		if(cashButton.isSelected()) {
			
			

			if(!(searchTextField.getText().equals("")) && !(amountTextField.getText().equals("")) ) {
				
			phone=Integer.parseInt(searchTextField.getText());
			int amount=Integer.parseInt(amountTextField.getText());
			String date=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()	;
			
				dao.acceptPayment(phone, 'C', amount,date,"");
				
				
				int credit=dao.getCredit(phone);
				creditLabel.setTextFill(Color.GREEN);
				creditLabel.setText(String.valueOf(credit));
				amountTextField.setText("");
				saveButton.setDisable(true);
				image.setVisible(true);
				label.setVisible(true);
				
			}
			else {
			new CallAlert(AlertType.WARNING,"","","Please, fulfil all necessary datas ! ");
			
			}
			
			
		}
		
		
		else if (mobileBankingButton.isSelected()) {
			

			
			
			if(!(searchTextField.getText().equals("")) && !(amountTextField.getText().equals("")) && !referenceTextField.getText().equals("") && bankComboBox.getValue()!=null) {
				
			phone=Integer.parseInt(searchTextField.getText());
			int amount=Integer.parseInt(amountTextField.getText());
			String date=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()	;
			
			

					dao.acceptPayment(phone, 'M', amount,date,referenceTextField.getText()+" - "+bankComboBox.getValue());
				
				
				
				
			
				int credit=dao.getCredit(phone);
				creditLabel.setTextFill(Color.GREEN);
				creditLabel.setText(String.valueOf(credit));
				amountTextField.setText("");
				saveButton.setDisable(true);
				image.setVisible(true);
				label.setVisible(true);
			}
			else {
			new CallAlert(AlertType.WARNING,"","","Please, fulfil all necessary datas ! ");
			
			}
			
		}
		
		
		
		
		}catch (StringIndexOutOfBoundsException es) {
			new  CallAlert (AlertType.WARNING,"","","coudn't get total payment since their is no prescription !");
			
		}
	catch (Exception ex) {
		
		new CallAlert(AlertType.WARNING,"AcceptPaymentController","saveMethod",ex.getClass().getName());

		
	}
	
		
		
		
		
		
	
	}
	
	
	
	public void search(ActionEvent e) {
		
		try {
		
		if(!(searchTextField.getText().equals("")) ) {
		
		
		int phone=Integer.parseInt(searchTextField.getText());
		String name=dao.getPatientsNameByPhone(phone);
		int total=dao.getTotalPaymentDynamic(phone);
		int credit=dao.getCredit(phone);
		totalLabel.setText(String.valueOf(total));
		creditLabel.setText(String.valueOf(credit));
		nameLabel.setText(name);
		}
		
		else {
			new CallAlert(AlertType.WARNING,"","","Please, enter the phone number");	
			nameLabel.setText("");
			creditLabel.setText("");
			totalLabel.setText("");
			
		}
		
		
		
		}catch (StringIndexOutOfBoundsException es) {
			new  CallAlert (AlertType.WARNING,"","","coudn't get total payment since their is no prescription !");
			
		}
		
		catch(NumberFormatException ex) {
			new CallAlert(AlertType.WARNING,"","","Please, insert the phone number in this format ( 0987654321 ) ");

			
		}
		
	catch (Exception ex) {
		
		new CallAlert(AlertType.WARNING,"AcceptPaymentController","searchMethod",ex.getClass().getName());

		
	}
	
	}







	@FXML public void selectPaymentMethod() {
		
		try {
		
		if(mobileBankingButton.isSelected()) {
			referenceTextField.setVisible(true);
			referenceLabel.setVisible(true);
			bankComboBox.setVisible(true);

		}else if(cashButton.isSelected()) {

			referenceTextField.setVisible(false);
			referenceLabel.setVisible(false);
			bankComboBox.setVisible(false);

		}
		
		}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AcceptPaymentController","selectPaymentMethod",e.getClass().getName());

			
		}
		
		
	}







	@FXML public void setPrescriptions() {
		
		
		
		try {
		if(!searchTextField.getText().equals("")) {
		
		
		String temporaryPrescription=dao.getTemporaryPrescription(Integer.parseInt( searchTextField.getText()));
		dayOfWeek=datePicker.getValue().getDayOfWeek().toString();
		
		List<TextField> textFieldList= new ArrayList<>();
		
		
		textFieldList.add(p1);
		textFieldList.add(p2);
		textFieldList.add(p3);
		textFieldList.add(p4);
		textFieldList.add(p5);
		textFieldList.add(p6);
		textFieldList.add(p7);
		textFieldList.add(p8);
		textFieldList.add(p9);
		textFieldList.add(p10);
		textFieldList.add(p11);
		textFieldList.add(p12);
		textFieldList.add(p13);
		textFieldList.add(p14);
		textFieldList.add(p15);
		
		
		int count=0;
		LocalDate date=datePicker.getValue();
		temporaryPrescription+=temporaryPrescription+".";
		
		
		
		
		
		
		
		
		if(dayOfWeek.equals("MONDAY")) {
			
			int pattern=1;

			
			for (int i = 0; i < 120; i+=4) {
				
				if(!temporaryPrescription.substring(i,i+4).equals("null")) {
					
					
					if(count<15) {
						
					
					textFieldList.get(count).setText(temporaryPrescription.substring(i,i+4)+","+date);
					count++;
					
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					
					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
					
					
					}
				}
				else {
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
				}
				
			}
			
			
		}
		
		else if(dayOfWeek.equals("TUESDAY")) {
			
	int pattern=1;

			
			for (int i = 0; i < 120; i+=4) {
				
				if(!temporaryPrescription.substring(i,i+4).equals("null")) {
					
					
					if(count<15) {
						
					
					textFieldList.get(count).setText(temporaryPrescription.substring(i,i+4)+","+date);
					count++;
					
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					
					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
					
					
					}
				}
				else {
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
				}
				
			}
			
			
		}
		
		else if(dayOfWeek.equals("WEDNESDAY")) {
			
			int pattern=2;


			for (int i = 0; i < 120; i+=4) {
				
				if(!temporaryPrescription.substring(i,i+4).equals("null")) {
					
					
					if(count<15) {
						
					
					textFieldList.get(count).setText(temporaryPrescription.substring(i,i+4)+","+date);
					count++;
					
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					
					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
					
					
					}
				}
				else {
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
				}
				
			}
			
		}
		
		else if(dayOfWeek.equals("THURSDAY")) {
			
			
	int pattern=2;

			
			for (int i = 0; i < 120; i+=4) {
				
				if(!temporaryPrescription.substring(i,i+4).equals("null")) {
					
					
					if(count<15) {
						
					
					textFieldList.get(count).setText(temporaryPrescription.substring(i,i+4)+","+date);
					count++;
					
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					
					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
					
					
					}
				}
				else {
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
				}
				
			}
			
			
		}

		else if(dayOfWeek.equals("FRIDAY")) {
			
			int pattern=3;


			for (int i = 0; i < 120; i+=4) {
				
				if(!temporaryPrescription.substring(i,i+4).equals("null")) {
					
					
					if(count<15) {
						
					
					textFieldList.get(count).setText(temporaryPrescription.substring(i,i+4)+","+date);
					count++;
					
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					
					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
					
					
					}
				}
				else {
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
				}
				
			}
			
			
		}

		else if(dayOfWeek.equals("SATURDAY")) {
			
	int pattern=3;

			
			for (int i = 0; i < 120; i+=4) {
				
				if(!temporaryPrescription.substring(i,i+4).equals("null")) {
					
					
					if(count<15) {
						
					
					textFieldList.get(count).setText(temporaryPrescription.substring(i,i+4)+","+date);
					count++;
					
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					
					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
					
					
					}
				}
				else {
					
					if(pattern%3==0) {
						
						date=date.plusDays(3);
						pattern++;

					}else {
						
						date=date.plusDays(2);
						pattern++;

					}
					
				}
				
			}
			
		}

		else if(dayOfWeek.equals("SUNDAY")) {
			
			new CallAlert(AlertType.WARNING,"","","Can't give an appointment on Sunday ! ");

		}
		
		
		startingDateLabel.setText(p1.getText());
		
		
		}else {
			new CallAlert(AlertType.WARNING,"","","Please, insert the phone number first ");

		}
		
		
	}catch (Exception e) {
		
		new CallAlert(AlertType.WARNING,"AcceptPaymentController","showPrescriptionBoxesMethod",e.getClass().getName());

		
	}
		
		
	}







	@FXML public void showPrescriptionBoxes() {
		
		try {
		if(checkBox.isSelected()) {
			p1.setVisible(true);
			p2.setVisible(true);
			p3.setVisible(true);
			p4.setVisible(true);
			p5.setVisible(true);
			p6.setVisible(true);
			p7.setVisible(true);
			p8.setVisible(true);
			p9.setVisible(true);
			p10.setVisible(true);
			p11.setVisible(true);
			p12.setVisible(true);
			p13.setVisible(true);
			p14.setVisible(true);
			p15.setVisible(true);
			l1.setVisible(true);
			l2.setVisible(true);
			l3.setVisible(true);
			l4.setVisible(true);
			l5.setVisible(true);
			l6.setVisible(true);
			l7.setVisible(true);
			l8.setVisible(true);
			l9.setVisible(true);
			l10.setVisible(true);
			l11.setVisible(true);
			l12.setVisible(true);
			l13.setVisible(true);
			l14.setVisible(true);
			l15.setVisible(true);
		datePicker.setVisible(true);
		Button.setVisible(true);
		startingDateLabel.setVisible(true);
		startingDateLabel.setText(dao.getFirstPrescription(searchTextField.getText()));

		}
		
		else {
			
			p1.setVisible(false);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
			p5.setVisible(false);
			p6.setVisible(false);
			p7.setVisible(false);
			p8.setVisible(false);
			p9.setVisible(false);
			p10.setVisible(false);
			p11.setVisible(false);
			p12.setVisible(false);
			p13.setVisible(false);
			p14.setVisible(false);
			p15.setVisible(false);
			l1.setVisible(false);
			l2.setVisible(false);
			l3.setVisible(false);
			l4.setVisible(false);
			l5.setVisible(false);
			l6.setVisible(false);
			l7.setVisible(false);
			l8.setVisible(false);
			l9.setVisible(false);
			l10.setVisible(false);
			l11.setVisible(false);
			l12.setVisible(false);
			l13.setVisible(false);
			l14.setVisible(false);
			l15.setVisible(false);
			datePicker.setVisible(false);
			startingDateLabel.setVisible(false);
		Button.setVisible(false);

			
		}
		
		
		
			}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AcceptPaymentController","setPrescriptionsMethod",e.getClass().getName());

			
		}
		
	}







	 public void savePrescription() {
		
		try {
		 
		 
if( dayOfWeek!=null) {
			
			dao.updatePrescription(Integer.parseInt( searchTextField.getText()), p1.getText(), p2.getText(), p3.getText(), p4.getText(), p5.getText(), p6.getText(), p7.getText(), p8.getText(), p9.getText(), p10.getText(), p11.getText(), p12.getText(), p13.getText(), p14.getText(), p15.getText());
			Button.setDisable(true);
			new CallAlert(AlertType.INFORMATION,"","","Saved Successfully ! ");
			
		}
		
else {
	new CallAlert(AlertType.WARNING,"","","Please select the date first ! ");

}
		


		}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AcceptPaymentController","savePrescriptionMethod",e.getClass().getName());

			
		}
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
