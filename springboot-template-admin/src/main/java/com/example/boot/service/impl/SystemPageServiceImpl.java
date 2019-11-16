package com.example.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.boot.mapper.SystemPageMapper;
import com.example.boot.model.page.payload.CreatePagePLO;
import com.example.boot.model.page.payload.ModifyPagePLO;
import com.example.boot.service.SystemPagePermissionRefService;
import com.example.boot.service.SystemPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPagePermissionRef;
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

    @Autowired
    public SystemPageServiceImpl(SystemPagePermissionRefService pagePermissionRefService) {
        this.pagePermissionRefService = pagePermissionRefService;
    }

    @Override
    public void create(CreatePagePLO plo) {
        SystemPage page = new SystemPage();
        BeanUtil.copyProperties(plo, page);
        this.save(page); //保存页面信息

        plo.getPagePermissions().forEach(pagePermission -> {
            SystemPagePermissionRef pagePermissionRef = new SystemPagePermissionRef();
            pagePermissionRef.setPageId(page.getId());
            pagePermissionRef.setPermissionId(pagePermission.getPermissionId());
            pagePermissionRef.setInterceptUrls(pagePermission.getInterceptUrls());
            pagePermissionRefService.save(pagePermissionRef);
        });
    }

    @Override
    public void modify(ModifyPagePLO plo) {
        SystemPage page = this.getById(plo.getPageId());
        Assert.notNull(page, "不存在ID[{}]的数据", plo.getPageId());

        pagePermissionRefService.remove(new UpdateWrapper<SystemPagePermissionRef>().lambda().eq(SystemPagePermissionRef::getPageId, plo.getPageId())); //删除旧关联
        plo.getPagePermissions().forEach(pagePermission -> {
            SystemPagePermissionRef pagePermissionRef = new SystemPagePermissionRef();
            pagePermissionRef.setPageId(page.getId());
            pagePermissionRef.setPermissionId(pagePermission.getPermissionId());
            pagePermissionRef.setInterceptUrls(pagePermission.getInterceptUrls());
            pagePermissionRefService.save(pagePermissionRef);
        });

        BeanUtil.copyProperties(plo, page);
        this.saveOrUpdate(page);
    }

    @Override
    public void delete(Long pageId) {
        int count = this.count(new QueryWrapper<SystemPage>().lambda().eq(SystemPage::getId, pageId));
        Assert.isTrue(count > 0, "不存在ID[{}]的数据", pageId);

        pagePermissionRefService.remove(new UpdateWrapper<SystemPagePermissionRef>().lambda().eq(SystemPagePermissionRef::getPageId, pageId)); //删除旧关联
        this.removeById(pageId); //删除系统页面
    }
}
