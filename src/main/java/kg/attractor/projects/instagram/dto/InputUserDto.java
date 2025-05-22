package kg.attractor.projects.instagram.dto;

import jakarta.validation.constraints.NotBlank;
import kg.attractor.projects.instagram.annotations.NonEmptyPicture;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputUserDto {
    private Long id;

    @NotBlank(message = "username cannot be blank")
    private String username;

    private String info;

    private MultipartFile avatar;
}
