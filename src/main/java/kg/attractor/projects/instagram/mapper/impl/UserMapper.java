package kg.attractor.projects.instagram.mapper.impl;

import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.Mapper;
import kg.attractor.projects.instagram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper implements Mapper<UserDto, User> {
    private final PasswordEncoder passwordEncoder;
    private final AuthorityMapper authorityMapper;
    private final PostMapper postMapper;

    @Override
    public UserDto mapToDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .avatar(entity.getAvatar())
                .info(entity.getInfo())
                .authority(authorityMapper.mapToDto(entity.getAuthority()))
                .posts(entity.getPosts() != null ? entity.getPosts().stream()
                        .map(postMapper::mapToDto)
                        .toList() : null)
                .build();
    }

    @Override
    public User mapToEntity(UserDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAvatar(dto.getAvatar());
        user.setInfo(dto.getInfo());
        user.setAuthority(authorityMapper.mapToEntity(dto.getAuthority()));
        user.setPosts(dto.getPosts() != null ? dto.getPosts().stream()
                .map(postMapper::mapToEntity)
                .toList() : null);
        return user;
    }
}
