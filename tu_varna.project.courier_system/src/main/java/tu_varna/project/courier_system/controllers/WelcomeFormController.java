package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.Admin;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.DataValidation;
import tu_varna.project.courier_system.helper.LogOut;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.LoginService;

public class WelcomeFormController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Label resultLabel;

	@FXML
	private Label usernameLabel;

	@FXML
	private Label passwordLabel;

	@FXML
	private void Login(ActionEvent event) {
		resultLabel.setText("");
		User user = null;
		boolean usernameIsEmpty = DataValidation.textFieldisEmpty(this.username, this.usernameLabel, "No data");
		boolean passwordIsEmpty = DataValidation.textFieldisEmpty(this.password, this.passwordLabel, "No data");
		if (!usernameIsEmpty && !passwordIsEmpty) {
			String username = this.username.getText();
			String password = this.password.getText();
			LoginService login = new LoginService();
			user = login.authenticateUserLogin(username, password);

			if (user != null) {
				FXMLLoader loader=OpenNewForm.openNewForm(user.loadView(), user.getClass().getSimpleName() + " workspace");
				user.loadController(loader);
				try {
					LogOut.logOut(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				resultLabel.setText("Wrong username or password");
				this.username.clear();
				this.password.clear();
			}

		}

	}
}
