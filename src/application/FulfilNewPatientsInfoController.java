package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ClassDesignForDB.Patient;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class FulfilNewPatientsInfoController implements Initializable {

	
	

    @FXML
    private Button assessmentInsertButton;

    @FXML
    private TextArea assessmentTextArea;

    @FXML
    private Button focusingAreaInsertButton;

    @FXML
    private TextArea focusingAreaTextArea;

    @FXML
    private Button historyInsertButton;

    @FXML
    private TextArea historyTextArea;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<String> p1;

    @FXML
    private ComboBox<String> p10;

    @FXML
    private ComboBox<String> p11;

    @FXML
    private ComboBox<String> p12;

    @FXML
    private ComboBox<String> p13;

    @FXML
    private ComboBox<String> p14;

    @FXML
    private ComboBox<String> p15;

    @FXML
    private ComboBox<String> p16;

    @FXML
    private ComboBox<String> p17;

    @FXML
    private ComboBox<String> p18;

    @FXML
    private ComboBox<String> p19;

    @FXML
    private ComboBox<String> p2;

    @FXML
    private ComboBox<String> p20;

    @FXML
    private ComboBox<String> p21;

    @FXML
    private ComboBox<String> p22;

    @FXML
    private ComboBox<String> p23;

    @FXML
    private ComboBox<String> p24;

    @FXML
    private ComboBox<String> p25;

    @FXML
    private ComboBox<String> p26;

    @FXML
    private ComboBox<String> p27;

    @FXML
    private ComboBox<String> p28;

    @FXML
    private ComboBox<String> p29;

    @FXML
    private ComboBox<String> p3;

    @FXML
    private ComboBox<String> p30;

    @FXML
    private ComboBox<String> p4;

    @FXML
    private ComboBox<String> p5;

    @FXML
    private ComboBox<String> p6;

    @FXML
    private ComboBox<String> p7;

    @FXML
    private ComboBox<String> p8;

    @FXML
    private ComboBox<String> p9;

    @FXML
    private Button saveButton;

    @FXML
    private Button treatmentInsertButton;

    @FXML
    private TextArea treatmentTextArea;

    @FXML private ListView<String> list;
	
	private ShalomDAO dao;

	private ObservableList<String> listItems;
	private ObservableList<String> prescriptionItems;
	
	int phone;
	Patient p;

	@FXML Button week_2_Reset_Button9;

	@FXML Button week_1_Reset_Button;

	@FXML Button week_10_Reset_Button1;

	@FXML Button week_5_Reset_Button2;

	@FXML Button week_9_Reset_Button3;

	@FXML Button week_4_Reset_Button4;

	@FXML Button week_8_Reset_Button5;

	@FXML Button week_7_Reset_Button6;

	@FXML Button week_6_Reset_Button7;

	@FXML Button week_3_Reset_Button8;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		
		
		dao= new ShalomDAO();
		
		listItems=dao.getAllListItemsForFullfilDatas();
		prescriptionItems=dao.getPrescriptionItems();
		
		list.setItems(listItems);
		setPrescriptionItems();
		
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		 phone=dao.searchCurrentPatient();
		 p=dao.getPatientByPhone(phone);
		
		 if(! (p==null)) {
		nameTextField.setText(p.getFullName());
		 }else {}
		 
		 
		 
		 inputValidation();
		 
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"FullfilNewPatientController","initializeMethod",ex.getClass().getName());


			}
		
	
	}


	
	
	

    private void inputValidation() {

    	assessmentTextArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (assessmentTextArea.getText().length()<=199) {
				assessmentTextArea.setText(newValue);
			} else {
				assessmentTextArea.setText(oldValue);
					}
			}
			
		});
    	
    	
    	treatmentTextArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (treatmentTextArea.getText().length()<=199) {
				treatmentTextArea.setText(newValue);
			} else {
				treatmentTextArea.setText(oldValue);
					}
			}
			
		});
    	
    	
    	
    	focusingAreaTextArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (focusingAreaTextArea.getText().length()<=199) {
				focusingAreaTextArea.setText(newValue);
			} else {
				focusingAreaTextArea.setText(oldValue);
					}
			}
			
		});
    	
    	
    	
    	
    	historyTextArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
			if (historyTextArea.getText().length()<=199) {
				historyTextArea.setText(newValue);
			} else {
				historyTextArea.setText(oldValue);
					}
			}
			
		});
    	
    	
	}






	private void setPrescriptionItems() {
    	
    	try {
    	
    	
		p1.setItems(prescriptionItems);
		p2.setItems(prescriptionItems);
		p3.setItems(prescriptionItems);
		p4.setItems(prescriptionItems);
		p5.setItems(prescriptionItems);
		p6.setItems(prescriptionItems);
		p7.setItems(prescriptionItems);
		p8.setItems(prescriptionItems);
		p9.setItems(prescriptionItems);
		p10.setItems(prescriptionItems);
		p11.setItems(prescriptionItems);
		p12.setItems(prescriptionItems);
		p13.setItems(prescriptionItems);
		p14.setItems(prescriptionItems);
		p15.setItems(prescriptionItems);
		p16.setItems(prescriptionItems);
		p17.setItems(prescriptionItems);
		p18.setItems(prescriptionItems);
		p19.setItems(prescriptionItems);
		p20.setItems(prescriptionItems);
		p21.setItems(prescriptionItems);
		p22.setItems(prescriptionItems);
		p23.setItems(prescriptionItems);
		p24.setItems(prescriptionItems);
		p25.setItems(prescriptionItems);
		p26.setItems(prescriptionItems);
		p27.setItems(prescriptionItems);
		p28.setItems(prescriptionItems);
		p29.setItems(prescriptionItems);
		p30.setItems(prescriptionItems);
		
		
		
		
		
		
    	}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"FullfilNewPatientController","setPrescriptionItemsMethod",ex.getClass().getName());


			}
		
		
		
	}






	@FXML
    void getAssessment(ActionEvent event) {
		try {
		assessmentTextArea.setText(list.getSelectionModel().getSelectedItems().toString());
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"FullfilNewPatientController","getAssessmentMethod",ex.getClass().getName());


			}
		}

    @FXML
    void getFocusingArea(ActionEvent event) {
    	try {
	focusingAreaTextArea.setText(list.getSelectionModel().getSelectedItems().toString());
    	}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"FullfilNewPatientController","getFocusingAreaMethod",ex.getClass().getName());


			}
    }

    @FXML
    void getHistory(ActionEvent event) {
    	try {
		historyTextArea.setText(list.getSelectionModel().getSelectedItems().toString());
    	}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"FullfilNewPatientController","getHistoryMethod",ex.getClass().getName());


			}
    }

    @FXML
    void getTreatment(ActionEvent event) {
    	try {
		treatmentTextArea.setText(list.getSelectionModel().getSelectedItems().toString());
    	}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"FullfilNewPatientController","getTreatmentMethod",ex.getClass().getName());


			}
    }

    @FXML
    void save(ActionEvent event) {

    	try {
    	
    	if(   ( !(historyTextArea.getText().equals("")) &&  !(assessmentTextArea.getText().equals(""))  && 
    			!(treatmentTextArea.getText().equals("")) &&  !(focusingAreaTextArea.getText().equals("")) ) 
    			
    															&& 
    					( ( (p1.getValue()!=null) )  ||  ((p2.getValue()!=null)) ||  ((p3.getValue()!=null))  ||  ((p4.getValue()!=null))  ||  ((p5.getValue()!=null))  ||  ((p6.getValue()!=null))  ||  ((p7.getValue()!=null)) 
    		    		||  ((p8.getValue()!=null))  ||  ((p9.getValue()!=null)) ||  ((p10.getValue()!=null)) ||  ((p11.getValue()!=null)) ||  ((p12.getValue()!=null)) ||  ((p13.getValue()!=null)) ||  ((p14.getValue()!=null))
    		    		||  ((p15.getValue()!=null)) ||  ((p16.getValue()!=null))||  ((p17.getValue()!=null))||  ((p18.getValue()!=null))||  ((p19.getValue()!=null))||  ((p20.getValue()!=null))||  ((p21.getValue()!=null))
    		    		||  ((p22.getValue()!=null)) ||  ((p23.getValue()!=null))||  ((p24.getValue()!=null))||  ((p25.getValue()!=null))||  ((p26.getValue()!=null))||  ((p27.getValue()!=null))||  ((p28.getValue()!=null))
    		    		||  ((p29.getValue()!=null)) ||  ((p30.getValue()!=null))  )  
    					
    			) {
    	
    		
    		
    		dao.takeNormalAttendance(phone, ' ', "firsta");
    		dao.fulfilPatientInfo(phone, assessmentTextArea.getText(),treatmentTextArea.getText(),focusingAreaTextArea.getText(),historyTextArea.getText());
    	
    	
    	String prescription="";
    	
    	if(p1.getValue()!=null) {
    		prescription+=p1.getValue();
    		
    	}
    	else {
    		prescription+="null";
    	}
    	
    	
    	if(p2.getValue()!=null) {
    		prescription+=p2.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p3.getValue()!=null) {
    		prescription+=p3.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p4.getValue()!=null) {
    		prescription+=p4.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p5.getValue()!=null) {
    		prescription+=p5.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p6.getValue()!=null) {
    		prescription+=p6.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p7.getValue()!=null) {
    		prescription+=p7.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p8.getValue()!=null) {
    		prescription+=p8.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p9.getValue()!=null) {
    		prescription+=p9.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p10.getValue()!=null) {
    		prescription+=p10.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p11.getValue()!=null) {
    		prescription+=p11.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p12.getValue()!=null) {
    		prescription+=p12.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p13.getValue()!=null) {
    		prescription+=p13.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p14.getValue()!=null) {
    		prescription+=p14.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p15.getValue()!=null) {
    		prescription+=p15.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p16.getValue()!=null) {
    		prescription+=p16.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p17.getValue()!=null) {
    		prescription+=p17.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p18.getValue()!=null) {
    		prescription+=p18.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p19.getValue()!=null) {
    		prescription+=p19.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p20.getValue()!=null) {
    		prescription+=p20.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p21.getValue()!=null) {
    		prescription+=p21.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p22.getValue()!=null) {
    		prescription+=p22.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p23.getValue()!=null) {
    		prescription+=p23.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p24.getValue()!=null) {
    		prescription+=p24.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p25.getValue()!=null) {
    		prescription+=p25.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p26.getValue()!=null) {
    		prescription+=p26.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p27.getValue()!=null) {
    		prescription+=p27.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p28.getValue()!=null) {
    		prescription+=p28.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p29.getValue()!=null) {
    		prescription+=p29.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	if(p30.getValue()!=null) {
    		prescription+=p30.getValue();
    	}
    	else {
    		prescription+="null";
    	}
    	
    	
    	dao.insertIntoTemporaryPrescription(phone, prescription,"");
    	
    	prescription+=".";
    	List<String> p= new ArrayList<>();
    	
    	for(int i=0;i<30;i=i+4) {
    		
    		if(p.size()<15) {
    			
    			if(prescription.substring(i,i+4)!="null") {
    				
    				p.add(prescription.substring(i,i+4));
    			}
    			
    		}
    		
    		
    	}
    	
    	
    	int length=p.size();
    	for(int i=length;i<15;i++)
    	{
    		p.add("null");
    	}
    	
    	
    	
    	dao.insertIntoPrescriptions(phone,p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14) );
    	
    	
    	
    	new CallAlert ( AlertType.INFORMATION,"","","SAVED SUCCESSFULLY !");
    	
    	saveButton.setDisable(true);
    	
    	
    	}
    	
    	
    	else {
    		
    		new CallAlert (AlertType.WARNING,"Warning","","1.The 4 boxes couldn't be left empty\n2.at least select one prescription");
    	}
    	
    	
    	
    	}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"FullfilNewPatientController","saveMethod",ex.getClass().getName());


			}
    }



    @FXML public void resetWeek1() {
    	
    	p1.setValue(null);
    	p2.setValue(null);
    	p3.setValue(null);
    }


	@FXML public void resetWeek2() {
		p4.setValue(null);
    	p5.setValue(null);
    	p6.setValue(null);
	}






	@FXML public void resetWeek3() {
		p7.setValue(null);
    	p8.setValue(null);
    	p9.setValue(null);
	}


	@FXML public void resetWeek4() {
		p10.setValue(null);
		p11.setValue(null);
		p12.setValue(null);
	}



	@FXML public void resetWeek5() {
		p13.setValue(null);
    	p14.setValue(null);
    	p15.setValue(null);
	}


	@FXML public void resetWeek6() {
		p16.setValue(null);
    	p17.setValue(null);
    	p18.setValue(null);
	}






	@FXML public void resetWeek7() {
		p19.setValue(null);
    	p20.setValue(null);
    	p21.setValue(null);
	}






	@FXML public void resetWeek8() {
		p22.setValue(null);
    	p23.setValue(null);
    	p24.setValue(null);
	}




	@FXML public void resetWeek9() {
		p25.setValue(null);
    	p26.setValue(null);
    	p27.setValue(null);
	}





	@FXML public void resetWeek10() {
		p28.setValue(null);
    	p29.setValue(null);
    	p30.setValue(null);
	}






	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}

