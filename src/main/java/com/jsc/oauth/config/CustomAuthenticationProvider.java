package com.jsc.oauth.config;

import com.jsc.oauth.entity.Role;
import com.jsc.oauth.entity.RoleApplicationUser;
import com.jsc.oauth.entity.Users;
import com.jsc.oauth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = (String) authentication.getCredentials();
        if (externalAuthentication()) {
            Users users = customUserDetailsService.findByUsername(name);
            List<RoleApplicationUser> roleApplicationUsers;
            @SuppressWarnings("unchecked")
            String scope = ((Map<String, String>) authentication.getDetails()).get("scope");
            if (scope == null || scope.equals("")) {
                roleApplicationUsers = users.getRoleApplicationUsers();
            } else {
                List<String> scopeList = Arrays.asList(scope.split("[+]"));
                roleApplicationUsers = users.getRoleApplicationUsers().stream().
                        filter(roleApplicationUser -> scopeList.contains(roleApplicationUser.getApplication().getName()))
                        .collect(Collectors.toList());
            }
            Set<GrantedAuthority> grantedAuthorities = populateAuthorities(roleApplicationUsers);
            return new UsernamePasswordAuthenticationToken(users, password, grantedAuthorities);
        } else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    private Set<GrantedAuthority> populateAuthorities(List<RoleApplicationUser> roleApplicationUsers) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        roleApplicationUsers.forEach(roleApplicationUser -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(roleApplicationUser.getApplication().getName()));
            Role role = roleApplicationUser.getRole();
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName())));
        });
        return grantedAuthorities;
    }


    private boolean externalAuthentication() {
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
