package kg.attractor.projects.instagram.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.instagram.dto.InputUserDto;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.FollowerService;
import kg.attractor.projects.instagram.service.PostService;
import kg.attractor.projects.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final AuthorizedUserService authorizedUserService;
    private final PostService postService;
    private final FollowerService followerService;
    private final UserService userService;

    @GetMapping("profile")
    public String getProfile(Model model) {
        Long userId = authorizedUserService.getAuthorizedUser().getId();
        model.addAttribute("user", authorizedUserService.getAuthorizedUser());
        model.addAttribute("posts", postService.getUsersPosts(userId));
        model.addAttribute("followers", followerService.numberOfFollowers(userId));
        model.addAttribute("receiver", followerService.numberOfReceivers(userId));
        return "users/profile";
    }

    @GetMapping("update/profile")
    public String updateProfile(Model model) {
        model.addAttribute("inputUserDto", authorizedUserService.getAuthorizedUserInput());
        return "users/update_profile";
    }

    @PostMapping("update/profile")
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
        return "redirect:/users/profile";
    }

    @GetMapping("profile/{login}")
    public String getProfile(@PathVariable String login,
                             Model model) {
        Long userId = userService.findUserByLogin(login).getId();
        model.addAttribute("user", userService.findUserByLogin(login));
        model.addAttribute("posts", postService.getUsersPosts(userId));
        model.addAttribute("followers", followerService.numberOfFollowers(userId));
        model.addAttribute("receiver", followerService.numberOfReceivers(userId));
        return "users/follower_profile";
    }
    @GetMapping()
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ofNullable(userService.getAllUsers());
    }
    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "search";
    }

}
