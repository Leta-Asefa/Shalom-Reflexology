package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class PayRoll {
	
	private SimpleStringProperty fullName ; 
	private SimpleStringProperty rx45 ; 
	private SimpleStringProperty rx60 ; 
	private SimpleStringProperty m130 ; 
	private SimpleStringProperty m230 ; 
	private SimpleStringProperty m330 ; 
	private SimpleStringProperty m145 ;
	private SimpleStringProperty m245 ;
	private SimpleStringProperty m345 ;
	private SimpleStringProperty m160 ;
	private SimpleStringProperty m260 ;
	private SimpleStringProperty m360 ;
	private SimpleStringProperty customers ;
	private SimpleStringProperty commission ;
	private SimpleStringProperty fixedSalary ;
	private SimpleStringProperty totalSalary ;
	

public PayRoll(){}
	
	public PayRoll(String fullName ,String rx45,String rx60,String m130,
			String m230,String m330,String m145 ,String m245,String m345,String m160,String m260,String m360,String customers,String commission,String fixedSalary,String totalSalary ){
		
		this.fullName=new SimpleStringProperty(fullName);
		this.rx45=new SimpleStringProperty(rx45);
		this.rx60=new SimpleStringProperty(rx60);
		this.m130=new SimpleStringProperty(m130);
		this.m230=new SimpleStringProperty(m230);
		this.m330=new SimpleStringProperty(m330);
		this.m145=new SimpleStringProperty(m145);
		this.m245=new SimpleStringProperty(m245);
		this.m345=new SimpleStringProperty(m345);
		this.m160=new SimpleStringProperty(m160);
		this.m260=new SimpleStringProperty(m260);
		this.m360=new SimpleStringProperty(m360);
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

	public String getM130() {
		return m130.get();
	}

	public void setM130(SimpleStringProperty m130) {
		this.m130 = m130;
	}

	public String getM230() {
		return m230.get();
	}

	public void setM230(SimpleStringProperty m230) {
		this.m230 = m230;
	}

	public String getM330() {
		return m330.get();
	}

	public void setM330(SimpleStringProperty m330) {
		this.m330 = m330;
	}

	public String getM145() {
		return m145.get();
	}

	public void setM145(SimpleStringProperty m145) {
		this.m145 = m145;
	}

	public String getM245() {
		return m245.get();
	}

	public void setM245(SimpleStringProperty m245) {
		this.m245 = m245;
	}

	public String getM345() {
		return m345.get();
	}

	public void setM345(SimpleStringProperty m345) {
		this.m345 = m345;
	}

	public String getM160() {
		return m160.get();
	}

	public void setM160(SimpleStringProperty m160) {
		this.m160 = m160;
	}

	public String getM260() {
		return m260.get();
	}

	public void setM260(SimpleStringProperty m260) {
		this.m260 = m260;
	}

	public String getM360() {
		return m360.get();
	}

	public void setM360(SimpleStringProperty m360) {
		this.m360 = m360;
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

	
	
	
	
	

}
