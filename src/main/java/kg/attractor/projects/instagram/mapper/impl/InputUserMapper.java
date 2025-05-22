package kg.attractor.projects.instagram.mapper.impl;

import kg.attractor.projects.instagram.dto.InputUserDto;
import kg.attractor.projects.instagram.mapper.Mapper;
import kg.attractor.projects.instagram.model.User;
import kg.attractor.projects.instagram.repository.UserRepository;
import kg.attractor.projects.instagram.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InputUserMapper implements Mapper<InputUserDto, User> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public InputUserDto mapToDto(User user) {
        return InputUserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .username(user.getUsername())
                .info(user.getInfo())
                .password(user.getPassword())
                .build();
    }

    @Override
    public User mapToEntity(InputUserDto inputUserDto) {
        User user = userRepository.findById(inputUserDto.getId())
                .orElseThrow(() -> new NoSuchElementException("user not found by id " + inputUserDto.getId()));

        user.setLogin(inputUserDto.getLogin());
        user.setUsername(inputUserDto.getUsername());
        user.setInfo(inputUserDto.getInfo());

        if (Util.isPasswordByCrypt(inputUserDto.getPassword()))
            user.setPassword(inputUserDto.getPassword());
        else
            user.setPassword(passwordEncoder.encode(inputUserDto.getPassword()));
        return user;
    }
}
