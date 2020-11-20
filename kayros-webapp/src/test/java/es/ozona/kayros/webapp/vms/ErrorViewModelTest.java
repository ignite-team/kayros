package es.ozona.kayros.webapp.vms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ErrorViewModelTest {

	private ErrorViewModel vm;
	private String code;
	private String message;
	private String errorMessage;

	@BeforeEach
	public void init() {

		vm = new ErrorViewModel();
		code = "500";
		message = "Error";
		errorMessage = code + " - " + message;

	}

	@Test
	protected void givenInitializedViewModel_whenVMGetCode_thenReturnNull() {

		vm.init();
		assertThat(vm.getCode()).isNull();

	}

	@Test
	protected void givenInitializedViewModel_whenVMGetMessage_thenReturnNull() {

		vm.init();
		assertThat(vm.getMessage()).isNull();

	}

	@Test
	protected void givenViewModel_whenVMGetMessage_thenReturnNull() {

		assertThat(vm.getMessage()).isNull();

	}

	@Test
	protected void givenViewModel_whenVMGetCode_thenReturnNull() {

		assertThat(vm.getCode()).isNull();

	}

	@Test
	protected void givenViewModel_whenVMSetCodeAndGetCode_thenReturnCode() {

		vm.setCode(code);
		assertThat(vm.getCode()).isEqualTo(code);

	}

	@Test
	protected void givenViewModel_whenVMSetMessageGetMessage_thenReturnMessage() {

		vm.setMessage(message);
		assertThat(vm.getMessage()).isEqualTo(message);

	}

	@Test
	protected void givenViewModel_whenVMSetCodeAndSetMessageAndGetErrorMessage_thenReturnErrorMessage() {

		vm.setMessage(message);
		vm.setCode(code);
		assertThat(vm.getErrorMessage()).isEqualTo(errorMessage);

	}

}
