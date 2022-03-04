package io.jnslabs.sbbase.security;

import io.jnslabs.sbbase.model.entity.Usuario;
import io.jnslabs.sbbase.repository.SecureUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 15:59
 */
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final SecureUserRepository secureUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Usuario user = secureUserRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
