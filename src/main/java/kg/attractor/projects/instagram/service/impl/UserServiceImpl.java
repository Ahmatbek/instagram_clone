package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.impl.UserMapper;
import kg.attractor.projects.instagram.repository.UserRepository;
import kg.attractor.projects.instagram.service.AuthorityService;
import kg.attractor.projects.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityService authorityService;

    @Override
    public UserDto findUserByLogin(String login) {
        Assert.isTrue(login != null && !login.isBlank(), "login should not be blank");

        return userRepository.findUserByLogin(login)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("user not found by login " + login));
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        userDto.setAuthority(authorityService.findByAuthorityName("USER"));
        return userMapper.mapToDto(userRepository.save(userMapper.mapToEntity(userDto)));
    }

    @Override
    public boolean isLoginExist(String login) {
        if (login == null || login.isBlank())
            return false;

        return userRepository.findUserByLogin(login).isPresent();
    }
}
