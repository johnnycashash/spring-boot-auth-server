package com.jsc.oauth.entity;

import com.jsc.oauth.entity.embedded.RoleApplicationUserId;
import lombok.Data;
import lombok.experimental.Delegate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
public class RoleApplicationUser {
    @EmbeddedId
    @Delegate
    RoleApplicationUserId roleApplicationUserId;
}
