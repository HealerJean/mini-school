package com.hlj.proj.service.user.identity.impl;

import com.hlj.proj.data.dao.mybatis.manager.system.ScfSysRefRoleMenuManager;
import com.hlj.proj.data.dao.mybatis.manager.system.ScfSysRefUserRoleManager;
import com.hlj.proj.data.dao.mybatis.manager.user.ScfUserInfoManager;
import com.hlj.proj.data.dao.mybatis.manager.user.ScfUserRefUserDepartmentManager;
import com.hlj.proj.data.pojo.system.ScfSysMenu;
import com.hlj.proj.data.pojo.system.ScfSysRefRoleMenuQuery;
import com.hlj.proj.data.pojo.system.ScfSysRefUserRoleQuery;
import com.hlj.proj.data.pojo.system.ScfSysRole;
import com.hlj.proj.data.pojo.user.ScfUserDepartment;
import com.hlj.proj.data.pojo.user.ScfUserInfo;
import com.hlj.proj.data.pojo.user.ScfUserRefUserDepartmentQuery;
import com.hlj.proj.dto.system.DepartmentDTO;
import com.hlj.proj.dto.system.RoleDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;
import com.hlj.proj.enums.MenuTypeEnum;
import com.hlj.proj.enums.StatusEnum;
import com.hlj.proj.exception.BusinessException;
import com.hlj.proj.service.user.identity.IdentityService;
import com.hlj.proj.utils.BeanUtils;
import com.hlj.proj.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName IdentityServiceImpl
 * @Author TD
 * @Date 2019/6/4 9:13
 * @Description 用户认证信息接口实现
 */
@Service
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private ScfUserInfoManager scfUserInfoManager;
    @Autowired
    private ScfUserRefUserDepartmentManager scfUserRefUserDepartmentManager;
    @Autowired
    private ScfSysRefUserRoleManager scfSysRefUserRoleManager;
    @Autowired
    private ScfSysRefRoleMenuManager scfSysRefRoleMenuManager;
    /**
     * 根据用户ID获取当前登陆用户信息
     * 1、用户基本信息
     * 2、用户所处部门信息
     * 3、角色集合
     * 4、获取角色所对应的所有菜单（这里的菜单指的是所有的url，包括前台菜单）
     */
    @Override
    public IdentityInfoDTO getUserInfo(Long userId) {
        if(userId == null || userId < 0){
            throw new BusinessException("用户ID不能为空");
        }
        //1、用户基本信息
        IdentityInfoDTO identityInfoDTO = new IdentityInfoDTO();
        ScfUserInfo scfUserInfo = scfUserInfoManager.findById(userId);
        if(scfUserInfo == null ){
            throw new BusinessException("用户不存在");
        }
        identityInfoDTO.setUserId(scfUserInfo.getId());
        identityInfoDTO.setUsername(scfUserInfo.getUsername());
        identityInfoDTO.setRealName(scfUserInfo.getRealName());
        identityInfoDTO.setUserType(scfUserInfo.getUserType());
        identityInfoDTO.setEmail(scfUserInfo.getEmail());
        identityInfoDTO.setTelephone(scfUserInfo.getTelephone());

        // 2、用户所处部门信息
        ScfUserRefUserDepartmentQuery scfUserRefUserDepartmentQuery = new ScfUserRefUserDepartmentQuery();
        scfUserRefUserDepartmentQuery.setRefUserId(scfUserInfo.getId());
        scfUserRefUserDepartmentQuery.setStatus(StatusEnum.生效.code);
        ScfUserDepartment scfUserDepartment = scfUserRefUserDepartmentManager.findByQueryContionToDepartment(scfUserRefUserDepartmentQuery);
        DepartmentDTO departmentDTO = BeanUtils.toDepartmentDTO(scfUserDepartment);
        identityInfoDTO.setDepartmentDTO(departmentDTO);

        // 3、角色集合
        ScfSysRefUserRoleQuery refUserRoleQuery = new ScfSysRefUserRoleQuery();
        refUserRoleQuery.setRefUserId(userId);
        List<ScfSysRole> roles = scfSysRefUserRoleManager.queryListToRole(refUserRoleQuery);
        if (roles != null && !roles.isEmpty()) {
            List<RoleDTO> roleDTOS = new ArrayList<>();
            for (ScfSysRole u : roles) {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setId(u.getId());
                roleDTO.setRoleName(u.getRoleName());
                roleDTOS.add(roleDTO);
            }
            identityInfoDTO.setRoles(roleDTOS);
            //  4、获取所有角色所对应的所有菜单（这里的菜单指的是所有的url，包括前台菜单）
            ScfSysRefRoleMenuQuery refRoleMenuQuery = new ScfSysRefRoleMenuQuery();
            refRoleMenuQuery.setRefRoleIds(roles.stream().map(item -> item.getId()).collect(Collectors.toList()));
            List<ScfSysMenu> menuList = scfSysRefRoleMenuManager.queryListToMenu(refRoleMenuQuery);

            List<ScfSysMenu> menus = new ArrayList<>();
            List<ScfSysMenu> permissions = new ArrayList<>();
            for (ScfSysMenu menu : menuList) {
                //所有有效的菜单
                if (StatusEnum.生效.code.equals(menu.getIsPermission())) {
                    permissions.add(menu);
                }
                //前端菜单
                if (MenuTypeEnum.前端菜单.code.equals(menu.getMenuType())) {
                    menus.add(menu);
                }
            }
            //递归获取树形关系
            identityInfoDTO.setMenus(BeanUtils.menuListToDTOsTree(menus));
            identityInfoDTO.setPermissions(BeanUtils.menuListToDTOs(permissions));
        }
        return identityInfoDTO;
    }
}
