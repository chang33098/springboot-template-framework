package com.example.boot.springboottemplaterepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/22 23:13
 */
@Repository
public interface UserRepository extends JpaRepository<SystemUser, Long>, JpaSpecificationExecutor<SystemUser> {

    Optional<SystemUser> findByUsername(String username);
}