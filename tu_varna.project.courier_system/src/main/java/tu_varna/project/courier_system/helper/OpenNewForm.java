package tu_varna.project.courier_system.helper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenNewForm {
	
	public static void openNewForm(String fxmlFileName, String formTitle) {
		try {
			FXMLLoader fxmlLoad = new FXMLLoader(new OpenNewForm().getClass().getResource("/tu_varna/project/courier_system/view/" + fxmlFileName));
			Parent root;
			root = (Parent) fxmlLoad.load();
			Stage stage = new Stage();
			stage.setTitle(formTitle);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			System.out.println("Can't load new window. ");
		}

	}
}
