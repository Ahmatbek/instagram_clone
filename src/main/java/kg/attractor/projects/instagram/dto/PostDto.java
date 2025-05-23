package kg.attractor.projects.instagram.dto;

import jakarta.validation.constraints.NotBlank;
import kg.attractor.projects.instagram.annotations.NonEmptyPicture;
import kg.attractor.projects.instagram.marks.ValidationGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Getter
@Setter
@Builder
public class PostDto {
    private Long id;
    private UserDto userDto;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NonEmptyPicture(groups = ValidationGroup.OnCreate.class)
    private MultipartFile photo;

    private String imageUrl;

    private Long likesCount;
    private Boolean likedByCurrentUser;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PostDto postDto = (PostDto) o;
        return Objects.equals(id, postDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
