package com.example.utils;

public class CheckDniUtil {

	private String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
	private String specialLetters = "XYZ";

	private String regex = "^[XYZ\\d]\\d{7,7}[^UIOÑ\\d]$";

	public Boolean checkDni(String dni) {

		boolean valid = false;

		if (dni.toUpperCase().matches(regex)) {

			char letter = dni.charAt(dni.length() - 1);

			if (specialLetters.indexOf(Character.toUpperCase(dni.charAt(0))) != -1) {

				dni = specialLetters.indexOf(dni.charAt(0)) + dni.substring(1);

			}

			int number = Integer.parseInt(dni.substring(0, dni.length() - 1));

			if (Character.toUpperCase(letter) == Character.toUpperCase(letters.charAt(number % 23))) {

				valid = true;

			}

		}

		return valid;

	}

}
