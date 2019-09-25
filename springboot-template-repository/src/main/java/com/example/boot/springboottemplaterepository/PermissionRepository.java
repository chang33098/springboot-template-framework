package com.example.boot.springboottemplaterepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by ANdady on 2019/7/22.
 */
@Repository
public interface PermissionRepository extends JpaRepository<SystemPermission, Long>, JpaSpecificationExecutor<SystemPermission> {
}