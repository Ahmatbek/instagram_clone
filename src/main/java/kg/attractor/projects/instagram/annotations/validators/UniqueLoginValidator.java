package kg.attractor.projects.instagram.annotations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.projects.instagram.annotations.UniqueLogin;
import kg.attractor.projects.instagram.service.UserService;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
    private final UserService userService;

    public UniqueLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isLoginExist(login);
    }
}
