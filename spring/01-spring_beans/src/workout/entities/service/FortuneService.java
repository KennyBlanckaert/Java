package workout.entities.service;

// When using 'FortuneService' as an injection field, make sure Spring can get an unique implementation
// annotation @Qualifier(implemantation.class) sets the implementation to use
// (above the field, before the parameter of the constructor, before the parameter of the setter)
public interface FortuneService {

	public String getFortune();

}
