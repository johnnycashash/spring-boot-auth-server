package com.jsc.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Role info.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleInfo {
    private String name;
    private List<PermissionInfo> permissions;

}
