package com.example.boot.springboottemplatestarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/22 23:13
 */
public interface UserRepository extends JpaRepository<SystemUser, Long> {
}
