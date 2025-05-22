package kg.attractor.projects.instagram.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ImageService {
    ResponseEntity<?> getImageByUrl(String url) throws IOException;
}
