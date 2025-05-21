package kg.attractor.projects.instagram.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "avatar cannot be null")
    private MultipartFile avatar;
}
