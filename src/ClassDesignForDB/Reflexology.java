package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class Reflexology {
	
	private SimpleStringProperty id ;
	private SimpleStringProperty massager ;
	private SimpleStringProperty reflexologyType ;
	
	
	Reflexology (){}
	
	public Reflexology (String id,String massager,String reflexologyType){
		
		this.id=new SimpleStringProperty(id);
		this.massager=new SimpleStringProperty(massager);
		this.reflexologyType=new SimpleStringProperty(reflexologyType);
		
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

	public String getReflexologyType() {
		return reflexologyType.get();
	}

	public void setReflexologyType(SimpleStringProperty reflexologyType) {
		this.reflexologyType = reflexologyType;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
