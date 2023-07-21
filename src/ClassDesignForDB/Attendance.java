package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class Attendance {

	
	private SimpleStringProperty phonefk ; 
	private SimpleStringProperty firsta ; 
	private SimpleStringProperty seconda ; 
	private SimpleStringProperty third ; 
	private SimpleStringProperty fourth ; 
	private SimpleStringProperty fifth ; 
	private SimpleStringProperty sixth ; 
	private SimpleStringProperty seventh ; 
	private SimpleStringProperty eighth ; 
	private SimpleStringProperty ninth ; 
	private SimpleStringProperty tenth ; 
	private SimpleStringProperty eleventh ; 
	private SimpleStringProperty twelvth ; 
	private SimpleStringProperty thirteenth ; 
	private SimpleStringProperty fourteenth ; 
	private SimpleStringProperty fifteenth ;
	
	Attendance(){}
	
	
	public Attendance (int phonefk,String firsta,String seconda,String third,String fourth,
			String fifth,String sixth,String seventh,String eighth,String ninth,String tenth,
			String eleventh,String twelvth,String thirteenth,String fourteenth,String fifteenth){
		
		this.phonefk=new SimpleStringProperty(String.valueOf(phonefk));
		this.firsta=new SimpleStringProperty(firsta);
		this.seconda=new SimpleStringProperty(seconda);
		this.third=new SimpleStringProperty(third);
		this.fourth=new SimpleStringProperty(fourth);
		this.fifth=new SimpleStringProperty(fifth);
		this.sixth=new SimpleStringProperty(sixth);
		this.seventh=new SimpleStringProperty(seventh);
		this.eighth=new SimpleStringProperty(eighth);
		this.ninth=new SimpleStringProperty(ninth);
		this.tenth=new SimpleStringProperty(tenth);
		this.eleventh=new SimpleStringProperty(eleventh);
		this.twelvth=new SimpleStringProperty(twelvth);
		this.thirteenth=new SimpleStringProperty(thirteenth);
		this.fourteenth=new SimpleStringProperty(fourteenth);
		this.fifteenth=new SimpleStringProperty();
		
		
		
	}


	public String getPhonefk() {
		return phonefk.get();
	}


	public void setPhonefk(SimpleStringProperty phonefk) {
		this.phonefk = phonefk;
	}


	public String getFirsta() {
		return firsta.get();
	}


	public void setFirsta(String firsta) {
		this.firsta = new SimpleStringProperty(firsta);
	}


	public String getSeconda() {
		return seconda.get();
	}


	public void setSeconda(String seconda) {
		this.seconda = new SimpleStringProperty(seconda);
	}


	public String getThird() {
		return third.get();
	}


	public void setThird(String third) {
		this.third = new SimpleStringProperty(third);
	}


	public String getFourth() {
		return fourth.get();
	}


	public void setFourth(String fourth) {
		this.fourth = new SimpleStringProperty(fourth);
	}


	public String getFifth() {
		return fifth.get();
	}


	public void setFifth(String fifth) {
		this.fifth = new SimpleStringProperty(fifth);
	}


	public String getSixth() {
		return sixth.get();
	}


	public void setSixth(String sixth) {
		this.sixth = new SimpleStringProperty(sixth);
	}


	public String getSeventh() {
		return seventh.get();
	}


	public void setSeventh(String seventh) {
		this.seventh = new SimpleStringProperty(seventh);
	}


	public String getEighth() {
		return eighth.get();
	}


	public void setEighth(String eighth) {
		this.eighth = new SimpleStringProperty(eighth);
	}


	public String getNinth() {
		return ninth.get();
	}


	public void setNinth(String ninth) {
		this.ninth = new SimpleStringProperty(ninth);
	}


	public String getTenth() {
		return tenth.get();
	}


	public void setTenth(String tenth) {
		this.tenth = new SimpleStringProperty(tenth);
	}


	public String getEleventh() {
		return eleventh.get();
	}


	public void setEleventh(String eleventh) {
		this.eleventh = new SimpleStringProperty(eleventh);
	}


	public String getTwelvth() {
		return twelvth.get();
	}


	public void setTwelvth(String twelvth) {
		this.twelvth = new SimpleStringProperty(twelvth);
	}


	public String getThirteenth() {
		return thirteenth.get();
	}


	public void setThirteenth(String thirteenth) {
		this.thirteenth = new SimpleStringProperty(thirteenth);
	}


	public String getFourteenth() {
		return fourteenth.get();
	}


	public void setFourteenth(String fourteenth) {
		this.fourteenth = new SimpleStringProperty(fourteenth);
	}


	public String getFifteenth() {
		return fifteenth.get();
	}


	public void setFifteenth(String fifteenth) {
		this.fifteenth = new SimpleStringProperty(fifteenth);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
