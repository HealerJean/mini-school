package com.hlj.proj.service.user.identity.impl;
import com.hlj.proj.data.dao.mybatis.manager.system.SysRoleMenuManager;
import com.hlj.proj.data.dao.mybatis.manager.system.SysUserRoleManager;
import com.hlj.proj.data.dao.mybatis.manager.user.UserInfoManager;
import com.hlj.proj.data.pojo.system.SysMenu;
import com.hlj.proj.data.pojo.system.SysRole;
import com.hlj.proj.data.pojo.system.SysRoleMenuQuery;
import com.hlj.proj.data.pojo.system.SysUserRoleQuery;
import com.hlj.proj.data.pojo.user.UserInfo;
import com.hlj.proj.dto.system.RoleDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;
import com.hlj.proj.enums.MenuTypeEnum;
import com.hlj.proj.enums.ResponseEnum;
import com.hlj.proj.enums.StatusEnum;
import com.hlj.proj.exception.BusinessException;
import com.hlj.proj.service.user.identity.IdentityService;
import com.hlj.proj.utils.BeanUtils;
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
    private UserInfoManager userInfoManager;
    @Autowired
    private SysUserRoleManager sysUserRoleManager;
    @Autowired
    private SysRoleMenuManager sysRoleMenuManager;
    /**
     * 根据用户ID获取当前登陆用户信息
     * @param userId
     * @return
     */
    @Override
    public IdentityInfoDTO getUserInfo(Long userId) {
        if(userId == null || userId < 0){
            throw new BusinessException("用户ID不能为空");
        }
        IdentityInfoDTO identityInfoDTO = new IdentityInfoDTO();
        SysUserRoleQuery refUserRoleQuery = new SysUserRoleQuery();
        UserInfo scfUserInfo = userInfoManager.findById(userId);
        if(scfUserInfo == null ){
            throw new BusinessException(ResponseEnum.用户不存在);
        }
        identityInfoDTO.setUserId(scfUserInfo.getId());
        identityInfoDTO.setUsername(scfUserInfo.getUsername());
        identityInfoDTO.setUserType(scfUserInfo.getUserType());
        identityInfoDTO.setEmail(scfUserInfo.getEmail());
        identityInfoDTO.setTelephone(scfUserInfo.getTelephone());
        //公司信息
        refUserRoleQuery.setRefUserId(userId);
        List<SysRole> roles = sysUserRoleManager.queryListToRole(refUserRoleQuery);
        //将查询的角色信息拼接成('1','2')格式的查询条件
        if (roles != null && !roles.isEmpty()) {
            List<RoleDTO> roleDTOS = new ArrayList<>();
            StringBuffer roleQuery = new StringBuffer("(");
            for (SysRole u : roles) {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setId(u.getId());
                roleDTO.setRoleName(u.getRoleName());
                roleDTOS.add(roleDTO);
                roleQuery.append("'").append(u.getId()).append("'").append(",");
            }
            roleQuery.deleteCharAt(roleQuery.length() - 1);
            roleQuery.append(")");
            identityInfoDTO.setRoles(roleDTOS);
            SysRoleMenuQuery refRoleMenuQuery = new SysRoleMenuQuery();
            refRoleMenuQuery.setRefRoleIds(roles.stream().map(item -> item.getId()).collect(Collectors.toList()));
            List<SysMenu> menuList = sysRoleMenuManager.queryListToMenu(refRoleMenuQuery);

            List<SysMenu> menus = new ArrayList<>();
            List<SysMenu> permissions = new ArrayList<>();
            for (SysMenu menu : menuList) {
                if (StatusEnum.生效.code.equals(menu.getIsPermission())) {
                    permissions.add(menu);
                }
                if (!MenuTypeEnum.后端菜单.code.equals(menu.getMenuType())) {
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
