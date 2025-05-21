package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.model.User;
import kg.attractor.projects.instagram.repository.UserRepository;
import kg.attractor.projects.instagram.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by email " + username));

        return new MyUserDetails(
                user.getLogin(),
                user.getAuthority().getName(),
                user.getPassword()
        );
    }
}
