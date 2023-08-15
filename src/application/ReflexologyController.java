package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class ReflexologyController implements Initializable{
	
@FXML
private ComboBox<String> massagerComboBox,typeComboBox;
@FXML
private TextField priceTextField;

@FXML
private RadioButton cashButton,mbButton;

@FXML
private Button addButton;
@FXML
private RadioButton isPatientButton;
@FXML private ImageView image;
@FXML private Label label;




	private ObservableList<String> massagerList;
	
	private ObservableList<String> typeList;
	private ObservableList<String> bankList;
	
	private ShalomDAO dao;
	@FXML private TextField referenceTextField;
	@FXML private Label referenceLabel;
	@FXML private ComboBox<String> bankComboBox;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		
		
		dao= new ShalomDAO();
		massagerList=FXCollections.observableArrayList();
		typeList=FXCollections.observableArrayList();
		massagerList=dao.getMassagerItems();
		typeList=dao.getReflexologyItems();
		bankList=dao.getBanksName();
	
		
		massagerComboBox.setItems(massagerList);
		typeComboBox.setItems(typeList);
		bankComboBox.setItems(bankList);
		
		cashButton.setSelected(true);
		inputValidation();
		
		
		
		}catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"RefleoxologyController","initializeMethod",ex.getClass().getName());


			}
		
		}
	
	
	
	private void inputValidation() {
		try {

		priceTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				
				if (newValue.matches("[0-9]{0,8}")) {
					priceTextField.setText(newValue);
					
				} else {
					priceTextField.setText(oldValue);
				}
				
			}
				
		});
		
		
		
		

		referenceTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				
				if (newValue.matches("[a-zA-z0-9]{0,40}")) {
					referenceTextField.setText(newValue);
					
				} else {
					referenceTextField.setText(oldValue);
				}
				
			}
				
		});
		
		}catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"RefleoxologyController","inputValidationMethod",ex.getClass().getName());


			}
		
	}



	public void add(ActionEvent e) {
		
		try {
		
		
		if (cashButton.isSelected()) {
			

			if(  !(priceTextField.getText().equals("")) && !( massagerComboBox.getValue()==null) && !( typeComboBox.getValue()==null) )
			{
				int price=Integer.parseInt(priceTextField.getText());
				LocalDate date= LocalDate.now();
				String currentDate=date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
				
				
					if(isPatientButton.isSelected()) {
					
						dao.insertIntoReflexology(massagerComboBox.getValue(),price,'C',currentDate ,typeComboBox.getValue() ,dao.getMassagerId(massagerComboBox.getValue()) ,"");
						dao.insertIntoReflexology("0",0-price,'C',currentDate ,typeComboBox.getValue() ,100000 ,"");
						
					}else {
						dao.insertIntoReflexology(massagerComboBox.getValue(),price,'C',currentDate ,typeComboBox.getValue() ,dao.getMassagerId(massagerComboBox.getValue()) ,"");
	}
					addButton.setDisable(true);
					image.setVisible(true);
					label.setVisible(true);
				
					
			}
			
			
			
			else {
			new CallAlert(AlertType.WARNING,"","","Please, fullfil all necessary datas !");	
			}
		
			
			
			
			
			
			
		} else if(mbButton.isSelected()) {

			if( !(priceTextField.getText().equals("")) && !( massagerComboBox.getValue()==null) && !( typeComboBox.getValue()==null) && (!(referenceTextField.getText().equals("")) )    &&  (! (bankComboBox.getValue()==null))     )
			{
				int price=Integer.parseInt(priceTextField.getText());
				LocalDate date= LocalDate.now();
				String currentDate=date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
				
					
					if(isPatientButton.isSelected()) {	
						
						dao.insertIntoReflexology(massagerComboBox.getValue(),price,'M',currentDate ,typeComboBox.getValue() ,dao.getMassagerId(massagerComboBox.getValue()),referenceTextField.getText()+" - "+bankComboBox.getValue());
						dao.insertIntoReflexology("",0-price,'M',currentDate ,typeComboBox.getValue() ,100000,referenceTextField.getText()+" - "+bankComboBox.getValue());

					}
					else {
						
						dao.insertIntoReflexology(massagerComboBox.getValue(),price,'M',currentDate ,typeComboBox.getValue() ,dao.getMassagerId(massagerComboBox.getValue()),referenceTextField.getText()+" - "+bankComboBox.getValue());
					}
						addButton.setDisable(true);
						image.setVisible(true);
						label.setVisible(true);
						
					} else {

						new CallAlert(AlertType.WARNING,"","","Please, fullfil all necessary datas !");	

					}
					
					
				} 
		}catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"RefleoxologyController","addMethod",ex.getClass().getName());


			}
		
		}
			
			
		
		
	


	@FXML public void getPrice() {
		
		try {
		
		priceTextField.setText(String.valueOf( dao.getPrice(typeComboBox.getValue(), 'R')));
		
		
		}catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"RefleoxologyController","getPriceMethod",ex.getClass().getName());


			}
		
	}



	@FXML public void selectPaymentMethod() {
		
		try {
		
		
		if (cashButton.isSelected()) {
			
			referenceTextField.setVisible(false);
			referenceLabel.setVisible(false);
			bankComboBox.setVisible(false);;
		
		} else if(mbButton.isSelected()) {

		
			referenceTextField.setVisible(true);
			referenceLabel.setVisible(true);
			bankComboBox.setVisible(true);;

		
		}
		}catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"RefleoxologyController","selecePaymentMethod",ex.getClass().getName());


			}
		
	}
	
























}
