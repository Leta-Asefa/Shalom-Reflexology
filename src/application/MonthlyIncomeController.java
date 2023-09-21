package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import ClassDesignForDB.Withdrawal;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class MonthlyIncomeController implements Initializable{
	
	@FXML
	private Label totalReflexology,totalCard,totalPatient,totalWithdrawal,
				totalCash,totalMB,netIncome,totalIncome;
	@FXML
	private DatePicker startingDatePicker,endingDatePicker;
	
	ShalomDAO dao;
	@FXML BarChart<String,Number> graph;
	@FXML TableView<Withdrawal> table;
	@FXML TableColumn<Withdrawal,String> name;
	@FXML TableColumn<Withdrawal,String> amount;
	@FXML TableColumn<Withdrawal,String> reason;
	@FXML TableColumn<Withdrawal,String> date;
	private ObservableList<Withdrawal> withdrawalList;
	private Alert alert;
	@FXML Label totalSalary;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
		
		dao= new ShalomDAO();
		alert= new Alert(AlertType.INFORMATION);
		
		
		name.setCellValueFactory(new PropertyValueFactory<Withdrawal,String>("fullName"));
		amount.setCellValueFactory(new PropertyValueFactory<Withdrawal,String>("amount"));
		reason.setCellValueFactory(new PropertyValueFactory<Withdrawal,String>("reason"));
		date.setCellValueFactory(new PropertyValueFactory<Withdrawal,String>("withdrawalDate"));

		
		
		table.setItems(withdrawalList);
		inputValidation();
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"MonthlyIncomeController","initializeMethod",ex.getClass().getName());


			}
	}
	
	
	public void inputValidation() {
		try {
		
	startingDatePicker.setEditable(false);
	endingDatePicker.setEditable(false);
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"MonthlyIncomeController","inputValidationMethod",ex.getClass().getName());


			}
		
	}
	
	public void show() {
		try {
			
		
		if(!(startingDatePicker.getValue()==null) && 	!(endingDatePicker.getValue()==null))  {
		LocalDate firstDate=startingDatePicker.getValue();
		String startingDate=firstDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate lastDate=endingDatePicker.getValue();
		String endingDate=lastDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		withdrawalList=dao.getWithdrawalForTable(startingDate, endingDate);
		
		table.setItems(withdrawalList);
		
		
		
		
		totalReflexology.setText(String.valueOf(dao.getMonthlyReflexology(startingDate,endingDate )));
		totalCard.setText(String.valueOf(dao.getMonthlyCard(startingDate,endingDate )));
		totalPatient.setText(String.valueOf(dao.getMonthlyPatient(startingDate,endingDate )));
		totalWithdrawal.setText(String.valueOf(dao.getMonthlyWithdrawal(startingDate,endingDate )+dao.getMonthlySalaryHistory(startingDate, endingDate)));
		totalSalary.setText(String.valueOf(dao.getMonthlySalaryHistory(startingDate, endingDate)));
		totalCash.setText(String.valueOf(dao.getMonthlyTotalCash(startingDate, endingDate)));
		totalMB.setText(String.valueOf(dao.getMonthlyTotalMB(startingDate, endingDate)));
		netIncome.setText(String.valueOf(dao.getMonthlyNetIncome(startingDate, endingDate)));
		totalIncome.setText(String.valueOf(dao.getMonthlyTotalIncome(startingDate, endingDate)));
		
		drawGraph(startingDate,endingDate);
		}
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"MonthlyIncomeController","showMethod",ex.getClass().getName());


			}
	}


	private void drawGraph(String startingDate,String endingDate) {
		
		try {
		
		XYChart.Series<String, Number> series= new XYChart.Series<>();
		String[] datas = {"Reflexology","Card","Patient","Withdrawal","Cash","MB","Total","Net"};
		Number rx= dao.getMonthlyReflexology(startingDate,endingDate);
		Number card=dao.getMonthlyCard(startingDate,endingDate);
		Number patient=dao.getMonthlyPatient(startingDate,endingDate);
		Number withdrawal=dao.getMonthlyWithdrawal(startingDate,endingDate);
		Number cash=dao.getMonthlyTotalCash(startingDate,endingDate);
		Number mb=dao.getMonthlyTotalMB(startingDate,endingDate);
		Number income= dao.getMonthlyTotalIncome(startingDate,endingDate);
		Number profit= dao.getMonthlyNetIncome(startingDate,endingDate);
		
		Number[] values = {rx,card,patient,withdrawal,cash,mb, income, profit};
		
		
		
		
		for (int i = 0; i <8; i++) {
		
		series.getData().add(new XYChart.Data<String, Number>(datas[i],values[i]));
		
		}
		graph.getData().clear();
		
		graph.getData().add(series);
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"MonthlyIncomeController","drawGraphMethod",ex.getClass().getName());


			}
	}@FXML public void clicked() {
		

//		if(!(startingDatePicker.getValue()==null)  &&  !(endingDatePicker.getValue()==null)) {
//			
//		try {
//			
//			
//		
//		int from30=dao.getMonthlyReflexologyCount(startingDatePicker.getValue().toString(),endingDatePicker.getValue().toString(), "30 MIN");
//		int from45=dao.getMonthlyReflexologyCount(startingDatePicker.getValue().toString(),endingDatePicker.getValue().toString(), "45 MIN");
//		int from60=dao.getMonthlyReflexologyCount(startingDatePicker.getValue().toString(),endingDatePicker.getValue().toString(), "60 MIN");
//
//		int fromMa10=dao.getMonthlyReflexologyCount(startingDatePicker.getValue().toString(),endingDatePicker.getValue().toString(), "Ma10");
//		int fromMa12=dao.getMonthlyReflexologyCount(startingDatePicker.getValue().toString(),endingDatePicker.getValue().toString(), "Ma12");
//		int fromMa15=dao.getMonthlyReflexologyCount(startingDatePicker.getValue().toString(),endingDatePicker.getValue().toString(), "Ma15");
//		int fromMa17=dao.getMonthlyReflexologyCount(startingDatePicker.getValue().toString(),endingDatePicker.getValue().toString(), "Ma17");
//		int fromMa20=dao.getMonthlyReflexologyCount(startingDatePicker.getValue().toString(),endingDatePicker.getValue().toString(), "Ma20");
//
//		int total=from30+from45+from60+fromMa10+fromMa12+fromMa15+fromMa17+fromMa20;
//
//alert.setHeaderText("From 30 MIN "+from30+" customer(s)\n"+
//				"From 45 MIN "+from45+" customer(s)\n" +
//				"From 60 MIN "+from60+" customer(s)\n"+
//				"From Ma10   "+fromMa10+" customer(s)\n" +
//				"From Ma12   "+fromMa12+" customer(s)\n" +
//				"From Ma15   "+fromMa15+" customer(s)\n" +
//				"From Ma17   "+fromMa17+" customer(s)\n" +
//				"From Ma20   "+fromMa20+" customer(s)\n" +
//				"\n\nTotal =    "+total+" customer(s)\n"
//				);
//		
//		alert.show();
//		
//		
//		
//		}
//		catch (Exception ex) {
//
//			new CallAlert(AlertType.WARNING,"DailyIncomeController","enteredMethod",ex.getClass().getName());
//
//
//			}
//		
//		
//		
//		
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}}
