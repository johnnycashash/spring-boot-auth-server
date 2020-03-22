package com.jsc.oauth.entity.embedded;

import com.jsc.oauth.entity.Application;
import com.jsc.oauth.entity.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * The type Role application user id.
 */
@Embeddable
@Data
public class RoleApplicationUserId implements Serializable {

    /**
     * The Role.
     */
    @ManyToOne
    @JoinColumn(name = "roleId")
    protected Role role;
    /**
     * The Application.
     */
    @ManyToOne
    @JoinColumn(name = "applicationId")
    protected Application application;
    /**
     * The Users id.
     */
    @Column(name = "usersId")
    protected int usersId;

}