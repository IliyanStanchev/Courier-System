package tu_varna.project.courier_system.helper;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class BuiltInForm {

	private BuiltInForm() {

	}

	public static void built_inForm(String fxmlFileName, AnchorPane workPane) {

		try {
			AnchorPane pane = FXMLLoader.load(new BuiltInForm().getClass().getResource("/tu_varna/project/courier_system/view/" + fxmlFileName));
			workPane.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println("Cant built-in form.");
		}

	}
}
