package application;

import java.net.URL;
import java.util.ResourceBundle;

import ClassDesignForDB.FullAttendance;
import ClassDesignForDB.FullPrescriptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class EnterPatientsController implements Initializable {

	
	@FXML
	private TableView<FullPrescriptions> prescriptionTable;
	@FXML
	private TableView<FullAttendance> attendanceTable;
	@FXML
	private TableColumn<FullPrescriptions,String> phonep,namep,first,second,third,fourth,fifth,sixth,seventh,eighth,ninth
						,tenth,eleventh,twelvth,thirteenth,fourteenth,fifteenth;
	@FXML
	private TableColumn<FullAttendance,String> phonea,namea,first1,second1,third1,fourth1,fifth1,sixth1
						,seventh1,eighth1,ninth1
						,tenth1,eleventh1,twelvth1,thirteenth1,fourteenth1,fifteenth1;
	
	private ShalomDAO dao;
	private ObservableList<FullPrescriptions> prescriptionList;
	private ObservableList<FullAttendance> attendanceList;
	@FXML Button shiftButton;
	@FXML Spinner<Integer> spinner;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		
		dao= new ShalomDAO();
		columnAssociation();
		columnsProperty();
		prescriptionList= FXCollections.observableArrayList();
		attendanceList= FXCollections.observableArrayList();
		
		prescriptionList=dao.getAllFullPrescriptions();
		attendanceList=dao.getAllFullAttendance();
		
		prescriptionTable.setItems(prescriptionList);
		attendanceTable.setItems(attendanceList);
		
		SpinnerValueFactory<Integer> factory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 14);
		spinner.setValueFactory(factory);
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EnterPatientController","initializeMethod",ex.getClass().getName());


			}
	}
	
	
	private void columnAssociation() {
		
		try {
		
		
		phonep.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("phonefk"));
		namep.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("fullName"));
		first.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("firstp"));
		second.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("secondp"));
		third.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("third"));
		fourth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("fourth"));
		fifth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("fifth"));
		sixth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("sixth"));
		seventh.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("seventh"));
		eighth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("eighth"));
		ninth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("ninth"));
		tenth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("tenth"));
		eleventh.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("eleventh"));
		twelvth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("twelvth"));
		thirteenth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("thirteenth"));
		fourteenth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("fourteenth"));
		fifteenth.setCellValueFactory(new PropertyValueFactory<FullPrescriptions,String>("fifteenth"));
		
		
		phonea.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("phonefk"));
		namea.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("fullName"));
		first1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("firsta"));
		second1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("seconda"));
		third1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("third"));
		fourth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("fourth"));
		fifth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("fifth"));
		sixth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("sixth"));
		seventh1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("seventh"));
		eighth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("eighth"));
		ninth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("ninth"));
		tenth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("tenth"));
		eleventh1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("eleventh"));
		twelvth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("twelvth"));
		thirteenth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("thirteenth"));
		fourteenth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("fourteenth"));
		fifteenth1.setCellValueFactory(new PropertyValueFactory<FullAttendance,String>("fifteenth"));
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EnterPatientController","columnAssociationMethod",ex.getClass().getName());


			}
		
}
		
	private void columnsProperty() {
		
		
try {
		
		first1.setCellFactory(TextFieldTableCell.forTableColumn());
		first1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
				
				//Attendance a=event.getRowValue();
				//a.setFirsta(event.getNewValue());
						
				event.getRowValue().setFirsta(event.getNewValue());//this line is the short notation of
																	//the above commented lines
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "firsta");
			
				}
			
			
			}
			
		});
		
		second1.setCellFactory(TextFieldTableCell.forTableColumn());
		second1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				
				if(!(event.getNewValue().equals(""))) {
					
				event.getRowValue().setSeconda(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "seconda");
			
				}
				
				
				}
			
		});
		
		
		third1.setCellFactory(TextFieldTableCell.forTableColumn());
		third1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
						
				if(!(event.getNewValue().equals(""))) {
					
				event.getRowValue().setThird(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "third");
				}
				}
			
		});
		
		fourth1.setCellFactory(TextFieldTableCell.forTableColumn());
		fourth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
						
				if(!(event.getNewValue().equals(""))) {
					
				
				event.getRowValue().setFourth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "fourth");
			
				}
				}
			
		});
		
		
		fifth1.setCellFactory(TextFieldTableCell.forTableColumn());
		fifth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
						
				if(!(event.getNewValue().equals(""))) {
					
				
				event.getRowValue().setFifth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "fifth");
				}
				}
			
		});
		
		
		sixth1.setCellFactory(TextFieldTableCell.forTableColumn());
		sixth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
							
				event.getRowValue().setSixth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "sixth");
				}}
			
		});
		
		seventh1.setCellFactory(TextFieldTableCell.forTableColumn());
		seventh1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
							
				event.getRowValue().setSeventh(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "seventh");
				}}
			
		});
		
		
		eighth1.setCellFactory(TextFieldTableCell.forTableColumn());
		eighth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
							
				event.getRowValue().setEighth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "eighth");
				}	}
			
		});
		
		
		ninth1.setCellFactory(TextFieldTableCell.forTableColumn());
		ninth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
							
				event.getRowValue().setNinth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "ninth");
				}}
			
		});
		
		
		tenth1.setCellFactory(TextFieldTableCell.forTableColumn());
		tenth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
								
				event.getRowValue().setTenth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "tenth");
				}}
			
		});
		
		
		eleventh1.setCellFactory(TextFieldTableCell.forTableColumn());
		eleventh1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
							
				event.getRowValue().setEleventh(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "eleventh");
				}	}
			
		});
		
		twelvth1.setCellFactory(TextFieldTableCell.forTableColumn());
		twelvth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
						
				event.getRowValue().setTwelvth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "twelvth");
				}	}
			
		});
		
		
		thirteenth1.setCellFactory(TextFieldTableCell.forTableColumn());
		thirteenth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
							
				event.getRowValue().setThirteenth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "thirteenth");
				}	}
			
		});
		
		fourteenth1.setCellFactory(TextFieldTableCell.forTableColumn());
		fourteenth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
								
				event.getRowValue().setFourteenth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "fourteenth");
				}}
			
		});
		
		fifteenth1.setCellFactory(TextFieldTableCell.forTableColumn());
		fifteenth1.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				if(!(event.getNewValue().equals(""))) {
							
				event.getRowValue().setFifteenth(event.getNewValue());
				
				dao.takeNormalAttendance(Integer.parseInt(event.getRowValue().getPhonefk()), 
						event.getNewValue().charAt(0), "fifteenth");
				}}
			
		});
		
		
		
		phonea.setCellFactory(TextFieldTableCell.forTableColumn());
		phonea.setOnEditCommit(new EventHandler<CellEditEvent<FullAttendance,String>> (){

			@Override
			public void handle(CellEditEvent<FullAttendance, String> event) {
				//for the reception , the phone number will be copy able
				}
			
		});
		
		
		
		attendanceTable.setEditable(true);
		
		prescriptionTable.setEditable(true);
		

		first.setCellFactory(TextFieldTableCell.forTableColumn());
		first.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setFirstp(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "firstp", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		second.setCellFactory(TextFieldTableCell.forTableColumn());
		second.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setSecondp(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "secondp", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});

		
		third.setCellFactory(TextFieldTableCell.forTableColumn());
		third.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setThird(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "third", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		fourth.setCellFactory(TextFieldTableCell.forTableColumn());
		fourth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setFourth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "fourth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});

		fifth.setCellFactory(TextFieldTableCell.forTableColumn());
		fifth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setFifth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "fifth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		seventh.setCellFactory(TextFieldTableCell.forTableColumn());
		seventh.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setSeventh(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "seventh", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		
		
		
		
		sixth.setCellFactory(TextFieldTableCell.forTableColumn());
		sixth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setSixth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "sixth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		eighth.setCellFactory(TextFieldTableCell.forTableColumn());
		eighth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setEighth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "eighth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		
		ninth.setCellFactory(TextFieldTableCell.forTableColumn());
		ninth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setNinth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "ninth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		
		tenth.setCellFactory(TextFieldTableCell.forTableColumn());
		tenth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setTenth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "tenth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		eleventh.setCellFactory(TextFieldTableCell.forTableColumn());
		eleventh.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setEleventh(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "eleventh", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		twelvth.setCellFactory(TextFieldTableCell.forTableColumn());
		twelvth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setTwelvth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "twelvth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		thirteenth.setCellFactory(TextFieldTableCell.forTableColumn());
		thirteenth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setThirteenth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "thirteenth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		fourteenth.setCellFactory(TextFieldTableCell.forTableColumn());
		fourteenth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setFourteenth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "fourteenth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		fifteenth.setCellFactory(TextFieldTableCell.forTableColumn());
		fifteenth.setOnEditCommit(new EventHandler<CellEditEvent<FullPrescriptions,String>> (){

			@Override
			public void handle(CellEditEvent<FullPrescriptions, String> event) {
				String  oldValue =event.getOldValue();
				String newValue=event.getNewValue();
				
				if(newValue.length()>=oldValue.length()) {
				
			if(newValue.charAt(0)==oldValue.charAt(0) && newValue.charAt(1)==oldValue.charAt(1) &&
					newValue.charAt(2)==oldValue.charAt(2) && newValue.charAt(3)==oldValue.charAt(3) && 
					newValue.charAt(4)==oldValue.charAt(4) ) {
				
				event.getRowValue().setFifteenth(newValue);
				dao.updatePrescription(Integer.parseInt(event.getRowValue().getPhonefk()), "fifteenth", newValue);
				
			}
			
				}
				else {
				new CallAlert(AlertType.WARNING, "", "insert the date correctly after the comma", "Example :  Ma10,2023-04-03");
			}
			
			}
			
		});
		
		
		
		
		
}

catch (Exception ex) {

	new CallAlert(AlertType.WARNING,"EnterPatientController","columnsPropertyMethod",ex.getClass().getName());


	}
	}


	@FXML public void shift() {
		
		
		
		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setTitle("");
		alert.setHeaderText("Are you sure to shift the " + spinner.getValue() + " column ?");
		alert.setContentText("Shifting the First column means the patient has missed the first appointment (This statment works for all columns )");
		if( alert.showAndWait().get()==ButtonType.OK) {
			
		
		dao.updateAbsentPrescriptions(prescriptionTable.getSelectionModel().getSelectedItem(),spinner.getValue());
		
		prescriptionList=dao.getAllFullPrescriptions();
		attendanceList=dao.getAllFullAttendance();
		
		prescriptionTable.setItems(prescriptionList);
		attendanceTable.setItems(attendanceList);
		
		}
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
