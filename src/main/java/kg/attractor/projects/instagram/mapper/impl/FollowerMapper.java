package kg.attractor.projects.instagram.mapper.impl;

import kg.attractor.projects.instagram.dto.FollowerDto;
import kg.attractor.projects.instagram.mapper.Mapper;
import kg.attractor.projects.instagram.model.Follower;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class FollowerMapper implements Mapper<FollowerDto, Follower> {
    private final UserMapper userMapper;

    @Override
    public FollowerDto mapToDto(Follower entity) {
        return FollowerDto.builder()
                .id(entity.getId())
                .userReceiver(userMapper.mapToDto(entity.getUserReceiver()))
                .userFollower(userMapper.mapToDto(entity.getUserFollower()))
                .build();
    }

    @Override
    public Follower mapToEntity(FollowerDto dto) {
        Follower follower = new Follower();
        follower.setId(dto.getId());
        follower.setUserReceiver(userMapper.mapToEntity(dto.getUserReceiver()));
        follower.setUserFollower(userMapper.mapToEntity(dto.getUserFollower()));
        return follower;
    }
}
