package application;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import ClassDesignForDB.PayRoll;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ComboBox;

public class PaySalaryController implements Initializable {

	@FXML
	private DatePicker startingDatePicker,endingDatePicker;
	
	@FXML
	private Button searchButton;
	
	private ShalomDAO dao;
	
	
	@FXML TableView<PayRoll> table;
	@FXML TableColumn<PayRoll,String> name;
	@FXML TableColumn<PayRoll,String>  rx45;
	@FXML TableColumn<PayRoll,String>  rx60;
	@FXML TableColumn<PayRoll,String>  m130;
	@FXML TableColumn<PayRoll,String>  m230;
	@FXML TableColumn<PayRoll,String>  m330;
	@FXML TableColumn<PayRoll,String>  m145;
	@FXML TableColumn<PayRoll,String>  m245;
	@FXML TableColumn<PayRoll,String>  m345;
	@FXML TableColumn<PayRoll,String>  m160;
	@FXML TableColumn<PayRoll,String>  m260;
	@FXML TableColumn<PayRoll,String>  m360;
	@FXML TableColumn <PayRoll,String> customers;
	@FXML TableColumn <PayRoll,String> commission;
	@FXML TableColumn <PayRoll,String> fixedSalary;
	@FXML TableColumn<PayRoll,String>  totalSalary;

	@FXML TextField textField;

	@FXML ComboBox<String> comboBox;
	ObservableList<String> employeesList;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		try {
		
		dao= new ShalomDAO();
		employeesList=dao.getAllEmployeeName();
		startingDatePicker.setEditable(false);
		endingDatePicker.setEditable(false);

		
		comboBox.setItems(employeesList);
		
		name.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("fullName"));
		rx45.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("rx45"));
		rx60.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("rx60"));
		m130.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("m130"));
		m230.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("m230"));
		m330.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("m330"));
		m145.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("m145"));
		m245.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("m245"));
		m345.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("m345"));
		m160.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("m160"));
		m260.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("m260"));
		m360.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("m360"));
		customers.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("customers"));
		commission.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("commission"));
		fixedSalary.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("fixedSalary"));
		totalSalary.setCellValueFactory(new PropertyValueFactory<PayRoll,String>("totalSalary"));

		
		
		
		
		
		table.setOnMouseClicked(new EventHandler<MouseEvent >  (){

			@Override
			public void handle(MouseEvent event) {
				
				if (table.getSelectionModel().getSelectedItem()!=null) {
				
				if (table.getSelectionModel().getSelectedItem().getTotalSalary()!=null) {
					
					textField.setText(table.getSelectionModel().getSelectedItem().getTotalSalary());
					comboBox.setValue(table.getSelectionModel().getSelectedItem().getFullName());				
				}
				
				}
				
			}
			
			
			
		});
		
		
		textField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[0-9]{0,9}")) {
				textField.setText(newValue);
			} else {
				textField.setText(oldValue);
					}
			}
			
		});
		
		
		
		
		
		
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PaySalaryController","initializeMethod",ex.getClass().getName());


			}
		
	}
	
	public void search(ActionEvent e) {
		
		String date1,date2;
		
		try {
			
			
			
			
		if(startingDatePicker.getValue()!=null && endingDatePicker.getValue()!=null ) {
			
			date1=(startingDatePicker.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
			date2=(endingDatePicker.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
			
			ObservableList<PayRoll> list=FXCollections.observableArrayList();
			list=dao.getPayRollForTable( date1,  date2);
			table.setItems(list);
			
		
			
						}
		
		
		
		
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PaySalaryController","searchMethod",ex.getClass().getName());


			}
		
		}
	
	
	
	@FXML public void pay() {
		
		try {
		
		if( (!textField.getText().equals("")) && startingDatePicker.getValue()!=null && endingDatePicker.getValue()!=null && comboBox.getValue()!=null ) {
		
	String	date1=(startingDatePicker.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
	String  date2=(endingDatePicker.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		
	
	dao.insertIntoSalaryHistory(comboBox.getValue(),Integer.parseInt( textField.getText()), date1, date2);
		
	
		
	new CallAlert (AlertType.INFORMATION,"","","Paid Successfully !");
	
	
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	catch (Exception ex) {
		
		new CallAlert(AlertType.WARNING,"PaySalaryController","initializeMethod",ex.getClass().getName());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}}
