package kg.attractor.projects.instagram.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.model.User;
import kg.attractor.projects.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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
}
