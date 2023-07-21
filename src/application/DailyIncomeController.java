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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class DailyIncomeController implements Initializable {


	@FXML
	private Label totalReflexology,totalCard,totalPatient,totalWithdrawal,
				totalCash,totalMB,netIncome,totalIncome;
	@FXML
	private DatePicker datePicker;
	
	
	ShalomDAO dao;
	@FXML BarChart<String,Number> graph;
	
	  @FXML
	    private CategoryAxis xAxis;

	    @FXML
	    private NumberAxis yAxis;
		@FXML TableView<Withdrawal> table;
		@FXML TableColumn<Withdrawal,String> name;
		@FXML TableColumn<Withdrawal,String> amount;
		@FXML TableColumn<Withdrawal,String> reason;
		@FXML TableColumn<Withdrawal,String> date;
		private ObservableList<Withdrawal> withdrawalList;
		@FXML Label fromRxLabel;
		private Alert alert;


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
		datePicker.setEditable(false);
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"DailyIncomeController","initializeMethod",ex.getClass().getName());


			}
	}
	
	private void graphSettingUp(String date) {
		
		try {
		
		XYChart.Series<String, Number> series= new XYChart.Series<>();
		
		
		String[] datas = {"Reflexology","Card","Patient","Withdrawal","Cash","MB","Total","Net"};
		Number rx= dao.getDailyReflexology(date);
		Number card=dao.getDailyCard(date);
		Number patient=dao.getDailyPatient(date);
		Number withdrawal=dao.getDailyWithdrawal(date);
		Number cash=dao.getDailyTotalCash(date);
		Number mb=dao.getDailyTotalMB(date);
		Number income= dao.getDailyTotalIncome(date);
		Number profit= dao.getDailyNetIncome(date);
		
		Number[] values = {rx,card,patient,withdrawal,cash,mb, income, profit};
		
		
		
		
		for (int i = 0; i <8; i++) {
		
		series.getData().add(new XYChart.Data<String, Number>(datas[i],values[i]));
		
		}
		graph.getData().clear();
		
		graph.getData().add(series);
		
		
	
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"DailyIncomeController","graphSettingUpMethod",ex.getClass().getName());


			}
		
		
		
		
	}

	public void show() {
		
		try {
		
		if(!(datePicker.getValue()==null)) {
			
		LocalDate firstDate=datePicker.getValue();
		String startingDate=firstDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		withdrawalList=dao.getWithdrawalForTableDaily(startingDate );
		
		table.setItems(withdrawalList);
		
		
		
		
		totalReflexology.setText(String.valueOf(dao.getDailyReflexology(startingDate)));
		totalCard.setText(String.valueOf(dao.getDailyCard(startingDate)));
		totalPatient.setText(String.valueOf(dao.getDailyPatient(startingDate)));
		totalWithdrawal.setText(String.valueOf(dao.getDailyWithdrawal(startingDate)));
		totalCash.setText(String.valueOf(dao.getDailyTotalCash(startingDate)));
		totalMB.setText(String.valueOf(dao.getDailyTotalMB(startingDate)));
		netIncome.setText(String.valueOf(dao.getDailyNetIncome(startingDate)));
		totalIncome.setText(String.valueOf(dao.getDailyTotalIncome(startingDate)));
		
		graphSettingUp(startingDate);
	
		
		
		
		
		}}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"DailyIncomeController","showMethod",ex.getClass().getName());


			}
		
		
	}

	@FXML public void clicked() {
		
		
		if(!(datePicker.getValue()==null)) {
			
		try {
			
			
		
		int from30=dao.getDailyReflexologyCount(datePicker.getValue().toString(), "30 MIN");
		int from45=dao.getDailyReflexologyCount(datePicker.getValue().toString(), "45 MIN");
		int from60=dao.getDailyReflexologyCount(datePicker.getValue().toString(), "60 MIN");

		int fromMa10=dao.getDailyReflexologyCount(datePicker.getValue().toString(), "Ma10");
		int fromMa12=dao.getDailyReflexologyCount(datePicker.getValue().toString(), "Ma12");
		int fromMa15=dao.getDailyReflexologyCount(datePicker.getValue().toString(), "Ma15");
		int fromMa17=dao.getDailyReflexologyCount(datePicker.getValue().toString(), "Ma17");
		int fromMa20=dao.getDailyReflexologyCount(datePicker.getValue().toString(), "Ma20");


alert.setHeaderText("From 30 MIN "+from30+" customer(s)\n"+
				"From 45 MIN "+from45+" customer(s)\n" +
				"From 60 MIN "+from60+" customer(s)\n"+
				"From Ma10   "+fromMa10+" customer(s)\n" +
				"From Ma12   "+fromMa12+" customer(s)\n" +
				"From Ma15   "+fromMa15+" customer(s)\n" +
				"From Ma17   "+fromMa17+" customer(s)\n" +
				"From Ma20   "+fromMa20+" customer(s)\n" 
				);
		
		alert.show();
		
		
		
		}
		catch (Exception ex) {

			new CallAlert(AlertType.WARNING,"DailyIncomeController","enteredMethod",ex.getClass().getName());


			}
		
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
