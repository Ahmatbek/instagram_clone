package kg.attractor.projects.instagram.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private final String userLogin;
    private final String authority;
    private final String userPassword;

    public MyUserDetails(String userLogin, String authority, String userPassword) {
        this.userLogin = userLogin;
        this.authority = authority;
        this.userPassword = userPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userLogin;
    }
}
