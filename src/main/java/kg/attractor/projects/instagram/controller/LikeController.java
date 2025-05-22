package kg.attractor.projects.instagram.controller;

import kg.attractor.projects.instagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("like/{postId}")
    public String like(@PathVariable Long postId) {
        likeService.likePost(postId);
        return "redirect:/users/profile";
    }

    @PostMapping("dislike/{postId}")
    public String disLike(@PathVariable Long postId) {
        likeService.dislikePost(postId);
        return "redirect:/users/profile";
    }
}
