package com.jsc.oauth.repository;

import com.jsc.oauth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * The interface User repository.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Integer> {

    /**
     * Find by username users.
     *
     * @param username the username
     * @return the users
     */
    Users findByUsername(String username);

}


