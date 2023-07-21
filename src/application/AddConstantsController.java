package application;

import java.net.URL;
import java.util.ResourceBundle;

import ClassDesignForDB.ComboBoxChoices;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class AddConstantsController implements Initializable {

	
	
	@FXML private TextField focusingAreaField;
	@FXML private ListView<String> focusingAreaListView;
	@FXML private TextField treatmentField;
	@FXML private ListView<String> treatmentListView;
	@FXML private ListView<String> jobListView;
	@FXML private TextField jobField;
	

	@FXML private ListView<String> historyListView;
	@FXML private TextField historyField;
	@FXML private TextField bankField;
	@FXML private  ListView<String> bankListView;
	
	
	@FXML private TableView<ComboBoxChoices> reflexologyTable;
	@FXML  private TableColumn<ComboBoxChoices,String> name;
	@FXML  private TableColumn<ComboBoxChoices,String> price;
	@FXML private  TableColumn<ComboBoxChoices,String> commission;
	@FXML private  TextField reflexologyTextField;

	@FXML TextField prescriptionTextField;
	@FXML TableColumn<ComboBoxChoices,String> commissionp;
	@FXML TableColumn<ComboBoxChoices,String> pricep;
	@FXML TableColumn<ComboBoxChoices,String> namep;
	@FXML TableView<ComboBoxChoices> prescriptionTable;

	private ShalomDAO dao;
	private ObservableList<String> focusingAreaList;
	private ObservableList<String> treatmentList;
	private ObservableList<String> jobList;
	private ObservableList<String> historyList;
	private ObservableList<String> bankList;
	private ObservableList<ComboBoxChoices> reflexologyList;
	private ObservableList<ComboBoxChoices> prescriptionList;
	private ObservableList<String> assessmentList;

	
	@FXML ListView<String> assessmentListView;
	@FXML TextField assessmentField;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		try {
		
		
		dao= new ShalomDAO();
		focusingAreaList=dao.getFocusingAreaItems();
		treatmentList=dao.getTreatmentItems();
		jobList=dao.getJobItems();
		historyList=dao.getHistoryItems();
		bankList=dao.getBanksName();
		reflexologyList=dao.getReflexologyItemsForTable();
		prescriptionList=dao.getPrescriptionItemsForTable();
		assessmentList=dao.getAssesmentItems();
		
		focusingAreaListView.setItems(focusingAreaList);
		treatmentListView.setItems(treatmentList);
		jobListView.setItems(jobList);
		historyListView.setItems(historyList);
		bankListView.setItems(bankList);
		reflexologyTable.setItems(reflexologyList);
		prescriptionTable.setItems(prescriptionList);
		assessmentListView.setItems(assessmentList);

		
		focusingAreaListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		treatmentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		jobListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		historyListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		bankListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		assessmentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		
		name.setCellValueFactory( new PropertyValueFactory< ComboBoxChoices,String>("fullName"));
		price.setCellValueFactory( new PropertyValueFactory< ComboBoxChoices,String>("price"));
		commission.setCellValueFactory( new PropertyValueFactory< ComboBoxChoices,String>("commission"));

		
		namep.setCellValueFactory( new PropertyValueFactory< ComboBoxChoices,String>("fullName"));
		pricep.setCellValueFactory( new PropertyValueFactory< ComboBoxChoices,String>("price"));
		commissionp.setCellValueFactory( new PropertyValueFactory< ComboBoxChoices,String>("commission"));

		
		
		
		
		
		
		
		
		
		columnProperty();
		inputValidation();
		
		
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","initializeMethod",e.getClass().getName());

			
		}
	}

	
	private void columnProperty() {
		
		try {
		
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		price.setCellFactory(TextFieldTableCell.forTableColumn());
		commission.setCellFactory(TextFieldTableCell.forTableColumn());
		
		name.setOnEditCommit( new EventHandler<CellEditEvent<ComboBoxChoices,String>> () {

			@Override
			public void handle(CellEditEvent<ComboBoxChoices, String> event) {
				
				
				if(!(event.getNewValue().equals("")  )) {
							
					event.getRowValue().setFullName(event.getNewValue());//this line is the short notation of
																		//the above commented lines
					
					dao.updateComboboxChoicesFullName(event.getOldValue(), 'R', event.getNewValue());
				
					}
				else {
					event.getRowValue().setFullName(event.getOldValue());
					refreshReflexology();
					
				}
				
				
				
				
				
				
				
				
			}
			
		});

		price.setOnEditCommit( new EventHandler<CellEditEvent<ComboBoxChoices,String>> () {

			@Override
			public void handle(CellEditEvent<ComboBoxChoices, String> event) {
				
				
				if(!(event.getNewValue().equals(""))) {
							
					event.getRowValue().setPrice(event.getNewValue());//this line is the short notation of
																		//the above commented lines
					
					dao.updateComboboxChoicesPrice(event.getRowValue().getFullName(), 'R',Integer.parseInt(( event.getNewValue())));
				
					}
				
				else {
					
					event.getRowValue().setPrice(event.getOldValue());
					refreshReflexology();

				}
				
				
			}
			
		});
		
		commission.setOnEditCommit( new EventHandler<CellEditEvent<ComboBoxChoices,String>> () {

			@Override
			public void handle(CellEditEvent<ComboBoxChoices, String> event) {
				
				
				if(!(event.getNewValue().equals(""))) {
							
					event.getRowValue().setPrice(event.getNewValue());//this line is the short notation of
																		//the above commented lines
					
					dao.updateComboboxChoicesCommission(event.getRowValue().getFullName(), 'R',Integer.parseInt(( event.getNewValue())));
				
					}
				
				else {
					
					event.getRowValue().setCommission(event.getOldValue());
					refreshReflexology();
				}
				
				
			}
			
		});
		
		
		
		
		namep.setCellFactory(TextFieldTableCell.forTableColumn());
		pricep.setCellFactory(TextFieldTableCell.forTableColumn());
		commissionp.setCellFactory(TextFieldTableCell.forTableColumn());
		
		namep.setOnEditCommit( new EventHandler<CellEditEvent<ComboBoxChoices,String>> () {

			@Override
			public void handle(CellEditEvent<ComboBoxChoices, String> event) {
				
				
				if(!(event.getNewValue().equals("")) && event.getNewValue().length()==4 ) {
							
					event.getRowValue().setFullName(event.getNewValue());//this line is the short notation of
																		//the above commented lines
					
					dao.updateComboboxChoicesFullName(event.getRowValue().getFullName(), 'P', event.getNewValue());
				
					}
				else {
					event.getRowValue().setFullName(event.getOldValue());
					refrehPrescription();
					
				}
				
				
			}
			
		});

		pricep.setOnEditCommit( new EventHandler<CellEditEvent<ComboBoxChoices,String>> () {

			@Override
			public void handle(CellEditEvent<ComboBoxChoices, String> event) {
				
				
				if(!(event.getNewValue().equals(""))) {
							
					event.getRowValue().setPrice(event.getNewValue());//this line is the short notation of
																		//the above commented lines
					
					dao.updateComboboxChoicesPrice(event.getRowValue().getFullName(), 'P',Integer.parseInt(( event.getNewValue())));
				
					}
				
				else {
					
					event.getRowValue().setPrice(event.getOldValue());
					refrehPrescription();

				}
				
				
			}
			
		});
		
		commissionp.setOnEditCommit( new EventHandler<CellEditEvent<ComboBoxChoices,String>> () {

			@Override
			public void handle(CellEditEvent<ComboBoxChoices, String> event) {
				
				
				if(!(event.getNewValue().equals(""))) {
							
					event.getRowValue().setPrice(event.getNewValue());//this line is the short notation of
																		//the above commented lines
					
					dao.updateComboboxChoicesCommission(event.getRowValue().getFullName(), 'P',Integer.parseInt(( event.getNewValue())));
				
					}
				
				else {
					
					event.getRowValue().setCommission(event.getOldValue());
					refrehPrescription();
				}
				
				
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		

		reflexologyTable.setEditable(true);
		prescriptionTable.setEditable(true);

}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","columnPropertyMethod",e.getClass().getName());

			
		}
	}


	private void inputValidation() {
		
		try {
		focusingAreaField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-z0-9\s]{0,45}")) {
				focusingAreaField.setText(newValue);
			}
			else {
				
				focusingAreaField.setText(oldValue);

			}
				
			}
			
		});

		treatmentField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-z0-9\s]{0,45}")) {
				treatmentField.setText(newValue);
			}
			else {
				
				treatmentField.setText(oldValue);

			}
				
			}
			
		});
		
		jobField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-z0-9\s]{0,45}")) {
				jobField.setText(newValue);
			}
			else {
				
				jobField.setText(oldValue);

			}
				
			}
			
		});
		

		historyField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-z0-9\s]{0,49}")) {
				historyField.setText(newValue);
			}
			else {
				
				historyField.setText(oldValue);

			}
				
			}
			
		});
		

		bankField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-z0-9\s]{0,49}")) {
				bankField.setText(newValue);
			}
			else {
				
				bankField.setText(oldValue);

			}
				
			}
			
		});
		

		reflexologyTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-z0-9\s]{0,40}")) {
				reflexologyTextField.setText(newValue);
			}
			else {
				
				reflexologyTextField.setText(oldValue);

			}
				
			}
			
		});

		

		prescriptionTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-z0-9\s]{0,4}")) {
				prescriptionTextField.setText(newValue);
			}
			else {
				
				prescriptionTextField.setText(oldValue);

			}
				
			}
			
		});
		

		assessmentField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (newValue.matches("[a-zA-z0-9\s]{0,40}")) {
				assessmentField.setText(newValue);
			}
			else {
				
				assessmentField.setText(oldValue);

			}
				
			}
			
		});
		
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","inputValidationMethod",e.getClass().getName());

			
		}
	}


	
	
	
	public void refreshFocusingArea() {  
try {
		
		focusingAreaList.clear();
		focusingAreaList=dao.getFocusingAreaItems();
		focusingAreaListView.setItems(focusingAreaList);
		
}catch (Exception e) {
	
	new CallAlert(AlertType.WARNING,"AddConstantsController","refereshFocusingAreaMethod",e.getClass().getName());

	
}
	}
	
	public void refreshTreatment() { 
		try {
		treatmentList.clear();
		treatmentList=dao.getTreatmentItems();
		treatmentListView.setItems(treatmentList);
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","refreshTreatmentMethod",e.getClass().getName());

			
		}
	}

	public void refreshJob() { 
		try {
	jobList.clear();
	jobList=dao.getJobItems();
	jobListView.setItems(jobList);
		
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","refreshJobMethod",e.getClass().getName());

			
		}
	}
	
	public void refreshReflexology() { 
		try {
		reflexologyList.clear();
		reflexologyList=dao.getReflexologyItemsForTable();
		reflexologyTable.setItems(reflexologyList);
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","refreshRefloxologyMethod",e.getClass().getName());

			
		}
		}
	
	private void refreshBank() {
		try {
		bankList.clear();
		bankList=dao.getBanksName();
		bankListView.setItems(bankList);
	}catch (Exception e) {
		
		new CallAlert(AlertType.WARNING,"AddConstantsController","refreshBankMethod",e.getClass().getName());

		
	}
	}

	private void refreshHistory() {
		try {
		historyList.clear();
		historyList=dao.getHistoryItems();
		historyListView.setItems(historyList);
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","refreshHistoryMethod",e.getClass().getName());

			
		}
	}

	private void refrehPrescription() {
		try {
		prescriptionList.clear();
		prescriptionList=dao.getPrescriptionItemsForTable();
		prescriptionTable.setItems(prescriptionList);
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","refreshPrescriptionMethod",e.getClass().getName());

			
		}
	}
	
	private void refrehAssessment() {
		try {
		assessmentList.clear();
		assessmentList=dao.getAssesmentItems();
		assessmentListView.setItems(assessmentList);
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","refreshAssessmentMethod",e.getClass().getName());

			
		}
	}

	
	
	
	
	
	@FXML public void addFocusingArea() {
		try {
		
		if(! ( focusingAreaField.getText().equals("") ) ){
			
			dao.addComboboxChoices(focusingAreaField.getText(), 'F', 0, 0);
		
			refreshFocusingArea();
			
			focusingAreaField.setText("");
			
		}
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","addFocusingAreaMethod",e.getClass().getName());

			
		}
		
	}

	@FXML public void deleteFocusingArea() {
		try {
		
		 ObservableList<String> deleted=focusingAreaListView.getSelectionModel().getSelectedItems();
		
		 if(! (deleted.isEmpty() ) ) {
			 for(String d:deleted) {
				 dao.deleteComboboxChoices(d, 'F');
			
			 	}
		 	}
		 
		 refreshFocusingArea();
			
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","deleteFocusingAreaMethod",e.getClass().getName());

			
		}	
	}

	
	
	@FXML public void addTreatment() {
		
		try {
		if(! ( treatmentField.getText().equals("") ) ){
			
			dao.addComboboxChoices(treatmentField.getText(), 'T', 0, 0);
		
			refreshTreatment();
			
			treatmentField.setText("");
			
		}
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","addTreatmentMethod",e.getClass().getName());

			
		}
	}

	@FXML public void deleteTreatment() {
		try {
		
		 ObservableList<String> deleted=treatmentListView.getSelectionModel().getSelectedItems();
			
		 if(! (deleted.isEmpty() ) ) {
			 for(String d: deleted) {
				 dao.deleteComboboxChoices(d, 'T');
				 
			 }
			 
		 	}
		 
		 refreshTreatment();
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","deleteTreatmentMethod",e.getClass().getName());

			
		}
	}


	@FXML public void addJob() {
		try {

		if(! ( jobField.getText().equals("") ) ){
			
			dao.addComboboxChoices(jobField.getText(), 'J', 0, 0);
		
			refreshJob();
			
			jobField.setText("");
			
		}
	}catch (Exception e) {
		
		new CallAlert(AlertType.WARNING,"AddConstantsController","addJobMethod",e.getClass().getName());

		
	}
		
		
	}

	@FXML public void deleteJob() {
		try {
		
		 ObservableList<String> deleted=jobListView.getSelectionModel().getSelectedItems();
			
		 if(! (deleted.isEmpty() ) ) {
			 for(String d: deleted) {
				 dao.deleteComboboxChoices(d, 'J');
				 
			 }
			 
		 	}
		 
		 refreshJob();
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","deleteJobMethod",e.getClass().getName());

			
		}
	}
	

	@FXML public void addHistory() {
		
		try {
		
if(! ( historyField.getText().equals("") ) ){
			
			dao.addComboboxChoices(historyField.getText(), 'H', 0, 0);
		
			refreshHistory();
			
			historyField.setText("");
			
		}
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","addHistoryMethod",e.getClass().getName());

			
		}
		
	}
	
	@FXML public void deleteHistory() {
		
		try {
		
		 ObservableList<String> deleted=historyListView.getSelectionModel().getSelectedItems();
			
		 if(! (deleted.isEmpty() ) ) {
			 for(String d: deleted) {
				 dao.deleteComboboxChoices(d, 'H');
				 
			 }
			 
		 	}
		 
		 refreshHistory();
		 
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","deleteHistoryMethod",e.getClass().getName());

			
		}
	}


	@FXML public void addBank() {
		try {
		
		if(! ( bankField.getText().equals("") ) ){
					
					dao.addComboboxChoices(bankField.getText(), 'B', 0, 0);
				
					refreshBank();
					
					bankField.setText("");
					
				}
				
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","addBankMethod",e.getClass().getName());

			
		}
			}
			
	@FXML public void deleteBank() {
		try {
				 ObservableList<String> deleted=bankListView.getSelectionModel().getSelectedItems();
					
				 if(! (deleted.isEmpty() ) ) {
					 for(String d: deleted) {
						 dao.deleteComboboxChoices(d, 'B');
						 
					 }
					 
				 	}
				 
				 refreshBank();
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","deleteBankMethod",e.getClass().getName());

			
		}
			}


	
	
	@FXML public void addReflexology() {
		try {
		
		if(! ( reflexologyTextField.getText().equals("") ) ){
					
					dao.addComboboxChoices(reflexologyTextField.getText(), 'R', 0, 0);
				
					refreshReflexology();
					
					reflexologyTextField.setText("");
					
				}
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","addReflexologyMethod",e.getClass().getName());

			
		}
			}
			
	@FXML public void deleteReflexology() {
		try {
				 ObservableList<ComboBoxChoices> deleted=reflexologyTable.getSelectionModel().getSelectedItems();
					
				 if(! (deleted.isEmpty() ) ) {
					 for(ComboBoxChoices d: deleted) {
						 dao.deleteComboboxChoices(d.getFullName(), 'R');
						 
					 }
					 
				 	}
				 
				 refreshReflexology();
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","deleteReflexologyMethod",e.getClass().getName());

			
		}
			}

	
	@FXML public void addPrescription() {
		try {
		
		if(! ( prescriptionTextField.getText().equals("") ) ){
					
					dao.addComboboxChoices(prescriptionTextField.getText(), 'P', 0, 0);
				
					refrehPrescription();
					
					prescriptionTextField.setText("");
					
				}
				
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","addPrescriptionMethod",e.getClass().getName());

			
		}
			}
			
	@FXML public void deletePrescription() {
		
		try {
				 ObservableList<ComboBoxChoices> deleted=prescriptionTable.getSelectionModel().getSelectedItems();
					
				 if(! (deleted.isEmpty() ) ) {
					 for(ComboBoxChoices d: deleted) {
						 dao.deleteComboboxChoices(d.getFullName(), 'P');
						 
					 }
					 
				 	}
				 
					refrehPrescription();
					
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","deletePrescriptionMethod",e.getClass().getName());

			
		}
			}


	@FXML public void deleteAssessment() {
		
		try {
		 ObservableList<String> deleted=assessmentListView.getSelectionModel().getSelectedItems();
			
		 if(! (deleted.isEmpty() ) ) {
			 for(String d: deleted) {
				 dao.deleteComboboxChoices(d, 'A');
				 
			 }
			 
		 	}
		 
			refrehAssessment();
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","deleteAssessmentMethod",e.getClass().getName());

			
		}
		
	}


	@FXML public void addAssessment() {
		try {
			
			
		
		if(! ( assessmentField.getText().equals("") ) ){
			
			dao.addComboboxChoices(assessmentField.getText(), 'A', 0, 0);
		
			refrehAssessment();
			
			assessmentField.setText("");
			
		}
}catch (Exception e) {
			
			new CallAlert(AlertType.WARNING,"AddConstantsController","addAssessmentMethod",e.getClass().getName());

			
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
