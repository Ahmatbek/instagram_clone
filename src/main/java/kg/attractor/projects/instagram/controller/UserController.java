package kg.attractor.projects.instagram.controller;

import kg.attractor.projects.instagram.service.AuthorizedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final AuthorizedUserService authorizedUserService;

    @GetMapping("profile")
    public String getProfile(Model model) {
        model.addAttribute("user", authorizedUserService.getAuthorizedUser());
        return "users/profile";
    }
}
