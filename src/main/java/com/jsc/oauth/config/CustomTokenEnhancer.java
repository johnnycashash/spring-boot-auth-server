package com.jsc.oauth.config;

import com.jsc.oauth.domain.PermissionInfo;
import com.jsc.oauth.domain.RoleInfo;
import com.jsc.oauth.domain.UserAccess;
import com.jsc.oauth.domain.UserApplicationInfo;
import com.jsc.oauth.entity.Role;
import com.jsc.oauth.entity.RoleApplicationUser;
import com.jsc.oauth.entity.Users;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Custom token enhancer.
 */
public class CustomTokenEnhancer extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Users users = (Users) authentication.getPrincipal();

        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

        info.put("email", users.getEmail());
        info.put("useraccess", getUserAccess(users.getRoleApplicationUsers(), users.getUsername(), accessToken.getScope()));

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);

        return super.enhance(customAccessToken, authentication);
    }

    private UserAccess getUserAccess(List<RoleApplicationUser> roleApplicationUsers, String username, Set<String> scope) {

        Map<UserApplicationInfo, List<RoleApplicationUser>> applicationInfoListMap = roleApplicationUsers.stream()
                .filter(roleApplicationUser -> scope.contains(roleApplicationUser.getApplication().getName()))
                .collect(Collectors.groupingBy(roleApplicationUser ->
                        new UserApplicationInfo(roleApplicationUser.getApplication().getName())));

        applicationInfoListMap.forEach((userApplicationInfo, roleApplicationUsers1) -> {
            Map<RoleInfo, List<RoleApplicationUser>> roleInfoListMap = roleApplicationUsers1.stream()
                    .collect(Collectors.groupingBy(roleApplicationUser -> {
                        Role role = roleApplicationUser.getRole();
                        List<PermissionInfo> permissions = role.getPermissions().stream().map(permission ->
                                new PermissionInfo(permission.getName())).collect(Collectors.toList());
                        return new RoleInfo(role.getName(), permissions);
                    }));
            userApplicationInfo.setRoles(new ArrayList<>(roleInfoListMap.keySet()));
        });

        Set<UserApplicationInfo> userApplicationInfos = applicationInfoListMap.keySet();

        return new UserAccess(username, new ArrayList<>(userApplicationInfos));
    }
}