package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.InputUserDto;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.impl.InputUserMapper;
import kg.attractor.projects.instagram.mapper.impl.UserMapper;
import kg.attractor.projects.instagram.model.User;
import kg.attractor.projects.instagram.repository.UserRepository;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorizedUserServiceImpl implements AuthorizedUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final InputUserMapper inputUserMapper;

    @Override
    public UserDetails getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken))
            return (UserDetails) authentication.getPrincipal();

        throw new IllegalArgumentException("user is not authenticated");
    }

    @Override
    public UserDto getAuthorizedUser() {
        return userRepository.findUserByLogin(getAuthentication().getUsername())
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("auth user not found"));
    }

    @Override
    public Long getAuthorizedUserId() {
        return userRepository.findUserByLogin(getAuthentication().getUsername())
                .map(User::getId)
                .orElseThrow(() -> new NoSuchElementException("auth user not found"));
    }

    @Override
    public InputUserDto getAuthorizedUserInput() {
        return userRepository.findUserByLogin(getAuthentication().getUsername())
                .map(inputUserMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("auth user not found"));
    }
}
