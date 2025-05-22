package kg.attractor.projects.instagram.controller;

import kg.attractor.projects.instagram.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("images")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{imageUrl}")
    public ResponseEntity<?> getImageForPost(@PathVariable  String imageUrl) throws IOException {
        return imageService.getImageByUrl(imageUrl);
    }
}
