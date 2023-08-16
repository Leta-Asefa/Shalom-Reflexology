package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class Patient {
	 private SimpleStringProperty phone ; 
	 private SimpleStringProperty fullName ; 
	 private SimpleStringProperty age ; 
	 private SimpleStringProperty sex ; 
	 private SimpleStringProperty assesment ; 
	 private SimpleStringProperty treatment ; 
	 private SimpleStringProperty 	focusingArea ;
	 private SimpleStringProperty 	history ;
	 
	 
	public Patient (){
		 
	 }
	 
	 public Patient(int phone,String fullName,int age,String sex,
			 String assesment,String treatment,
			 String focusingArea,String history){
		 
		 this.phone=new SimpleStringProperty(String.valueOf(phone));
		 this.fullName=new SimpleStringProperty(fullName);
		 this.age=new SimpleStringProperty(String.valueOf(age));
		 this.sex=new SimpleStringProperty(sex);
		 this.assesment=new SimpleStringProperty(assesment);
		 this.treatment=new SimpleStringProperty(treatment);
		 this.focusingArea=new SimpleStringProperty(focusingArea);
		 this.setHistory(new SimpleStringProperty(history));
		 
		 
	 }
	 
	 
	 
	 public String getPhone() {
		return phone.get();
	}

	public void setPhone(String string) {
		this.phone = new SimpleStringProperty(string);
	}

	public String getFullName() {
		return fullName.get();
	}

	public void setFullName(String string) {
		this.fullName = new SimpleStringProperty(string);
	}

	public String getAge() {
		return age.get();
	}

	public void setAge(SimpleStringProperty age) {
		this.age = age;
	}

	public String getSex() {
		return sex.get();
	}

	public void setSex(String string) {
		this.sex = new SimpleStringProperty(string);
	}

	public String getAssesment() {
		return assesment.get();
	}

	public void setAssesment(SimpleStringProperty assesment) {
		this.assesment = assesment;
	}

	public String getTreatment() {
		return treatment.get();
	}

	public void setTreatment(SimpleStringProperty treatment) {
		this.treatment = treatment;
	}

	public String getFocusingArea() {
		return focusingArea.get();
	}

	public void setFocusingArea(SimpleStringProperty focusingArea) {
		this.focusingArea = focusingArea;
	}

	public String getHistory() {
		return history.get();
	}

	public void setHistory(SimpleStringProperty history) {
		this.history = history;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
