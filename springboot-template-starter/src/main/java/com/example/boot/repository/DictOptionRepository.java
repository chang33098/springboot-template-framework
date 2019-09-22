package com.example.boot.repository;

import com.example.boot.springboottemplatedomain.dict.persistent.SystemDictOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictOptionRepository extends JpaRepository<SystemDictOption, Long> {

    List<SystemDictOption> findAllByDictId(Long dictId);

    void deleteAllByDictId(Long dictId);
}
