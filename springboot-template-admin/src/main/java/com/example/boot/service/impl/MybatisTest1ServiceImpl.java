package com.example.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.MybatisTest1Mapper;
import com.example.boot.model.mybatistest.MybatisTest1;
import com.example.boot.service.MybatisTest1Service;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chang_
 * @since 2019-11-10
 */
@Service
public class MybatisTest1ServiceImpl extends ServiceImpl<MybatisTest1Mapper, MybatisTest1> implements MybatisTest1Service {
}
