package com.example.boot.springboottemplatestarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.dict.constants.DictLevel;
import com.example.boot.springboottemplatedomain.dict.payload.CreateDictOptionPLO;
import com.example.boot.springboottemplatedomain.dict.payload.CreateDictPLO;
import com.example.boot.springboottemplatedomain.dict.payload.FindDictTablePLO;
import com.example.boot.springboottemplatedomain.dict.payload.ModifyDictOptionPLO;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import com.example.boot.springboottemplatedomain.dict.response.GetParentDictListRO;
import com.example.boot.springboottemplatestarter.exception.ResourceNotFoundException;
import com.example.boot.springboottemplatestarter.repository.DictRepository;
import com.example.boot.springboottemplatestarter.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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


//    public Page<Order> getLicenseList(Order order, Pageable pageable) {
//        Specification<License> specification = new Specification<Order>() {
//            @Override
//            public Predicate toPredicate(Root<License> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> list = new ArrayList<Predicate>();
//                Join<Order, Express> expressJoin = root.join("express", JoinType.LEFT);
//                Join<Order, Product> proJoin = root.join("product", JoinType.LEFT);
//                Join<Order, Customer> customerJoin = root.join("customer", JoinType.LEFT);
//                if (null != order.getCustomer() && !StringUtils.isEmpty(order.getCustomer().getCode())) {
//                    list.add(criteriaBuilder.like(customerJoin.get("code").as(String.class), "%" + order.getCustomer().getCode() + "%"));
//                }
//                if (null != order.getCustomer() && !StringUtils.isEmpty(order.getCustomer().getName())) {
//                    list.add(criteriaBuilder.like(customerJoin.get("name").as(String.class), "%" + order.getCustomer().getName() + "%"));
//                }
//                if (null != order.getProduct() && !StringUtils.isEmpty(order.getProduct().getCode())) {
//                    list.add(criteriaBuilder.like(proJoin.get("code").as(String.class), "%" + order.getProduct().getCode() + "%"));
//                }
//                if (null != order.getProduct() && !StringUtils.isEmpty(order.getProduct().getName())) {
//                    list.add(criteriaBuilder.like(proJoin.get("name").as(String.class), "%" + order.getProduct().getName() + "%"));
//                }
//                if (null != order.getExpress() && !StringUtils.isEmpty(order.getExpress().getCode())) {
//                    list.add(criteriaBuilder.like(expressJoin.get("code").as(String.class), "%" + order.getExpress().getCode() + "%"));
//                }
//                if (!StringUtils.isEmpty(order.getCode())) {
//                    list.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + order.getCode() + "%"));
//                }
//                if (null != order.getCreateDate()) {
//                    list.add(criteriaBuilder.lessThan(root.get("createDate").as(Date.class), order.getCreateDate()));
//                }
//                Predicate[] p = new Predicate[list.size()];
//                return criteriaBuilder.and(list.toArray(p));
//            }
//        };
//        return orderDao.findAll(specification, pageable);
//    }

    @Override
    public Page<SystemDict> findDictTable(FindDictTablePLO plo) {
        int pageNo = plo.getPageNo();
        int limit = plo.getLimit();

        Pageable pageable = PageRequest.of(pageNo - 1, limit);

        Page<SystemDict> page = dictRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            Join<SystemDict, SystemDict> parentJoin = root.join("parent", JoinType.LEFT);
//            list.add(criteriaBuilder.equal(parentJoin.get("id").as(Long.class), plo.getParentId()));
            list.add(criteriaBuilder.equal(root.get("deleted").as(Boolean.class), false));

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaQuery.where(list.toArray(predicates)).getRestriction();
        }, pageable);

        return page;
    }

    @Override
    public void createDict(CreateDictPLO plo) {
//        SystemDict dict = new SystemDict();
//        dict.setDictLevel(DictLevel.PARENT.getLevel());
//        dict.setName(plo.getName());
//        dict.setType(plo.getType());
//        dict.setDescription(plo.getDescription());
//        dict.setDeleted(false);
//        dictRepository.save(dict);
//
//        plo.getOptions().forEach(option -> {
//            SystemDict dictOption = new SystemDict();
//            dictOption.setCode(option.getCode());
//            dictOption.setValue(option.getValue());
//            dictOption.setDeleted(false);
//            dictOption.setParent(dictOption);
//            dictRepository.save(dictOption);
//        });
    }

    @Override
    public void createDictOption(CreateDictOptionPLO plo) {
//        SystemDict dict = dictRepository.findById(plo.getParentId()).orElseThrow(() -> new ResourceNotFoundException("字典ID [" + plo.getParentId() + "] 不存在"));
//        plo.getDictOptions().forEach(option -> {
//            SystemDict dictOption = dictRepository.findByParentIdAndCode(dict.getId(), option.getCode());
//            if (dictOption == null) {
//                dictOption = new SystemDict();
//            }
//            dict.setDictLevel(DictLevel.OPTION.getLevel());
//            dictOption.setCode(option.getCode());
//            dictOption.setValue(option.getValue());
//            dictOption.setDeleted(false);
//            dictOption.setParent(dict);
//            dictRepository.save(dictOption);
//        });
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
        List<SystemDict> dictList = dictRepository.findAllByDictLevelAndDeletedIsFalse(DictLevel.PARENT.getLevel());
        List<GetParentDictListRO> dictROS = dictList.stream().map(dict -> {
            GetParentDictListRO dictRO = new GetParentDictListRO();
            dictRO.setId(dict.getId());
            dictRO.setName(dict.getName());
            return dictRO;
        }).collect(Collectors.toList());

        return dictROS;
    }
}
