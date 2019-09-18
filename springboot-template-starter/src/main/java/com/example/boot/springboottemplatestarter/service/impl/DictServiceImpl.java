package com.example.boot.springboottemplatestarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.dict.payload.CreateDictPLO;
import com.example.boot.springboottemplatedomain.dict.payload.FindDictTablePLO;
import com.example.boot.springboottemplatedomain.dict.payload.ModifyDictOptionPLO;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDictOption;
import com.example.boot.springboottemplatestarter.exception.ResourceNotFoundException;
import com.example.boot.springboottemplatestarter.repository.DictOptionRepository;
import com.example.boot.springboottemplatestarter.repository.DictRepository;
import com.example.boot.springboottemplatestarter.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 21:58
 */
@Service
public class DictServiceImpl implements DictService {

    private final DictRepository dictRepository;
    private final DictOptionRepository dictOptionRepository;

    @Autowired
    public DictServiceImpl(DictRepository dictRepository, DictOptionRepository dictOptionRepository) {
        this.dictRepository = dictRepository;
        this.dictOptionRepository = dictOptionRepository;
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
            list.add(criteriaBuilder.equal(root.get("deleted").as(Boolean.class), false));

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaQuery.where(list.toArray(predicates)).getRestriction();
        }, pageable);

        return page;
    }

    @Override
    public SystemDict getDictById(Long dictId) {
        return dictRepository.findById(dictId).orElseThrow(() -> new ResourceNotFoundException("字典ID [" + dictId + "] 不存在"));
    }

    @Override
    public List<SystemDictOption> getOptionListByDictId(Long dictId) {
        return dictOptionRepository.findAllByDictId(dictId);
    }

    @Override
    public void createDict(CreateDictPLO plo) {
        SystemDict dict = new SystemDict();
        dict.setType(plo.getType());
        dict.setName(plo.getName());
        dict.setDescription(plo.getDescription());
        dict.setDeleted(false);
        dict.setCreateTime(new Timestamp(System.currentTimeMillis()));
        dictRepository.save(dict);

        plo.getOptions().forEach(option -> {
            SystemDictOption dictOption = new SystemDictOption();
            dictOption.setCode(option.getCode());
            dictOption.setValue(option.getValue());
            dictOption.setDict(dict);
            dictOptionRepository.save(dictOption);
        });
    }

    @Override
    public void deleteDict(Long optionId) {

    }
}
