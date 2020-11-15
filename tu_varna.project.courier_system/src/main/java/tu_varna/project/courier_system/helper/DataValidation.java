package tu_varna.project.courier_system.helper;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class DataValidation {

	private static UserService service = new UserServiceImpl();

	public static boolean textFieldisEmpty(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isEmpty = false;
		String validationString = null;
		if (inputTextField.getText().isEmpty()) {
			isEmpty = true;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isEmpty;
	}

	public static boolean dataLength(TextField inputTextField, Label inputLabel, String validationText,
			int requiredLength) {
		boolean isDataLength = true;
		String validationString = null;
		if (inputTextField.getText().length() < requiredLength) {
			isDataLength = false;
			validationString = validationText;

		}
		inputLabel.setText(validationString);
		return isDataLength;

	}

	public static boolean textNumeric(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isNumeric = true;
		String validationString = null;

		if (!inputTextField.getText().matches("[0-9]+")) {
			isNumeric = false;
			validationString = validationText;

		}
		inputLabel.setText(validationString);
		return isNumeric;

	}

	public static boolean textAlphabet(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isAlphabet = true;
		String validationString = null;

		if (!inputTextField.getText().matches("[a-z A-Z]+")) {
			isAlphabet = false;
			validationString = validationText;

		}
		inputLabel.setText(validationString);

		System.out.println(inputTextField.getText().matches("[a-z A-Z]"));
		return isAlphabet;

	}

	public static boolean emailFormat(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isEmail = true;
		String validationString = null;

		if (!inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {
			isEmail = false;
			validationString = validationText;

		}
		inputLabel.setText(validationString);
		return isEmail;

	}

	public static boolean streetNFormat(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isStreet = true;
		String validationString = null;

		if (!inputTextField.getText().matches("[a-z A-Z]+¹[0-9]")) {
			isStreet = false;
			validationString = validationText;

		}
		inputLabel.setText(validationString);
		return isStreet;

	}

	public static boolean priceFormat(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isPrice = true;
		String validationString = null;

		if (!inputTextField.getText().matches("[0-9]+([.][0-9]{1,2})?")) {
			isPrice = false;
			validationString = validationText;

		}
		inputLabel.setText(validationString);
		return isPrice;

	}

	public static boolean isUsername(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isUsername = true;
		String validationString = null;
		if (!inputTextField.getText().matches("^[a-zA-Z0-9_.-]*$")) {
			isUsername = false;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isUsername;
	}

	/*
	 * public static boolean isUnique(String check, TextField inputTextField, Label
	 * inputLabel, String validationText) { boolean isUnique = true; String
	 * validationString = null; if (check.equalsIgnoreCase("phonenumber")) isUnique
	 * = (service.SearchUserByPhone(inputTextField.getText()) == null); else if
	 * (check.equalsIgnoreCase("username")) { isUnique =
	 * (service.SearchUserByUsername(inputTextField.getText()) == null); } else if
	 * (check.equalsIgnoreCase("bulstat")) { isUnique =
	 * (service.SearchCompany(Integer.parseInt((inputTextField.getText()))) ==
	 * null);
	 * 
	 * } if (!isUnique) { validationString = validationText; }
	 * inputLabel.setText(validationString);
	 * 
	 * return isUnique;
	 * 
	 * }
	 */

}
