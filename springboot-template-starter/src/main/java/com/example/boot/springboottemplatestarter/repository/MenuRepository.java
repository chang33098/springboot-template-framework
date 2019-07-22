package com.example.boot.springboottemplatestarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.page.persistent.SystemMenu;

/**
 * Created by ANdady on 2019/7/22.
 */
public interface MenuRepository extends JpaRepository<SystemMenu, Long> {
}
