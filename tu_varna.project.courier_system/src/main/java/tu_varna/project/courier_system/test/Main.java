package tu_varna.project.courier_system.test;

import javafx.application.Application;
import javafx.stage.Stage;
import tu_varna.project.courier_system.dao.entityManager;
import tu_varna.project.courier_system.helper.OpenNewForm;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		entityManager.initEntityManager("persistence");
		try {
			OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome! ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
