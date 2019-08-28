package com.example.boot.springboottemplatestarter.service.impl;

import com.example.boot.springboottemplatedomain.user.payload.FindUserTablePLO;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.example.boot.springboottemplatestarter.repository.UserRepository;
import com.example.boot.springboottemplatestarter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            if (plo.getRoleId() != 0) {
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

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaQuery.where(list.toArray(predicates)).getRestriction();
        }, pageable);

        return page;
    }

    @Override
    public Optional<SystemUser> securityGetUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
