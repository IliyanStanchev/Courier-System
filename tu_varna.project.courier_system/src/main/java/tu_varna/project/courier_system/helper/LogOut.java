package tu_varna.project.courier_system.helper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class LogOut {

	public static void logOut(ActionEvent event) throws IOException {

		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		// OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome! ");
	}
}
