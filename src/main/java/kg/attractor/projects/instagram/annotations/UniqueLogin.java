package kg.attractor.projects.instagram.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import kg.attractor.projects.instagram.annotations.validators.UniqueLoginValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueLoginValidator.class)
public @interface UniqueLogin {
    String message() default "login should be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
