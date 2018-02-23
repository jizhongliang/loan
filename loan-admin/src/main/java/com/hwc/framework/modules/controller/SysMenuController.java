package com.hwc.framework.modules.controller;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSysMenu;
import com.hwc.framework.modules.model.SysMenu;
import com.hwc.framework.modules.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 2017/09/22.
 */
@RestController
@RequestMapping("/api/admin/sys/menu")
public class SysMenuController {
    private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);

    @Resource
    private SysMenuService menuService;

    /**
     * 新增菜单
     */
    @PostMapping("/addOne")
    public Response addMenu(@RequestBody DSysMenu menu) {
        Response response = menuService.addOneMenu(menu);
        return response;
    }


    /**
     *编辑菜单
     */
    @PostMapping("/updateOne")
    public Response updateOne(@RequestBody DSysMenu menu) {
        Response response = menuService.updateMenu(menu);
        return response;
    }

    /**
     * 获取一个菜单
     */
    @PostMapping("/getOne")
    public Response getOne(@RequestBody IdRequest<Integer> menu) {
        Response response = menuService.getOneMenu(menu.getId());
        return response;
    }

    /**
     * 删除菜单
     */
    @PostMapping("/deleteOne")
    public Response delete(@RequestBody IdRequest<Integer> menu) {
        Response response =  menuService.deleteMenu(menu.getId());
        return response;
    }

    /**
     * 获取菜单列表
     */
    @PostMapping("/list")
    public Response list(@RequestBody DSysMenu dMenu) {
        List<SysMenu> menuList = menuService.selectMenus(dMenu.getName(), dMenu.getLevels());
        return Response.success(menuList);
    }

    /**
     * 获取菜单列表
     */
    @PostMapping("/listByRole")
    public Response menuList(@RequestBody IdRequest<Integer> role) {
        List<DSysMenu> menuList = menuService.getMenuListByRoleId(role.getId());
        return Response.success(menuList);
    }
}
