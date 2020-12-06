package tu_varna.project.courier_system.controllers;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.BuiltInForm;
import tu_varna.project.courier_system.helper.CloseForm;
import tu_varna.project.courier_system.helper.OpenNewForm;

public class CourierWorkspaceFormController
{

	private static final Logger logger = LogManager.getLogger(CourierWorkspaceFormController.class);

	private Courier courier;

	@FXML
	private Label welcomeUser;

	@FXML
	private Label label;

	@FXML
	private AnchorPane workPane;

	@FXML
	private void loadHomePage(ActionEvent event) throws IOException
	{
		FXMLLoader loader = BuiltInForm.built_inForm("CourierHomeForm.fxml", workPane);
		CourierHomeFormController next = loader.getController();
		next.setCourier(courier);

	}

	@FXML
	private void viewProfile(ActionEvent event) throws IOException
	{
		FXMLLoader loader = BuiltInForm.built_inForm("CourierProfileForm.fxml", workPane);
		CourierProfileFormController next = loader.getController();
		next.setCourier(courier);

	}

	@FXML
	private void logOut(ActionEvent event) throws IOException
	{
		CloseForm.closeForm(event);
		logger.info("Courier with id: " + courier.getId() + " successfully logged out!");
		OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome");
	}

	public void setUser(User user)
	{

		this.courier = (Courier) user;
		welcomeUser.setText("Welcome " + courier.getName());
		logger.info("Courier with id: " + courier.getId() + " successfully logged in!");
	}

}
