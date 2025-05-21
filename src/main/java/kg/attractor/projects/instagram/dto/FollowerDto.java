package kg.attractor.projects.instagram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowerDto {
    private Long id;
    private UserDto userFollower;
    private UserDto userReceiver;
}
