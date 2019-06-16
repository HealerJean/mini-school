/*
 * Powered By code-generator
 */
package com.hlj.proj.api.system;

import com.hlj.proj.dto.PageDTO;
import com.hlj.proj.dto.system.MenuDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MenuService
 * @Author DYB
 * @Date 2019/6/11 9:42
 * @Description 菜单管理接口
 * @Version V1.0
 */
public interface MenuService {

    /**
     * 菜单添加
     *
     * @param menu
     */
    void addMenu(MenuDTO menu, IdentityInfoDTO identityInfoDTO);

    /**
     * 菜单列表查询
     *
     * @param query
     * @return
     */
    PageDTO<MenuDTO> getMenus(MenuDTO query);

    /**
     * 菜单列表查询无分页,返回树状
     *
     * @param menuDTO
     * @return
     */
    Map<String, MenuDTO> getMenusNoPageToTree(MenuDTO menuDTO);

    /**
     * 菜单查询
     *
     * @param menuId
     * @return
     */
    MenuDTO getMenu(Integer menuId);

    /**
     * 菜单修改
     *
     * @param menuDTO
     */
    void updateMenu(MenuDTO menuDTO, IdentityInfoDTO identityInfoDTO);

    /**
     * 菜单删除
     *
     * @param menuIds
     */
    void deleteMenu(List<Long> menuIds);
}
