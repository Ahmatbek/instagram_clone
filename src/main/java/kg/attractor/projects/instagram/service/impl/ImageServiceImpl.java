package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.service.ImageService;
import kg.attractor.projects.instagram.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public ResponseEntity<?> getImageByUrl(String url) throws IOException {
        MediaType mediaType = Util.defineFileType(url);
        return Util.getOutputResource(url, mediaType);
    }
}
