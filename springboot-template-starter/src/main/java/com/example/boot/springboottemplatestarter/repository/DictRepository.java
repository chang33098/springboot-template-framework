package com.example.boot.springboottemplatestarter.repository;

import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by ANdady on 2019/9/4.
 */
public interface DictRepository extends JpaRepository<SystemDict, Long>, JpaSpecificationExecutor<SystemDict> {

    SystemDict findByParentIdAndCode(Long parentId, Integer code);

    List<SystemDict> findAllByDictLevelAndDeletedIsFalse(Integer dictLevel);
}
