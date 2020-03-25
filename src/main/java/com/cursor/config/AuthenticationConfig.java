package com.cursor.config;

import com.cursor.models.Role;
import com.cursor.models.User;
import com.cursor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class AuthenticationConfig implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authenticationToken = null;

        String username = Saml2RelyingPartyProperties.Registration.Signing.Credential.class.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findUserByUsername(username);

        if (user != null && username.equals(user.getUsername()) && BCrypt.checkpw(password, user.getPassword())) {

            Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
            authenticationToken = new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user)), user.getPassword(), grantedAuthorities);

        } else {
            throw new UsernameNotFoundException("User + " + username + " not found.");
        }
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        boolean isAdmin = (user.getRole().getName().equals(Role.ADMIN.getName())) ?
                grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN")) :
                grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

        return grantedAuthorities;
    }
}
