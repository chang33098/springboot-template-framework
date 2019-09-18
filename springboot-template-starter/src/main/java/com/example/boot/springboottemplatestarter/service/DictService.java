package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.dict.payload.*;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDictOption;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DictService {

    Page<SystemDict> findDictTable(FindDictTablePLO plo);

    SystemDict getDictById(Long dictId);

    List<SystemDictOption> getOptionListByDictId(Long dictId);

    void createDict(CreateDictPLO plo);

    void deleteDict(Long optionId);
}
