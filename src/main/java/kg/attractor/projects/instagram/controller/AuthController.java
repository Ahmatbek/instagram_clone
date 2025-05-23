package kg.attractor.projects.instagram.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.model.User;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.UserService;
import kg.attractor.projects.instagram.storage.TemporalStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthorizedUserService authorizedUserService;
    private final TemporalStorage temporalStorage;

    @GetMapping("login")
    public String authLogin(Model model) {
        model.addAttribute("user", new User());
        return "auth/login";
    }

    @GetMapping("registration")
    public String registerForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("registration")
    public String registerPost(
            @Valid UserDto userDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "auth/register";
        }

        userService.registerUser(userDto);
        return "redirect:/users/profile";
    }

    @GetMapping("password/confirm")
    public String passwordConfirm() {
        return "auth/password_confirm";
    }

    @GetMapping("password/validate")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String passwordValidate(@RequestParam String password, Model model) {
        if (password == null || password.isBlank()) {
            model.addAttribute("password_format_error", "password cannot be blank or null");
            return "auth/password_confirm";
        }

        if (!authorizedUserService.isUserAuthenticated(password)) {
            model.addAttribute("password_valid_error", "password is not correct for auth user");
            return "auth/password_confirm";
        }

        temporalStorage.addData("password", password);
        return "redirect:/users/update/profile";
    }
}
