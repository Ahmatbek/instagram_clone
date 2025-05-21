package kg.attractor.projects.instagram.controller;

import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.FollowerService;
import kg.attractor.projects.instagram.service.PostService;
import kg.attractor.projects.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
