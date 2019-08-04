package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermissionUrl;

import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/3 17:07
 */
public interface PermissionService {

    List<SystemPermissionUrl> findAllPermissionUrl();
}
