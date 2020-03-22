package com.jsc.oauth.entity;

import com.jsc.oauth.entity.embedded.RoleApplicationUserId;
import lombok.Data;
import lombok.experimental.Delegate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * The type Role application user.
 */
@Entity
@Data
public class RoleApplicationUser {
    /**
     * The Role application user id.
     */
    @EmbeddedId
    @Delegate
    RoleApplicationUserId roleApplicationUserId;
}
