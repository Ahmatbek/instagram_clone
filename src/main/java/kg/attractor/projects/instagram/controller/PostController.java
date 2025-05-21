package kg.attractor.projects.instagram.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.instagram.dto.PostDto;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public String savePost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/new_post";
    }

    @PostMapping
    public String savePost(
            @Valid PostDto postDto,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postDto);
            return "posts/new_post";
        }

        postService.savePost(postDto);
        return "redirect:/users/profile";
    }
}
