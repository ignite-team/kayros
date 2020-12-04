package com.example.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DniCheckerUtilTest {

	private String dni = "45909311T";
	private String regex = "^[XYZ\\d]\\d{7,7}[^UIOÑ\\d]$";
	private String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
	private String specialLetters = "XYZ";

	@Test
	public void testShouldFailWhenDniNotStringClass() {

		assertThat(dni).isInstanceOf(String.class);

	}

	@Test
	public void testShouldFailWhenDniIsNull() {

		assertThat(dni).isNotNull();

	}

	@Test
	public void testShouldFailWhenDniIsEmpty() {

		assertThat(dni).isNotEmpty();

	}

	@Test
	public void testShouldFailWhenDniTooLarge() {

		assertThat(dni.length()).isEqualTo(9);

	}

	@Test
	public void testShouldFailWhenDniNotMatchRegex() {

		assertThat(dni.toUpperCase().matches(regex)).isEqualTo(true);

	}

	@Test
	public void testShouldFailWhenDniEnsWithInvalidLetters() {

		assertThat(Character.toUpperCase(dni.charAt(dni.length() - 1))).isNotIn(specialLetters.toCharArray());

	}

	@Test
	public void testShouldFailWhenDniStartWithInvalidLetters() {

		assertThat(Character.toUpperCase(dni.charAt(0))).isNotIn("U", "I", "O", "Ñ");

	}

	@Test
	public void testShouldFailWhenDniInNumericPartContainsLetters() {

		assertThat(dni.toUpperCase().substring(1, dni.length() - 1).matches("\\w")).isEqualTo(false);

	}

	@Test
	public void testShouldFailWhenDniInvalidLetter() {

		if (specialLetters.indexOf(Character.toUpperCase(dni.charAt(0))) != -1) {

			dni = specialLetters.indexOf(dni.charAt(0)) + dni.substring(1);

		}

		int number = Integer.parseInt(dni.substring(0, dni.length() - 1));

		char letter = dni.charAt(dni.length() - 1);

		assertThat(Character.toUpperCase(letter)).isEqualTo(Character.toUpperCase(letters.charAt(number % 23)));

	}

}
