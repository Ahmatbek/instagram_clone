package kg.attractor.projects.instagram.controller;

import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.FollowerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
@Controller
@RequestMapping("followers")
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;
    private final AuthorizedUserService authorizedUserService;
    @GetMapping("userFollower")
    public String getFollowers(Model model) {
        model.addAttribute("followers", followerService.userFollowers(authorizedUserService.getAuthorizedUser().getId()));
        return "follower/followers";
    }
    @GetMapping("userReceiver")
    public String getReceivers(Model model) {
        model.addAttribute("receivers", followerService.userReceivers(authorizedUserService.getAuthorizedUser().getId()));
        return "follower/receivers";
    }

    @PostMapping("{login}")
    public String postFollower(@PathVariable String login) {
        followerService.follow(login);
        return "redirect:/users/profile";

    }
}
