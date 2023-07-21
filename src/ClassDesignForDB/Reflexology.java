package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class Reflexology {
	
	private SimpleStringProperty id ;
	private SimpleStringProperty massager ;
	private SimpleStringProperty price ;
	private SimpleStringProperty paymentMethod ;
	private SimpleStringProperty paymentDate ;
	
	
	Reflexology (){}
	
	Reflexology (String id,String massager,String price,String paymentMethod,String paymentDate){
		
		this.id=new SimpleStringProperty(id);
		this.massager=new SimpleStringProperty(massager);
		this.price=new SimpleStringProperty(price);
		this.paymentMethod=new SimpleStringProperty(paymentMethod);
		this.paymentDate=new SimpleStringProperty(paymentDate);
		
	}

	public String getId() {
		return id.get();
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public String getMassager() {
		return massager.get();
	}

	public void setMassager(SimpleStringProperty massager) {
		this.massager = massager;
	}

	public String getPrice() {
		return price.get();
	}

	public void setPrice(SimpleStringProperty price) {
		this.price = price;
	}

	public String getPaymentMethod() {
		return paymentMethod.get();
	}

	public void setPaymentMethod(SimpleStringProperty paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentDate() {
		return paymentDate.get();
	}

	public void setPaymentDate(SimpleStringProperty paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
