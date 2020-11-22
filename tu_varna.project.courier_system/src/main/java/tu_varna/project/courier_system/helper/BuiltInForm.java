package tu_varna.project.courier_system.helper;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class BuiltInForm {

	public static FXMLLoader built_inForm(String fxmlFileName, AnchorPane workPane) {
		FXMLLoader fxmlLoad = null;
		try {

			fxmlLoad = new FXMLLoader(
					new BuiltInForm().getClass().getResource("/tu_varna/project/courier_system/view/" + fxmlFileName));
			AnchorPane pane = fxmlLoad.load();
			workPane.getChildren().setAll(pane);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cant built-in form.");
		}
		return fxmlLoad;

	}

	private BuiltInForm() {

	}
}
