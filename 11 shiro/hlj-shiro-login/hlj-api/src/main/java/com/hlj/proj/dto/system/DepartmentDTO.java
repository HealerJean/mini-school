package com.hlj.proj.dto.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName DepartmentDTO
 * @Author DYB
 * @Date 2019/6/4 10:17
 * @Description
 * @Version V1.0
 */
@Data
public class DepartmentDTO implements Serializable {

    /** 主键 */
    private Long id;
    /** 部门名称 */
    private String departmentName;
    /** 部门描述 */
    private String departmentDesc;
    /** 父级部门id */
    private Long pid;
    /** 状态 */
    private String status;
    /** 子部门 */
    private List<DepartmentDTO> childDepartment;
}
