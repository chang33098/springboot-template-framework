package com.example.boot.springboottemplatestarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermissionUrl;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ANdady on 2019/7/22.
 */
@Repository
public interface PermissionUrlRepository extends JpaRepository<SystemPermissionUrl, Long>, JpaSpecificationExecutor<SystemPermissionUrl> {

    /**
     * 通过权限ID获取对应的matchUrl
     *
     * @param permissionId 权限ID
     * @return matchUrls
     */
    List<SystemPermissionUrl> findAllByPermissionId(Long permissionId);

    /**
     * 通过权限ID删除对应的数据
     *
     * @param permissionId 权限ID
     */
    void deleteAllByPermissionId(Long permissionId);
}
