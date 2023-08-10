package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class BankAnalysis {

	private SimpleStringProperty total ;
	private SimpleStringProperty withdrawal ;
	private SimpleStringProperty mb ;

	private SimpleStringProperty forBank ;
	private SimpleStringProperty net ;
	private SimpleStringProperty referenceNumber ;
	private SimpleStringProperty date ;

	
	BankAnalysis (){}
	
	public BankAnalysis (String total,String withdrawal,String mb,String forBank,String net,String referenceNumber,String date){
		
		this.total=new SimpleStringProperty(total);
		this.withdrawal=new SimpleStringProperty(withdrawal);
		this.setMb(new SimpleStringProperty(mb));
		this.forBank=new SimpleStringProperty(forBank);
		this.net=new SimpleStringProperty(net);
		this.referenceNumber=new SimpleStringProperty(referenceNumber);
		this.date=new SimpleStringProperty(date);
		
		
	}
	
	
	
	public String getTotal() {
		return total.get();
	}

	public void setTotal(SimpleStringProperty total) {
		this.total = total;
	}

	public String getWithdrawal() {
		return withdrawal.get();
	}

	public void setWithdrawal(SimpleStringProperty withdrawal) {
		this.withdrawal = withdrawal;
	}

	public String getForBank() {
		return forBank.get();
	}

	public void setForBank(SimpleStringProperty forBank) {
		this.forBank = forBank;
	}

	public String getNet() {
		return net.get();
	}

	public void setNet(String string) {
		this.net = new SimpleStringProperty( string);
	}

	public String getReferenceNumber() {
		return referenceNumber.get();
	}

	public void setReferenceNumber(SimpleStringProperty referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(SimpleStringProperty date) {
		this.date = date;
	}

	public String getMb() {
		return mb.get();
	}

	public void setMb(SimpleStringProperty mb) {
		this.mb = mb;
	}


	
	
}
