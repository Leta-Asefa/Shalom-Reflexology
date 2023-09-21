package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import ClassDesignForDB.Patient;
import ClassDesignForDB.Payments;
import ClassDesignForDB.Prescriptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

public class CurrentPatientController implements Initializable {
	@FXML
	private AnchorPane contentArea;
	@FXML
	private TextArea assesmentTextField,treatmentsTextField,focusingAreasTextField;
	@FXML
	private TableView<Patient> table1;
	@FXML
	private TableView<Prescriptions> table2;
	@FXML
	private TableColumn<Patient,String> phone,name,age,sex;
	
	
	
	@FXML
	private TableColumn<Prescriptions,String> first,second,third,fourth,fifth,sixth,
								seventh,eighth,ninth,tenth,eleventh,twelvth,thirteenth,fourteenth
								,fifteenth;
	
	private ShalomDAO dao;
	private ObservableList<Patient> patientList;
	private ObservableList<Prescriptions> prescriptionsList;
	private ObservableList<Payments> paymentList;

	private int phoneNumber;
	private Patient p;
	@FXML private TextArea historyTextField;
	@FXML private TableView<Payments> table;
	@FXML private TableColumn<Payments,String> cardCash;
	@FXML private TableColumn<Payments,String> cardMb;
	@FXML private TableColumn<Payments,String> paymentCash;
	@FXML private TableColumn<Payments,String> paymentMb;
	@FXML private TableColumn<Payments,String> date;
	@FXML private TableColumn<Payments,String> referenceNumber;
	@FXML private Label creditLabel;
	@FXML private Label totalLabel;
	@FXML private Label treatmentLabel;
	@FXML Button fulFillButton;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		
		dao=new ShalomDAO();
		patientList= FXCollections.observableArrayList();
		prescriptionsList= FXCollections.observableArrayList();
		phoneNumber=	dao.searchCurrentPatient();
		p= dao.getPatientByPhone(phoneNumber);
		
				
				
		phone.setCellValueFactory(new PropertyValueFactory<Patient,String>("phone"));
		name.setCellValueFactory(new PropertyValueFactory<Patient,String>("fullName"));
		age.setCellValueFactory(new PropertyValueFactory<Patient,String>("age"));
		sex.setCellValueFactory(new PropertyValueFactory<Patient,String>("sex"));
		
		first.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("firstp"));
		second.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("secondp"));
		third.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("third"));
		fourth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("fourth"));
		fifth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("fifth"));
		sixth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("sixth"));
		seventh.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("seventh"));
		eighth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("eighth"));
		ninth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("ninth"));
		tenth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("tenth"));
		eleventh.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("eleventh"));
		twelvth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("twelvth"));
		thirteenth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("thirteenth"));
		fourteenth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("fourteenth"));
		fifteenth.setCellValueFactory( new PropertyValueFactory<Prescriptions,String>("fifteenth"));
		
		cardCash.setCellValueFactory(new PropertyValueFactory<Payments,String> ("cardCash"));
		cardMb.setCellValueFactory(new PropertyValueFactory<Payments,String> ("cardMb"));
		paymentCash.setCellValueFactory(new PropertyValueFactory<Payments,String> ("paymentCash"));
		paymentMb.setCellValueFactory(new PropertyValueFactory<Payments,String> ("paymentMb"));
		date.setCellValueFactory(new PropertyValueFactory<Payments,String> ("paymentDate"));
		referenceNumber.setCellValueFactory(new PropertyValueFactory<Payments,String> ("referenceNumber"));

		
		
		show();
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"CurrentPatientController","initializeMethod",ex.getClass().getName());


			}
	}
	
	
	
	
	public void fulfillNewPatientsInfo(ActionEvent e) {
		
		try {
		
		if(phoneNumber!=0) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/FulfilNewPatientsInfo.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		
		
		else {
			
			new CallAlert (AlertType.WARNING,"Warning","","There is no patient so you can't use this button");
		}
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"CurrentPatientController","fullfillNewPatientInfosMethod",ex.getClass().getName());


			}
	}
	
	public  void show() {
	
		try {
		
		
			patientList = dao.searchPatientByPhone(phoneNumber);
	paymentList=dao.getCurrentPatientPaymentHistory(phoneNumber);
	prescriptionsList=dao.searchPrescriptionsByPhoneExact(phoneNumber);
	
	
	for(int i=0;i<prescriptionsList.size();i++) {
		String date=LocalDate.now().toString();
		
		if(prescriptionsList.get(i).getFirstp().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getFirstp()+"( 1st )");
		}
		
		if(prescriptionsList.get(i).getSecondp().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getSecondp()+"( 2nd )");
		}
		
		if(prescriptionsList.get(i).getThird().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getThird()+"( 3rd )");
		}
		
		if(prescriptionsList.get(i).getFourth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getFourth()+"( 4th )");
		}
		
		if(prescriptionsList.get(i).getFifth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getFifth()+"( 5th )");
		}
		
		if(prescriptionsList.get(i).getSixth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getSixth()+"( 6th )");
		}
		
		if(prescriptionsList.get(i).getSeventh().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getSeventh()+"( 7th )");
		}
		
		if(prescriptionsList.get(i).getEighth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getEighth()+"( 8th )");
		}
		
		if(prescriptionsList.get(i).getNinth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getNinth()+"( 9th )");
		}
		
		if(prescriptionsList.get(i).getTenth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getTenth()+"( 10th )");
		}
		
		if(prescriptionsList.get(i).getEleventh().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getEleventh()+"( 11th )");
		}
		
		if(prescriptionsList.get(i).getTwelvth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getTwelvth()+"( 12th )");
		}
		
		if(prescriptionsList.get(i).getThirteenth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getThirteenth()+"( 13th )");
		}
		
		if(prescriptionsList.get(i).getFourteenth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getFourteenth()+"( 14th )");
		}
		
		if(prescriptionsList.get(i).getFifteenth().contains(date) 	) {
			
			treatmentLabel.setText(prescriptionsList.get(i).getFifteenth()+"( 15th )");
		}
	
	
	}
	
	
	if(patientList.isEmpty()) {
		Alert alert= new Alert(AlertType.INFORMATION);
		alert.setHeaderText("SORRY !! NO PATIENTS TO ENTER");
		alert.show();
	}
	
	else {
		
	table1.setItems(patientList);
	table2.setItems(prescriptionsList);
	table.setItems(paymentList);
	
	//Patient patient=patientList.get(0);
	assesmentTextField.setText(patientList.get(0).getAssesment());
	treatmentsTextField.setText(patientList.get(0).getTreatment());
	focusingAreasTextField.setText(patientList.get(0).getFocusingArea());
	historyTextField.setText(patientList.get(0).getHistory());
	
	totalLabel.setText(String.valueOf( dao.getTotalPaymentDynamic(phoneNumber)));
	int credit=dao.getCredit(phoneNumber);
	
	creditLabel.setText(String.valueOf(credit ));
	
	
	
	}
	
	
	
		}catch (StringIndexOutOfBoundsException e) {
			
		}
	catch (Exception ex) {

		new CallAlert(AlertType.WARNING,"CurrentPatientController","showMethod",ex.getClass().getName());


		}
	}




	@FXML public void refresh() {
		phoneNumber=	dao.searchCurrentPatient();
		p= dao.getPatientByPhone(phoneNumber);
		if(p!=null)
			show();
		else
		{
			try {
				Parent root=FXMLLoader.load(getClass().getResource("/application/AdminFxml/CurrentPatient.fxml"));
				contentArea.getChildren().removeAll();
				contentArea.getChildren().setAll(root);
	    	
	    	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	catch (Exception ex) {

	new CallAlert(AlertType.WARNING,"AdminController","currentPatienMethod",ex.getClass().getName());


	}
		}
	}




	@FXML public void inactivate() {
		try {
			
			Alert alert= new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Box" );
			alert.setContentText("Are you sure to inactivate  "+p.getFullName()+"'s  account ?");
			
			
			if(alert.showAndWait().get()==ButtonType.OK) {
			
			
		
		if(p!=null) {
			ObservableList<Prescriptions> prescription= dao.searchPrescriptionsByPhoneExact( Integer.parseInt( table1.getSelectionModel().getSelectedItem().getPhone()));	
			
			String credit=String.valueOf(dao.getCredit(phoneNumber));
			String pre=dao.getTemporaryPrescription(Integer.parseInt (p.getPhone()));
		dao.deletePrescriptionAndAttendance(phoneNumber);
		dao.deleteTemporaryPrescription(phoneNumber);
		dao.addInactivePatient(Integer.parseInt (p.getPhone()), p.getFullName(),Integer.parseInt (p.getAge()), p.getSex().charAt(0), p.getAssesment(), p.getTreatment(), p.getFocusingArea(),p.getHistory());
		dao.insertIntoInactivePrescriptions(Integer.parseInt (p.getPhone()), prescription.get(0) );
		dao.insertIntoInactiveTemporaryPrescription(Integer.parseInt (p.getPhone()), pre, credit);
		
		
		
		
Alert alert1=new  Alert(AlertType.INFORMATION);
		alert1.setContentText("Inactivated Successfully");
		alert1.show();
		}
		
		
			} else {
				
			}
			
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"CurrentPatientController","inactivateMethod",ex.getClass().getName());


			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
