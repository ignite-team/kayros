package es.ozona.kairos.employee.domain.model.valueobjects;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserAccountTest {

	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;

	private UserAccount emptyUserAccount;
	private UserAccount userAccount;

	@BeforeEach
	public void init() {

		username = "username";
		firstName = "firstname";
		lastName = "lastname";
		email = "email";
		preferredLanguage = "es_ES";

		emptyUserAccount = new UserAccount();
		userAccount = new UserAccount(username, firstName, lastName, email, preferredLanguage);

	}

	@Test
	protected void givenUserAccount_whenUserAccountGetUsername_thenReturnUsername() {

		assertThat(userAccount.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenUserAccount_whenUserAccountGetFirstName_thenReturnFirstName() {

		assertThat(userAccount.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenUserAccount_whemUserAccountGetLastName_thenReturnLastName() {

		assertThat(userAccount.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenUserAccount_whenUserAccountGetEmail_thenReturnEmail() {

		assertThat(userAccount.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenUserAccount_whenUserAccountGetPreferredLanguage_thenReturnlanguage() {

		assertThat(userAccount.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmptyUserAccount_whenEmptyUserAccountSetUsernameAndGetUsername_thenReturnUsername() {

		emptyUserAccount.setUsername(username);
		assertThat(emptyUserAccount.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmptyUserAccount_whenEmptyUserAccountSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyUserAccount.setFirstname(firstName);
		assertThat(emptyUserAccount.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmptyUserAccount_whenEmptyUserAccountSetLastNameAndGetLastName_thenReturnLastName() {

		emptyUserAccount.setLastname(lastName);
		assertThat(emptyUserAccount.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmptyUserAccounte_whenEmptyUserAccountSetEmailAndGetEmail_thenReturnEmail() {

		emptyUserAccount.setEmail(email);
		assertThat(emptyUserAccount.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmptyEmptyUserAccount_whenEmptyUserAccountSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyUserAccount.setPreferredLanguage(preferredLanguage);
		assertThat(emptyUserAccount.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmployeeResource_whenEqualsWithEmptyUserAccount_thenReturnFalse() {

		assertThat(userAccount.equals(emptyUserAccount)).isFalse();

	}

	@Test
	protected void givenUserAccount_whenEqualsWithNull_thenReturnFalse() {

		assertThat(userAccount.equals(null)).isFalse();

	}

	@Test
	protected void givenUserAccount_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(userAccount.equals(email)).isFalse();

	}

	@Test
	protected void givenUserAccount_whenEqualsWithUserAccount_thenReturnTrue() {

		assertThat(userAccount.equals(userAccount)).isTrue();

	}

}
