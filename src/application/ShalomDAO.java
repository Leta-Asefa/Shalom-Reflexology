package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ClassDesignForDB.Attendance;
import ClassDesignForDB.BankAnalysis;
import ClassDesignForDB.ComboBoxChoices;
import ClassDesignForDB.Employee;
import ClassDesignForDB.FullAttendance;
import ClassDesignForDB.FullPrescriptions;
import ClassDesignForDB.Patient;
import ClassDesignForDB.PayRoll;
import ClassDesignForDB.Payments;
import ClassDesignForDB.Prescriptions;
import ClassDesignForDB.Withdrawal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.util.converter.LocalDateStringConverter;

public class ShalomDAO {
	
	String url = "jdbc:mysql://localhost:3306/shalom";
	// String url="jdbc:mysql://192.168.1.1:3306/shalom";
	
	String userName = "root";
	// String userName="Reception";
	
	String password="0991175590";
	
	
	Connection myConn;
	PreparedStatement statement;
	ResultSet resultSet;
	
	private final int th10=1000;
	private final int th12=1200;
	private final int th15=1500;
	private final int th17=1700;

	private final int th20=2000;
	private final int th25=2500;
	
	private final int ma10=1000;
	private final int ma12=1200;
	private final int ma15=1500;
	private final int ma17=1700;
	private final int ma20=2000;
	private final int ma25=2500;

	
	
	
	ShalomDAO (){
		
		try {
			
			myConn=DriverManager.getConnection(url,userName,password);
		
		}
		
		
		
		catch (SQLException e) {
			
		e.printStackTrace();
			System.out.println("Check : ShalomDAO constructor");
			System.out.println("     1. the database URL ");
			System.out.println("     2. the database userName ");
			System.out.println("     3. the database password ");
			
		}
		
		
	}


	public boolean IsThereAnAdmin() {
		int num=0;
		try {
			statement=myConn.prepareStatement("select count(id) from employee");

			resultSet=statement.executeQuery();
			while(resultSet.next()) {	
			num=resultSet.getInt("count(id)");
			}

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
		if(num==0) {
			return false;
			
		}else {
			return true;
		}


}
	
	public boolean IsThereThisAccount(String userName,int password,String job) {
		int num=0;
		try {
			statement=myConn.prepareStatement("select count(id) from employee where (userName=?) and (userPassoword=?)and (job=?)");

			statement.setString(1, userName);
			statement.setInt(2, password);
			statement.setString(3, job);
			
			resultSet=statement.executeQuery();
			while(resultSet.next()) {	
			num=resultSet.getInt("count(id)");
			}

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
		if(num==0) {
			return false;
			
		}else {
			return true;
		}


}
	
	
	public void insertIntoPrescriptions(int phonefk,String firsta,String seconda,String third,String fourth,
			String fifth,String sixth,String seventh,String eighth,String ninth,String tenth,
			String eleventh,String twelvth,String thirteenth,String fourteenth,String fifteenth) {
		
		
		//the reason why i use firsta and seconda is since they are keywords in MySQL,therefore
		//they can't be column name.
		try {
statement=myConn.prepareStatement("insert into prescriptions values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
		statement.setInt(1, phonefk);
		statement.setString(2, firsta);	
		statement.setString(3, seconda);
		statement.setString(4,third );
		statement.setString(5,fourth );
		statement.setString(6,fifth );
		statement.setString(7,sixth );
		statement.setString(8, seventh);
		statement.setString(9, eighth);
		statement.setString(10,ninth );
		statement.setString(11,tenth );
		statement.setString(12, eleventh);
		statement.setString(13, twelvth);
		statement.setString(14,thirteenth );
		statement.setString(15,fourteenth );
		statement.setString(16,fifteenth );	
		
		statement.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public void insertIntoTemporaryPrescription(int phone,String prescription,String bitCode) {
		
		try {
			statement=myConn.prepareStatement("insert into temporaryPrescription values (null, ?,?,?)");
			statement.setInt(1,phone);
			statement.setString(2, prescription);
			statement.setString(3, bitCode);
			
			statement.execute();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void updateTemporaryPrescription(int phone, String prescription) {

		try {
			statement = myConn.prepareStatement("update temporaryPrescription set prescription=? where phonefk=?");
			statement.setString(1, prescription);
			statement.setInt(2, phone);

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	public String getTemporaryPrescription(int phone) {
	
		String prescription = null;
		
		try {
			
			
			statement=myConn.prepareStatement("select prescription from temporaryPrescription where phonefk=?");
			statement.setInt(1, phone);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				prescription=resultSet.getString("prescription");
			
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return prescription;
	}
	

	public void insertIntoBankAnalysis(int total,int withdrawal,int bank,int net,int mb, String referenceNumber,String date) {
		
		try {
			statement=myConn.prepareStatement("insert into bankentryanalysis values (null,?,?,?,?,?,?,?) ");
			statement.setInt(1,total);
			statement.setInt(2,withdrawal);
			statement.setInt(3,mb);
			statement.setInt(4,bank);
			statement.setInt(5,net);
			statement.setString(6, referenceNumber);
			statement.setString(7, date);
			
			statement.execute();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

    public ObservableList<BankAnalysis> getBankAnalysis(){

    	
    	ObservableList<BankAnalysis> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select total,totalwithdrawal,mobileBanking,forbank,net,referenceNumber,entrydate from bankentryanalysis");
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add( new BankAnalysis(  resultSet.getString("total") ,resultSet.getString("totalwithdrawal"),resultSet.getString("mobileBanking") ,resultSet.getString("forbank"),resultSet.getString("net"),resultSet.getString("referenceNumber"), resultSet.getString("entrydate")  ) );
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    
    public ObservableList<BankAnalysis> getBankAnalysis(String date){

    	
    	ObservableList<BankAnalysis> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select total,totalwithdrawal,mobileBanking,forbank,net,referenceNumber,entrydate from bankentryanalysis where entrydate=?");
    		statement.setString(1, date);
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add( new BankAnalysis(  resultSet.getString("total") ,resultSet.getString("totalwithdrawal"),resultSet.getString("mobileBanking"), resultSet.getString("forbank"),resultSet.getString("net"),resultSet.getString("referenceNumber"), resultSet.getString("entrydate")  ) );
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }

    public ObservableList<BankAnalysis> getBankAnalysis(String startingDate,String endingDate){

    	
    	ObservableList<BankAnalysis> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select total,totalwithdrawal,mobileBanking,forbank,net,referenceNumber,entrydate from bankentryanalysis where (entrydate>=?) and (entrydate<=?)");
    		statement.setString(1, startingDate);
    		statement.setString(2, endingDate);
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add( new BankAnalysis(  resultSet.getString("total") ,resultSet.getString("totalwithdrawal"),resultSet.getString("mobileBanking"), resultSet.getString("forbank"),resultSet.getString("net"),resultSet.getString("referenceNumber"), resultSet.getString("entrydate")  ) );
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    
	public void checkBankEntry(char status,String date) {
		try {
			
		statement=myConn.prepareStatement("update bankentryanalysis set net=? where entryDate=? and id!=0");
		
		statement.setString(1, String.valueOf(status));
		statement.setString(2, date);
		
		statement.executeUpdate();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

	public void insertIntoSalaryHistory(String fullName,int amount,String startingDate,String endingDate) {
		
		try {
			statement=myConn.prepareStatement("insert into salaryHistory values (null, ?,?,?,?)");
			statement.setString(1,fullName);
			statement.setInt(2, amount);
			statement.setString(3, startingDate);
			statement.setString(4, endingDate);
			
			statement.execute();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	
    
    
    
    
    
 	public void addNewEmployee(int id,String fullName,String sex,int phone,
								int fixedSalary,String userName,
								int userPassword,String job) {
		
		try {
statement=myConn.prepareStatement("insert into employee values (?,?,?,?,?,?,?,?)");
	
	statement.setInt(1,id );		
	statement.setString(2,fullName);
	statement.setString(3,sex);
	statement.setInt(4,phone );		
	statement.setInt(5,fixedSalary );		
	statement.setString(6,userName);
	statement.setInt(7,userPassword );		
	statement.setString(8,job);

		statement.execute();
		
		
		
		
		} catch (SQLException e) {
			
			if(e.getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) {
				new CallAlert(AlertType.ERROR,"Error","ShalomDAO->addNewEmployee","This ID exists already\n\nChange the ID and try it again");
			}
			
			else {
				
				new CallAlert(AlertType.ERROR,"Error","Check : ShalomDAO->addNewEmployee","");
		
			}
		}
		
		
	}
	
 	public void deleteEmployee(int id) {

		try {
					statement=myConn.prepareStatement("delete from employee  where id=?");

			
					statement.setInt(1,id);
					

					statement.execute();
					


			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}


		}


 	
 	public void createAdminAccount(String userName,int userPassword,String job) {

try {
statement=myConn.prepareStatement("insert into employee values (null,null,null,null,null,?,?,?)");

	
statement.setString(1,userName);
statement.setInt(2,userPassword );		
statement.setString(3,job);

statement.execute();




} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}


}

 	
 	
	public void addNewPatient(int phone,String fullName,int age,char sex,
							String assesment,String treatment,
							String focusingArea,String history) {
		try {
statement=myConn.prepareStatement("insert into patient values (?,?,?,?,?,?,?,?)");
			
			statement.setInt(1,phone);		
			statement.setString(2,fullName);
			statement.setInt(3, age);
			statement.setString(4, String.valueOf(sex));
			statement.setString(5,assesment);
			statement.setString(6,treatment);
			statement.setString(7,focusingArea);
			statement.setString(8,history);
			
			
			statement.execute();
			
		
		}
		
		
		catch (SQLException e) {
			
			if(e.getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) {
				new CallAlert(AlertType.ERROR,"Error","ShalomDAO->addNewPatient","This phone number exists already\n\nChange the phone number and try it again");
			}
			
			else {
				
				new CallAlert(AlertType.ERROR,"Error","Check : ShalomDAO->addNewPatient","");
		
			}
		}
		
		
	}


	public void addInactivePatient(int phone,String fullName,int age,char sex,
							String assesment,String treatment,
							String focusingArea,String history) {
		try {
			statement=myConn.prepareStatement("insert into inactivepatient values (?,?,?,?,?,?,?,?)");
			
			statement.setInt(1,phone);		
			statement.setString(2,fullName);
			statement.setInt(3, age);
			statement.setString(4, String.valueOf(sex));
			statement.setString(5,assesment);
			statement.setString(6,treatment);
			statement.setString(7,focusingArea);
			statement.setString(8,history);
			
			
			statement.execute();
			
			statement=myConn.prepareStatement("delete from patient where phone =? ");
			statement.setInt(1,phone);
			statement.execute();
			
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void activatePatient(int phone) {
		

		try {
			statement=myConn.prepareStatement("select * from inactivepatient where phone=?");
			statement.setInt(1, phone);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
			 this.addNewPatient(
					resultSet.getInt("phone"),
					resultSet.getString("fullName"),
					resultSet.getInt("age"),
					resultSet.getString("sex").charAt(0),
					resultSet.getString("assesment"),
					resultSet.getString("treatment"),
					resultSet.getString("focusingArea"),
					resultSet.getString("patienthistory")
					
					);
				
		}

			statement=myConn.prepareStatement("delete from inactivepatient where phone=?");
			statement.setInt(1, phone);
			statement.execute();
			
			

	} catch (SQLException e) {
		
		e.printStackTrace();
	}

		
		
	}

	
	public void takeFirstAttendance(int phonefk,char tick,String columnName) {
		
try {
	
statement=myConn.prepareStatement("insert into attendance  (phonefk,"+columnName+") values (?,?)");
	statement.setInt(1, phonefk);
	statement.setString(2, String.valueOf(tick));
	
	statement.execute();
	
	
	
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		
	}
	
	
	
	public void takeNormalAttendance(int phonefk,char tick,String columnName) {
		
		
		try {
			
		statement=myConn.prepareStatement("update attendance set "+columnName+"=? where phonefk=?");
			statement.setString(1, String.valueOf(tick));
			statement.setInt(2, phonefk);
			
			statement.execute();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void acceptPayment(int phonefk,char paymentMethod,int price,String date,String referenceNumber) {
		

		try {
			//paymentMethods are M(for MobileBanking) and C(for Cash)
			//paymentTypes   are P(for payment)       and C(for Card)
			
			switch(paymentMethod) {
			case 'M':
				
					statement=myConn.prepareStatement("insert into payments(phonefk,paymentmb,paymentdate,referenceNumber) values (?,?,?,?)");
					statement.setInt(1,phonefk);
					statement.setInt(2, price);
					statement.setString(3, date);
					statement.setString(4, referenceNumber);
					statement.execute();
					 
				break;
				
			case 'C':
				
				
					statement=myConn.prepareStatement("insert into payments(phonefk,paymentCash,paymentdate,referenceNumber) values (?,?,?,?)");
					statement.setInt(1,phonefk);
					statement.setInt(2, price);
					statement.setString(3, date);
					statement.setString(4, referenceNumber);
					statement.execute();
					 break;
				
				
			}
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void recieveCardPayment(int phone,int amount,char paymentMethod,String date,String referenceNumber) {
		
		
		try {
			
			switch (paymentMethod) {
			
			case 'M':
				
				statement=myConn.prepareStatement("insert  into payments(phonefk,cardMb,paymentdate,referenceNumber) values (?,?,?,?);");
				statement.setInt(1, phone);
				statement.setInt(2, amount);
				statement.setString(3, date);
				statement.setString(4, referenceNumber);
			
				statement.execute();
				
				
				
				break;
				
			case 'C':
				
				statement=myConn.prepareStatement("insert  into payments(phonefk,cardcash,paymentdate) values (?,?,?);");
				statement.setInt(1, phone);
				statement.setInt(2, amount);
				statement.setString(3, date);
			
				statement.execute();
				
				
				
				break;
			
			
			
			
			
			}
			
			
			
			
			
			
			
			
			
			
			
			

	} catch (SQLException e) {
		
		e.printStackTrace();
	}

		
		
	}
	
	public void insertIntoReflexology(String massager,int price,char paymentMethod,String date,String type,int id,String referenceNumber) {
		
		try {
			
			switch(paymentMethod) {
			
			case 'C':
			
			statement=myConn.prepareStatement("insert into reflexology values (null,?,?,?,?,?,?,?)");
			
			statement.setString(1, massager);
			statement.setInt(2, price);
			statement.setString(3, String.valueOf(paymentMethod));
			statement.setString(4, date);
			statement.setString(5, type);
			statement.setInt(6, id);
			statement.setString(7, "");
			statement.executeUpdate();
			
			break;
			
			case 'M' :
				
				statement=myConn.prepareStatement("insert into reflexology values (null,?,?,?,?,?,?,?)");
				
				statement.setString(1, massager);
				statement.setInt(2, price);
				statement.setString(3, String.valueOf(paymentMethod));
				statement.setString(4, date);
				statement.setString(5, type);
				statement.setInt(6, id);
				statement.setString(7, referenceNumber);
				statement.executeUpdate();
				
				break;
		
			}
		
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertIntoWithdrawal(String fullName,int amount,String reason,String date) {
		
		//don't forget to delete the fullName column later
		//its better to insert the name rather than using join in the database for this scenario
		try {
		statement=myConn.prepareStatement("insert into withdrawal values(null,?,?,?,?) ");
		statement.setString(1, fullName);
		statement.setInt(2,amount);
		statement.setString(3, reason);
		statement.setString(4,date);
		
		statement.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void addComboboxChoices(String name,char category,int price,int commision) {

try {
			statement=myConn.prepareStatement("insert into COMBOBOXCHOICES values (null,?,?,?,?)");

	
			statement.setString(1,name);
			statement.setString(2,String.valueOf(category) );
			statement.setInt(3, price);
			statement.setInt(4, commision);

			statement.execute();

			


	} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}


}
	
	public void updateComboboxChoicesPrice(String name,char category,int price) {

		try {
					statement=myConn.prepareStatement("update comboboxchoices set price=? where (id>=0) and (fullName=?) and (category =?)");

			
					statement.setInt(1, price);
					statement.setString(2,name);
					statement.setString(3,String.valueOf(category) );

					statement.execute();

					


			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}


		}

	public void updateComboboxChoicesCommission(String name,char category,int commission) {


		try {
					statement=myConn.prepareStatement("update comboboxchoices set commission=? where (id>=0) and (fullName=?) and (category =?)");

			
					statement.setInt(1, commission);
					statement.setString(2,name);
					statement.setString(3,String.valueOf(category) );

					statement.execute();

					


			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public void updateComboboxChoicesFullName(String oldName,char category,String newName) {


		try {
					statement=myConn.prepareStatement("update comboboxchoices set fullName=? where (id>=0) and (fullName=?) and (category =?)");

			
					statement.setString(1, newName);
					statement.setString(2,oldName);
					statement.setString(3,String.valueOf(category) );

					statement.execute();

					


			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public void deleteComboboxChoices(String name,char category) {

		try {
					statement=myConn.prepareStatement("delete from COMBOBOXCHOICES  where (id>=1) and ((fullName=?) and (category=?))");

			
					statement.setString(1,name);
					statement.setString(2,String.valueOf(category));
					

					statement.execute();
					//make the name column unique 
					//don't forget to catch the exception if unique constraint is not obeyed



			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}


		}


	
	
	public void deletePrescriptionAndAttendance(int phone) {

		try {
					statement=myConn.prepareStatement("delete from prescriptions  where phonefk=?");
					statement.setInt(1,phone);
					statement.execute();
					
					statement=myConn.prepareStatement("delete from attendance  where phonefk=?");
					statement.setInt(1,phone);
					statement.execute();
					
					
					


			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}


		}
	
	public void updatePrescription(int phonefk,String firsta,String seconda,String third,String fourth,
			String fifth,String sixth,String seventh,String eighth,String ninth,String tenth,
			String eleventh,String twelvth,String thirteenth,String fourteenth,String fifteenth) {
		
		try {
			statement=myConn.prepareStatement("update prescriptions set firstp=?, secondp=?, third=?, fourth=?, fifth=?, sixth=?, seventh=?, eighth=?, ninth=?, tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?");
			
			statement.setString(1,firsta );
			statement.setString(2, seconda);
			statement.setString(3, third);
			statement.setString(4, fourth);
			statement.setString(5, fifth);
			statement.setString(6, sixth);
			statement.setString(7, seventh);
			statement.setString(8, eighth);
			statement.setString(9, ninth);
			statement.setString(10, tenth);
			statement.setString(11, eleventh);
			statement.setString(12,twelvth );
			statement.setString(13,thirteenth );
			statement.setString(14,fourteenth );
			statement.setString(15,fifteenth );
			statement.setInt(16,phonefk );
		
			statement.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void updatePrescription(int phonefk,String columnName,String prescription) {
		
		try {
			statement=myConn.prepareStatement("update prescriptions set "+columnName+"=? where phonefk=?");
			
			statement.setString(1,prescription );
			statement.setInt(2,phonefk );
		
			statement.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
    public void updateEmployee(int id,String fullName,String sex,int phone,int fixedSalary,
    							String userName,int userPassword,String job) {
    	
    	try {
	statement=myConn.prepareStatement("update employee set fullName=?,sex=?,phone=?,fixedSalary=?,userName=?,userPassword=?,job=? where id=?");
	
		statement.setInt(1,id );
		statement.setInt(4,phone );
		statement.setInt(5,fixedSalary );
		statement.setInt(7,userPassword );
		statement.setString(2,fullName);
		statement.setString(3,sex);
		statement.setString(6,userName);
		statement.setString(7,job);
		
		statement.executeUpdate();
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	} 
	
    public void updateEmployeeStringValues(int id,String value,String columnName) {

    	String query="update employee set  "+columnName;
    	
    	try {
    		statement=myConn.prepareStatement(query  + "=? where id=?");

    		statement.setString(1,value );
    		statement.setInt(2,id );
    		statement.executeUpdate();

    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}


    } 

    public void updateEmployeeIntegerValues(int id,int value,String columnName) {

    	String query="update employee set  "+columnName;
    	
    	try {
    		statement=myConn.prepareStatement(query  + "=? where id=?");

    		statement.setInt(1,value );
    		statement.setInt(2,id );
    		statement.executeUpdate();

    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}


    } 
    
    
    public void fulfilPatientInfo(int phone,String assesment,String treatment,
							String focusingArea,String history) {
    	
    	try {
			statement=myConn.prepareStatement("update patient set  assesment=?, treatment=?, focusingArea=?,patienthistory=? where phone=?");
			
			statement.setString(1, assesment);
			statement.setString(2, treatment);
			statement.setString(3, focusingArea);
			statement.setString(4, history);
			statement.setInt(5, phone);
			
			statement.executeUpdate();
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
    public void updatePatient(int phone,String fullName,int age,String sex,
			String assesment,String treatment,
			String focusingArea,String history) {

try {
statement=myConn.prepareStatement("update patient set fullName=?, age=?, sex=?, assesment=?, treatment=?, focusingArea=?,patienthistory=? where phone=?");
statement.setInt(2, age);
statement.setString(1, fullName);
statement.setString(3, sex);
statement.setString(4, assesment);
statement.setString(5, treatment);
statement.setString(6, focusingArea);
statement.setString(7, history);
statement.setInt(8, phone);
statement.executeUpdate();

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
    
    
    
    

    public int getDailyReflexologyCount(String date,String type) {
    	
int income=0;
    	
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (paymentdate=?) and (reflexologyType=?)");
			statement.setString(1, date);
			statement.setString(2, type);

			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				income=resultSet.getInt("count(price)");
			}
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    return income;
    	
    }


    public int getMonthlyReflexologyCount(String startingDate,String endingDate,String type) {
    	
     	
    	int income=0;
    	    	
    	    	try {
    				statement=myConn.prepareStatement("select count(price) from reflexology where (paymentdate>=?)and (paymentdate<=?) and (reflexologyType=?)");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				statement.setString(3, type);

    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					income=resultSet.getInt("count(price)");
    				}
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return income;
    	    	
    }
	

    public int getMonthlyReflexologyCount(String startingDate,String endingDate,String type,int id) {
    	
     	
    	int income=0;
    	    	
    	    	try {
    				statement=myConn.prepareStatement("select count(price) from reflexology where (paymentdate>=?)and (paymentdate<=?) and (reflexologyType=?)and(idfk=?)");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				statement.setString(3, type);
    				statement.setInt(4, id);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					income=resultSet.getInt("count(price)");
    				}
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return income;
    	    	
    }
	
    
    
    
    
    
    
    public int getDailyCard(String date) {
    	int income=0;
    	
    	try {
			statement=myConn.prepareStatement("select sum(cardmb ) + sum(cardcash) from payments where paymentdate=?");
			statement.setString(1, date);
    
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				income=resultSet.getInt("sum(cardmb ) + sum(cardcash)");
			}
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    return income;
    }

    public int getDailyReflexology(String date) {
    	
int income=0;
    	
    	try {
			statement=myConn.prepareStatement("select sum(price) from reflexology where paymentdate=?");
			statement.setString(1, date);
    
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				income=resultSet.getInt("sum(price)");
			}
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    return income;
    	
    }

    public int getDailyPatient(String date) {
    	
int income=0;
    	
    	try {
			statement=myConn.prepareStatement("select sum(paymentmb) + sum(paymentcash) from payments where paymentDate=?");
			statement.setString(1, date);
    
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				income=resultSet.getInt("sum(paymentmb) + sum(paymentcash)");
			}
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    return income;
    	
    }

    public int getDailyWithdrawal(String date) {
    	
     	
    	int income=0;
    	    	
    	    	try {
    				statement=myConn.prepareStatement("select sum(amount) from withdrawal where withdrawaldate=?");
    				statement.setString(1, date);
    	    
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					income=resultSet.getInt("sum(amount)");
    				}
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return income;
    	    	
    }
	
    public int getDailyTotalMB(String date) {

    	int fromReflexology=0;
    	int fromPatient=0;
    	int total=0;
    	    	
    	    	try {
    	    		
    				statement=myConn.prepareStatement("select sum(price) from reflexology where (paymentdate=?) and (paymentMethod='M')");
    				statement.setString(1, date);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					fromReflexology=resultSet.getInt("sum(price)");
    				}
    				
    				
    				statement=myConn.prepareStatement("select sum(cardmb) + sum(paymentmb) from payments where paymentdate=?");
    				statement.setString(1, date);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					fromPatient=resultSet.getInt("sum(cardmb) + sum(paymentmb)");
    				}
    				
    				total=fromPatient + fromReflexology;
    				
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return total;
    	    	
    }

    public int getDailyTotalCash(String date) {

    	int fromReflexology=0;
    	int fromPatient=0;
    	int total=0;
    	    	
    	    	try {
    	    		
    				statement=myConn.prepareStatement("select sum(price) from reflexology where (paymentdate=?) and (paymentMethod='C')");
    				statement.setString(1, date);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					fromReflexology=resultSet.getInt("sum(price)");
    				}
    				
    				
    				statement=myConn.prepareStatement("select sum(cardcash) + sum(paymentcash) from payments where paymentdate=?");
    				statement.setString(1, date);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					fromPatient=resultSet.getInt("sum(cardcash) + sum(paymentcash)");
    				}
    				
    				total=fromPatient + fromReflexology;
    				
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return total;
    	    	
    	
    }
	
    public int getDailyTotalIncome(String date) {

    	int total=  this.getDailyTotalCash(date)+this.getDailyTotalMB(date);
    	    
    	    return total;
    	    	
    	
    }

    public int getDailyNetIncome(String date) {

    	int total=  this.getDailyTotalIncome(date)-this.getDailyWithdrawal(date);
    	    
    	    return total;
    	    	
    	
    }
    
    
    public int getMonthlyCard(String startingDate,String endingDate) { 
int income=0;
    	
    	try {
			statement=myConn.prepareStatement("select sum(cardmb ) + sum(cardcash) from payments where (paymentdate>=?)and (paymentdate<=?)");
			statement.setString(1, startingDate);
			statement.setString(2, endingDate);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				income=resultSet.getInt("sum(cardmb ) + sum(cardcash)");
			}
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    return income;
    }

    public int getMonthlyReflexology(String startingDate,String endingDate) {
    	
     	
    	int income=0;
    	    	
    	    	try {
    				statement=myConn.prepareStatement("select sum(price) from reflexology where (paymentdate>=?)and (paymentdate<=?)");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					income=resultSet.getInt("sum(price)");
    				}
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return income;
    	    	
    }
	
    public int getMonthlyPatient(String startingDate,String endingDate) {
    	
    	int income=0;
    	    	
    	    	try {
    				statement=myConn.prepareStatement("select sum(paymentmb) + sum(paymentcash) from payments where (paymentdate>=?)and (paymentdate<=?)");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					income=resultSet.getInt("sum(paymentmb) + sum(paymentcash)");
    				}
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return income;
    	
    }
	
    public int getMonthlyWithdrawal(String startingDate,String endingDate) {
    	

     	
    	int income=0;
    	    	
    	    	try {
    				statement=myConn.prepareStatement("select sum(amount) from withdrawal where (withdrawaldate>=?)and (withdrawaldate<=?)");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					income=resultSet.getInt("sum(amount)");
    				}
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return income;
    	    	
    }
	

    public int getMonthlySalaryHistory(String startingDate,String endingDate) {
    	

     	
    	int amount=0;
    	    	
    	    	try {
    				statement=myConn.prepareStatement("select sum(amount) from salaryHistory where (startingdate>=?)and (endingdate<=?)");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					amount=resultSet.getInt("sum(amount)");
    				}
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return amount;
    	    	
    }
	
    
    public int getMonthlyTotalMB(String startingDate,String endingDate) {

    	int fromReflexology=0;
    	int fromPatient=0;
    	int total=0;
    	    	
    	    	try {
    	    		
    				statement=myConn.prepareStatement("select sum(price) from reflexology where ((paymentdate>=?)and (paymentdate<=?)) and (paymentMethod='M')");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					fromReflexology=resultSet.getInt("sum(price)");
    				}
    				
    				
    				statement=myConn.prepareStatement("select sum(cardmb) + sum(paymentmb) from payments where (paymentdate>=?)and (paymentdate<=?)");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					fromPatient=resultSet.getInt("sum(cardmb) + sum(paymentmb)");
    				}
    				
    				total=fromPatient + fromReflexology;
    				
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return total;
    	    	
    }

    public int getMonthlyTotalCash(String startingDate,String endingDate) {

    	int fromReflexology=0;
    	int fromPatient=0;
    	int total=0;
    	    	
    	    	try {
    	    		
    				statement=myConn.prepareStatement("select sum(price) from reflexology where ((paymentdate>=?)and (paymentdate<=?)) and (paymentMethod='C')");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					fromReflexology=resultSet.getInt("sum(price)");
    				}
    				
    				
    				statement=myConn.prepareStatement("select sum(cardcash) + sum(paymentcash) from payments where (paymentdate>=?)and (paymentdate<=?)");
    				statement.setString(1, startingDate);
    				statement.setString(2, endingDate);
    				resultSet=statement.executeQuery();
    				while(resultSet.next()) {
    					fromPatient=resultSet.getInt("sum(cardcash) + sum(paymentcash)");
    				}
    				
    				total=fromPatient + fromReflexology;
    				
    	    	
    	    	
    	    	} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    	    return total;
    	    	
    	
    }

    public int getMonthlyTotalIncome(String startingDate,String endingDate) {

    	int total=  this.getMonthlyTotalCash(startingDate, endingDate)+this.getMonthlyTotalMB(startingDate, endingDate);
    	    
    	    return total;
    	    	
    	
    }
    
    public int getMonthlyNetIncome(String startingDate,String endingDate) {

    	int total=  this.getMonthlyTotalIncome(startingDate, endingDate)-(this.getMonthlyWithdrawal(startingDate, endingDate)+this.getMonthlySalaryHistory(startingDate, endingDate)  );
    	    
    	    return total;
    	    	
    	
    }
    
	
    public int howManyTherapies(String date) {
    	
    	String string="th%"+date;
    	int customers=0;
    	try {
statement=myConn.prepareStatement("select count(*) from prescriptions where (firstp like ?)or(secondp like ?)or(third like ?)or (fourth like ?)or(fifth like ?)or(sixth like ?)or(seventh like ?)or(eighth like ?)or(ninth like ?)or(tenth like ?)or(eleventh like ?)or(twelvth like ?)or (thirteenth like ?)or(fourteenth like ?)or(fifteenth like ?)");
		
		statement.setString(1, string);
		statement.setString(2, string);
		statement.setString(3, string);
		statement.setString(4, string);
		statement.setString(5, string);
		statement.setString(6, string);
		statement.setString(7, string);
		statement.setString(8, string);
		statement.setString(9, string);
		statement.setString(10, string);
		statement.setString(11, string);
		statement.setString(12, string);
		statement.setString(13, string);
		statement.setString(14, string);
		statement.setString(15, string);
		
		resultSet=statement.executeQuery();
		while (resultSet.next()) {
			customers=resultSet.getInt("count(*)");
		}
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return customers;
    }

    public int howManyMassages(String date) {
    	

    	String string="ma%"+date;
    	int customers=0;
    	try {
statement=myConn.prepareStatement("select count(*) from prescriptions where (firstp like ?)or(secondp like ?)or(third like ?)or (fourth like ?)or(fifth like ?)or(sixth like ?)or(seventh like ?)or(eighth like ?)or(ninth like ?)or(tenth like ?)or(eleventh like ?)or(twelvth like ?)or (thirteenth like ?)or(fourteenth like ?)or(fifteenth like ?)");
		
		statement.setString(1, string);
		statement.setString(2, string);
		statement.setString(3, string);
		statement.setString(4, string);
		statement.setString(5, string);
		statement.setString(6, string);
		statement.setString(7, string);
		statement.setString(8, string);
		statement.setString(9, string);
		statement.setString(10, string);
		statement.setString(11, string);
		statement.setString(12, string);
		statement.setString(13, string);
		statement.setString(14, string);
		statement.setString(15, string);
		
		resultSet=statement.executeQuery();
		while (resultSet.next()) {
			customers=resultSet.getInt("count(*)");
		}
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return customers;
    	
    }

    public int howManyReflexology(String date) {

    	String string="rx%"+date;
    	int customers=0;
    	try {
statement=myConn.prepareStatement("select count(*) from prescriptions where (firstp like ?)or(secondp like ?)or(third like ?)or (fourth like ?)or(fifth like ?)or(sixth like ?)or(seventh like ?)or(eighth like ?)or(ninth like ?)or(tenth like ?)or(eleventh like ?)or(twelvth like ?)or (thirteenth like ?)or(fourteenth like ?)or(fifteenth like ?)");
		
		statement.setString(1, string);
		statement.setString(2, string);
		statement.setString(3, string);
		statement.setString(4, string);
		statement.setString(5, string);
		statement.setString(6, string);
		statement.setString(7, string);
		statement.setString(8, string);
		statement.setString(9, string);
		statement.setString(10, string);
		statement.setString(11, string);
		statement.setString(12, string);
		statement.setString(13, string);
		statement.setString(14, string);
		statement.setString(15, string);
		
		resultSet=statement.executeQuery();
		while (resultSet.next()) {
			customers=resultSet.getInt("count(*)");
		}
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return customers;
    }
	

    public int getTotalPayment(int phonefk)  {
    	List<String> prescriptions = new ArrayList<>();
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select firstp,secondp,third,fourth,fifth,sixth,seventh,eighth,ninth,tenth,eleventh,twelvth,thirteenth,fourteenth,fifteenth from prescriptions where phonefk=?");
			statement.setInt(1, phonefk);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				prescriptions.add( resultSet.getString("firstp"));
				prescriptions.add (resultSet.getString("secondp"));
				prescriptions.add (resultSet.getString("third"));
				prescriptions.add ( resultSet.getString("fourth"));
				prescriptions.add ( resultSet.getString("fifth"));
				prescriptions.add ( resultSet.getString("sixth"));
				prescriptions.add ( resultSet.getString("seventh"));
				prescriptions.add ( resultSet.getString("eighth"));
				prescriptions.add ( resultSet.getString("ninth"));
				prescriptions.add ( resultSet.getString("tenth"));
				prescriptions.add ( resultSet.getString("eleventh"));
				prescriptions.add ( resultSet.getString("twelvth"));
				prescriptions.add ( resultSet.getString("thirteenth"));
				prescriptions.add ( resultSet.getString("fourteenth"));
				prescriptions.add (resultSet.getString("fifteenth"));
			}
			
			
			for(String p:prescriptions) {
				if(p.isEmpty()) {
					continue;
				}
				//=========Therapy=================
				if(p.substring(0,4).equals("Th10")) {
					total+=th10;
				}
				
				if(p.substring(0,4).equals("Th12")) {
					total+=th12;
				}
				if(p.substring(0,4).equals("Th15")) {
					total+=th15;
				}if(p.substring(0,4).equals("Th7")) {
					total+=th17;
				}
				if(p.substring(0,4).equals("Th20")) {
					total+=th20;
				}
				if(p.substring(0,4).equals("Th25")) {
					total+=th25;
				}
				
				//==========Massage=====================
				if(p.substring(0,4).equals("Ma10")) {
					total+=ma10;
				}
				if(p.substring(0,4).equals("Ma12")) {
					total+=ma12;
				}
				if(p.substring(0,4).equals("Ma15")) {
					total+=ma15;
				}
				if(p.substring(0,4).equals("Ma17")) {
					total+=ma17;
				}
				if(p.substring(0,4).equals("Ma20")) {
					total+=ma20;
				}if(p.substring(0,4).equals("Ma25")) {
					total+=ma25;
				}
				
				//==========Reflexology==============
				if(p.substring(0,4).equals("Rx45")) {
					total+=this.getPrice("45 MIN", 'R');
				}
				if(p.substring(0,4).equals("Rx60")) {
					total+=this.getPrice("60 MIN", 'R');
				}
				
				
				
				
				
			}
    	
    	
    	
    	}
    	catch (StringIndexOutOfBoundsException siobe){
    		//this exception is going to be thrown if the p.sub(0,4) is out of bound
    		//for example let say prescriptions after tenth are null ( no prescription)
    		//during this time this kind of exceptions will be thrown 
    		//but if all (15) are not null(15 days prescriptions are exist  ) , it won't 
    		//throw exception
    		
   
    	}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return total;
    }
    
    
    public int getTotalPaymentDynamic(int phonefk)  {
    	List<String> prescriptions = new ArrayList<>();
    	String temporaryPrescription=null;
    	int total=0;
    	try {
    		statement=myConn.prepareStatement("select prescription from temporaryprescription where phonefk=?");
			statement.setInt(1, phonefk);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				temporaryPrescription=resultSet.getString("prescription");
		
			}
			
			
			ObservableList<String> list= this.getPrescriptionItems();
			temporaryPrescription=temporaryPrescription+".";
			
			
			for (int i = 0; i < 60; i = i + 4) {
				
				prescriptions.add(temporaryPrescription.substring(i,i+4));
			}
			
			for(String p:prescriptions) {
				if(p.isEmpty()) {
					continue;
				}
			
				
				for(int i=0;i<list.size();i++) {
				
				if(p.equals(list.get(i))) {
					
					total=total+this.getPrice(list.get(i), 'P');
					
				}
				else {
					
				}
				
				
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
				
				
				
				
				
			
    	
    	
    	
    	}
    	catch (StringIndexOutOfBoundsException siobe){
    		//this exception is going to be thrown if the p.sub(0,4) is out of bound
    		//for example let say prescriptions after tenth are null ( no prescription)
    		//during this time this kind of exceptions will be thrown 
    		//but if all (15) are not null(15 days prescriptions are exist  ) , it won't 
    		//throw exception
    		
    		siobe.printStackTrace();
    	}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return total;
    }
    
    
    public int getTotalPaymentFromTemporaryPrescription(int phonefk)  {
    	
    	String prescription = null;
    	
    	int total=0;
    	
    	try {
			statement=myConn.prepareStatement("select prescription from temporaryprescription where phonefk=?");
			statement.setInt(1, phonefk);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				prescription=resultSet.getString("prescription");
		
			}
			
		String p=null;
		prescription=prescription+".";
			
			for(int i=0;i<120;i=i+4) {
				
				p=prescription.substring(i,i+4);
				
				//=========Therapy=================
				if(p.equals("Th10")) {
					total+=this.getPrice("Th10", 'P');
				}
				
				if(p.equals("Th12")) {
					total+=this.getPrice("Th12", 'P');
				}
				if(p.equals("Th15")) {
					total+=this.getPrice("Th15", 'P');
				}
				if(p.equals("Th20")) {
					total+=this.getPrice("Th20", 'P');
				}
				if(p.equals("Th25")) {
					total+=this.getPrice("Th25", 'P');
				}
				
				//==========Massage=====================
				if(p.equals("Ma10")) {
					total+=this.getPrice("Ma10", 'P');
				}
				if(p.equals("Ma12")) {
					total+=this.getPrice("Ma12", 'P');
				}
				if(p.equals("Ma15")) {
					total+=this.getPrice("Ma15", 'P');
				}
				if(p.equals("Ma17")) {
					total+=this.getPrice("Ma17", 'P');
				}
				
				//==========Reflexology==============
				if(p.equals("Rx45")) {
					total+=this.getPrice("Rx45", 'P');
				}
				if(p.equals("Rx60")) {
					total+=this.getPrice("Rx60", 'P');
				}
				
				
			}
				
				
			
    	
    	
    	
    	}
    	
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return total;
    }

    public int getTotalPaid(int phonefk) {
    	List<Integer> payment= new ArrayList<>();
    	int total = 0;
    	try {
			statement=myConn.prepareStatement("select cardMb,cardCash,paymentMb,paymentCash from payments where phonefk=?");
			statement.setInt(1, phonefk);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				payment.add(resultSet.getInt("paymentMb"));
				payment.add(resultSet.getInt("paymentCash"));
			}
    	
			for (Integer Int:payment) {
				if(Int==0)
				{continue;}
				
				total+=Int;
			}
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return total;
    }
	

    public int getTotalPaidForCurrentPatient(int phonefk) {
    	List<Integer> payment= new ArrayList<>();
    	int total = 0;
    	try {
			statement=myConn.prepareStatement("select cardMb,cardCash,paymentMb,paymentCash from payments where phonefk=?");
			statement.setInt(1, phonefk);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				payment.add(resultSet.getInt("paymentMb"));
				payment.add(resultSet.getInt("paymentCash"));
			}
    	
			for (Integer Int:payment) {
				if(Int==0)
				{continue;}
				
				total+=Int;
			}
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return total;
    }
	
    
    public int getCredit(int phonefk) {
    	
    	int total=this.getTotalPaymentDynamic(phonefk) - this.getTotalPaid(phonefk);
    	return total;
    }
    
public int getCreditFromTemporaryPrescription(int phonefk) {
    	
    	int total=this.getTotalPaymentFromTemporaryPrescription(phonefk) - this.getTotalPaid(phonefk);
    	return total;
    }
    

    public int getCreditForCurrentPatients(int phonefk) {
    	
    	int total=this.getTotalPayment(phonefk) - this.getTotalPaidForCurrentPatient(phonefk);
    	return total;
    }
    
 
    public int paySalary30Min(String startingDate,String endingDate,int id) {
    	
    	int total=0;
    	try {
    		
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "30 MIN");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx30Commision;
    	
    	return total;
    	
    }
    
    public int paySalary45Min(String startingDate,String endingDate,int id) {
    	
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "45 MIN");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx45Commision;
    	return total;
    	
    }
    
    public int paySalary60Min(String startingDate,String endingDate,int id) {
    	
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "60 MIN");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx60Commision;
    	return total;
    	
    }
    
    public int paySalary75Min(String startingDate,String endingDate,int id) {
    	
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "75 MIN");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx75Commision;
    	return total;
    	
    }
    
    public int paySalary90Min(String startingDate,String endingDate,int id) {
    	
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "90 MIN");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx90Commision;
    	return total;
    	
    }
    
    public int paySalaryMa10(String startingDate,String endingDate,int id) {
    	
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "Ma10");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx90Commision;
    	return total;
    	
    }

    public int paySalaryMa12(String startingDate,String endingDate,int id) {
    	
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "Ma12");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx90Commision;
    	return total;
    	
    }
    
    public int paySalaryMa15(String startingDate,String endingDate,int id) {

    	
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "Ma15");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx90Commision;
    	return total;
    	
    }

    public int paySalaryMa17(String startingDate,String endingDate,int id) {
    	
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "Ma17");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx90Commision;
    	return total;
    	
    }

    public int paySalaryMa20(String startingDate,String endingDate,int id) {
    	
    	int total=0;
    	try {
			statement=myConn.prepareStatement("select count(price) from reflexology where (idfk=? )and ((paymentDate >=?) and (paymentDate <=?)) and (reflexologyType=?)");
			statement.setInt(1, id);
			statement.setString(2, startingDate);
			statement.setString(3, endingDate);
			statement.setString(4, "Ma20");
    	
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				total=resultSet.getInt("count(price)");
				
			}
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//total+=rx90Commision;
    	return total;
    	
    }
       
    public int getCommission(String fullName){
    	
    			int commission=0;
    	try {
			statement=myConn.prepareStatement("select commission from comboboxchoices where (fullName=?) and (category ='R') ");
			statement.setString(1, fullName);
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				
				commission=resultSet.getInt("commission");
					
			}
    	
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return commission;
    	
    }
    
    
    public int getPrice(String name, char category) {
    
    int price=0;
	try {
		statement=myConn.prepareStatement("  select price from comboboxchoices where (fullName=?) &&  (category=?)");
		statement.setString(1, name);
		statement.setString(2, String.valueOf(category));
	
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			
			price=resultSet.getInt("price");
			
		}
		
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return price;
    }

    public int getMassagerId(String name) {
        
        int id=0;
    	try {
    		statement=myConn.prepareStatement(" select id from employee where fullName=? and job=?");
    		statement.setString(1, name);
    		statement.setString(2, "Massager");
    	
    		resultSet=statement.executeQuery();
    		while(resultSet.next()) {
    			
    			id=resultSet.getInt("id");
    			
    		}
    		
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return id;
        }
    
    
    
    
    
    public ObservableList<Employee> getAllEmployee(){ 
    	
    	
    	ObservableList<Employee> list= FXCollections.observableArrayList();
    	try {
			statement=myConn.prepareStatement("select * from employee");
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				list.add(new Employee(
						String.valueOf(resultSet.getInt("id")),
						resultSet.getString("fullName"),
						resultSet.getString("sex"),
						String.valueOf(resultSet.getInt("phone")),
						String.valueOf(resultSet.getInt("fixedSalary")),
						resultSet.getString("userName"),
						String.valueOf(resultSet.getInt("userPassoword")),
						resultSet.getString("job")
						));
				
			}
    	
    	
    	} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	return list;
    }
    
    public ObservableList<Employee> searchEmployeeByName(String name){ 
    	
    	String fullName=name+"%";
    	ObservableList<Employee> list= FXCollections.observableArrayList();
    	try {
			statement=myConn.prepareStatement("select * from employee where fullName like ?");
			statement.setString(1, fullName);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				list.add(new Employee(
						String.valueOf(resultSet.getInt("id")),
						resultSet.getString("fullName"),
						resultSet.getString("sex"),
						String.valueOf(resultSet.getInt("phone")),
						String.valueOf(resultSet.getInt("fixedSalary")),
						resultSet.getString("userName"),
						String.valueOf(resultSet.getInt("userpassoword")),
						resultSet.getString("job")
						));
				
			}
    	
    	
    	} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	return list;
    }
    
    public ObservableList<Employee> searchEmployeeById(String id){ 
    	
//    	String Id=id+"%";
    	ObservableList<Employee> list= FXCollections.observableArrayList();
//    	try {
//			statement=myConn.prepareStatement("select * from employee where id like ?");
//			statement.setString(1, Id);
//			resultSet=statement.executeQuery();
//			while(resultSet.next()) {
//				
//				list.add(new Employee(
//						String.valueOf(resultSet.getInt("id")),
//						resultSet.getString("fullName"),
//						resultSet.getString("sex"),
//						String.valueOf(resultSet.getInt("phone")),
//						String.valueOf(resultSet.getInt("fixedSalary")),
//						resultSet.getString("userName"),
//						String.valueOf(resultSet.getInt("userpassoword")),
//						resultSet.getString("job")
//						));
//				
//			}
//    	
//    	
//    	} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//    	
//    	
  	return list;
    }
	
    public ObservableList<Patient> searchPatientsByPhone(int phone){

    		ObservableList<Patient> list= FXCollections.observableArrayList();
    	try {
			statement=myConn.prepareStatement("select * from patient where phone like ?");
			statement.setString(1, String.valueOf(phone+"%"));
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				list.add( new Patient(
						resultSet.getInt("phone"),
						resultSet.getString("fullName"),
						resultSet.getInt("age"),
						resultSet.getString("sex"),
						resultSet.getString("assesment"),
						resultSet.getString("treatment"),
						resultSet.getString("focusingArea"),
						resultSet.getString("patientHistory")
						
						));
				
				
				
			}
    	
    	
    	} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	return list;
    }

	public ObservableList<Patient> searchPatientByPhone(int phone) {

		ObservableList<Patient> list = FXCollections.observableArrayList();
		try {
			statement = myConn.prepareStatement("select * from patient where phone=?");
			statement.setInt(1, phone);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(new Patient(resultSet.getInt("phone"), resultSet.getString("fullName"),
						resultSet.getInt("age"), resultSet.getString("sex"), resultSet.getString("assesment"),
						resultSet.getString("treatment"), resultSet.getString("focusingArea"),
						resultSet.getString("patientHistory")

				));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}

    public ObservableList<Patient> searchInactivePatientsByPhone(int phone){

    		ObservableList<Patient> list= FXCollections.observableArrayList();
    	try {
			statement=myConn.prepareStatement("select * from inactivepatient where phone like ?");
			statement.setString(1, String.valueOf(phone+"%"));
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				list.add( new Patient(
						resultSet.getInt("phone"),
						resultSet.getString("fullName"),
						resultSet.getInt("age"),
						resultSet.getString("sex"),
						resultSet.getString("assesment"),
						resultSet.getString("treatment"),
						resultSet.getString("focusingArea"),
						resultSet.getString("patientHistory")
						
						));
				
				
				
			}
    	
    	
    	} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	return list;
    }

    
    public ObservableList<Patient> getAllInactivePatients(){

		ObservableList<Patient> list= FXCollections.observableArrayList();
	try {
		statement=myConn.prepareStatement("select * from inactivepatient ");
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			list.add( new Patient(
					resultSet.getInt("phone"),
					resultSet.getString("fullName"),
					resultSet.getInt("age"),
					resultSet.getString("sex"),
					resultSet.getString("assesment"),
					resultSet.getString("treatment"),
					resultSet.getString("focusingArea"),
					resultSet.getString("patientHistory")
					
					));
			
			
			
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return list;
}
    
    
    public ObservableList<Patient> searchPatientsNameByPhone(int phone){

		ObservableList<Patient> list= FXCollections.observableArrayList();
	try {
		statement=myConn.prepareStatement("select fullName from patient where phone like ?");
		statement.setString(1, String.valueOf(phone+"%"));
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			list.add(new Patient(0,resultSet.getString("fullName"),0,"","","","",""));
			
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return list;
}


    public ObservableList<Patient> getAllPatientsNameOnly(){

		ObservableList<Patient> list= FXCollections.observableArrayList();
	try {
		statement=myConn.prepareStatement("select fullName from patient ");
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			list.add(new Patient(0,resultSet.getString("fullName"),0,"","","","",""));
			
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return list;
}


    public String getPatientsNameByPhone(int phone){

		String name= "";
	try {
		statement=myConn.prepareStatement("select fullName from patient where phone=? ");
		statement.setInt(1, phone);
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			name=resultSet.getString("fullName");
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return name;
}
    
    public ObservableList<Patient> getAllPatients(){

		ObservableList<Patient> list= FXCollections.observableArrayList();
	try {
		statement=myConn.prepareStatement("select * from patient ");
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			list.add( new Patient(
					resultSet.getInt("phone"),
					resultSet.getString("fullName"),
					resultSet.getInt("age"),
					resultSet.getString("sex"),
					resultSet.getString("assesment"),
					resultSet.getString("treatment"),
					resultSet.getString("focusingArea"),
					resultSet.getString("patientHistory")
					
					));
			
			
			
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return list;
}
    


    public ObservableList<String> getAllEmployeeName(){
		ObservableList<String> list= FXCollections.observableArrayList();

    	
	try {
		statement=myConn.prepareStatement("select fullName from employee");
		

		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			list.add(resultSet.getString("fullName"));
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return list;
}
    

    public String getEmployeeNameById(int id){

    	String name=null;
	try {
		statement=myConn.prepareStatement("select fullName from employee where (id=?)");
		statement.setInt(1, id);

		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			name=resultSet.getString("fullName");
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return name;
}
    
    
    
	
    public Patient getPatientByPhone(int phone){
    	
    	Patient p = null;
	
	try {
		statement=myConn.prepareStatement("select * from patient where phone=?");
		statement.setInt(1, phone);
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
		 p= new Patient(
				resultSet.getInt("phone"),
				resultSet.getString("fullName"),
				resultSet.getInt("age"),
				resultSet.getString("sex"),
				resultSet.getString("assesment"),
				resultSet.getString("treatment"),
				resultSet.getString("focusingArea"),
				resultSet.getString("patientHistory")
				
				);
		
		
		
	}


} catch (SQLException e) {
	
	e.printStackTrace();
}


return p;
}

    public ObservableList<Attendance> getAllAttendance() {
    

		ObservableList<Attendance> list= FXCollections.observableArrayList();
	try {
		statement=myConn.prepareStatement("select * from attendance ");
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			list.add( new Attendance(
					resultSet.getInt("phonefk"),
					resultSet.getString("firsta"),
					resultSet.getString("seconda"),
					resultSet.getString("third"),
					resultSet.getString("fourth"),
					resultSet.getString("fifth"),
					resultSet.getString("sixth"),
					resultSet.getString("seventh"),
					resultSet.getString("eighth"),
					resultSet.getString("ninth"),
					resultSet.getString("tenth"),
					resultSet.getString("eleventh"),
					resultSet.getString("twelvth"),
					resultSet.getString("thirteenth"),
					resultSet.getString("fourteenth"),
					resultSet.getString("fifteenth")
					
					));
			
			
			
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return list;
	}


    public ObservableList<Payments> getCurrentPatientPaymentHistory(int phone){

		ObservableList<Payments> list= FXCollections.observableArrayList();
	try {
		statement=myConn.prepareStatement("select cardMb,cardCash,paymentMb,paymentCash,paymentDate,referenceNumber from payments where phonefk=?");
		statement.setInt(1, phone);
		resultSet=statement.executeQuery();
		
		while(resultSet.next()) {
			
			list.add( new Payments(
					"","",String.valueOf( resultSet.getInt("cardMb")),
					String.valueOf( resultSet.getInt("cardCash")),
					String.valueOf( resultSet.getInt("paymentMb")),
					String.valueOf( resultSet.getInt("paymentCash")),
					 resultSet.getString("paymentDate"),
					 resultSet.getString("referenceNumber")
					));
			
			
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return list;
}
    
	
	
    public ObservableList<Prescriptions> searchPrescriptionsByPhone(int phone){
    	
    
   
    ObservableList<Prescriptions> list= FXCollections.observableArrayList();
	try {
		statement=myConn.prepareStatement("select * from prescriptions where phonefk like ?");
		statement.setString(1, String.valueOf(phone+"%"));
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			list.add( new Prescriptions(
					String.valueOf(	resultSet.getInt("id")),
					String.valueOf(	resultSet.getInt("phonefk")),
					resultSet.getString("firstp"),
					resultSet.getString("secondp"),
					resultSet.getString("third"),
					resultSet.getString("fourth"),
					resultSet.getString("fifth"),
					resultSet.getString("sixth"),
					resultSet.getString("seventh"),
					resultSet.getString("eighth"),
					resultSet.getString("ninth"),
					resultSet.getString("tenth"),
					resultSet.getString("eleventh"),
					resultSet.getString("twelvth"),
					resultSet.getString("thirteenth"),
					resultSet.getString("fourteenth"),
					resultSet.getString("fifteenth")
					
					));
			
			
			
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return list;

}

    public ObservableList<Attendance> searchAttendanceByPhone(int phone){
    	
        
    	   
        ObservableList<Attendance> list= FXCollections.observableArrayList();
    	try {
    		statement=myConn.prepareStatement("select * from attendance where phonefk like ?");
    		statement.setString(1, String.valueOf(phone+"%"));
    		resultSet=statement.executeQuery();
    		while(resultSet.next()) {
    			list.add( new Attendance(
    					
    					resultSet.getInt("phonefk") ,
    					resultSet.getString("firsta"),
    					resultSet.getString("seconda"),
    					resultSet.getString("third"),
    					resultSet.getString("fourth"),
    					resultSet.getString("fifth"),
    					resultSet.getString("sixth"),
    					resultSet.getString("seventh"),
    					resultSet.getString("eighth"),
    					resultSet.getString("ninth"),
    					resultSet.getString("tenth"),
    					resultSet.getString("eleventh"),
    					resultSet.getString("twelvth"),
    					resultSet.getString("thirteenth"),
    					resultSet.getString("fourteenth"),
    					resultSet.getString("fifteenth")
    					
    					));
    			
    			
    			
    		}
    	
    	
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    	
    	
    	return list;

    }
    
    public ObservableList<Prescriptions> getAllPrescriptions(){
    	
        
    	   
        ObservableList<Prescriptions> list= FXCollections.observableArrayList();
    	try {
    		statement=myConn.prepareStatement("select * from prescriptions ");
    		resultSet=statement.executeQuery();
    		while(resultSet.next()) {
    			list.add( new Prescriptions(
    					String.valueOf(	resultSet.getInt("id")),
    					String.valueOf(	resultSet.getInt("phonefk")),
    					resultSet.getString("firstp"),
    					resultSet.getString("secondp"),
    					resultSet.getString("third"),
    					resultSet.getString("fourth"),
    					resultSet.getString("fifth"),
    					resultSet.getString("sixth"),
    					resultSet.getString("seventh"),
    					resultSet.getString("eighth"),
    					resultSet.getString("ninth"),
    					resultSet.getString("tenth"),
    					resultSet.getString("eleventh"),
    					resultSet.getString("twelvth"),
    					resultSet.getString("thirteenth"),
    					resultSet.getString("fourteenth"),
    					resultSet.getString("fifteenth")
    					
    					));
    			
    			
    			
    		}
    	
    	
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    	
    	
    	return list;

    }


    public ObservableList<FullPrescriptions> getAllFullPrescriptions(){
    	
    	String string="%";
    	string +=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	   
        ObservableList<FullPrescriptions> list= FXCollections.observableArrayList();
    	try {
    		statement=myConn.prepareStatement("SELECT phone,FULLname,firstp,secondp, third, fourth, fifth ,sixth,seventh,eighth,ninth,tenth,eleventh,twelvth,thirteenth,fourteenth,fifteenth from "
    				+ " (SELECT phone,FULLname,firstp,secondp, third, fourth, fifth ,sixth,seventh,eighth,ninth,tenth,eleventh,twelvth,thirteenth,fourteenth,fifteenth FROM  prescriptions INNER JOIN PATIENT WHERE prescriptions.PHONEFK=PATIENT.PHONE )sub where (firstp like ?)or(secondp like ?)or(third like ?)or (fourth like ?)or(fifth like ?)or(sixth like ?)or(seventh like ?)or(eighth like ?)or(ninth like ?)or(tenth like ?)or(eleventh like ?)or(twelvth like ?)or (thirteenth like ?)or(fourteenth like ?)or(fifteenth like ?)");
    		
    		
    		statement.setString(1, string);
    		statement.setString(2, string);
    		statement.setString(3, string);
    		statement.setString(4, string);
    		statement.setString(5, string);
    		statement.setString(6, string);
    		statement.setString(7, string);
    		statement.setString(8, string);
    		statement.setString(9, string);
    		statement.setString(10, string);
    		statement.setString(11, string);
    		statement.setString(12, string);
    		statement.setString(13, string);
    		statement.setString(14, string);
    		statement.setString(15, string);
    		
    		
    		
    		
    		
    		resultSet=statement.executeQuery();
    		while(resultSet.next()) {
    			list.add( new FullPrescriptions(
    					String.valueOf(	resultSet.getInt("phone")),
    					resultSet.getString("FULLname"),
    					resultSet.getString("firstp"),
    					resultSet.getString("secondp"),
    					resultSet.getString("third"),
    					resultSet.getString("fourth"),
    					resultSet.getString("fifth"),
    					resultSet.getString("sixth"),
    					resultSet.getString("seventh"),
    					resultSet.getString("eighth"),
    					resultSet.getString("ninth"),
    					resultSet.getString("tenth"),
    					resultSet.getString("eleventh"),
    					resultSet.getString("twelvth"),
    					resultSet.getString("thirteenth"),
    					resultSet.getString("fourteenth"),
    					resultSet.getString("fifteenth")
    					
    					));
    			
    			
    			
    		}
    	
    	
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    	
    	
    	return list;

    }


    public ObservableList<FullAttendance> getAllFullAttendance(){
    	
    	
        ObservableList<FullPrescriptions> prescritionList= this.getAllFullPrescriptions();
  
        ObservableList<FullAttendance> attendanceList= FXCollections.observableArrayList();
       
        
        for(FullPrescriptions p:prescritionList) {
        	
        	try {
        		statement=myConn.prepareStatement("SELECT phone,FULLname,firsta,seconda, third, fourth, fifth ,sixth,seventh,eighth,ninth,tenth,eleventh,twelvth,thirteenth,fourteenth,fifteenth from (SELECT phone,FULLname,firsta,seconda, third, fourth, fifth ,sixth,seventh,eighth,ninth,tenth,eleventh,twelvth,thirteenth,fourteenth,fifteenth FROM  attendance INNER JOIN PATIENT WHERE attendance.PHONEFK=PATIENT.PHONE )sub where phone=?");
        		
        		statement.setInt(1,Integer.parseInt(p.getPhonefk()) );
        		
        		resultSet=statement.executeQuery();
        		while(resultSet.next()) {
        			attendanceList.add( new FullAttendance(
        					resultSet.getInt("phone"),
        					resultSet.getString("FULLname"),
        					resultSet.getString("firsta"),
        					resultSet.getString("seconda"),
        					resultSet.getString("third"),
        					resultSet.getString("fourth"),
        					resultSet.getString("fifth"),
        					resultSet.getString("sixth"),
        					resultSet.getString("seventh"),
        					resultSet.getString("eighth"),
        					resultSet.getString("ninth"),
        					resultSet.getString("tenth"),
        					resultSet.getString("eleventh"),
        					resultSet.getString("twelvth"),
        					resultSet.getString("thirteenth"),
        					resultSet.getString("fourteenth"),
        					resultSet.getString("fifteenth")
        					
        					));
        			
        			
        			
        		}
        		
        		
        	} catch (SQLException e) {
        		
        		e.printStackTrace();
        	}
      
        
        }
        
        
        
    	
    	
    	return attendanceList;

    }
    
    

    
    
    public ObservableList<String> getTreatmentItems(){
    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	try {
			statement=myConn.prepareStatement("select fullName from comboboxchoices where category='T'");
			//statement.setString(1, String.valueOf('T'));
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				
				list.add(resultSet.getString("fullName"));
				
				
			}
    	
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    	
    }
    
    public ObservableList<String> getAssesmentItems(){
    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	try {
			statement=myConn.prepareStatement("select fullName from comboboxchoices where category='A'");
			//statement.setString(1, String.valueOf('T'));
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				
				list.add(resultSet.getString("fullName"));
				
				
			}
    	
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    	
    }

    public ObservableList<String> getHistoryItems(){
    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	try {
			statement=myConn.prepareStatement("select fullName from comboboxchoices where category='H'");
			//statement.setString(1, String.valueOf('T'));
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				
				list.add(resultSet.getString("fullName"));
				
				
			}
    	
    	
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    	
    }

    public ObservableList<String> getAllListItemsForFullfilDatas(){
    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	
    	try {
    		
    		list.add("");
    		list.add("--------------- HISTORIES  ----------------");
    		list.add("");
    		
			statement=myConn.prepareStatement("select fullName from comboboxchoices where category='H'");
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				list.add(resultSet.getString("fullName"));
			}
			
			
			
			
			
			list.add("");
			list.add("--------------- ASSESSMENTS  ----------------");
			list.add("");
			
			
			
			
			statement=myConn.prepareStatement("select fullName from comboboxchoices where category='A'");
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				list.add(resultSet.getString("fullName"));
			}
			
			
			
			list.add("");
			list.add("--------------- TREATMENTS  ----------------");
			list.add("");
			
			
			
			statement=myConn.prepareStatement("select fullName from comboboxchoices where category='T'");
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				list.add(resultSet.getString("fullName"));
			}
			
			
			
			list.add("");
			list.add("--------------- FOCUSING AREAS  ----------------");
			list.add("");
			
			
			
			statement=myConn.prepareStatement("select fullName from comboboxchoices where category='F'");
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				
				list.add(resultSet.getString("fullName"));
			}
			
			
			
			
			
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    	
    }

    public ObservableList<String> getFocusingAreaItems(){

	
	ObservableList<String> list= FXCollections.observableArrayList();

	try {
		statement=myConn.prepareStatement("select fullName from comboboxchoices where category='F'");
		//statement.setString(1, String.valueOf('T'));
		resultSet=statement.executeQuery();
		
		while(resultSet.next()) {
			
			list.add(resultSet.getString("fullName"));
			
			
		}
	
	
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
	
}

    public ObservableList<String> getReflexologyItems(){

    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select fullName from comboboxchoices where category='R'");
    		//statement.setString(1, String.valueOf('T'));
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add(resultSet.getString("fullName"));
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }

    public ObservableList<String> getPrescriptionItems(){

    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select fullName from comboboxchoices where category='P'");
    		//statement.setString(1, String.valueOf('T'));
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add(resultSet.getString("fullName"));
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    
    public ObservableList<String> getMassagerItems(){

    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select fullName from employee where job='massager'");
    		//statement.setString(1, String.valueOf('T'));
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add(resultSet.getString("fullName"));
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }

    public ObservableList<String> getJobItems(){

    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select fullName from comboboxchoices where category='J'");
    		//statement.setString(1, String.valueOf('T'));
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add(resultSet.getString("fullName"));
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    
    public ObservableList<String> getBanksName(){

    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select fullName from comboboxchoices where category='B'");
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add(resultSet.getString("fullName"));
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    

    public ObservableList<ComboBoxChoices> getReflexologyItemsForTable(){

    	
    	ObservableList<ComboBoxChoices> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select fullName,price,commission from comboboxchoices where category='R'");
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add( new ComboBoxChoices(   "",resultSet.getString("fullName"), "R",resultSet.getString("price"),resultSet.getString("commission")   ) );
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    
    public ObservableList<ComboBoxChoices> getPrescriptionItemsForTable(){

    	
    	ObservableList<ComboBoxChoices> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select fullName,price,commission from comboboxchoices where category='P'");
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add( new ComboBoxChoices(   "",resultSet.getString("fullName"), "R",resultSet.getString("price"),resultSet.getString("commission")   ) );
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    

    public ObservableList<Withdrawal> getWithdrawalForTable(String startingDate,String endingDate){

    	
    	ObservableList<Withdrawal> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select fullName,amount,reason,withdrawalDate from withdrawal   where withdrawalDate>=? and withdrawalDate<=?");
    		statement.setString(1, startingDate);
    		statement.setString(2, endingDate);

    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add( new Withdrawal(   "",resultSet.getString("fullName"),String.valueOf( resultSet.getInt("amount")),resultSet.getString("reason"), resultSet.getString("withdrawalDate")  ) );
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    

    public ObservableList<Withdrawal> getWithdrawalForTableDaily(String date){

    	
    	ObservableList<Withdrawal> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select fullName,amount,reason,withdrawalDate from withdrawal   where withdrawalDate=?");
    		statement.setString(1, date);

    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add( new Withdrawal(   "",resultSet.getString("fullName"),String.valueOf( resultSet.getInt("amount")),resultSet.getString("reason"), resultSet.getString("withdrawalDate")  ) );
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    
    
    public int searchCurrentPatient(){
	 	
	 	int phone=0;
		try {
			
			statement=myConn.prepareStatement("select phonefk from attendance  where (firsta=?) or (seconda=?) or (third=?) or (fourth=?) or (fifth=?) or (sixth=?) or (seventh=?) or (eighth=?) or (ninth=?) or (tenth=?) or (eleventh=?) or (twelvth=?) or (thirteenth=?) or (fourteenth=?) or (fifteenth=?)" );
			statement.setString(1, String.valueOf('E'));
			statement.setString(2, String.valueOf('E'));
			statement.setString(3, String.valueOf('E'));
			statement.setString(4, String.valueOf('E'));
			statement.setString(5, String.valueOf('E'));
			statement.setString(6, String.valueOf('E'));
			statement.setString(7, String.valueOf('E'));
			statement.setString(8, String.valueOf('E'));
			statement.setString(9, String.valueOf('E'));
			statement.setString(10, String.valueOf('E'));
			statement.setString(11, String.valueOf('E'));
			statement.setString(12, String.valueOf('E'));
			statement.setString(13, String.valueOf('E'));
			statement.setString(14, String.valueOf('E'));
			statement.setString(15, String.valueOf('E'));
			
			resultSet=statement.executeQuery();
			while( resultSet.next()) {
				phone=resultSet.getInt("phonefk");
				break;
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phone;	
	 
 }

    public ObservableList<String> getMassagerId(){

    	
    	ObservableList<String> list= FXCollections.observableArrayList();

    	try {
    		statement=myConn.prepareStatement("select id from employee where job='masager'");
    		//statement.setString(1, String.valueOf('T'));
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			list.add(String.valueOf(resultSet.getInt("id")));
    			
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }


    public int getFixedSalary(int id){

    	
int salary = 0;
    	try {
    		statement=myConn.prepareStatement("select fixedSalary from employee where id=?");
    		statement.setInt(1,id );
    		resultSet=statement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			salary=resultSet.getInt("fixedSalary");
    			
    		}
    	
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return salary;
    	
    }

	public String getFirstPrescription(String phone) {
		

    	String name=null;
	try {
		statement=myConn.prepareStatement("select firstp from prescriptions where phonefk=? ");
		statement.setString(1, phone);
		resultSet=statement.executeQuery();
		while(resultSet.next()) {
			name=resultSet.getString("firstp");
		}
	
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return name;
	}

	
	public ObservableList<PayRoll> getPayRollForTable(String startingDate,String endingDate){

	    	
	    	ObservableList<PayRoll> list= FXCollections.observableArrayList();
	    	List<Integer> id= new ArrayList<>();
	    	
	    	try {
	    		statement=myConn.prepareStatement("select id from employee ");
	    		resultSet=statement.executeQuery();
	    		while(resultSet.next()) {
	    			id.add(resultSet.getInt("id"));
	    				}
	    		
	    for (int i = 0; i < id.size(); i++) {
			
	    list.add(	new PayRoll( 
	    			this.getEmployeeNameById(id.get(i)),
	    			String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "30 MIN",id.get(i))),
	    			String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "45 MIN",id.get(i))),
	    			String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "60 MIN",id.get(i))),
	    			String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma10",id.get(i))),
	    			String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma12",id.get(i)) ),
	    			String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma15",id.get(i)) ),
	    			String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma17",id.get(i)) ),
	    			String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma20",id.get(i))  )  ,

String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "30 MIN",id.get(i))+
this.getMonthlyReflexologyCount(startingDate, endingDate, "45 MIN",id.get(i))+
this.getMonthlyReflexologyCount(startingDate, endingDate, "60 MIN",id.get(i))+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma10",id.get(i))+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma12",id.get(i))+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma15",id.get(i))+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma17",id.get(i))+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma20",id.get(i))     ),



String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "30 MIN",id.get(i))*this.getCommission("30 MIN")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "45 MIN",id.get(i))*this.getCommission("45 MIN")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "60 MIN",id.get(i))*this.getCommission("60 MIN")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma10",id.get(i))*this.getCommission("Ma10")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma12",id.get(i))*this.getCommission("Ma12")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma15",id.get(i))*this.getCommission("Ma15")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma17",id.get(i))*this.getCommission("Ma17")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma20",id.get(i))*this.getCommission("Ma20")  ) ,

String.valueOf(
this.getFixedSalary(id.get(i)) ),


String.valueOf(
this.getMonthlyReflexologyCount(startingDate, endingDate, "30 MIN",id.get(i))*this.getCommission("30 MIN")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "45 MIN",id.get(i))*this.getCommission("45 MIN")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "60 MIN",id.get(i))*this.getCommission("60 MIN")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma10",id.get(i))*this.getCommission("Ma10")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma12",id.get(i))*this.getCommission("Ma12")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma15",id.get(i))*this.getCommission("Ma15")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma17",id.get(i))*this.getCommission("Ma17")+
this.getMonthlyReflexologyCount(startingDate, endingDate, "Ma20",id.get(i))*this.getCommission("Ma20")+  this.getFixedSalary(id.get(i))

	    	)		
	    			)  );//end of new PayRoll();




	    	
		}
	    		
	    		
	    		
	    		
	    		
	    		
	    	
	    	
	    	
	    	} catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	
	    	return list;
	    	
	    }

	LocalDate date=null;
	int counter=0;
 
	public void updateAbsentPrescriptions(FullPrescriptions person,int startingPoint) {
		
		try {
		
			String query="";
			String lastPrescription="";
			
			//if (person.getFirstp().length()==0) 
			if (person.getSecondp().length()==0 ||person.getSecondp().length()==1 )  lastPrescription=person.getFirstp().substring(5);
			else if (person.getThird().length()==0 || person.getThird().length()==1)  lastPrescription=person.getSecondp().substring(5);
			else if (person.getFourth().length()==0 || person.getFourth().length()==1)  lastPrescription=person.getThird().substring(5);
			else if (person.getFifth().length()==0 || person.getFifth().length()==1)  lastPrescription=person.getFourth().substring(5);
			else if (person.getSixth().length()==0 || person.getSixth().length()==1)  lastPrescription=person.getFifth().substring(5);
			else if (person.getSeventh().length()==0 || person.getSeventh().length()==1)  lastPrescription=person.getSixth().substring(5);
			else if (person.getEighth().length()==0 || person.getEighth().length()==1)  lastPrescription=person.getSeventh().substring(5);
			else if (person.getNinth().length()==0 || person.getNinth().length()==1)  lastPrescription=person.getEighth().substring(5);
			else if (person.getTenth().length()==0 || person.getTenth().length()==1)  lastPrescription=person.getNinth().substring(5);
			else if (person.getEleventh().length()==0 || person.getEleventh().length()==1)  lastPrescription=person.getTenth().substring(5);
			else if (person.getTwelvth().length()==0 || person.getTwelvth().length()==1)  lastPrescription=person.getEleventh().substring(5);
			else if (person.getThirteenth().length()==0 || person.getThirteenth().length()==1)  lastPrescription=person.getTwelvth().substring(5);
			else if (person.getFourteenth().length()==0 || person.getFourteenth().length()==1)  lastPrescription=person.getThird().substring(5);
			else if (person.getFifteenth().length()==0 || person.getFifteenth().length()==1)  lastPrescription=person.getFourteenth().substring(5);
			
			String pattern="yyyy-MM-dd";
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
			 date=LocalDate.parse(lastPrescription, formatter);
			
			if (date.getDayOfWeek().toString().equals("FRIDAY"))
				date=date.plusDays(3);
			else if (date.getDayOfWeek().toString().equals("SATURDAY"))
				date=date.plusDays(3);
			else if (date.getDayOfWeek().toString().equals("SUNDAY"))
				date=date.plusDays(0);
			else
				date=date.plusDays(2);
			
			
			if(startingPoint==1) {
				if(! person.getFirstp().equals("")) {
					query=query+"update prescriptions set  secondp=?, third=?, fourth=?, fifth=?, sixth=?, seventh=?, eighth=?, ninth=?, tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				statement=myConn.prepareStatement(query);
				
				statement.setString(1, getPrescriptionSubstring(person.getFirstp())+  getSubstring(person.getSecondp()) );
				statement.setString(2,  getPrescriptionSubstring(person.getSecondp())+ getSubstring(person.getThird())  );	
				statement.setString(3,  getPrescriptionSubstring( person.getThird())+ getSubstring(person.getFourth())  );
				statement.setString(4, getPrescriptionSubstring( person.getFourth())+ getSubstring(person.getFifth())   );
				statement.setString(5,  getPrescriptionSubstring(person.getFifth())+ getSubstring(person.getSixth())   );
				statement.setString(6,  getPrescriptionSubstring(person.getSixth())+ getSubstring(person.getSeventh())   );
				statement.setString(7,  getPrescriptionSubstring(person.getSeventh())+ getSubstring(person.getEighth())   );
				statement.setString(8,  getPrescriptionSubstring( person.getEighth())+ getSubstring(person.getNinth())  );
				statement.setString(9,  getPrescriptionSubstring( person.getNinth())+ getSubstring(person.getTenth())  );
				statement.setString(10,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(11, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(12,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(13,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(14,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(15 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			} }
			
			else if(startingPoint==2) {
				
				if(! person.getSecondp().equals("")) {
					query=query+"update prescriptions set   third=?, fourth=?, fifth=?, sixth=?, seventh=?, eighth=?, ninth=?, tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				statement=myConn.prepareStatement(query);
				
				statement.setString(1,  getPrescriptionSubstring(person.getSecondp())+ getSubstring(person.getThird())  );	
				statement.setString(2,  getPrescriptionSubstring( person.getThird())+ getSubstring(person.getFourth())  );
				statement.setString(3, getPrescriptionSubstring( person.getFourth())+ getSubstring(person.getFifth())   );
				statement.setString(4,  getPrescriptionSubstring(person.getFifth())+ getSubstring(person.getSixth())   );
				statement.setString(5,  getPrescriptionSubstring(person.getSixth())+ getSubstring(person.getSeventh())   );
				statement.setString(6,  getPrescriptionSubstring(person.getSeventh())+ getSubstring(person.getEighth())   );
				statement.setString(7,  getPrescriptionSubstring( person.getEighth())+ getSubstring(person.getNinth())  );
				statement.setString(8,  getPrescriptionSubstring( person.getNinth())+ getSubstring(person.getTenth())  );
				statement.setString(9,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(10, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(11,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(12,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(13,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(14 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				 counter=0;
			}}
			
			else if(startingPoint==3) {
				if(! person.getThird().equals("")) {
					query=query+"update prescriptions set    fourth=?, fifth=?, sixth=?, seventh=?, eighth=?, ninth=?, tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				statement=myConn.prepareStatement(query);
				
				statement.setString(1,  getPrescriptionSubstring( person.getThird())+ getSubstring(person.getFourth())  );
				statement.setString(2, getPrescriptionSubstring( person.getFourth())+ getSubstring(person.getFifth())   );
				statement.setString(3,  getPrescriptionSubstring(person.getFifth())+ getSubstring(person.getSixth())   );
				statement.setString(4,  getPrescriptionSubstring(person.getSixth())+ getSubstring(person.getSeventh())   );
				statement.setString(5,  getPrescriptionSubstring(person.getSeventh())+ getSubstring(person.getEighth())   );
				statement.setString(6,  getPrescriptionSubstring( person.getEighth())+ getSubstring(person.getNinth())  );
				statement.setString(7,  getPrescriptionSubstring( person.getNinth())+ getSubstring(person.getTenth())  );
				statement.setString(8,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(9, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(10,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(11,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(12,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(13 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			}}
			
			else if(startingPoint==4) {
				if(! person.getFourth().equals("")) {
				query=query+"update prescriptions set     fifth=?, sixth=?, seventh=?, eighth=?, ninth=?, tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				statement=myConn.prepareStatement(query);
				
				statement.setString(1, getPrescriptionSubstring( person.getFourth())+ getSubstring(person.getFifth())   );
				statement.setString(2,  getPrescriptionSubstring(person.getFifth())+ getSubstring(person.getSixth())   );
				statement.setString(3,  getPrescriptionSubstring(person.getSixth())+ getSubstring(person.getSeventh())   );
				statement.setString(4,  getPrescriptionSubstring(person.getSeventh())+ getSubstring(person.getEighth())   );
				statement.setString(5,  getPrescriptionSubstring( person.getEighth())+ getSubstring(person.getNinth())  );
				statement.setString(6,  getPrescriptionSubstring( person.getNinth())+ getSubstring(person.getTenth())  );
				statement.setString(7,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(8, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(9,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(10,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(11,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(12 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			}}
			
			else if(startingPoint==5) {
				System.out.println("in 5 before if");
				if(! person.getFifth().equals("")) {
					query=query+"update prescriptions set      sixth=?, seventh=?, eighth=?, ninth=?, tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				
				statement=myConn.prepareStatement(query);
				
				statement.setString(1,  getPrescriptionSubstring(person.getFifth())+ getSubstring(person.getSixth())   );
				statement.setString(2,  getPrescriptionSubstring(person.getSixth())+ getSubstring(person.getSeventh())   );
				statement.setString(3,  getPrescriptionSubstring(person.getSeventh())+ getSubstring(person.getEighth())   );
				statement.setString(4,  getPrescriptionSubstring( person.getEighth())+ getSubstring(person.getNinth())  );
				statement.setString(5,  getPrescriptionSubstring( person.getNinth())+ getSubstring(person.getTenth())  );
				statement.setString(6,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(7, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(8,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(9,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(10,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(11 , Integer.parseInt( person.getPhonefk()));
				System.out.println("before execute");
				statement.execute();
				
				counter=0;
				System.out.println("after execute");
				
			}}
			
			else if(startingPoint==6) {
				if(! person.getSixth().equals("")) {
					query=query+"update prescriptions set       seventh=?, eighth=?, ninth=?, tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				
				statement=myConn.prepareStatement(query);
				
				statement.setString(1,  getPrescriptionSubstring(person.getSixth())+ getSubstring(person.getSeventh())   );
				statement.setString(2,  getPrescriptionSubstring(person.getSeventh())+ getSubstring(person.getEighth())   );
				statement.setString(3,  getPrescriptionSubstring( person.getEighth())+ getSubstring(person.getNinth())  );
				statement.setString(4,  getPrescriptionSubstring( person.getNinth())+ getSubstring(person.getTenth())  );
				statement.setString(5,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(6, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(7,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(8,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(9,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(10 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			}}
			else if(startingPoint==7) {
				if(! person.getSeventh().equals("")) {
					query=query+"update prescriptions set        eighth=?, ninth=?, tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				
				statement=myConn.prepareStatement(query);
				
				statement.setString(1,  getPrescriptionSubstring(person.getSeventh())+ getSubstring(person.getEighth())   );
				statement.setString(2,  getPrescriptionSubstring( person.getEighth())+ getSubstring(person.getNinth())  );
				statement.setString(3,  getPrescriptionSubstring( person.getNinth())+ getSubstring(person.getTenth())  );
				statement.setString(4,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(5, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(6,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(7,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(8,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(9 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			}}
			
			else if(startingPoint==8) {
				if(! person.getEighth().equals("")) {
					query=query+"update prescriptions set         ninth=?, tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				
				statement=myConn.prepareStatement(query);
				
				statement.setString(1,  getPrescriptionSubstring( person.getEighth())+ getSubstring(person.getNinth())  );
				statement.setString(2,  getPrescriptionSubstring( person.getNinth())+ getSubstring(person.getTenth())  );
				statement.setString(3,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(4, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(5,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(6,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(7,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(8 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			}}
			
			else if(startingPoint==9) {
				if(! person.getNinth().equals("")) {
					query=query+"update prescriptions set          tenth=?, eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				
				statement=myConn.prepareStatement(query);
				
				statement.setString(1,  getPrescriptionSubstring( person.getNinth())+ getSubstring(person.getTenth())  );
				statement.setString(2,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(3, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(4,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(5,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(6,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(7 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			}}
			
			else if(startingPoint==10) {
				if(! person.getTenth().equals("")) {
					query=query+"update prescriptions set          eleventh=?, twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				
				statement=myConn.prepareStatement(query);
				
				statement.setString(1,  getPrescriptionSubstring(person.getTenth())+ getSubstring(person.getEleventh())  );
				statement.setString(2, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(3,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(4,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(5,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(6 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			}}
			
			else if(startingPoint==11) {
				if(! person.getEleventh().equals("")) {
					query=query+"update prescriptions set           twelvth=?, thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				
				statement=myConn.prepareStatement(query);
				
				statement.setString(1, getPrescriptionSubstring( person.getEleventh())+ getSubstring(person.getTwelvth())   );
				statement.setString(2,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(3,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(4,  getPrescriptionSubstring(person.getFourteenth())    );
				statement.setInt(5 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			}}
			
			else if(startingPoint==12) {
				if(! person.getTwelvth().equals("")) {
					query=query+"update prescriptions set            thirteenth=?, fourteenth=? , fifteenth=? where phonefk=?";
				
				statement=myConn.prepareStatement(query);
				
				statement.setString(1,  getPrescriptionSubstring( person.getTwelvth())+ getSubstring(person.getThirteenth())  );
			statement.setString(2,  getPrescriptionSubstring( person.getThirteenth())+ getSubstring(person.getFourteenth())  );
				statement.setString(3,  getPrescriptionSubstring(person.getFourteenth()) );
				statement.setInt(4 , Integer.parseInt( person.getPhonefk()));
				
				statement.execute();
				counter=0;
			}}
			
		else if (startingPoint == 13) {
			if (!person.getThirteenth().equals("")) {
				query = query + "update prescriptions set            fourteenth=? , fifteenth=? where phonefk=?";
			
			statement = myConn.prepareStatement(query);

			statement.setString(1,getPrescriptionSubstring(person.getThirteenth()) + getSubstring(person.getFourteenth()));
			statement.setString(2, getPrescriptionSubstring(person.getFourteenth()));
			statement.setInt(3, Integer.parseInt(person.getPhonefk()));

			statement.execute();
			counter = 0;
		}}
			
		else if (startingPoint == 14) {
			if (!person.getFourteenth().equals("")) {
				query = query + "update prescriptions set             fifteenth=? where phonefk=?";

					statement = myConn.prepareStatement(query);

					statement.setString(1, getPrescriptionSubstring(person.getFourteenth()));
					statement.setInt(2, Integer.parseInt(person.getPhonefk()));

					statement.execute();
					counter = 0;
			}}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		//the reason why i use firsta and seconda is since they are keywords in MySQL,therefore
		//they can't be column name.
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	
	
	private String getSubstring(String string) {
		
		if(counter==0 && string.length()==0)
			{counter++;
			return ","+date.toString();
			}
		
		if(string.length()==0 && counter!=0 )
				return "";
		if (string.length()!=0 ) {
			return string.substring(4);
		}
		
		return "";
	}
 
	private String getPrescriptionSubstring(String string) {
		
		
		if(string.length()!=0 && string.length()!=1)
		return string.substring(0,4);
		
		return "";
	}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
	
	
	
	
	



    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
}