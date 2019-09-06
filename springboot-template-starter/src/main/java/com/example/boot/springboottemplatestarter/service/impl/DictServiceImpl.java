package com.example.boot.springboottemplatestarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.dict.payload.CreateDictOptionPLO;
import com.example.boot.springboottemplatedomain.dict.payload.CreateDictPLO;
import com.example.boot.springboottemplatedomain.dict.payload.FindDictTablePLO;
import com.example.boot.springboottemplatedomain.dict.payload.ModifyDictOptionPLO;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import com.example.boot.springboottemplatedomain.dict.response.GetParentDictListRO;
import com.example.boot.springboottemplatedomain.page.constants.DictLevel;
import com.example.boot.springboottemplatestarter.exception.ResourceNotFoundException;
import com.example.boot.springboottemplatestarter.repository.DictRepository;
import com.example.boot.springboottemplatestarter.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 21:58
 */
@Service
public class DictServiceImpl implements DictService {

    private final DictRepository dictRepository;

    @Autowired
    public DictServiceImpl(DictRepository dictRepository) {
        this.dictRepository = dictRepository;
    }

    @Override
    public Page<SystemDict> findDictTable(FindDictTablePLO plo) {
        int pageNo = plo.getPageNo();
        int limit = plo.getLimit();

        Pageable pageable = PageRequest.of(pageNo - 1, limit);

        Page<SystemDict> page = dictRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), plo.getType()));
            list.add(criteriaBuilder.equal(root.get("deleted").as(Boolean.class), false));

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaQuery.where(list.toArray(predicates)).getRestriction();
        }, pageable);

        return page;
    }

    @Override
    public void createDict(CreateDictPLO plo) {
        SystemDict dict = new SystemDict();
        dict.setType(plo.getType());
        dict.setDescription(plo.getDescription());
        dictRepository.save(dict);

        plo.getOptions().forEach(option -> {
            SystemDict dictOption = new SystemDict();
            dictOption.setCode(option.getCode());
            dictOption.setValue(option.getValue());
            dictOption.setParent(dictOption);
            dictRepository.save(dictOption);
        });
    }

    @Override
    public void createDictOption(CreateDictOptionPLO plo) {
        SystemDict dict = dictRepository.findById(plo.getParentId()).orElseThrow(() -> new ResourceNotFoundException("字典ID [" + plo.getParentId() + "] 不存在"));
        plo.getDictOptions().forEach(option -> {
            SystemDict dictOption = dictRepository.findByParentIdAndCode(dict.getId(), option.getCode());
            if (dictOption == null) {
                dictOption = new SystemDict();
            }
            dictOption.setCode(option.getCode());
            dictOption.setValue(option.getValue());
            dictOption.setParent(dict);
            dictRepository.save(dictOption);
        });
    }

    @Override
    public void modifyDictOption(Long optionId, ModifyDictOptionPLO plo) {
        SystemDict option = dictRepository.findById(optionId).orElseThrow(() -> new ResourceNotFoundException("字典ID [" + optionId + "] 不存在"));
        BeanUtil.copyProperties(plo, option);
        dictRepository.save(option);
    }

    @Override
    public void deleteDict(Long optionId) {

    }

    @Override
    public List<GetParentDictListRO> getParentDictList() {
        List<SystemDict> dictList = dictRepository.findAllByDictLevelAndDeletedIsFalse(DictLevel.PARENT.getType());
        List<GetParentDictListRO> dictROS = dictList.stream().map(dict -> {
            GetParentDictListRO dictRO = new GetParentDictListRO();
            dictRO.setId(dict.getId());
            dictRO.setName(dict.getName());
            return dictRO;
        }).collect(Collectors.toList());

        return dictROS;
    }
}
