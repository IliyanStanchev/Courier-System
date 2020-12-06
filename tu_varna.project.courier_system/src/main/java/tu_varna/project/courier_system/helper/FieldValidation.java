package tu_varna.project.courier_system.helper;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FieldValidation
{

	public static boolean alphabetValidation(TextField field, Label validationLabel)
	{
		boolean isEmpty = DataValidation.textFieldisEmpty(field, validationLabel, "The field is empty.");
		if (!isEmpty)
		{
			boolean isAlphabet = DataValidation.textAlphabet(field, validationLabel, "Wrong alphabet format.");
			return isAlphabet;
		}
		return false;
	}

	public static boolean bulstatValidation(TextField field, Label validationLabel)
	{
		boolean isEmpty = DataValidation.textFieldisEmpty(field, validationLabel, "The field is empty.");
		if (!isEmpty)
		{
			boolean isNumeric = DataValidation.textNumeric(field, validationLabel, "Wrong numeric format.");
			return isNumeric;
		}
		return false;
	}

	public static boolean emailValidation(TextField field, Label validationLabel)
	{
		boolean isEmpty = DataValidation.textFieldisEmpty(field, validationLabel, "The field is empty.");
		if (!isEmpty)
		{
			boolean isEmail = DataValidation.emailFormat(field, validationLabel, "Wrong email format.");
			return isEmail;
		}
		return false;

	}

	public static boolean numberValidation(TextField field, Label validationLabel)
	{
		boolean isEmpty = DataValidation.textFieldisEmpty(field, validationLabel, "The field is empty.");
		if (!isEmpty)
		{
			boolean isNumeric = DataValidation.textNumeric(field, validationLabel, "Wrong phone number format.");
			return isNumeric;
		}
		return false;
	}

	public static boolean passwordLength(TextField field, Label validationLabel)
	{
		boolean isEmpty = DataValidation.textFieldisEmpty(field, validationLabel, "The field is empty.");
		if (!isEmpty)
		{
			boolean isSixSymbolsLong = DataValidation.dataLength(field, validationLabel,
					"Password must be at least 6 characters long.", 6);
			return isSixSymbolsLong;
		}
		return false;
	}

	public static boolean priceValidation(TextField field, Label validationLabel)
	{
		boolean isEmpty = DataValidation.textFieldisEmpty(field, validationLabel, "The field is empty.");
		if (!isEmpty)
		{
			boolean isPrice = DataValidation.priceFormat(field, validationLabel, "Wrong price format.");
			return isPrice;
		}
		return false;

	}

	public static boolean streetNValidation(TextField field, Label validationLabel)
	{

		boolean isEmpty = DataValidation.textFieldisEmpty(field, validationLabel, "The field is empty.");
		if (!isEmpty)
		{
			boolean isStreetN = DataValidation.streetNFormat(field, validationLabel, "Wrong street format.");
			return isStreetN;
		}
		return false;

	}

	public static boolean usernameValidation(TextField field, Label validationLabel)
	{
		boolean isEmpty = DataValidation.textFieldisEmpty(field, validationLabel, "The field is empty.");
		if (!isEmpty)
		{
			/*
			 * boolean isUnique = DataValidation.isUnique("username", field,
			 * validationLabel, "This username is already taken!"); return isUnique;
			 */
			boolean isUsername = DataValidation.isUsername(field, validationLabel,
					"Username may contain only letters, numbers, _ , -, .");
			return isUsername;
		}

		return false;
	}
}
