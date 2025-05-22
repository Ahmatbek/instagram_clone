package kg.attractor.projects.instagram.dto;

import jakarta.validation.constraints.NotBlank;
import kg.attractor.projects.instagram.annotations.UniqueLogin;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputUserDto {
    private Long id;

    @NotBlank(message = "login cannot be blank")
    @UniqueLogin
    private String login;

    @NotBlank(message = "username cannot be blank")
    private String username;

    private String info;

    @NotBlank(message = "password cannot be blank")
    private String password;

    private MultipartFile avatar;
}
