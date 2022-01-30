package es.ozona.kayros.webapp.vms.filters;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface AndExpression {
	String language() default "RSQL";

	String expression() default "";
}
