package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.dto.UserDto;

public interface UserService {
    UserDto findUserByLogin(String login);

    UserDto registerUser(UserDto userDto);

    boolean isLoginExist(String login);
}
