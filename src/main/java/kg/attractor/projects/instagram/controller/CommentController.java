package kg.attractor.projects.instagram.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.projects.instagram.dto.CommentDto;
import kg.attractor.projects.instagram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public String makeComment(
            @Valid CommentDto commentDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("comment", commentDto);
            return "redirect:/posts/comment" + commentDto.getPostId();
        }

        commentService.save(commentDto);
        return "redirect:/posts/comment/" + commentDto.getPostId();
    }

    @PostMapping("{commentId}")
    public String deleteComment(@PathVariable Long commentId, @RequestParam Long postId, HttpServletRequest request) {
        commentService.deleteCommentById(commentId, postId);
        return "redirect:" + request.getHeader("Referer");
    }
}
