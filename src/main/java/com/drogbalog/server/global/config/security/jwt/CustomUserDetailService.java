package com.drogbalog.server.global.config.security.jwt;

import com.drogbalog.server.global.config.security.auth.Role;
import com.drogbalog.server.user.domain.entity.UserEntity;
import com.drogbalog.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found UserName"));

        return new User(userEntity.getEmail() , userEntity.getPassword() , getGrantedAuthorities());
    }

    private Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.toString()));

        return grantedAuthorities;
    }
}
