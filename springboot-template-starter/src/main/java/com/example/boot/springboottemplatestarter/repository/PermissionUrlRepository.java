package com.example.boot.springboottemplatestarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermissionUrl;
import org.springframework.stereotype.Repository;

/**
 * Created by ANdady on 2019/7/22.
 */
@Repository
public interface PermissionUrlRepository extends JpaRepository<SystemPermissionUrl, Long> {
}
