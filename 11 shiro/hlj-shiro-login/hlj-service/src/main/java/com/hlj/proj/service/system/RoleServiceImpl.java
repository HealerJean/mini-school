/*
 * Powered By code-generator
 */
package com.hlj.proj.service.system;

import com.hlj.proj.api.system.RoleService;
import com.hlj.proj.data.dao.mybatis.manager.system.SysMenuManager;
import com.hlj.proj.data.dao.mybatis.manager.system.SysRoleManager;
import com.hlj.proj.data.dao.mybatis.manager.system.SysRoleMenuManager;
import com.hlj.proj.data.pojo.system.*;
import com.hlj.proj.dto.PageDTO;
import com.hlj.proj.dto.system.MenuDTO;
import com.hlj.proj.dto.system.RoleDTO;
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


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleManager roleManager;

    @Autowired
    private SysMenuManager sysMenuManager;

    @Autowired
    private SysRoleMenuManager sysRoleMenuManager;

    /**
     * 角色添加
     *
     * @param roleDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleDTO roleDTO, IdentityInfoDTO identityInfoDTO) {
        SysRole role = BeanUtils.DTOtoRole(roleDTO);
        SysRoleQuery query = new SysRoleQuery();
        query.setRoleName(role.getRoleName());
        query.setRefSystemCode(role.getRefSystemCode());
        query.setStatus(StatusEnum.生效.code);
        List<SysRole> resultList = roleManager.queryList(query);
        if (resultList != null && !resultList.isEmpty()) {
            throw new BusinessException(ResponseEnum.角色已经存在) ;
        }
        role.setStatus(StatusEnum.生效.code);
        role.setCreateUser(identityInfoDTO.getUserId());
        role.setCreateName(identityInfoDTO.getUsername());
        roleManager.insertSelective(role);
    }

    /**
     * 角色列表查询，根据系统CODE，名称模糊查询
     *
     * @param dto
     * @return
     */
    @Override
    public PageDTO<RoleDTO> getRoles(RoleDTO dto) {
        SysRolePage page = null;
        SysRoleQuery query = new SysRoleQuery();
        List<SysRole> roles;
        if (dto.getIsPage() == null || !dto.getIsPage()) {
            //查询不分页
            query.setStatus(StatusEnum.生效.code);
            roles = roleManager.queryListLike(query);
        } else {
            if (dto != null) {
                query.setRefSystemCode(dto.getSystemCode());
                if (dto.getPageNo() != null) {
                    query.setPageNo(dto.getPageNo());
                }
                if (dto.getPageSize() != null) {
                    query.setPageSize(dto.getPageSize());
                }
                query.setRefSystemCode(dto.getSystemCode());
                query.setRoleName(dto.getRoleName());
            }
            query.setStatus(StatusEnum.生效.code);
            page = roleManager.queryPageListLike(query);
            roles = page.getValues();
        }
        List<RoleDTO> roleDTOS = new ArrayList<>();
        if (roles != null) {
            roles.forEach(item -> roleDTOS.add(BeanUtils.roleToDTO(item)));
        }
        return BeanUtils.toPageDTO(page, roleDTOS);
    }

    @Override
    public List<RoleDTO> getRolesNoPage(RoleDTO roleDTO) {
        SysRoleQuery query = new SysRoleQuery();
        query.setRefSystemCode(roleDTO.getSystemCode());
        query.setRoleName(roleDTO.getRoleName());
        query.setStatus(StatusEnum.生效.code);
        List<SysRole> roles = roleManager.queryListLike(query);
        if (roles != null) {
            return roles.stream().map(BeanUtils::roleToDTO).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 角色查询
     *
     * @param roleId
     * @return
     */
    @Override
    public RoleDTO getRole(Long roleId) {
        SysRole result = roleManager.findById(roleId);
        if (result != null) {
            RoleDTO roleDTO = BeanUtils.roleToDTO(result);
            //查询菜单
            List<MenuDTO> menusByRole = this.getMenusByRole(roleId, new MenuDTO());
            //前后分端菜单按菜单类型做分组
            Map<String, List<MenuDTO>> collect = menusByRole.stream().collect(Collectors.groupingBy(item -> item.getMenuType()));
            //分开后做树状菜单
            for (Map.Entry<String, List<MenuDTO>> entry : collect.entrySet()) {
                String key = entry.getKey();
                List<MenuDTO> value = entry.getValue();
                value = BeanUtils.recursionMenus(value, 0L);
                switch (MenuTypeEnum.toEnum(key)) {
                    case 前端菜单:
                        roleDTO.setBackMenus(value);
                        break;
                    case 后端菜单:
                        roleDTO.setFrontMenus(value);
                        break;
                    default:
                }
            }
            return roleDTO;

        } else {
            throw new BusinessException(ResponseEnum.角色不存在);
        }
    }


    /**
     * 角色修改
     *
     * @param roleDTO
     */
    @Override
    public void updateRole(RoleDTO roleDTO, IdentityInfoDTO identityInfoDTO) {
        SysRoleQuery query = new SysRoleQuery();
        query.setRoleName(roleDTO.getRoleName());
        query.setRefSystemCode(roleDTO.getSystemCode());
        query.setStatus(StatusEnum.生效.code);
        List<SysRole> resultList = roleManager.queryList(query);
        if (resultList.size() > 1 || (resultList.size() == 1 && !resultList.get(0).getId().equals(roleDTO.getId()))) {
            throw new BusinessException(ResponseEnum.角色已经存在) ;
        }
        SysRole role = BeanUtils.DTOtoRole(roleDTO);
        role.setUpdateUser(identityInfoDTO.getUserId());
        role.setUpdateName(identityInfoDTO.getUsername());
        int i = roleManager.updateSelective(role);
        if (i < 1) {
            throw new BusinessException(ResponseEnum.角色不存在) ;
        }
    }

    /**
     * 角色删除
     *
     * @param roleId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long roleId, IdentityInfoDTO identityInfoDTO) {
        SysRole role = roleManager.findById(roleId);
        if (role == null) {
            throw new BusinessException(ResponseEnum.角色不存在) ;
        }
        role.setStatus(StatusEnum.废弃.code);
        role.setUpdateUser(identityInfoDTO.getUserId());
        role.setUpdateName(identityInfoDTO.getUsername());
        roleManager.updateSelective(role);
        SysRoleMenuQuery query = new SysRoleMenuQuery();
        query.setRefRoleId(roleId);
        sysRoleMenuManager.delete(query);
    }

    /**
     * 角色权限查询
     *
     * @param roleId
     * @return
     */
    @Override
    public List<MenuDTO> getMenusByRole(Long roleId, MenuDTO dto) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("menuName", dto.getMenuName());
        map.put("menuType", dto.getMenuType());
        map.put("status", StatusEnum.生效.code);
        List<SysMenu> menus = sysRoleMenuManager.selectMenusByRoleId(map);
        if (menus != null) {
            return menus.stream().map(BeanUtils::menuToDTO).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 角色权限修改
     *
     * @param roleId
     * @param menuDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenusByRole(Long roleId, MenuDTO menuDTO, IdentityInfoDTO identityInfoDTO) {
        //先将角色现有菜单删除，再批量添加菜单信息
        SysRoleMenuQuery delQuery = new SysRoleMenuQuery();
        delQuery.setRefRoleId(roleId);
        sysRoleMenuManager.delete(delQuery);
        List<Long> back = menuDTO.getBack();
        List<Long> front = menuDTO.getFront();
        List<SysRoleMenu> refRoleMenus = new ArrayList<>();
        //添加前端菜单
        if (front != null) {
            for (Long i : front) {
                refRoleMenus.add(toRefRoleMenu(roleId, identityInfoDTO, i));
            }
        }
        //添加后端菜单
        if (back != null) {
            List<SysMenu> ids = sysMenuManager.findByIds(back);
            List<Long> longList = ids.stream().filter(i -> MenuTypeEnum.后端菜单.code.equals(i.getMenuType()))
                    .map(SysMenu::getId).collect(Collectors.toList());
            for (Long i : longList) {
                refRoleMenus.add(toRefRoleMenu(roleId, identityInfoDTO, i));
            }
        }
        sysRoleMenuManager.batchInsert(refRoleMenus);
    }

    private SysRoleMenu toRefRoleMenu(Long roleId, IdentityInfoDTO identityInfoDTO, Long menuId) {
        SysRoleMenu refMenu = new SysRoleMenu();
        refMenu.setRefRoleId(roleId);
        refMenu.setRefMenuId(menuId);
        refMenu.setStatus(StatusEnum.生效.code);
        refMenu.setCreateUser(identityInfoDTO.getUserId());
        refMenu.setCreateName(identityInfoDTO.getUsername());
        return refMenu;
    }
}
