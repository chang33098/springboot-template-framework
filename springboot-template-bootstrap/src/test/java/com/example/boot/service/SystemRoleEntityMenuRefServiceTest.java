package com.example.boot.service;

import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetRoleMenuListByRoleIdVO;
import com.example.boot.springboottemplatebase.service.SystemRoleMenuRefService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [SystemRoleMenuRefService]测试用例
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class SystemRoleEntityMenuRefServiceTest {

    @Autowired
    private SystemRoleMenuRefService roleMenuRefService;

    @Test
    public void securityGetRoleMenuListByRoleIdTest() {
        final Long roleId = 1L; //角色ID
        List<SecurityGetRoleMenuListByRoleIdVO> roleMenuList = roleMenuRefService.securityGetRoleMenuListByRoleId(roleId);
        roleMenuList.forEach(roleMenu -> log.info(roleMenu.toString()));
    }
}
