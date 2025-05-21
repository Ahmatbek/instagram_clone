package kg.attractor.projects.instagram.dto;

import jakarta.validation.constraints.NotBlank;
import kg.attractor.projects.instagram.annotations.UniqueLogin;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "login cannot be blank")
    @UniqueLogin(message = "user login should be unique")
    private String login;

    @NotBlank(message = "username cannot be blank")
    private String username;

    @NotBlank(message = "password cannot be blank")
    private String password;

    private String avatar;

    private String info;
    private AuthorityDto authority;
    private List<PostDto> posts;
}
