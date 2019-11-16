package com.example.boot.service.impl;

import com.example.boot.mapper.SystemPagePermissionRefMapper;
import com.example.boot.service.SystemPagePermissionRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPagePermissionRef;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Service
@Transactional
public class SystemPagePermissionRefServiceImpl extends ServiceImpl<SystemPagePermissionRefMapper, SystemPagePermissionRef> implements SystemPagePermissionRefService {


}
