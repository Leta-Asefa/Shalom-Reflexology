package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class Payments {
	private SimpleStringProperty id ; 
	private SimpleStringProperty phonefk ; 
	private SimpleStringProperty cardMb ; 
	private SimpleStringProperty cardCash ; 
	private SimpleStringProperty paymentMb ; 
	private SimpleStringProperty paymentCash ; 
	private SimpleStringProperty paymentDate ;
	private SimpleStringProperty referenceNumber ;
	
	Payments(){}
	public Payments(String id,String phonefk,String cardMb,String cardCash,String paymentMb,
			String paymentCash,String paymentDate,String referenceNumber){
		
		this.id=new SimpleStringProperty(id);
		this.phonefk=new SimpleStringProperty(phonefk);
		this.cardMb=new SimpleStringProperty(cardMb);
		this.cardCash=new SimpleStringProperty(cardCash);
		this.paymentMb=new SimpleStringProperty(paymentMb);
		this.paymentCash=new SimpleStringProperty(paymentCash);
		this.paymentDate=new SimpleStringProperty(paymentDate);
		this.setReferenceNumber(new SimpleStringProperty(referenceNumber));
		
	}
	
	public String getId() {
		return id.get();
	}
	public void setId(SimpleStringProperty id) {
		this.id = id;
	}
	public String getPhonefk() {
		return phonefk.get();
	}
	public void setPhonefk(SimpleStringProperty phonefk) {
		this.phonefk = phonefk;
	}
	public String getCardMb() {
		return cardMb.get();
	}
	public void setCardMb(SimpleStringProperty cardMb) {
		this.cardMb = cardMb;
	}
	public String getCardCash() {
		return cardCash.get();
	}
	public void setCardCash(SimpleStringProperty cardCash) {
		this.cardCash = cardCash;
	}
	public String getPaymentMb() {
		return paymentMb.get();
	}
	public void setPaymentMb(SimpleStringProperty paymentMb) {
		this.paymentMb = paymentMb;
	}
	public String getPaymentCash() {
		return paymentCash.get();
	}
	public void setPaymentCash(SimpleStringProperty paymentCash) {
		this.paymentCash = paymentCash;
	}
	public String getPaymentDate() {
		return paymentDate.get();
	}
	public void setPaymentDate(SimpleStringProperty paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getReferenceNumber() {
		return referenceNumber.get();
	}
	public void setReferenceNumber(SimpleStringProperty referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
