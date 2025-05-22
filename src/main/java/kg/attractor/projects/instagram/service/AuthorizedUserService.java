package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.dto.InputUserDto;
import kg.attractor.projects.instagram.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthorizedUserService {
    UserDetails getAuthentication();

    UserDto getAuthorizedUser();

    Long getAuthorizedUserId();

    InputUserDto getAuthorizedUserInput();
}
