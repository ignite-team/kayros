package es.ozona.micro.core.application.internal.exceptions;

/**
 * {@code ApplicationException} is a subclass which wraps RuntimeException generates by business logic. 
 *
 * <p>
 * {@code ApplicationException} and its subclasses are <em>unchecked exceptions</em>. Unchecked exceptions do <em>not</em> need to be declared in a method or
 * constructor's {@code throws} clause if they can be thrown by the execution of the method or constructor and propagate outside the method or constructor
 * boundary.
 *
 * @author Xose
 * @since 1.0
 */
public abstract class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new runtime exception with {@code null} as its detail message. The cause is not initialized, and may subsequently be initialized by a call
	 * to {@link #initCause}.
	 */
	public ApplicationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and cause.
	 * <p>
	 * Note that the detail message associated with {@code cause} is <i>not</i> automatically incorporated in this runtime exception's detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
	 * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that
	 *                the cause is nonexistent or unknown.)
	 * @since 1.0
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new runtime exception with the specified detail message. The cause is not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
	 * @since 1.0
	 */
	public ApplicationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new runtime exception with the specified cause and a detail message of {@code (cause==null ? null : cause.toString())} (which typically
	 * contains the class and detail message of {@code cause}). This constructor is useful for runtime exceptions that are little more than wrappers for other
	 * throwables.
	 *
	 * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the
	 *              cause is nonexistent or unknown.)
	 * @since 1.0
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new runtime exception with the specified detail message, cause, suppression enabled or disabled, and writable stack trace enabled or
	 * disabled.
	 *
	 * @param message            the detail message.
	 * @param cause              the cause. (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
	 * @param enableSuppression  whether or not suppression is enabled or disabled
	 * @param writableStackTrace whether or not the stack trace should be writable
	 *
	 * @since 1.0
	 */
	public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
