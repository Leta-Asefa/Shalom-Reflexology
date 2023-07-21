package ClassDesignForDB;

import javafx.beans.property.SimpleStringProperty;

public class Employee {
	
	private SimpleStringProperty id ; 
	private SimpleStringProperty fullName ; 
	private SimpleStringProperty sex ; 
	private SimpleStringProperty phone ; 
	private SimpleStringProperty fixedSalary ; 
	private SimpleStringProperty userName ; 
	private SimpleStringProperty userPassoword ; 
	private SimpleStringProperty job ;
	
	public Employee(){}
	
	public Employee(String id ,String fullName,String sex,String phone,String fixedSalary,
			String userName,String userPassoword,String job ){
		
		this.id=new SimpleStringProperty(id);
		this.fullName=new SimpleStringProperty(fullName);
		this.sex=new SimpleStringProperty(sex);
		this.phone=new SimpleStringProperty(phone);
		this.fixedSalary=new SimpleStringProperty(fixedSalary);
		this.userName=new SimpleStringProperty(userName);
		this.userPassoword=new SimpleStringProperty(userPassoword);
		this.job=new SimpleStringProperty(job);
		
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

	public void setFullName(String fullName) {
		this.fullName = new SimpleStringProperty( fullName);
	}

	public String getSex() {
		return sex.get();
	}

	public void setSex(String sex) {
		this.sex = new SimpleStringProperty( sex);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty( phone);
	}

	public String getFixedSalary() {
		return fixedSalary.get();
	}

	public void setFixedSalary(String fixedSalary) {
		this.fixedSalary = new SimpleStringProperty( fixedSalary);
	}

	public String getUserName() {
		return userName.get();
	}

	public void setUserName(String userName) {
		this.userName = new SimpleStringProperty( userName);
	}

	public String getUserPassoword() {
		return userPassoword.get();
	}

	public void setUserPassoword(String userPassoword) {
		this.userPassoword = new SimpleStringProperty( userPassoword);
	}

	public String getJob() {
		return job.get();
	}

	public void setJob(String job) {
		this.job =new SimpleStringProperty( job);
	}
	
}
