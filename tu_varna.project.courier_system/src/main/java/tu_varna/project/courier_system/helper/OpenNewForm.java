package tu_varna.project.courier_system.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class OpenNewForm {

	public static FXMLLoader openNewForm(String fxmlFileName, String formTitle) {
		FXMLLoader fxmlLoad = null;
		try {
			fxmlLoad = new FXMLLoader(
					new OpenNewForm().getClass().getResource("/tu_varna/project/courier_system/view/" + fxmlFileName));

			Parent root = (Parent) fxmlLoad.load();
			Stage stage = new Stage();
			stage.setTitle(formTitle);
			stage.setScene(new Scene(root));
			stage.getIcons().add(new Image(new OpenNewForm().getClass()
					.getResourceAsStream("/tu_varna/project/courier_system/img/appIcon.png")));
			stage.show();

		} catch (Exception e) {
			System.out.println("Can't load new window. ");
		}
		return fxmlLoad;

	}
}
