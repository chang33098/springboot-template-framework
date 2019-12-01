package com.example.boot.springboottemplatebase.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.boot.springboottemplatebase.domain.systempage.payload.CreatePagePLO;
import com.example.boot.springboottemplatebase.domain.systempage.payload.ModifyPagePLO;
import com.example.boot.springboottemplatebase.mapper.SystemPageMapper;
import com.example.boot.springboottemplatebase.mapper.SystemPagePermissionRefMapper;
import com.example.boot.springboottemplatebase.service.SystemPagePermissionRefService;
import com.example.boot.springboottemplatebase.service.SystemPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systempage.persistent.SystemPage;
import com.example.boot.springboottemplatebase.domain.systempage.persistent.SystemPagePermissionRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Service
@Transactional
public class SystemPageServiceImpl extends ServiceImpl<SystemPageMapper, SystemPage> implements SystemPageService {

    private final SystemPagePermissionRefService pagePermissionRefService;
    private final SystemPagePermissionRefMapper pagePermissionRefMapper;

    @Autowired
    public SystemPageServiceImpl(SystemPagePermissionRefService pagePermissionRefService, SystemPagePermissionRefMapper pagePermissionRefMapper) {
        this.pagePermissionRefService = pagePermissionRefService;
        this.pagePermissionRefMapper = pagePermissionRefMapper;
    }

    @Override
    public void create(CreatePagePLO payload) {
        SystemPage page = new SystemPage();
        BeanUtil.copyProperties(payload, page);
        this.save(page); //保存页面信息

        payload.getPagePermissions().forEach(pagePermission -> {
            SystemPagePermissionRef pagePermissionRef = new SystemPagePermissionRef();
            pagePermissionRef.setPageId(page.getId());
            pagePermissionRef.setPermissionId(pagePermission.getPermissionId());
            pagePermissionRef.setInterceptUrls(pagePermission.getInterceptUrls());
            pagePermissionRefService.save(pagePermissionRef);
        });
    }

    @Override
    public void modify(ModifyPagePLO payload) {
        SystemPage page = this.getById(payload.getPageId());
        Assert.notNull(page, "不存在ID[{}]的数据", payload.getPageId());

        pagePermissionRefMapper.deleteRefByPageId(payload.getPageId()); //删除旧关联(硬性删除)

        payload.getPagePermissions().forEach(pagePermission -> {
            SystemPagePermissionRef pagePermissionRef = new SystemPagePermissionRef();
            pagePermissionRef.setPageId(page.getId());
            pagePermissionRef.setPermissionId(pagePermission.getPermissionId());
            pagePermissionRef.setInterceptUrls(pagePermission.getInterceptUrls());
            pagePermissionRefService.save(pagePermissionRef);
        });

        BeanUtil.copyProperties(payload, page);
        this.saveOrUpdate(page);
    }

    @Override
    public void delete(Long pageId) {
        int count = this.count(new QueryWrapper<SystemPage>().lambda().eq(SystemPage::getId, pageId));
        Assert.isTrue(count > 0, "不存在ID[{}]的数据", pageId);
        pagePermissionRefService.remove(new UpdateWrapper<SystemPagePermissionRef>().lambda()
                .eq(SystemPagePermissionRef::getPageId, pageId)); //调用SystemPagePermissionRef的逻辑删除方法
        this.removeById(pageId); //删除系统页面
    }
}
