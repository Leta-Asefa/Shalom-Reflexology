package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;

import ClassDesignForDB.Patient;
import ClassDesignForDB.Prescriptions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;

public class PatientsInformationController implements Initializable{

	@FXML
	private TableView<Patient> table1;
	@FXML
	private TableView<Prescriptions> table2;
	@FXML
	private ListView<String> listView;
	
	@FXML
	private TableColumn<Prescriptions,String> first,second,third,fourth,fifth,sixth,seventh,eighth,ninth
						,tenth,eleventh,twelvth,thirteenth,fourteenth,fifteenth;
	
	@FXML
	private TableColumn<Patient,String> phone,name,age,sex;
	@FXML
	private TextArea assesment,treatment,focusingArea;
	@FXML
	private TextField searchTextField;
	@FXML
	private Button saveButton,getAssesment,getTreatment,getFocusingArea;
	
	
	
	private ShalomDAO dao;
	private ObservableList<Patient> patientList;
	private ObservableList<Prescriptions> prescriptionList;
	private ObservableList<String > assesmentList;
	private ObservableList<String > treatmentList;
	private ObservableList<String > focusingAreaList;
	private ObservableList<String> allList;
	ObservableList<String> historyList;
	@FXML TextArea history;
	@FXML Button getHistory;
	@FXML Label creditLabel;
	@FXML Label totalLabel;
	@FXML Button deleteButton;
	@FXML Label assesmentLabel;
	@FXML Label treatmentLabel;
	@FXML Label focusingLabel;
	@FXML Button eidtButton;
	@FXML Label historyLabel;
	@FXML CheckBox checkBox;
	@FXML Label creditLabelLabel;
	@FXML Label totalLabelLabel;
	@FXML Button searchButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
		
		dao= new ShalomDAO();
		
		
		getAllLists();
		columnAssociation();
		
		
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		allList=dao.getAllListItemsForFullfilDatas();
		listView.setItems(allList);
		
		table1.setItems(patientList);
		table2.setItems(prescriptionList);
		
		inputValidation();
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","initializeMethod",ex.getClass().getName());


			}
		
	}
	
	private void inputValidation() {
		try {
		
		searchTextField.textProperty().addListener(new ChangeListener<String>() {

			

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				if (newValue.matches("[0-9]{0,10}")) {
					searchTextField.setText(newValue);
				} else {
					searchTextField.setText(oldValue);
				}
				
			}
			
			
			
			
			
		});
	
		
		
		

    	assesment.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (assesment.getText().length()<=199) {
				assesment.setText(newValue);
			} else {
				assesment.setText(oldValue);
					}
			}
			
		});
    	
    	
    	treatment.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (treatment.getText().length()<=199) {
				treatment.setText(newValue);
			} else {
				treatment.setText(oldValue);
					}
			}
			
		});
    	
    	
    	
    	focusingArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (focusingArea.getText().length()<=199) {
				focusingArea.setText(newValue);
			} else {
				focusingArea.setText(oldValue);
					}
			}
			
		});
    	
    	
    	
    	
    	history.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (history.getText().length()<=199) {
				history.setText(newValue);
			} else {
				history.setText(oldValue);
					}
			}
			
		});
    	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","inputValidationMethod",ex.getClass().getName());


			}
	}

	private void columnAssociation() {
		
		try {
		
		phone.setCellValueFactory(new PropertyValueFactory<Patient,String>("phone"));
		name.setCellValueFactory(new PropertyValueFactory<Patient,String>("fullName"));
		age.setCellValueFactory(new PropertyValueFactory<Patient,String>("age"));
		sex.setCellValueFactory(new PropertyValueFactory<Patient,String>("sex"));
		
		
		first.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("firstp"));
		second.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("secondp"));
		third.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("third"));
		fourth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("fourth"));
		fifth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("fifth"));
		sixth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("sixth"));
		seventh.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("seventh"));
		eighth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("eighth"));
		ninth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("ninth"));
		tenth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("tenth"));
		eleventh.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("eleventh"));
		twelvth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("twelvth"));
		thirteenth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("thirteenth"));
		fourteenth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("fourteenth"));
		fifteenth.setCellValueFactory(new PropertyValueFactory<Prescriptions,String>("fifteenth"));
		
		table1.setOnMouseClicked(new EventHandler<MouseEvent >  (){

			@Override
			public void handle(MouseEvent event) {
				Patient p=table1.getSelectionModel().getSelectedItem();
				
			if(checkBox.isSelected()) {
				
				if(p!=null) {
					int phoneNumber=Integer.parseInt(p.getPhone());
					totalLabel.setText(String.valueOf( dao.getInactiveTotalPaymentDynamic(phoneNumber)));
					
					}
				
			
			}else {
				
				if(p!=null) {
					int phoneNumber=Integer.parseInt(p.getPhone());
					totalLabel.setText(String.valueOf( dao.getTotalPaymentDynamic(phoneNumber)));
					int credit=dao.getCredit(phoneNumber);
					creditLabel.setText(String.valueOf(credit ));
					
					prescriptionList=dao.searchPrescriptionsByPhoneExact(phoneNumber);
					table2.setItems(prescriptionList);
					
					}
				
				
			}
				
				
				if (p.getAssesment()!=null) {
					
					assesment.setText(p.getAssesment());
				
				}
				
				if (p.getTreatment()!=null) {
					
					treatment.setText(p.getTreatment());
					
				}

				if (p.getFocusingArea()!=null) {
	
					focusingArea.setText(p.getFocusingArea());
	
				}
				if (p.getHistory()!=null) {
					
					history.setText(p.getHistory());
	
				}
				
			
			
			
			}
			
			
		});
		
		
		
		phone.setCellFactory(TextFieldTableCell.forTableColumn());
		phone.setOnEditCommit(new EventHandler<CellEditEvent<Patient,String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
			if( (!event.getNewValue().equals("")) && (event.getNewValue().length()==9 || event.getNewValue().length()==10) ) {
			
				dao.updatePatientInts("phone",Integer.parseInt(event.getNewValue()), Integer.parseInt(event.getOldValue()));
				event.getRowValue().setPhone(event.getNewValue());
			}
			
		}
			});
		
		
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(new EventHandler<CellEditEvent<Patient,String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				if( !event.getNewValue().equals("")){
					dao.updatePatientStrings("fullName", event.getNewValue(), Integer.parseInt(event.getRowValue().getPhone()));
					event.getRowValue().setFullName(event.getNewValue());
				}
			}
			
		});
		
		age.setCellFactory(TextFieldTableCell.forTableColumn());
		age.setOnEditCommit(new EventHandler<CellEditEvent<Patient,String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
			
				if( event.getNewValue().length()==1 || event.getNewValue().length()==2 ){	
			
					dao.updatePatientInts("age", Integer.parseInt(event.getNewValue()) , Integer.parseInt(event.getRowValue().getPhone()));
					}
			}
			
		});
		
		
		sex.setCellFactory(TextFieldTableCell.forTableColumn());
		sex.setOnEditCommit(new EventHandler<CellEditEvent<Patient,String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
			String value= event.getNewValue();
				if(value.equals("m") || value.equals("M") || value.equals("f") || value.equals("F") ){
					
					dao.updatePatientStrings("sex", event.getNewValue(), Integer.parseInt(event.getRowValue().getPhone()));
					event.getRowValue().setSex(event.getNewValue());
				}
			}
			
		});
		
		first.setCellFactory(TextFieldTableCell.forTableColumn());
		first.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setFirstp(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
	dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "firstp", event.getRowValue().getFirstp());
	dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),getUpdatedTemporaryPrescription(event));
			

				
			}
			
			}});
		
		
		
		second.setCellFactory(TextFieldTableCell.forTableColumn());
		second.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setSecondp(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "secondp", event.getRowValue().getSecondp());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});
		
		
		third.setCellFactory(TextFieldTableCell.forTableColumn());
		third.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setThird(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "third", event.getRowValue().getThird());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});
		
		
		fourth.setCellFactory(TextFieldTableCell.forTableColumn());
		fourth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setFourth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "fourth", event.getRowValue().getFourth());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});

		
		fifth.setCellFactory(TextFieldTableCell.forTableColumn());
		fifth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setFifth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "fifth", event.getRowValue().getFifth());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});

		
		sixth.setCellFactory(TextFieldTableCell.forTableColumn());
		sixth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setSixth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "sixth", event.getRowValue().getSixth());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});

		
		seventh.setCellFactory(TextFieldTableCell.forTableColumn());
		seventh.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setSeventh(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "seventh", event.getRowValue().getSeventh());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});

		
		eighth.setCellFactory(TextFieldTableCell.forTableColumn());
		eighth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setEighth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "eighth", event.getRowValue().getEighth());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});

		
		
		ninth.setCellFactory(TextFieldTableCell.forTableColumn());
		ninth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setNinth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "ninth", event.getRowValue().getNinth());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});

		
		
		
		
		tenth.setCellFactory(TextFieldTableCell.forTableColumn());
		tenth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setTenth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "tenth", event.getRowValue().getTenth());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});

		
		
		
		eleventh.setCellFactory(TextFieldTableCell.forTableColumn());
		eleventh.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setEleventh(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "eleventh", event.getRowValue().getEleventh());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});

		
		
		
		twelvth.setCellFactory(TextFieldTableCell.forTableColumn());
		twelvth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setTwelvth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "twelvth", event.getRowValue().getTwelvth());
			
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
				
			}
			
			}});

		
		
		
		thirteenth.setCellFactory(TextFieldTableCell.forTableColumn());
		thirteenth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setThirteenth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "thirteenth", event.getRowValue().getThirteenth());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
			
				
			}
			
			}});

		
		
		fourteenth.setCellFactory(TextFieldTableCell.forTableColumn());
		fourteenth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setFourteenth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "fourteenth", event.getRowValue().getFourteenth());

					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
				
			}
			
			}});

		

		fifteenth.setCellFactory(TextFieldTableCell.forTableColumn());
		fifteenth.setOnEditCommit(new EventHandler<CellEditEvent<Prescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<Prescriptions, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setFifteenth(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.updatePrescription( Integer.parseInt((event.getRowValue().getPhonefk())), "fifteenth", event.getRowValue().getFifteenth());
					dao.updateTemporaryPrescription(Integer.parseInt((event.getRowValue().getPhonefk())),
							getUpdatedTemporaryPrescription(event));
				
			}
			
			}});

		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","columnAssociationMethod",ex.getClass().getName());


			}
		
		
		
	}



	private void getAllLists() {
		
		
		try {
		
		patientList=dao.getAllPatients();
		prescriptionList=dao.getAllPrescriptions();
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","getAllListsMethod",ex.getClass().getName());


			}
	}

	private void refresh() {
		try {
		
		patientList.clear();
		prescriptionList.clear();
		

		patientList=dao.getAllPatients();
		prescriptionList=dao.getAllPrescriptions();
		
		table1.setItems(patientList);
		table2.setItems(prescriptionList);
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","refreshMethod",ex.getClass().getName());


			}
	}
	
	public void search(ActionEvent e) {
		
try {
		
		if(!(searchTextField.getText().equals("")))
		{
			
			patientList.clear();
			prescriptionList.clear();
			
			patientList=dao.searchPatientsByPhone(Integer.parseInt(searchTextField.getText()));
			prescriptionList=dao.searchPrescriptionsByPhoneExact(Integer.parseInt(searchTextField.getText()));
			
			
			table1.setItems(patientList);
			table2.setItems(prescriptionList);
			
			if(searchTextField.getText().length()==9||searchTextField.getText().length()==10) {
				
				Patient p=dao.getPatientByPhone(Integer.parseInt(searchTextField.getText()));
						if(p!=null) {
						assesment.setText(p.getAssesment());
						treatment.setText(p.getTreatment());
						focusingArea.setText(p.getFocusingArea());
						history.setText(p.getHistory());
						
						int phoneNumber=Integer.parseInt(searchTextField.getText());
						totalLabel.setText(String.valueOf( dao.getTotalPaymentDynamic(phoneNumber)));
						int credit=dao.getCredit(phoneNumber);
						creditLabel.setText(String.valueOf(credit ));
						}else {
							assesment.setText("");
							treatment.setText("");
							focusingArea.setText("");
							history.setText("");
							new CallAlert(AlertType.INFORMATION,"","No Patient with this phone number ","");
						}
		
			}
			else {
			
			assesment.setText("");
			treatment.setText("");
			focusingArea.setText("");
			history.setText("");
			}

		}
		
		else {
		
			patientList=dao.getAllPatients();
			prescriptionList=dao.getAllPrescriptions();
			table1.setItems(patientList);
			table2.setItems(prescriptionList);
			
			
			
		}
		
		
}
catch (Exception ex) {

	new CallAlert(AlertType.WARNING,"PatientInformationController","searchMethod",ex.getClass().getName());


	}
	}
	
	public void edit(ActionEvent e) {
		
		try {
		
		assesment.setDisable(false);
		treatment.setDisable(false);
		focusingArea.setDisable(false);
		history.setDisable(false);

		getAssesment.setDisable(false);
		getTreatment.setDisable(false);
		getFocusingArea.setDisable(false);
		getHistory.setDisable(false);

		listView.setDisable(false);
		saveButton.setDisable(false);
		
		deleteButton.setVisible(true);
		
		table2.setEditable(true);
		table1.setEditable(true);
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","editMethod",ex.getClass().getName());


			}
		
	}
	
	public void save(ActionEvent e) {
		try {
		
		
		if( !(history.getText().equals("")) && !(assesment.getText().equals("")) && !(treatment.getText().equals("")) && !(focusingArea.getText().equals("")) && !(table1.getSelectionModel().getSelectedItem().getPhone().equals("")) ) {
		
		dao.updatePatient(Integer.parseInt( (table1.getSelectionModel().getSelectedItem().getPhone())),
				table1.getSelectionModel().getSelectedItem().getFullName(),
				Integer.parseInt ((table1.getSelectionModel().getSelectedItem().getAge())),
				table1.getSelectionModel().getSelectedItem().getSex(),
				assesment.getText(), treatment.getText(), focusingArea.getText(),history.getText());
		
		
		
		assesment.setDisable(true);
		history.setDisable(true);
		treatment.setDisable(true);
		focusingArea.setDisable(true);
		getAssesment.setDisable(true);
		getTreatment.setDisable(true);
		getFocusingArea.setDisable(true);
		listView.setDisable(true);
		saveButton.setDisable(true);
		
		Alert alert= new Alert(AlertType.INFORMATION);
		alert.setContentText("UPDATED SUCCESSFULLY ! \n THANK YOU");
		alert.show();
		
		refresh();
	}
		else {
			
			
			new CallAlert(AlertType.WARNING,"WARNING","Please check that : ","1.All the 4 boxes are not empty\n2.You have selected a row from the table");
		}
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","saveMethod",ex.getClass().getName());


			}
	}
	

	
	public void addAssesment() {
		try {
		
		assesment.setText(listView.getSelectionModel().getSelectedItems().toString());
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","addAssessmentMethod",ex.getClass().getName());


			}
		}
	
	public void addTreatment() {
		try {
		treatment.setText(listView.getSelectionModel().getSelectedItems().toString());
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","addTreatmentMethod",ex.getClass().getName());


			}
	}
	
	public void addFocusingArea() {
		try {
		focusingArea.setText(listView.getSelectionModel().getSelectedItems().toString());
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","addFoucsingMethod",ex.getClass().getName());


			}
	}

	@FXML public void addHistory() {
		try {
		history.setText(listView.getSelectionModel().getSelectedItems().toString());
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"PatientInformationController","addHistoryMethod",ex.getClass().getName());


			}
	}

	protected String getUpdatedTemporaryPrescription(CellEditEvent<Prescriptions, String> event) {

		Prescriptions person=event.getRowValue();
		String temporaryPrescription = "";
		
		if (  !person.getFirstp().equals("null")  && person.getFirstp().length()!=0 ) {
			temporaryPrescription += person.getFirstp().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}
		
		if (!person.getSecondp().equals("null") && person.getSecondp().length()!=0) {
			temporaryPrescription += person.getSecondp().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}
		
		
		if (!person.getThird().equals("null") && person.getThird().length()!=0) {
			temporaryPrescription += person.getThird().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}
		
		if (!person.getFourth().equals("null") && person.getFourth().length()!=0) {
			temporaryPrescription += person.getFourth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getFifth().equals("null") && person.getFifth().length()!=0) {
			temporaryPrescription += person.getFifth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getSixth().equals("null") && person.getSixth().length()!=0) {
			temporaryPrescription += person.getSixth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getSeventh().equals("null") && person.getSeventh().length()!=0) {
			temporaryPrescription += person.getSeventh().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getEighth().equals("null") && person.getEighth().length()!=0) {
			temporaryPrescription += person.getEighth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getNinth().equals("null") && person.getNinth().length()!=0) {
			temporaryPrescription += person.getNinth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getTenth().equals("null") && person.getTenth().length()!=0) {
			temporaryPrescription += person.getTenth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}
		
		if (!person.getEleventh().equals("null") && person.getEleventh().length()!=0) {
			temporaryPrescription += person.getEleventh().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getTwelvth().equals("null") && person.getTwelvth().length()!=0) {
			temporaryPrescription += person.getTwelvth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getThirteenth().equals("null") && person.getThirteenth().length()!=0) {
			temporaryPrescription += person.getThirteenth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getFourteenth().equals("null") && person.getThirteenth().length()!=0) {
			temporaryPrescription += person.getFourteenth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}

		if (!person.getFifteenth().equals("null") && person.getFifteenth().length()!=0) {
			temporaryPrescription += person.getFifteenth().substring(0, 4);
		} else {
			temporaryPrescription += "null";
		}
		
		if(person.getFirstp().length()>5) {
		
			for (int i = 0; i < 15; i++) {
				temporaryPrescription += "null";
			}
		}
		else {
			String leftTemporaryPrescription=dao.getTemporaryPrescription(Integer.parseInt(person.getPhonefk()));
			temporaryPrescription+=leftTemporaryPrescription.substring(60);
			
				
		}
		
		return temporaryPrescription;

	}
	
	
	@FXML
	public void delete() {
		Patient p=table1.getSelectionModel().getSelectedItem();
		String	temporaryPrescription=dao.getTemporaryPrescription(Integer.parseInt(p.getPhone()));
			
			if(p!=null) {
		
			Alert alert= new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Box" );
			alert.setContentText("Are you sure to inactivate  "+p.getFullName()+"'s  account ?");
			
			
			if(alert.showAndWait().get()==ButtonType.OK) {
			
				int phone=Integer.parseInt( (p.getPhone()));
				dao.deletePrescriptionAndAttendance(phone);
				dao.deleteTemporaryPrescription(phone);
				dao.addInactivePatient(phone, p.getFullName(), Integer.parseInt(p.getAge()), p.getSex().charAt(0),
						p.getAssesment(), p.getTreatment(), p.getFocusingArea(), p.getHistory());
			try {
				dao.insertIntoInactiveTemporaryPrescription(phone, temporaryPrescription, " ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Alert alert1=new  Alert(AlertType.INFORMATION);
			alert1.setContentText("Inactivated Successfully");
			alert1.show();
			refresh();
			creditLabel.setText("");
			totalLabel.setText("");
			history.setText("");
			assesment.setText("");
			treatment.setText("");
			focusingArea.setText("");
			}
	}
		
	}

	@FXML public void getInactivePatients() {
		
		if(checkBox.isSelected()) {
			patientList=dao.getAllInactivePatients();
			table1.setItems(patientList);
			
			table2.setVisible(false);
			creditLabel.setVisible(false);
			creditLabelLabel.setVisible(false);
			
			creditLabel.setText("");
			totalLabel.setText("");
			
			getHistory.setVisible(false);
			getTreatment.setVisible(false);
			getFocusingArea.setVisible(false);
			getAssesment.setVisible(false);
			eidtButton.setVisible(false);
			listView.setVisible(false);
			searchButton.setVisible(false);
			searchTextField.setVisible(false);
		}else {
			patientList=dao.getAllPatients();
			table1.setItems(patientList);
			
			table2.setVisible(true);
			creditLabel.setVisible(true);
			creditLabelLabel.setVisible(true);
			
			creditLabel.setText("");
			totalLabel.setText("");
			
			getHistory.setVisible(true);
			getTreatment.setVisible(true);
			getFocusingArea.setVisible(true);
			getAssesment.setVisible(true);
			eidtButton.setVisible(true);
			listView.setVisible(true);
			searchButton.setVisible(true);
			searchTextField.setVisible(true);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

}