package mvc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
	
	// default course code
	public String[] value() default {"IW"};
	
	// default message attribute
	public String message() default "course codes must start with IW";
	
	// groups
	public Class<?>[] groups() default {};
	
	// payloads
	public Class<? extends Payload>[] payload() default {};

}
