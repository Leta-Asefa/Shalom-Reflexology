package application;

import java.net.URL;
import java.util.ResourceBundle;

import ClassDesignForDB.Employee;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;

public class EmployeesInformationController implements Initializable{

	@FXML
	private TableView<Employee> table;
	@FXML
	private TableColumn<Employee,String> id, name,phone,salary,job,userName,password,sex;
	@FXML
	private RadioButton yes,no;
	@FXML
	private TextField  searchTextField,idTextField,nameTextField,userNameTextField,passwordTextField,
					phoneTextField,salaryTextField;
	@FXML
	private Button addButton,saveButton,editButton;
	@FXML
	private Label userNameLabel,passwordLabel,idLabel,nameLabel,sexLabel,phoneLabel,salaryLabel,jobLabel;
	
	private ShalomDAO dao;
	private ObservableList<String> jobItems;
	private ObservableList<String> sexItems;
	@FXML ComboBox<String> sexComboBox;
	@FXML ComboBox<String> jobComboBox;
	
	int deletedId=0;
	@FXML Button deleteButton;
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		dao= new ShalomDAO();
		jobItems=dao.getJobItems();
		sexItems=FXCollections.observableArrayList();
		sexItems.add("M");
		sexItems.add("F");
		
		jobComboBox.setItems(jobItems);
		sexComboBox.setItems(sexItems);
		
		columnAssociation();
		columnProperties();
		
		table.setItems(dao.getAllEmployee());
		
		no.setSelected(true);
		
		inputValidation();
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","initializeMethod",ex.getClass().getName());


			}
		
	}

	public void refreshTable() {
		try {
		table.setItems(dao.getAllEmployee());
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","refreshTableMethod",ex.getClass().getName());


			}
	}

	private void inputValidation() {
		try {
		
		searchTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				
				if(newValue.matches("[a-zA-z\s]{0,15}")) {
					
					searchTextField.setText(newValue);
					
				}else {
					
					searchTextField.setText(oldValue);
					
				}
				
			}
			
		});

		nameTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				
				if(newValue.matches("[a-zA-z\s]{0,15}")) {
					
					nameTextField.setText(newValue);
					
				}else {
					
					nameTextField.setText(oldValue);
					
				}
				
			}
			
		});

		userNameTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				
				if(newValue.matches("[a-zA-z]{0,15}")) {
					
					userNameTextField.setText(newValue);
					
				}else {
					
					userNameTextField.setText(oldValue);
					
				}
				
			}
			
		});

		

		
		
		
		
		
		idTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				
				if(newValue.matches("[0-9]{0,6}")) {
					
					idTextField.setText(newValue);
					
				}else {
					
					idTextField.setText(oldValue);
					
				}
				
			}
			
		});


		phoneTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				
				if(newValue.matches("[0-9]{0,10}")) {
					
					phoneTextField.setText(newValue);
					
				}else {
					
					phoneTextField.setText(oldValue);
					
				}
				
			}
			
		});
		
		passwordTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				
				if(newValue.matches("[0-9]{0,8}")) {
					
					passwordTextField.setText(newValue);
					
				}else {
					
					passwordTextField.setText(oldValue);
					
				}
				
			}
			
		});

		salaryTextField.textProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				
				if(newValue.matches("[0-9]{0,8}")) {
					
					salaryTextField.setText(newValue);
					
				}else {
					
					salaryTextField.setText(oldValue);
					
				}
				
			}
			
		});

		
		
		
		
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","inputValidationMethod",ex.getClass().getName());


			}
		
		
	}

	public void columnAssociation() {
		try {
		
		id.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Employee,String>("fullName"));
		phone.setCellValueFactory(new PropertyValueFactory<Employee,String>("phone"));
		salary.setCellValueFactory(new PropertyValueFactory<Employee,String>("fixedSalary"));
		job.setCellValueFactory(new PropertyValueFactory<Employee,String>("job"));
		userName.setCellValueFactory(new PropertyValueFactory<Employee,String>("userName"));
		password.setCellValueFactory(new PropertyValueFactory<Employee,String>("userPassoword"));
		sex.setCellValueFactory(new PropertyValueFactory<Employee,String>("sex"));
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","columnAssociationMethod",ex.getClass().getName());


			}
		
		
	}
	
	public void columnProperties() {
		try {
		
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(new EventHandler<CellEditEvent<Employee,String>>(){

			@Override
			public void handle(CellEditEvent<Employee, String> event) {
				
			if(!event.getNewValue().equals("")) {
				Employee e=event.getRowValue();
				e.setFullName(event.getNewValue());
				
			dao.updateEmployeeStringValues(Integer.parseInt(e.getId()) , e.getFullName(), "fullName");
			}
			
			else {
				event.getRowValue().setFullName(event.getOldValue());
				refreshTable();
			}
			
			}
			
			
			
		});

		
		phone.setCellFactory(TextFieldTableCell.forTableColumn());
		phone.setOnEditCommit(new EventHandler<CellEditEvent<Employee,String>>(){

			@Override
			public void handle(CellEditEvent<Employee, String> event) {
				
				
				if(!event.getNewValue().equals("")) {
				Employee e=event.getRowValue();
				
				if(event.getNewValue().length()<=10) {
					
				e.setPhone(event.getNewValue());
				
			dao.updateEmployeeIntegerValues(Integer.parseInt(e.getId()) , Integer.parseInt(e.getPhone()), "phone");
				
				}
				else {
					e.setPhone(event.getOldValue());
					refreshTable();
				}
			
				}
				
				else {
					event.getRowValue().setPhone(event.getOldValue());
					refreshTable();
					
				}
				
				
				
			}
			
			
			
		});

		
		salary.setCellFactory(TextFieldTableCell.forTableColumn());
		salary.setOnEditCommit(new EventHandler<CellEditEvent<Employee,String>>(){

			@Override
			public void handle(CellEditEvent<Employee, String> event) {
				
				
				if(!event.getNewValue().equals("")) {

				
				event.getRowValue().setFixedSalary(event.getNewValue());
				
			dao.updateEmployeeIntegerValues(Integer.parseInt(event.getRowValue().getId()) , Integer.parseInt(event.getRowValue().getFixedSalary()), "FixedSalary");
			
			}
			
			
			else {
				
				event.getRowValue().setFixedSalary(event.getOldValue());
				refreshTable();
			}
			
			
			}});

		
		job.setCellFactory(TextFieldTableCell.forTableColumn());
		job.setOnEditCommit(new EventHandler<CellEditEvent<Employee,String>>(){

			@Override
			public void handle(CellEditEvent<Employee, String> event) {
				
				if(!event.getNewValue().equals("")) {

				event.getRowValue().setJob(event.getNewValue());
				
			dao.updateEmployeeStringValues(Integer.parseInt(event.getRowValue().getId()) , event.getRowValue().getJob(), "job");
		
			}
			else {
				event.getRowValue().setJob(event.getOldValue());
				refreshTable();
			}
			}
		
			
			
		});
		
		userName.setCellFactory(TextFieldTableCell.forTableColumn());
		userName.setOnEditCommit(new EventHandler<CellEditEvent<Employee,String>>(){

			@Override
			public void handle(CellEditEvent<Employee, String> event) {

				if(!event.getNewValue().equals("")) {

					event.getRowValue().setUserName(event.getNewValue());
					
					dao.updateEmployeeStringValues(Integer.parseInt(event.getRowValue().getId()) , event.getRowValue().getUserName(), "UserName");
					
				}
				else {
					event.getRowValue().setUserName(event.getOldValue());
					refreshTable();
				}
				
				

			}
			
			
			
		});
		
		
		password.setCellFactory(TextFieldTableCell.forTableColumn());
		password.setOnEditCommit(new EventHandler<CellEditEvent<Employee,String>>(){

			@Override
			public void handle(CellEditEvent<Employee, String> event) {
				
				if(!event.getNewValue().equals("")) {
					event.getRowValue().setUserPassoword(event.getNewValue());
					
					dao.updateEmployeeIntegerValues(Integer.parseInt(event.getRowValue().getId()) , Integer.parseInt(event.getRowValue().getUserPassoword()), "UserPassoword");
					

				}
				else {
					event.getRowValue().setUserPassoword(event.getOldValue());
					refreshTable();
				}
				
				
				
			}
			
			
			
		});

		
		
		sex.setCellFactory(TextFieldTableCell.forTableColumn());
		sex.setOnEditCommit(new EventHandler<CellEditEvent<Employee,String>>(){

			@Override
			public void handle(CellEditEvent<Employee, String> event) {
				
				
				if(!event.getNewValue().equals("")) {

					
					event.getRowValue().setSex(event.getNewValue());
					
					dao.updateEmployeeStringValues(Integer.parseInt(event.getRowValue().getId()) , event.getRowValue().getSex(), "sex");
					
				}
				else {
					event.getRowValue().setSex(event.getOldValue());
					refreshTable();
					}
				
			}
			
			
			
		});
		
		
		
		
		table.setOnMouseClicked(new EventHandler<MouseEvent >  (){

			@Override
			public void handle(MouseEvent event) {
				
				if (table.getSelectionModel().getSelectedItem().getId()!="0") {
					
					deletedId=Integer.parseInt(table.getSelectionModel().getSelectedItem().getId());
				}
				
				
			}
			
			
			
		});
		
		
		
		
		
		
		
		
		table.setEditable(true);
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","columnPropertiesMethod",ex.getClass().getName());


			}
	}
	
	
	public void add(ActionEvent e) {
		try {
		
		
		idTextField.setDisable(false);
		nameTextField.setDisable(false);
		phoneTextField.setDisable(false);
		salaryTextField.setDisable(false);
		jobComboBox.setDisable(false);
		sexComboBox.setDisable(false);
		idLabel.setDisable(false);
		nameLabel.setDisable(false);
		phoneLabel.setDisable(false);
		salaryLabel.setDisable(false);
		jobLabel.setDisable(false);
		sexLabel.setDisable(false);
		saveButton.setDisable(false);
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","addMethod",ex.getClass().getName());


			}
		
	}
	
	public void edit(ActionEvent e) {
		
		
	}

	public void search(ActionEvent e) {
		
		try {
		
		if(searchTextField.getText().matches("[a-zA-z]*")) {
		
			table.setItems(dao.searchEmployeeByName(searchTextField.getText()));
		}
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","searchMethod",ex.getClass().getName());


			}
		
	}
	
	public void save(ActionEvent e) {
		
		try {
		
		if(   yes.isSelected()  && 	!(idTextField.getText().equals(""))	&&	!(sexComboBox.getValue()==null) &&
				!(phoneTextField.getText().equals(""))	 &&  !(salaryTextField.getText().equals(""))	&&
						!(userNameTextField.getText().equals(""))	&&  !(passwordTextField.getText().equals(""))
						&&  !(jobComboBox.getValue()==null)) {
			
			dao.addNewEmployee(Integer.parseInt(idTextField.getText()),
					nameTextField.getText(), sexComboBox.getValue(), 
					Integer.parseInt(phoneTextField.getText()), 
					Integer.parseInt(salaryTextField.getText()), 
					userNameTextField.getText(), 
					Integer.parseInt(passwordTextField.getText()) ,jobComboBox.getValue() );
			
			disableNewEmployeeInputNodes();
			refreshTable();		
		}
		else if(   no.isSelected() && 	!(idTextField.getText().equals(""))	&&	!(sexComboBox.getValue()==null) &&
				!(phoneTextField.getText().equals(""))	 &&  !(salaryTextField.getText().equals(""))	&&
				 !(jobComboBox.getValue()==null)) {
			
			dao.addNewEmployee(Integer.parseInt(idTextField.getText()),
					nameTextField.getText(), sexComboBox.getValue(), 
					Integer.parseInt(phoneTextField.getText()), 
					Integer.parseInt(salaryTextField.getText()), 
					"", 
					0 ,jobComboBox.getValue() );
			
			disableNewEmployeeInputNodes();
			refreshTable();
			
		}
		else {
			new CallAlert(AlertType.WARNING,"","","please, fullfil all necessary datas ! ");
		}
		
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","saveMethod",ex.getClass().getName());


			}
		
	}
	
	public void createAccount(ActionEvent e) {
		
		try {
		
		
		if(yes.isSelected()) {
			userNameTextField.setDisable(false);
			passwordTextField.setDisable(false);
			userNameLabel.setDisable(false);
			passwordLabel.setDisable(false);
			
		}
		
		if(!yes.isSelected()) {
			userNameTextField.setDisable(true);
			passwordTextField.setDisable(true);
			userNameLabel.setDisable(true);
			passwordLabel.setDisable(true);
			
		}
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","createAccountMethod",ex.getClass().getName());


			}
		
	}
	
	public void disableNewEmployeeInputNodes() {
		
		try {
		
		idTextField.setDisable(true);
		nameTextField.setDisable(true);
		phoneTextField.setDisable(true);
		salaryTextField.setDisable(true);
		jobComboBox.setDisable(true);
		sexComboBox.setDisable(true);
		idLabel.setDisable(true);
		nameLabel.setDisable(true);
		phoneLabel.setDisable(true);
		salaryLabel.setDisable(true);
		jobLabel.setDisable(true);
		sexLabel.setDisable(true);
		userNameTextField.setDisable(true);
		passwordTextField.setDisable(true);
		userNameLabel.setDisable(true);
		passwordLabel.setDisable(true);
		saveButton.setDisable(true);
		
		no.setSelected(true);
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","displayNewEmployeeInformationMethod",ex.getClass().getName());


			}
	}
	
	
	
	
	
	
	
	
	
public void delete(ActionEvent e) {
		
		try {
		
		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure to delete "+dao.getEmployeeNameById(deletedId) +"'s  datas ?");
		
		
		if(alert.showAndWait().get()==ButtonType.OK) {
			
			dao.deleteEmployee(deletedId);
			table.setItems(dao.getAllEmployee());
			
		}
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"EmployeeInformationController","createAccountMethod",ex.getClass().getName());


			}
		
	}
	
	
	
	
	
	
	
}
