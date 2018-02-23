package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.base.api.ResponseCode;
import com.hwc.framework.modules.dao.MenuMapper;
import com.hwc.framework.modules.domain.DSysMenu;
import com.hwc.framework.modules.model.SysMenu;
import com.hwc.framework.modules.service.SysMenuService;
import com.hwc.mybatis.core.AbstractService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/09/22.
 */
@Service
public class SysMenuServiceImpl extends AbstractService<MenuMapper, SysMenu> implements SysMenuService {

    @Override
    public DSysMenu getOneMenuByCode(String code) {
        DSysMenu dMenu = new DSysMenu();
        Map<String, String> param = new HashMap<String, String>();
        param.put("code",code);
        SysMenu menu = this.mapper.selectOneBySelective(param);
        if(menu == null){
            return null;
        }
        covertToDMenu(dMenu,menu);
        return dMenu;
    }

    @Override
    public Response getOneMenu(Integer menuId) {
        if (FsUtils.strsEmpty(menuId)){
            return Response.fail(ResponseCode.MISSING_PARAMETER);
        }
        DSysMenu dMenu = new DSysMenu();
        SysMenu menu = this.mapper.selectByPrimaryKey(menuId);
        if(menu == null){
            return Response.fail(ResponseCode.OBJECT_IS_EMPTY);
        }
        covertToDMenu(dMenu,menu);
        return Response.success(dMenu);
    }

    @Override
    public Response addOneMenu(DSysMenu dMenu) {
        DSysMenu findMenu = this.getOneMenuByCode(dMenu.getCode());
        if (findMenu != null){
           return Response.fail("菜单标识已经存在");
        }else {
            SysMenu menu =  new SysMenu();
            menuSetPcode(dMenu);
            covertToMenu(menu,dMenu);
            this.mapper.insert(menu);
            return  Response.success();
        }
    }

    @Override
    public Response updateMenu(DSysMenu dMenu) {
        DSysMenu findMenu = this.getOneMenuByCode(dMenu.getCode());
        if (findMenu != null && !FsUtils.strsEmpty(findMenu.getId())){
           if (findMenu.getId().equals(dMenu.getId())){
               SysMenu menu =  new SysMenu();
               menuSetPcode(dMenu);
               covertToMenu(menu,dMenu);
               this.mapper.updateByPrimaryKeySelective(menu);
               return Response.success();
           }else {
               return Response.fail("菜单标识已存在");
           }
        }else {
            return Response.fail("对象不存在");
        }
    }

    @Override
    public Response deleteMenu(Integer menuId) {
        if (FsUtils.strsEmpty(menuId)){
            return Response.fail(ResponseCode.MISSING_PARAMETER);
        }
        this.mapper.deleteByPrimaryKey(menuId);
        return Response.success();
    }

    @Override
    public List<SysMenu> selectMenus(String menuName, Integer level) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name",menuName);
        param.put("levels",level);
        return this.mapper.selectBySelective(param);
    }

    @Override
    public List<DSysMenu> getMenuListByRoleId(Integer roleId) {
        List<DSysMenu> dMenuList = new ArrayList<DSysMenu>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleId",roleId);
        List<SysMenu> menuList = this.mapper.getMenuListByUserRole(roleId);
        for (int i = 0; i < menuList.size(); i++) {
            DSysMenu dMenu = new DSysMenu();
            covertToDMenu(dMenu,menuList.get(i));
            dMenuList.add(dMenu);
        }
        return dMenuList;
    }

    @Override
    public Response addMenu2(DSysMenu dMenu) {
        return Response.fail();
    }


    public void covertToDMenu (DSysMenu dMenu, SysMenu menu){
        try {
            BeanUtils.copyProperties(dMenu,menu);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void covertToMenu (SysMenu menu, DSysMenu dMenu){
        try {
            BeanUtils.copyProperties(menu,dMenu);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据请求的父级菜单编号设置pcode和层级
     */
    private void menuSetPcode(DSysMenu dmenu) {
        if (FsUtils.strsEmpty(dmenu.getPcode()) || dmenu.getPcode().equals("0")) {
            dmenu.setPcode("0");
            dmenu.setPcodes("[0],");
            dmenu.setLevels(1);
        } else {
            DSysMenu pDMenu = this.getOneMenuByCode(dmenu.getPcode());
            Integer pLevels = pDMenu.getLevels();
            dmenu.setPcode(pDMenu.getCode());
            dmenu.setLevels(pLevels + 1);
            dmenu.setPcodes(pDMenu.getPcodes() + "[" + pDMenu.getCode() + "],");
        }
    }
}
