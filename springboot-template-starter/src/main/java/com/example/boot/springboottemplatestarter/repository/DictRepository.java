package com.example.boot.springboottemplatestarter.repository;

import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by ANdady on 2019/9/4.
 */
@Repository
public interface DictRepository extends JpaRepository<SystemDict, Long>, JpaSpecificationExecutor<SystemDict> {
}
