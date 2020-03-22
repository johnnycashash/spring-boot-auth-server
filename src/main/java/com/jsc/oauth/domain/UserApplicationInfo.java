package com.jsc.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type User application info.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApplicationInfo {
    private String name;
    private List<RoleInfo> roles;

    /**
     * Instantiates a new User application info.
     *
     * @param name the name
     */
    public UserApplicationInfo(String name) {
        this.name = name;
    }
}
