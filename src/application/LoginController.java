package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{


    @FXML
    private Button adminButton,creatAccountButton;

    @FXML
    private Label creatAccountLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button receptionButton;

    @FXML
    private TextField userNameTextField;
	
	
	private ShalomDAO dao;
	
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	dao= new ShalomDAO();
    	inputValidation();
    	
    	
    	
    	
    	
    	if(dao.IsThereAnAdmin()) {
    		adminButton.setVisible(true);
    		receptionButton.setVisible(true);
    		
    	} else {
    		creatAccountLabel.setVisible(true);
    		creatAccountButton.setVisible(true);
    	}
    	
    	
    	
    	
    	
    	
    }
	
	void inputValidation() {
	
		
		userNameTextField.textProperty().addListener(new ChangeListener<String> (){

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				
				if(newValue.length()>=20) {
					
					userNameTextField.setText(oldValue);
				}
				
			}
    		
    		
    	});;
    	
    	
    	passwordTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> change, String oldValue, String newValue) {
				
				
				if(newValue.length()>=9) {
					
					passwordTextField.setText(oldValue);
					return;
				}
				
				
				if(newValue.matches("[0-9]*")) {
					passwordTextField.setText(newValue);
				}else {
					passwordTextField.setText(oldValue);
					
				}
				
			}
    		
    		
    		
    		
    	});
    	
    	
    	
    	
    	
	}


    @FXML
    void creatAccount(ActionEvent event) {
    	
    	
    	if(!(passwordTextField.getText().equals("")) && !(userNameTextField.getText().equals(""))) {
    		
    		dao.createAdminAccount(userNameTextField.getText(), Integer.parseInt(passwordTextField.getText()), "Admin");
    		new CallAlert(AlertType.INFORMATION,"","","Created Successfully !");
    		initialize(null, null);
    		creatAccountLabel.setVisible(false);
    		creatAccountButton.setVisible(false);
    	}
    	
    	else {
    		
    		new CallAlert(AlertType.WARNING,"Warning","","Make sure that the userName and the password are not empty !");
    		
    	}
    	

    }

	public void goToReceptionHomepage(ActionEvent e) {
		

		try {
			Stage stage=new Stage();
			stage=(Stage)((Node) e.getSource()).getScene().getWindow();
			
			Parent root =FXMLLoader.load(getClass().getResource("/application/ReceptionFxml/ReceptionHomePage.fxml"));
			Scene scene= new Scene(root);
			
			stage.setScene(scene);
			stage.show();
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
		
//		
//		if(!(passwordTextField.getText().equals("")) && !(userNameTextField.getText().equals(""))) {
//	    	
//		if(dao.IsThereThisAccount(userNameTextField.getText(), Integer.parseInt(passwordTextField.getText()), "Reception"))
//		{
//			
//			
//		//-------------------bring here the above try catch block---------
//		
//		}
//		
//		else {
//			new CallAlert(AlertType.WARNING,"Warning","","Make sure that the userName and the password are correct !");
//			
//		}
//		
//		}
//		
//		else {
//			new CallAlert(AlertType.WARNING,"Warning","","Make sure that the userName and the password are not empty !");
//			
//		}
		
	}

	public void goToAdminHomePage(ActionEvent e) {
		try {
			
			Stage stage= new Stage();
			stage= (Stage)((Node)e.getSource()).getScene().getWindow();
			Parent root= FXMLLoader.load(getClass().getResource("/application/AdminFxml/AdminHomePage.fxml"));
			Scene scene= new Scene(root);
			stage.setScene(scene);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

		
		
		
		
		
//		
//		
//		if(!(passwordTextField.getText().equals("")) && !(userNameTextField.getText().equals(""))) {
//	    	
//			if(dao.IsThereThisAccount(userNameTextField.getText(), Integer.parseInt(passwordTextField.getText()), "Admin"))
//			{
//				
//				
//				
//				
//		//-----------bring here the above try catch block to make logic correct
//			
//			
//			}
//			
//			
//			else {
//				new CallAlert(AlertType.WARNING,"Warning","","Make sure that the userName and the password are correct !");
//				
//			}
//			
//		}
//		else {
//    		new CallAlert(AlertType.WARNING,"Warning","","Make sure that the userName and the password are not empty !");
//
//		}
	}

	
	
}
