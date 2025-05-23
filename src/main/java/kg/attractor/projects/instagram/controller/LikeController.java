package kg.attractor.projects.instagram.controller;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.projects.instagram.dto.LikeDto;
import kg.attractor.projects.instagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("like/{postId}")
    public String like(@PathVariable Long postId, HttpServletRequest request) {
        likeService.likePost(postId);
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("dislike/{postId}")
    public String disLike(@PathVariable Long postId, HttpServletRequest request) {
        likeService.dislikePost(postId);
        return "redirect:" + request.getHeader("Referer");
    }
}
