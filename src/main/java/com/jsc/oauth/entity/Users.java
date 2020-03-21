package com.jsc.oauth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Users implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String email;
    private String username;
    private String password;
    private boolean enabled;

    @Column(name = "account_locked")
    private boolean accountNonLocked;

    @Column(name = "account_expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_expired")
    private boolean credentialsNonExpired;

    @OneToMany(mappedBy = "roleApplicationUserId.usersId", fetch = FetchType.EAGER)
    private List<RoleApplicationUser> roleApplicationUsers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
