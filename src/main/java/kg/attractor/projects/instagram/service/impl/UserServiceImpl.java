package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.InputUserDto;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.impl.InputUserMapper;
import kg.attractor.projects.instagram.mapper.impl.UserMapper;
import kg.attractor.projects.instagram.model.User;
import kg.attractor.projects.instagram.repository.UserRepository;
import kg.attractor.projects.instagram.security.MyUserDetails;
import kg.attractor.projects.instagram.service.AuthorityService;
import kg.attractor.projects.instagram.service.UserService;
import kg.attractor.projects.instagram.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;
    private final InputUserMapper inputUserMapper;

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

    @Override
    public UserDto updateProfile(InputUserDto inputUserDto) {
        MultipartFile multipartFile = inputUserDto.getAvatar();
        String avatarName = multipartFile != null && !multipartFile.isEmpty() ?
                Util.uploadResource(inputUserDto.getAvatar()) : "";

        User user = inputUserMapper.mapToEntity(inputUserDto);
        if (!avatarName.isBlank()) user.setAvatar(avatarName);

        UserDto userDto = userMapper.mapToDto(userRepository.save(user));

        MyUserDetails myUserDetails = new MyUserDetails(userDto.getLogin(), userDto.getAuthority().getName(), userDto.getPassword());
        Authentication authentication = new UsernamePasswordAuthenticationToken(myUserDetails, null, myUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userDto;
    }

    @Override
    public UserDto findUserById(Long id) {
        Assert.notNull(id, "id must not be null");

        return userRepository.findById(id)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("user not found by id " + id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToDto)
                .toList();
    }
}
