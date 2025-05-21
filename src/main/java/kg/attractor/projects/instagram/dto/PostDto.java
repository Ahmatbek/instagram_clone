package kg.attractor.projects.instagram.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class PostDto {
    private Long id;
    private Long userId;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotNull(message = "multipart file cannot be null")
    private MultipartFile photo;
}
