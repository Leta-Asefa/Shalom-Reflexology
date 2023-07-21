package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class Withdrawal {
	private SimpleStringProperty id ;
	private SimpleStringProperty fullName ;
	private SimpleStringProperty amount ;
	private SimpleStringProperty reason ;
	private SimpleStringProperty withdrawalDate ;
	
	Withdrawal(){}
	
	public Withdrawal(String id,String fullName,String amount,String reason,String withdrawalDate){
		this.id=new SimpleStringProperty(id);
		this.fullName=new SimpleStringProperty(fullName);
		this.amount=new SimpleStringProperty(amount);
		this.reason=new SimpleStringProperty(reason);
		this.withdrawalDate=new SimpleStringProperty(withdrawalDate);
	}

	public String getId() {
		return id.get();
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName.get();
	}

	public void setFullName(SimpleStringProperty fullName) {
		this.fullName = fullName;
	}

	public String getAmount() {
		return amount.get();
	}

	public void setAmount(SimpleStringProperty amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason.get();
	}

	public void setReason(SimpleStringProperty reason) {
		this.reason = reason;
	}

	public String getWithdrawalDate() {
		return withdrawalDate.get();
	}

	public void setWithdrawalDate(SimpleStringProperty withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
