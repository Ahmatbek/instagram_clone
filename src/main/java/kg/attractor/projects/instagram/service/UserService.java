package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.dto.InputUserDto;
import kg.attractor.projects.instagram.dto.UserDto;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDto findUserByLogin(String login);

    UserDto registerUser(UserDto userDto);

    boolean isLoginExist(String login);

    UserDto updateProfile(InputUserDto inputUserDto) throws IOException;

    List<UserDto> getAllUsers();
}
