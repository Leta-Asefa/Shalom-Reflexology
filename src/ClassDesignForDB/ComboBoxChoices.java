package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class ComboBoxChoices {
	
	

	private SimpleStringProperty id ; 
	private SimpleStringProperty fullName ; 
	private SimpleStringProperty category ; 
	private SimpleStringProperty price ;
	private SimpleStringProperty commission ;
	
	ComboBoxChoices(){
		
	}
	
	public ComboBoxChoices(String id,String fullName,String category,String price,String commission){
		
		this.setId(new SimpleStringProperty(id));
		this.setFullName(fullName);
		this.setCategory(new SimpleStringProperty(category));
		this.setPrice(price);
		this.setCommission(commission);

		
		
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

	public void setFullName(String fullName ) {
		this.fullName = new SimpleStringProperty(fullName) ;
	}

	public String getCategory() {
		return category.get();
	}

	public void setCategory(SimpleStringProperty category) {
		this.category = category;
	}

	public String getPrice() {
		return price.get();
	}

	public void setPrice(String price) {
		this.price = new SimpleStringProperty(price);
	}

	public String getCommission() {
		return commission.get();
	}

	public void setCommission(String commission) {
		this.commission = new SimpleStringProperty(commission);
	}

}
