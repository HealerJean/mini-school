package com.hlj.proj.utils;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.common.result.PageListResult;
import com.hlj.proj.data.pojo.system.ScfSysMenu;
import com.hlj.proj.data.pojo.system.ScfSysRole;
import com.hlj.proj.data.pojo.user.ScfUserDepartment;
import com.hlj.proj.data.pojo.user.ScfUserInfo;
import com.hlj.proj.data.pojo.user.ScfUserInfoQuery;
import com.hlj.proj.dto.PageDTO;
import com.hlj.proj.dto.system.DepartmentDTO;
import com.hlj.proj.dto.system.MenuDTO;
import com.hlj.proj.dto.system.RoleDTO;
import com.hlj.proj.dto.user.UserDTO;
import com.hlj.proj.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class BeanUtils {

    public static <T> PageDTO<T> toPageDTO(PageListResult pageView, List<T> datas) {
        if (pageView == null && (datas == null || datas.isEmpty())) {
            return null;
        } else if (pageView == null) {
            return new PageDTO<>(datas);
        } else if (pageView.getPagenation() == null) {
            return new PageDTO<>(datas);
        } else {
            Pagenation pagenation = pageView.getPagenation();
            return new PageDTO(pagenation.getPageNo(), pagenation.getPageSize(), pagenation.getItemCount(),
                    pagenation.getPageCount(), datas);
        }
    }





    public static List<MenuDTO> menuListToDTOs(List<ScfSysMenu> menuList) {
        List<MenuDTO> menuDTOs = new ArrayList<>();
        for (ScfSysMenu menu : menuList) {
            MenuDTO dto = new MenuDTO();
            dto.setMenuName(menu.getMenuName());
            dto.setUrl(menu.getUrl());
            dto.setMethod(menu.getMethod());
            if (menu.getSort() != null) {
                dto.setSort(menu.getSort());
            }
            dto.setIcon(menu.getIcon());
            dto.setFrontKey(menu.getFrontKey());
            dto.setMenuType(menu.getMenuType());
            menuDTOs.add(dto);
        }
        return menuDTOs;
    }

    public static List<MenuDTO> menuListToDTOsTree(List<ScfSysMenu> menuList) {
        return menuRecursion(menuList, 0L);
    }

    private static List<MenuDTO> menuRecursion(List<ScfSysMenu> menuList, Long pid) {
        List<MenuDTO> menuDTOs = new ArrayList<>();
        for (ScfSysMenu u : menuList) {
            if (u.getPid().equals(pid)) {
                MenuDTO menuDTO = new MenuDTO();
                menuDTO.setMenuName(u.getMenuName());
                menuDTO.setUrl(u.getUrl());
                menuDTO.setMethod(u.getMethod());
                menuDTO.setIcon(u.getIcon());
                if (u.getSort() != null) {
                    menuDTO.setSort(u.getSort());
                }
                menuDTO.setFrontKey(u.getFrontKey());
                menuDTO.setMenuType(u.getMenuType());
                menuDTO.setSubmenus(menuRecursion(menuList, u.getId()));
                menuDTOs.add(menuDTO);
            }
        }
        //根据sort排序
        menuDTOs.sort((o1, o2) -> {
            if (o1.getSort() == null) {
                return 1;
            }
            if (o2.getSort() == null) {
                return -1;
            }
            if (Integer.valueOf(o1.getSort()).equals(Integer.valueOf(o2.getSort()))) {
                return 0;
            } else if (Integer.valueOf(o1.getSort()) < Integer.valueOf(o2.getSort())) {
                return -1;
            } else {
                return 1;
            }
        });
        return menuDTOs;
    }

    /**
     * 递归菜单
     *
     * @param menus
     * @return
     */
    public static List<MenuDTO> recursionMenus(List<MenuDTO> menus, long pid) {
        List<MenuDTO> result = new LinkedList<>();
        if (menus != null) {
            for (MenuDTO menuDTO : menus) {
                if (menuDTO.getPid().equals(pid)) {
                    MenuDTO menuDTO1 = new MenuDTO();
                    menuDTO1.setId(menuDTO.getId());
                    menuDTO1.setMenuName(menuDTO.getMenuName());
                    menuDTO1.setSystemCode(menuDTO.getSystemCode());
                    menuDTO1.setUrl(menuDTO.getUrl());
                    menuDTO1.setSort(menuDTO.getSort());
                    menuDTO1.setMethod(menuDTO.getMethod());
                    menuDTO1.setStatus(menuDTO.getStatus());
                    menuDTO1.setDesc(menuDTO.getDesc());
                    menuDTO1.setPid(menuDTO.getPid());
                    menuDTO1.setPchain(menuDTO.getPchain());
                    menuDTO1.setSort(menuDTO.getSort());
                    menuDTO1.setIcon(menuDTO.getIcon());
                    menuDTO1.setFrontKey(menuDTO.getFrontKey());
                    menuDTO1.setIsPermission(menuDTO.getIsPermission());
                    menuDTO1.setMenuType(menuDTO.getMenuType());
                    menuDTO1.setSubmenus(recursionMenus(menus, menuDTO.getId()));
                    result.add(menuDTO1);
                }
            }
        }
        result.sort((o1, o2) -> {
            if (o1.getSort() == null && o2.getSort() == null) {
                return 0;
            }
            if (o1.getSort() == null) {
                return 1;
            }
            if (o2.getSort() == null) {
                return -1;
            }
            if (Integer.valueOf(o1.getSort()).equals(Integer.valueOf(o2.getSort()))) {
                return 0;
            } else if (Integer.valueOf(o1.getSort()) < Integer.valueOf(o2.getSort())) {
                return -1;
            }
            return 1;

        });
        return result;
    }

    /**
     * 后端菜单树状形成,定制化
     *
     * @param backMenus
     * @param allMenus
     * @return
     */
    public static List<MenuDTO> backMenusToTree(List<MenuDTO> backMenus, List<MenuDTO> allMenus) {
        if (backMenus == null || backMenus.isEmpty()) {
            return new ArrayList<>();
        }
        if (allMenus == null || allMenus.isEmpty()) {
            return backMenus;
        }
        //首先获取出所有后端菜单的父菜单
        Set<Long> set = new HashSet<>();
        for (MenuDTO backMenu : backMenus) {
            String pchain = backMenu.getPchain();
            if (StringUtils.isBlank(pchain)) {
                continue;
            }
            String[] split = pchain.split(",");
            List<Long> collect = Arrays.stream(split).map(Long::valueOf).collect(Collectors.toList());
            set.addAll(collect);
            set.add(backMenu.getId());
        }
        //排序递归
        List<Long> collect = set.stream().sorted().collect(Collectors.toList());
        Map<Long, MenuDTO> map = new HashMap<>(collect.size() >> 2);
        map.put(0L, new MenuDTO());
        for (MenuDTO menus : allMenus) {
            Long id = menus.getId();
            if (collect.contains(id)) {
                map.put(id, menus);
            }
        }
        //构建一颗树结构
        for (Long i : collect) {
            if (i != 0) {
                MenuDTO menuDTO = map.get(i);
                if (menuDTO == null) {
                    continue;
                }
                Long pid = menuDTO.getPid();
                MenuDTO pMenu = map.get(pid);
                List<MenuDTO> children = pMenu.getSubmenus();
                if (children == null) {
                    children = new LinkedList<>();
                    pMenu.setSubmenus(children);
                }
                children.add(menuDTO);
            }
        }
        return map.get(collect.get(0)).getSubmenus();
    }

    public static ScfUserInfoQuery toUserInfoQuey(UserDTO userDTO) {
        ScfUserInfoQuery query = new ScfUserInfoQuery();
        query.setUsername(userDTO.getUsername());
        query.setRealName(userDTO.getRealName());
        query.setEmail(userDTO.getEmail());
        query.setTelephone(userDTO.getTelephone());
        query.setGender(userDTO.getGender());
        query.setPassword(userDTO.getPassword());
        query.setUserType(userDTO.getUserType());
        query.setStatus(userDTO.getUserStatus());
        return query;
    }

    public static UserDTO toUserDTO(ScfUserInfo scfUserInfo) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(scfUserInfo.getId());
        userDTO.setRealName(scfUserInfo.getRealName());
        userDTO.setEmail(scfUserInfo.getEmail());
        userDTO.setTelephone(scfUserInfo.getTelephone());
        userDTO.setUserType(scfUserInfo.getUserType());
        return userDTO;
    }

    /**
     * 用户dto转pojo
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public static ScfUserInfo userDtoToPojo(ScfUserInfo userInfo, UserDTO dto) {
        if (userInfo == null) {
            userInfo = new ScfUserInfo();
        }
        userInfo.setId(dto.getUserId());
        userInfo.setUsername(dto.getUsername());
        userInfo.setRealName(dto.getRealName());
        userInfo.setEmail(dto.getEmail());
        userInfo.setTelephone(dto.getTelephone());
        userInfo.setGender(dto.getGender());
        userInfo.setStatus(dto.getUserStatus());
        return userInfo;
    }

    /**
     * 部门dto转pojo
     *
     * @param dto
     * @return
     */
    public static ScfUserDepartment departmentDtoToPojo(DepartmentDTO dto) {
        ScfUserDepartment department = new ScfUserDepartment();
        department.setId(dto.getId());
        department.setDepartmentName(dto.getDepartmentName());
        department.setDepartmentDesc(dto.getDepartmentDesc());
        if (dto.getPid() == null) {
            department.setPid(0L);
        } else {
            department.setPid(dto.getPid());
        }
        department.setStatus(StatusEnum.生效.code);
        return department;
    }

    /**
     * 部门pojo转dto
     *
     * @param department
     * @return
     */
    public static DepartmentDTO departmentToDto(ScfUserDepartment department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDepartmentDesc(department.getDepartmentDesc());
        if (department.getPid() == null) {
            dto.setPid(0L);
        } else {
            dto.setPid(department.getPid());
        }
        dto.setStatus(department.getStatus());
        return dto;
    }

    /**
     * 部门集合转换树形结构
     *
     * @param departmentList
     * @return
     */
    public static List<DepartmentDTO> departmentListToDTOsTree(List<ScfUserDepartment> departmentList) {
        return departmentRecursion(departmentList, 0L);
    }

    private static List<DepartmentDTO> departmentRecursion(List<ScfUserDepartment> departmentList, Long pid) {
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (ScfUserDepartment d : departmentList) {
            if (d.getPid().equals(pid)) {
                DepartmentDTO departmentDTO = new DepartmentDTO();
                departmentDTO.setId(d.getId());
                departmentDTO.setDepartmentName(d.getDepartmentName());
                departmentDTO.setDepartmentDesc(d.getDepartmentDesc());
                departmentDTO.setPid(d.getPid());
                departmentDTO.setStatus(d.getStatus());
                departmentDTO.setChildDepartment(departmentRecursion(departmentList, d.getId()));
                departmentDTOS.add(departmentDTO);
            }
        }
        return departmentDTOS;
    }




    /**
     * 菜单pojo转dto
     */
    public static MenuDTO menuToDTO(ScfSysMenu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setMenuName(menu.getMenuName());
        dto.setSystemCode(menu.getRefSystemCode());
        dto.setUrl(menu.getUrl());
        dto.setMethod(menu.getMethod());
        dto.setStatus(menu.getStatus());
        dto.setPid(menu.getPid());
        dto.setPchain(menu.getPchain());
        dto.setDesc(menu.getDesc());
        dto.setSort(menu.getSort());
        dto.setIcon(menu.getIcon());
        dto.setFrontKey(menu.getFrontKey());
        dto.setIsPermission(menu.getIsPermission());
        dto.setMenuType(menu.getMenuType());
        return dto;
    }

    /**
     * 菜单dto转pojo
     */
    public static ScfSysMenu dtoToMenu(MenuDTO dto) {
        ScfSysMenu menu = new ScfSysMenu();
        menu.setId(dto.getId());
        menu.setMenuName(dto.getMenuName());
        menu.setRefSystemCode(dto.getSystemCode());
        menu.setUrl(dto.getUrl());
        menu.setMethod(dto.getMethod());
        menu.setStatus(dto.getStatus());
        menu.setPid(dto.getPid());
        menu.setPchain(dto.getPchain());
        menu.setDesc(dto.getDesc());
        menu.setSort(dto.getSort());
        menu.setIcon(dto.getIcon());
        menu.setFrontKey(dto.getFrontKey());
        menu.setIsPermission(dto.getIsPermission());
        menu.setMenuType(dto.getMenuType());
        return menu;
    }

    /**
     * 角色pojo转dto
     */
    public static RoleDTO roleToDTO(ScfSysRole role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setRoleName(role.getRoleName());
        dto.setSystemCode(role.getRefSystemCode());
        dto.setStatus(role.getStatus());
        dto.setDesc(role.getDesc());
        return dto;
    }

    /**
     * 角色dto转pojo
     */
    public static ScfSysRole DTOtoRole(RoleDTO dto) {
        ScfSysRole role = new ScfSysRole();
        role.setId(dto.getId());
        role.setRoleName(dto.getRoleName());
        role.setRefSystemCode(dto.getSystemCode());
        role.setStatus(dto.getStatus());
        role.setDesc(dto.getDesc());
        return role;
    }




    public static DepartmentDTO toDepartmentDTO(ScfUserDepartment scfUserDepartment) {
        if (scfUserDepartment == null) {
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(scfUserDepartment.getId());
        departmentDTO.setDepartmentName(scfUserDepartment.getDepartmentName());
        departmentDTO.setDepartmentDesc(scfUserDepartment.getDepartmentDesc());
        return departmentDTO;
    }


}
