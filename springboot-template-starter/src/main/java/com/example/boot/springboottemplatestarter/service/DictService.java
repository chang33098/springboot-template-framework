package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.dict.payload.*;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import com.example.boot.springboottemplatedomain.dict.response.GetParentDictListRO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DictService {

    Page<SystemDict> findDictTable(FindDictTablePLO plo);

    void createDict(CreateDictPLO plo);

    void createDictOption(CreateDictOptionPLO plo);

    void modifyDictOption(Long optionId, ModifyDictOptionPLO plo);

    void deleteDict(Long optionId);

    List<GetParentDictListRO> getParentDictList();
}
