package com.jsc.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type User access.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccess {
    /**
     * The Username.
     */
    public String username;
    private List<UserApplicationInfo> applications;
}
