package com.example.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.model.user.payload.CreateUserPLO;
import com.example.boot.model.user.payload.FindUserTablePLO;
import com.example.boot.model.user.payload.ModifyUserPLO;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatedomain.user.constants.UserStatus;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.example.boot.exception.ResourceNotFoundException;
import com.example.boot.service.RoleService;
import com.example.boot.service.UserService;
import com.example.boot.springboottemplaterepository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/28 15:06
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<SystemUser> findUserTable(FindUserTablePLO plo) {
        int pageNo = plo.getPageNo();
        int limit = plo.getLimit();

        Pageable pageable = PageRequest.of(pageNo - 1, limit);
        Page<SystemUser> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (plo.getRoleId() != null) {
                list.add(criteriaBuilder.equal(root.get("role_id").as(Long.class), plo.getRoleId()));
            }
            if (!StringUtils.isEmpty(plo.getUsername())) {
                list.add(criteriaBuilder.like(root.get("username").as(String.class), "%" + plo.getUsername() + "%"));
            }
            if (!StringUtils.isEmpty(plo.getNickname())) {
                list.add(criteriaBuilder.like(root.get("nickname").as(String.class), "%" + plo.getNickname() + "%"));
            }
            if (!StringUtils.isEmpty(plo.getPhone())) {
                list.add(criteriaBuilder.like(root.get("phone").as(String.class), "%" + plo.getPhone() + "%"));
            }
            if (plo.getStatus() != null) {
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), plo.getStatus()));
            }
            list.add(criteriaBuilder.equal(root.get("deleted").as(Boolean.class), false));

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaQuery.where(list.toArray(predicates)).getRestriction();
        }, pageable);

        return page;
    }

    @Override
    public SystemUser getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("用户ID [" + userId + "] 不存在"));
    }

    @Override
    public void createUser(CreateUserPLO plo) {
        SystemUser user = new SystemUser();
        BeanUtil.copyProperties(plo, user, "password"); //密码另外进行加密处理

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String BCryptpassword = encoder.encode(plo.getPassword());
        user.setPassword(BCryptpassword);

        SystemRole role = roleService.getRoleById(plo.getRoleId());
        user.setRole(role);

        user.setStatus(UserStatus.ENABLED.getStatus());
        user.setDeleted(false);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));

        userRepository.save(user);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String BCryptpassword = encoder.encode("123456");
        System.out.println(BCryptpassword);
    }

    @Override
    public void modifyUser(Long userId, ModifyUserPLO plo) {
        SystemUser user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("用户ID [" + userId + "] 不存在"));
        BeanUtil.copyProperties(plo, user);

        SystemRole role = roleService.getRoleById(plo.getRoleId());
        user.setRole(role);

        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        SystemUser user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("用户ID [" + userId + "] 不存在"));
        user.setDeleted(true);

        userRepository.save(user);
    }
}
