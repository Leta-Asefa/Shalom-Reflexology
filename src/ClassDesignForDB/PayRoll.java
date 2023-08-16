package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class PayRoll {
	
	private SimpleStringProperty fullName ; 
	private SimpleStringProperty rx30 ; 
	private SimpleStringProperty rx45 ; 
	private SimpleStringProperty rx60 ; 
	private SimpleStringProperty ma10 ; 
	private SimpleStringProperty ma12 ; 
	private SimpleStringProperty ma15 ; 
	private SimpleStringProperty ma17 ;
	private SimpleStringProperty ma20 ;
	private SimpleStringProperty ma25 ;
	private SimpleStringProperty customers ;
	private SimpleStringProperty commission ;
	private SimpleStringProperty fixedSalary ;
	private SimpleStringProperty totalSalary ;
	

public PayRoll(){}
	
	public PayRoll(String fullName ,String rx30,String rx45,String rx60,String ma10,
			String ma12,String ma15,String ma17 ,String ma20,String ma25,String customers,String commission,String fixedSalary,String totalSalary ){
		
		this.fullName=new SimpleStringProperty(fullName);
		this.rx30=new SimpleStringProperty(rx30);
		this.rx45=new SimpleStringProperty(rx45);
		this.rx60=new SimpleStringProperty(rx60);
		this.ma10=new SimpleStringProperty(ma10);
		this.ma12=new SimpleStringProperty(ma12);
		this.ma15=new SimpleStringProperty(ma15);
		this.ma17=new SimpleStringProperty(ma17);
		this.ma20=new SimpleStringProperty(ma20);
		this.setMa25(new SimpleStringProperty(ma25));
		this.customers=new SimpleStringProperty(customers);
		this.commission=new SimpleStringProperty(commission);
		this.fixedSalary=new SimpleStringProperty(fixedSalary);
		this.totalSalary=new SimpleStringProperty(totalSalary);
		
		
	}
	
	
	
	
	
	
	
	
	
	
public String getFullName() {
		return fullName.get();
	}

	public void setFullName(SimpleStringProperty fullName) {
		this.fullName = fullName;
	}

	public String getRx30() {
		return rx30.get();
	}

	public void setRx30(SimpleStringProperty rx30) {
		this.rx30 = rx30;
	}

	public String getRx45() {
		return rx45.get();
	}

	public void setRx45(SimpleStringProperty rx45) {
		this.rx45 = rx45;
	}

	public String getRx60() {
		return rx60.get();
	}

	public void setRx60(SimpleStringProperty rx60) {
		this.rx60 = rx60;
	}

	public String getMa10() {
		return ma10.get();
	}

	public void setMa10(SimpleStringProperty ma10) {
		this.ma10 = ma10;
	}

	public String getMa12() {
		return ma12.get();
	}

	public void setMa12(SimpleStringProperty ma12) {
		this.ma12 = ma12;
	}

	public String getMa15() {
		return ma15.get();
	}

	public void setMa15(SimpleStringProperty ma15) {
		this.ma15 = ma15;
	}

	public String getMa17() {
		return ma17.get();
	}

	public void setMa17(SimpleStringProperty ma17) {
		this.ma17 = ma17;
	}

	public String getMa20() {
		return ma20.get();
	}

	public void setMa20(SimpleStringProperty ma20) {
		this.ma20 = ma20;
	}

	public String getCustomers() {
		return customers.get();
	}

	public void setCustomers(SimpleStringProperty customers) {
		this.customers = customers;
	}

	public String getCommission() {
		return commission.get();
	}

	public void setCommission(SimpleStringProperty commission) {
		this.commission = commission;
	}

	public String getFixedSalary() {
		return fixedSalary.get();
	}

	public void setFixedSalary(SimpleStringProperty fixedSalary) {
		this.fixedSalary = fixedSalary;
	}

	public String getTotalSalary() {
		return totalSalary.get();
	}

	public void setTotalSalary(SimpleStringProperty totalSalary) {
		this.totalSalary = totalSalary;
	}

	public String getMa25() {
		return ma25.get();
	}

	public void setMa25(SimpleStringProperty ma25) {
		this.ma25 = ma25;
	}

	
	
	
	

}
