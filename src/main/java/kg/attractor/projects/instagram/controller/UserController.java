package kg.attractor.projects.instagram.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.instagram.dto.InputUserDto;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final AuthorizedUserService authorizedUserService;
    private final UserService userService;

    @GetMapping("profile")
    public String getProfile(Model model) {
        model.addAttribute("user", authorizedUserService.getAuthorizedUser());
        return "users/profile";
    }

    @GetMapping("update/profile")
    public String updateProfile(Model model) {
        model.addAttribute("inputUserDto", authorizedUserService.getAuthorizedUserInput());
        return "users/update_profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @Valid InputUserDto user,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("inputUserDto", user);
            return "users/update_profile";
        }
        userService.updateProfile(user);
        return "users/profile";
    }
}
