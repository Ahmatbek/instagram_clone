package kg.attractor.projects.instagram.annotations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.projects.instagram.annotations.UniqueLogin;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.UserService;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
    private final UserService userService;
    private final AuthorizedUserService authorizedUserService;

    public UniqueLoginValidator(UserService userService, AuthorizedUserService authorizedUserService) {
        this.userService = userService;
        this.authorizedUserService = authorizedUserService;
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isLoginExist(login) || authorizedUserService.getAuthorizedUser().getLogin().equals(login);
    }
}
