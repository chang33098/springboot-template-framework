package com.example.boot.springboottemplatebase.service.impl;

import com.example.boot.springboottemplatebase.mapper.SystemDictOptionMapper;
import com.example.boot.springboottemplatebase.service.SystemDictOptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemdict.entity.SystemDictOptionEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chang_
 * @since 2019-11-13
 */
@Service
public class SystemDictOptionServiceImpl extends ServiceImpl<SystemDictOptionMapper, SystemDictOptionEntity> implements SystemDictOptionService {
}
