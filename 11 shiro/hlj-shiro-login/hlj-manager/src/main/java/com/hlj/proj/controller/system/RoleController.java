package com.hlj.proj.controller.system;
import com.hlj.proj.api.system.RoleService;
import com.hlj.proj.constants.CommonConstants;
import com.hlj.proj.dto.ResponseBean;
import com.hlj.proj.dto.system.MenuDTO;
import com.hlj.proj.dto.system.RoleDTO;
import com.hlj.proj.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName RoleController
 * @Author TD
 * @Date 2019/3/18 14:05
 * @Description 角色管理controller
 */
@RestController
@RequestMapping("/api/roles")
@Slf4j
public class RoleController   {

    @Autowired
    private RoleService roleService;


    /**
     * 新增角色
     *
     * @param roleDTO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseBean addRoles(@RequestBody(required = false) RoleDTO roleDTO) {
        roleService.addRole(roleDTO, UserUtils.getAuthUser());
        return   ResponseBean.buildSuccess("新增角色成功", roleDTO);
    }


    /**
     * 角色删除
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
    public ResponseBean deleteRole(@PathVariable Long roleId) {
        log.info("角色管理--------角色删除--------角色id：{}", roleId);
        roleService.deleteRole(roleId, UserUtils.getAuthUser());
        return   ResponseBean.buildSuccess("角色删除成功");
    }


    /**
     * 角色修改
     *
     * @param roleId
     * @param roleDTO
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
    public ResponseBean updateRole(@PathVariable Long roleId, @RequestBody(required = false) RoleDTO roleDTO) {
        roleDTO.setId(roleId);
        log.info("角色管理--------角色修改--------角色id：{}，修改信息：{}", roleId, roleDTO);
        roleService.updateRole(roleDTO, UserUtils.getAuthUser());
        return   ResponseBean.buildSuccess("角色修改成功");
    }

    /**
     * 角色查询
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public ResponseBean getRole(@PathVariable Long roleId) {
        log.info("角色管理--------角色查询--------查询角色id：{}", roleId);
        return   ResponseBean.buildSuccess("角色列表查询成功", roleService.getRole(roleId));
    }


    /**
     * 角色列表查询
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseBean getUsers(RoleDTO dto) {
        log.info("角色管理--------角色列表查询--------查询条件：{}", dto);
        return   ResponseBean.buildSuccess("角色列表查询成功", roleService.getRoles(dto));
    }






    /**
     * 角色权限查询
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}/menus", method = RequestMethod.GET)
    public ResponseBean getMenusByRole(@PathVariable Long roleId, MenuDTO menuDTO) {
        log.info("角色管理--------角色权限查询--------角色id：{}", roleId);
        List<MenuDTO> menus = roleService.getMenusByRole(roleId, menuDTO);
        return   ResponseBean.buildSuccess("角色列表查询成功", menus);
    }


    /**
     * 角色权限修改
     *
     * @param roleId
     * @param menuDTO
     * @return
     */
    @RequestMapping(value = "/{roleId}/menus", method = RequestMethod.PUT)
    public ResponseBean updateMenusByRole(@PathVariable Long roleId, @RequestBody(required = false) MenuDTO menuDTO) {
        log.info("角色管理--------角色权限修改--------角色id：{}", roleId);
        roleService.updateMenusByRole(roleId, menuDTO, UserUtils.getAuthUser());
        return   ResponseBean.buildSuccess("角色权限修改成功");
    }

}
