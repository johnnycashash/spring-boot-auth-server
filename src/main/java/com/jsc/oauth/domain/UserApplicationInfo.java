package com.jsc.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApplicationInfo {
    private String name;
    private List<RoleInfo> roles;

    public UserApplicationInfo(String name) {
        this.name = name;
    }
}
