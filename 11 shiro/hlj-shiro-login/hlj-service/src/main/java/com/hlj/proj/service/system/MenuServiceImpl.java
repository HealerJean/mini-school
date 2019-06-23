/*
 * Powered By code-generator
 */
package com.hlj.proj.service.system;

import com.hlj.proj.api.system.MenuService;
import com.hlj.proj.data.dao.mybatis.manager.system.ScfSysMenuManager;
import com.hlj.proj.data.pojo.system.ScfSysMenu;
import com.hlj.proj.data.pojo.system.ScfSysMenuPage;
import com.hlj.proj.data.pojo.system.ScfSysMenuQuery;
import com.hlj.proj.dto.PageDTO;
import com.hlj.proj.dto.system.MenuDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;
import com.hlj.proj.enums.MenuTypeEnum;
import com.hlj.proj.enums.ResponseEnum;
import com.hlj.proj.enums.StatusEnum;
import com.hlj.proj.exception.BusinessException;
import com.hlj.proj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @ClassName MenuServiceImpl
 * @Author DYB
 * @Date 2019/6/11 9:42
 * @Description 菜单管理实现
 * @Version V1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private ScfSysMenuManager menuManager;

    /**
     * 新增菜单
     *
     * @param menuDTO
     */
    @Override
    public void addMenu(MenuDTO menuDTO, IdentityInfoDTO identityInfoDTO) {
        ScfSysMenuQuery query = new ScfSysMenuQuery();
        query.setMenuName(menuDTO.getMenuName());
        query.setRefSystemCode(menuDTO.getSystemCode());
        query.setPid(menuDTO.getPid());
        ScfSysMenu result = menuManager.findByQueryContion(query);
        if (result != null) {
            if (result.getStatus().equals(StatusEnum.生效.code)) {
                throw new BusinessException(ResponseEnum.菜单不存在.code);
            } else {
                //存在已经被删除过的数据，还原为有效状态
                result.setUrl(menuDTO.getUrl());
                result.setDesc(menuDTO.getDesc());
                result.setSort(menuDTO.getSort());
                result.setIcon(menuDTO.getIcon());
                result.setPchain(getPchain(result.getPid()));
                result.setStatus(StatusEnum.生效.code);
                result.setUpdateUser(identityInfoDTO.getUserId());
                result.setUpdateName(identityInfoDTO.getUsername());
                menuManager.updateSelective(result);
            }
        } else {
            ScfSysMenu menu = BeanUtils.dtoToMenu(menuDTO);
            menu.setPchain(getPchain(menu.getPid()));
            menu.setStatus(StatusEnum.生效.code);
            menu.setCreateUser(identityInfoDTO.getUserId());
            menu.setCreateName(identityInfoDTO.getUsername());
            menuManager.insertSelective(menu);
        }
    }

    /**
     * 菜单列表查询，根据系统code，父级id，名称模糊查询
     *
     * @param dto
     * @return
     */
    @Override
    public PageDTO<MenuDTO> getMenus(MenuDTO dto) {
        ScfSysMenuQuery query = new ScfSysMenuQuery();
        query.setRefSystemCode(dto.getSystemCode());
        query.setMenuName(dto.getMenuName());
        query.setMenuType(dto.getMenuType());
        query.setPid(dto.getPid());
        if (dto.getPageNo() != null) {
            query.setPageNo(dto.getPageNo());
        }
        if (dto.getPageSize() != null) {
            query.setPageSize(dto.getPageSize());
        }
        query.setStatus(StatusEnum.生效.code);
        ScfSysMenuPage page = menuManager.queryPageListLike(query);
        List<ScfSysMenu> menuList = page.getValues();
        List<MenuDTO> menuDTOS = new ArrayList<>();
        if (menuList != null) {
            menuList.forEach(item -> menuDTOS.add(BeanUtils.menuToDTO(item)));
        }
        return BeanUtils.toPageDTO(page, menuDTOS);
    }


    /**
     * 菜单列表查询无分页,返回树状
     *
     * @param menuDTO
     * @return
     */
    @Override
    public Map<String, MenuDTO> getMenusNoPageToTree (MenuDTO menuDTO) {
        ScfSysMenuQuery query = new ScfSysMenuQuery();
        query.setRefSystemCode(menuDTO.getSystemCode());
        query.setStatus(StatusEnum.生效.code);
        List<ScfSysMenu> menus = menuManager.queryList(query);
        if(menus == null){
            return new HashMap<>(0);
        }
        Map<String, List<ScfSysMenu>> mapSystem = menus.stream().collect(Collectors.groupingBy(ScfSysMenu::getRefSystemCode));
        Map<String, MenuDTO> result = new HashMap<>(mapSystem.size());
        //前后分端菜单按菜单类型做分组
        for(Map.Entry<String, List<ScfSysMenu>> systemEntry : mapSystem.entrySet()) {
            MenuDTO resultMenuDTO =  new MenuDTO();
            List<ScfSysMenu> systemValue = systemEntry.getValue();
            if(systemValue == null){
                continue;
            }
            Map<String, List<ScfSysMenu>> mapMenu = systemValue.stream().collect(Collectors.groupingBy(item -> item.getMenuType()));
            //分开后做树状菜单
            //前端菜单树状
            List<MenuDTO> data = null;
            List<ScfSysMenu> displayMenus = mapMenu.get(MenuTypeEnum.前端菜单.code);
            if (displayMenus != null) {
                data = displayMenus.stream().map(item -> BeanUtils.menuToDTO(item)).collect(Collectors.toList());
                data = BeanUtils.recursionMenus(data, 0L);
                resultMenuDTO.setFrontMenus(data);
            }
            //后端菜单树状
            List<ScfSysMenu> serviceMenus = mapMenu.get(MenuTypeEnum.后端菜单.code);
            if(serviceMenus != null) {
                List<MenuDTO> serviceData = serviceMenus.stream().map(item -> BeanUtils.menuToDTO(item)).collect(Collectors.toList());
                List<MenuDTO> systemData = systemValue.stream().map(item -> BeanUtils.menuToDTO(item)).collect(Collectors.toList());
                serviceData = BeanUtils.backMenusToTree(serviceData, systemData);
                resultMenuDTO.setBackMenus(serviceData);
            }
            result.put(systemEntry.getKey(),resultMenuDTO);
        }
        return result;
    }

    /**
     * 菜单查询
     *
     * @param menuId
     * @return
     */
    @Override
    public MenuDTO getMenu(Integer menuId) {
        ScfSysMenu result = menuManager.findById(menuId);
        if (result != null) {
            return BeanUtils.menuToDTO(result);
        } else {
            throw new BusinessException(ResponseEnum.菜单不存在.code);
        }
    }

    /**
     * 菜单修改
     *
     * @param menuDTO
     */
    @Override
    public void updateMenu(MenuDTO menuDTO, IdentityInfoDTO identityInfoDTO) {
        ScfSysMenuQuery query = new ScfSysMenuQuery();
        query.setMenuName(menuDTO.getMenuName());
        query.setRefSystemCode(menuDTO.getSystemCode());
        query.setPid(menuDTO.getPid());
        ScfSysMenu result = menuManager.findByQueryContion(query);
        if (result != null && !result.getId().equals(menuDTO.getId())) {
            if (result.getStatus().equals(StatusEnum.生效.code)) {
                throw new BusinessException(ResponseEnum.菜单已经存在.code);
            } else {
                //存在已经被删除过的数据，彻底删除
                menuManager.deleteById(result.getId());
            }
        }
        ScfSysMenu menu = BeanUtils.dtoToMenu(menuDTO);
        menu.setPchain(getPchain(menuDTO.getPid()));
        menu.setUpdateUser(identityInfoDTO.getUserId());
        menu.setUpdateName(identityInfoDTO.getUsername());
        int i = menuManager.updateSelective(menu);
        if (i < 1) {
            throw new BusinessException(ResponseEnum.菜单不存在.code);
        }
    }

    /**
     * 菜单删除
     *
     * @param menuIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(List<Long> menuIds) {
        List<Long> collect = menuIds.stream().distinct().collect(Collectors.toList());
        List<ScfSysMenu> menus = menuManager.findByIds(menuIds);
        if (menus == null || menus.size() != collect.size()) {
            throw new BusinessException(ResponseEnum.菜单不存在.code);
        }
        //逻辑删除
        menus.forEach(item -> {
            item.setStatus(StatusEnum.废弃.code);
        });
        menuManager.batchUpdate(menus);
    }

    /**
     * 获取父菜单链，格式：2,1,0
     *
     * @param pid
     * @return
     */
    private String getPchain(Long pid) {
        Long id = pid;
        StringBuffer stringBuffer = new StringBuffer(id + ",");
        while (id != 0) {
            ScfSysMenu menu = menuManager.findById(id);
            id = menu.getPid();
            stringBuffer.append(id).append(",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }
}
