package tu_varna.project.courier_system.controllers;

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
import tu_varna.project.courier_system.helper.DataValidation;
import tu_varna.project.courier_system.helper.LogOut;
import tu_varna.project.courier_system.services.LoginService;

public class WelcomeFormController implements Initializable {

	private LoginService login = new LoginService();

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

		boolean usernameIsEmpty = DataValidation.textFieldisEmpty(this.username, this.usernameLabel, "No data");
		boolean passwordIsEmpty = DataValidation.textFieldisEmpty(this.password, this.passwordLabel, "No data");
		if (!usernameIsEmpty && !passwordIsEmpty) {
			String username = this.username.getText();
			String password = this.password.getText();

			User user = login.authenticateUserLogin(username, password);
			if (user != null) {

				user.loadController();
				try {
					LogOut.logOut(event);
				} catch (IOException e) {
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
