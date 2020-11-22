package tu_varna.project.courier_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.CloseForm;
import tu_varna.project.courier_system.helper.DataValidation;
import tu_varna.project.courier_system.services.LoginService;
import tu_varna.project.courier_system.services.LoginServiceImpl;

public class WelcomeFormController {

	private LoginService loginService = new LoginServiceImpl();

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

			User user = loginService.authenticateUserLogin(username, password);
			if (user != null) {

				user.loadController();
				CloseForm.closeForm(event);

			} else {
				resultLabel.setText("Wrong username or password");
				this.username.clear();
				this.password.clear();
				this.username.requestFocus();
			}

		}

	}
}
