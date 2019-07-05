/*
 * Powered By code-generator
 */
package com.hlj.proj.api.system;


import com.hlj.proj.dto.PageDTO;
import com.hlj.proj.dto.system.MenuDTO;
import com.hlj.proj.dto.system.RoleDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;

import java.util.List;


public interface RoleService {


    /**
     * 新增角色
     *
     * @param roleDTO
     */
    void addRole(RoleDTO roleDTO, IdentityInfoDTO identityInfoDTO);

    /**
     * 角色列表查询
     *
     * @param dto
     * @return
     */
    PageDTO<RoleDTO> getRoles(RoleDTO dto);

    /**
     * 角色列表查询，无分页
     *
     * @param roleDTO
     * @return
     */
    List<RoleDTO> getRolesNoPage(RoleDTO roleDTO);

    /**
     * 角色查询
     *
     * @param roleId
     * @return
     */
    RoleDTO getRole(Long roleId);

    /**
     * 角色修改
     *
     * @param roleDTO
     */
    void updateRole(RoleDTO roleDTO, IdentityInfoDTO identityInfoDTO);

    /**
     * 角色删除
     *
     * @param roleId
     */
    void deleteRole(Long roleId, IdentityInfoDTO identityInfoDTO);

    /**
     * 角色权限查询
     *
     * @param roleId
     * @return
     */
    List<MenuDTO> getMenusByRole(Long roleId, MenuDTO dto);

    /**
     * 角色权限修改
     *
     * @param roleId
     * @param dto
     */
    void updateMenusByRole(Long roleId, MenuDTO dto, IdentityInfoDTO identityInfoDTO);
}
