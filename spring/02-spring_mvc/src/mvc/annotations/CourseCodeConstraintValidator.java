package mvc.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

	private String[] courseCodePrefixes;
	
	// Initialize courseCodePrefix with the value in the annotation
	@Override
	public void initialize(CourseCode courseCode) {
		this.courseCodePrefixes = courseCode.value();
	}
	
	// Check if the input of the user starts with the given code
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			for (String prefix : this.courseCodePrefixes) {
				if (value.startsWith(prefix)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
