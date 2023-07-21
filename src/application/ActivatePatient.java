package application;

import java.net.URL;
import java.util.ResourceBundle;

import ClassDesignForDB.Patient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ActivatePatient implements Initializable {


    @FXML
    private Button activateButton;

    @FXML
    private TableColumn<Patient, String> name, phone;

    @FXML
    private TableView<Patient> table;

    @FXML
    private TextField textField;
    @FXML private ImageView image;
    
    
   private ObservableList<Patient> list;
   private ShalomDAO dao;

   
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	try {
    	
    	dao= new ShalomDAO();
    	list=dao.getAllInactivePatients();
    	
    	phone.setCellValueFactory(new PropertyValueFactory<Patient,String>("phone"));
    	name.setCellValueFactory(new PropertyValueFactory<Patient,String>("fullName"));
    	
    	table.setItems(list);
    	inputValidation();
    	
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"ActivatePatient","initializeMethod",e.getClass().getName());

			
		}
		
    	
    }

    private void inputValidation() {
    	
    	try {
    	
    	textField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[0-9]{0,10}")) {
				textField.setText(newValue);
			} else {
				textField.setText(oldValue);
					}
			}
			
		});
    	
    	
    	
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"ActivatePatient","inputValidationMethod",e.getClass().getName());

			
		}
		
		
	}

	public void columnProperties() {
		try {
    	phone.setCellValueFactory(new PropertyValueFactory<Patient,String>("phone"));
    	name.setCellValueFactory(new PropertyValueFactory<Patient,String>("fullName"));
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"ActivatePatient","columnPropertiesMethod",e.getClass().getName());

			
		}
		
    	
    }
    
	@FXML public void activate() {
		
		try {
		Patient p=table.getSelectionModel().getSelectedItem();
		
		if(p!=null) {
		dao.activatePatient(Integer.parseInt( p.getPhone()));
		list.clear();
		list=dao.getAllInactivePatients();
		
		dao.takeFirstAttendance(Integer.parseInt( p.getPhone()), 'E', "firsta");
		
		table.setItems(list);
		image.setVisible(true);
		activateButton.setDisable(true);
		}
		else {
			
			new CallAlert(AlertType.WARNING,"","","Please, select a row to be activated");

		}
		
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"ActivatePatient","activateMethod",e.getClass().getName());

			
		}
		
	}

	@FXML public void search() {
		
try {
		if(!(textField.getText().equals(""))){
			
			list.clear();
			list=dao.searchInactivePatientsByPhone(Integer.parseInt(textField.getText()));
			table.setItems(list);
			
		}
		else {
			list.clear();
			list=dao.getAllInactivePatients();
			table.setItems(list);
			
			
		}
		
}
	catch(NumberFormatException ex) {
		new CallAlert(AlertType.WARNING,"","","Please, insert the phone number in this format ( 0987654321 ) ");

	}

	catch (Exception e) {
		
		new CallAlert(AlertType.WARNING,"ActivatePatient","searchMethod",e.getClass().getName());

		
	}
	}


}
