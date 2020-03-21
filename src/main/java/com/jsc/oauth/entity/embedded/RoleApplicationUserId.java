package com.jsc.oauth.entity.embedded;

import com.jsc.oauth.entity.Application;
import com.jsc.oauth.entity.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class RoleApplicationUserId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "roleId")
    protected Role role;
    @ManyToOne
    @JoinColumn(name = "applicationId")
    protected Application application;
    @Column(name = "usersId")
    protected int usersId;

}