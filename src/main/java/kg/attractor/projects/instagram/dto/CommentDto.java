package kg.attractor.projects.instagram.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDto {
    private Long id;
    private UserDto userDto;

    @NotNull(message = "post id cannot be null")
    private Long postId;

    @NotBlank(message = "message should not be blank")
    private String message;

    private String createdAt;
}
