package com.hlj.proj.api.system;


import com.hlj.proj.dto.system.DepartmentDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;

import java.util.List;

/**
 * @ClassName DepartmentService
 * @Author DYB
 * @Date 2019/6/4 9:50
 * @Description 部门管理接口
 * @Version V1.0
 */
public interface DepartmentService {

    /**
     * 获取部门树结构
     * @return
     */
    List<DepartmentDTO> getDepartmentTree();

    /**
     * 增加部门
     * @param departmentDTO
     * @return
     */
    DepartmentDTO addDepartment(DepartmentDTO departmentDTO, IdentityInfoDTO identityInfoDTO);

    /**
     * 修改部门
     * @param departmentDTO
     * @return
     */
    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, IdentityInfoDTO identityInfoDTO);

    /**
     * 删除部门
     * @param departmentDTO
     */
    void deleteDepartment(DepartmentDTO departmentDTO);
}
