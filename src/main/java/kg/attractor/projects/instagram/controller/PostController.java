package kg.attractor.projects.instagram.controller;

import kg.attractor.projects.instagram.dto.PostDto;
import kg.attractor.projects.instagram.marks.ValidationGroup;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.CommentService;
import kg.attractor.projects.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final AuthorizedUserService authorizedUserService;
    private final CommentService commentService;

    @GetMapping
    public String savePost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/new_post";
    }

    @PostMapping
    public String savePost(
            @Validated(ValidationGroup.OnCreate.class) PostDto postDto,
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

    @GetMapping("update/{postId}")
    public String updatePost(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.findPostById(postId));
        return "posts/update_post";
    }

    @PostMapping("update")
    public String updatePost(
            @Validated(ValidationGroup.OnUpdate.class) PostDto postDto,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postDto);
            return "posts/update_post";
        }

        postService.updatePostDto(postDto);
        return "redirect:/users/profile";
    }

    @PostMapping("{id}")
    public String deletePost(
            @PathVariable Long id
    ){
        postService.deletePost(id);
        return "redirect:/users/profile";
    }

    @GetMapping("user")
    public String usersPosts(Model model) {
        postService.getUsersPosts(authorizedUserService.getAuthorizedUser().getId());
        model.addAttribute("posts", postService.getAllPosts());
        return "posts/all_posts";
    }

    @GetMapping("comment/{postId}")
    public String commentPost(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            Model model
    ) {
        model.addAttribute("post", postService.findPostById(postId));
        model.addAttribute("comments", commentService.findAllCommentByPostId(postId, page, size));
        return "posts/post_comments";
    }

    @GetMapping("/all")
    public String allPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts/all_posts";
    }

}
