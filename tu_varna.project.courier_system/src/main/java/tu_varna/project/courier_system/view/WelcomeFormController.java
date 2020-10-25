package tu_varna.project.courier_system.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.LogOut;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.LoginService;

public class WelcomeFormController implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label label;
	
	@FXML
	private void Login(ActionEvent event)
	{
		String username= this.username.getText();
		String password= this.password.getText();
		LoginService login = new LoginService();
		User user=login.authenticateUserLogin(username,password);
		if(user!=null)
		{
			OpenNewForm.openNewForm(user.loadView(),user.getClass().getSimpleName()+" workspace");
			try {
				LogOut.logOut(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			label.setText("Wrong username or password");
	}
	
	
	

}
