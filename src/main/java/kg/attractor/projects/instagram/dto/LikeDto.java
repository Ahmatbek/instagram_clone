package kg.attractor.projects.instagram.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LikeDto {
    private Long id;
    private Long postId;
    private Long userId;
}
