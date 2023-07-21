package application;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import ClassDesignForDB.BankAnalysis;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BankAdmin implements Initializable{


    @FXML
    private TableColumn<BankAnalysis, String> bank;

    @FXML
    private TableColumn<BankAnalysis, String> date;

    @FXML
    private DatePicker datePicker1;

    @FXML
    private DatePicker datePicker2;

    @FXML
    private DatePicker datePicker3;

    @FXML
    private TableColumn<BankAnalysis, String> net;

    @FXML
    private TableColumn<BankAnalysis, String> referenceNumber;

    @FXML
    private TableView<BankAnalysis> table;

    @FXML
    private TableColumn<BankAnalysis, String> total;

    @FXML
    private TableColumn<BankAnalysis, String> withdrawal;

    @FXML TableColumn<BankAnalysis, String> mb;

	
	
	
	
	
	private ShalomDAO dao;
	private ObservableList<BankAnalysis> list;

	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		
		dao= new ShalomDAO();
		list=dao.getBankAnalysis();
		
		total.setCellValueFactory(new PropertyValueFactory<BankAnalysis, String>("total"));
		withdrawal.setCellValueFactory(new PropertyValueFactory<BankAnalysis, String>("withdrawal"));
		mb.setCellValueFactory(new PropertyValueFactory<BankAnalysis, String>("mb"));
		bank.setCellValueFactory(new PropertyValueFactory<BankAnalysis, String>("forBank"));
		net.setCellValueFactory(new PropertyValueFactory<BankAnalysis, String>("net"));
		referenceNumber.setCellValueFactory(new PropertyValueFactory<BankAnalysis, String>("referenceNumber"));
		date.setCellValueFactory(new PropertyValueFactory<BankAnalysis, String>("date"));

		
		table.setItems(list);
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"BankAdmin","initializeMethod",ex.getClass().getName());


			}
		
	}

	
	
	@FXML
	void intervalSearch(ActionEvent event) {
		
		try {
		
if (datePicker2.getValue()!=null && datePicker3.getValue()!=null) {
			
			String date2=datePicker2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String date3=datePicker3.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			list.clear();
			list=dao.getBankAnalysis(date2,date3);
			table.setItems(list);
			
			
		} else {
			new CallAlert(AlertType.WARNING,"","","please, fulfill all neccessary datas !");

		}
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"BankAdmin","intervalSearchMethod",ex.getClass().getName());


			}
	}
	
	@FXML
	void specificSearch(ActionEvent event) {
		
		try {
		
		
		if (datePicker1.getValue()!=null) {
			
			String date=datePicker1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			list.clear();
			list=dao.getBankAnalysis(date);
			table.setItems(list);
			
			
		} else {
			new CallAlert(AlertType.WARNING,"","","please, fulfill all neccessary datas !");

		}
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"BankAdmin","specificSearchMethod",ex.getClass().getName());


			}
	}
	
	
	
}
