package com.hlj.proj.service.system;

import com.hlj.proj.api.system.DepartmentService;
import com.hlj.proj.data.dao.mybatis.manager.user.ScfUserDepartmentManager;
import com.hlj.proj.data.dao.mybatis.manager.user.ScfUserRefUserDepartmentManager;
import com.hlj.proj.data.pojo.user.ScfUserDepartment;
import com.hlj.proj.data.pojo.user.ScfUserDepartmentQuery;
import com.hlj.proj.data.pojo.user.ScfUserRefUserDepartment;
import com.hlj.proj.data.pojo.user.ScfUserRefUserDepartmentQuery;
import com.hlj.proj.dto.system.DepartmentDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;
import com.hlj.proj.enums.ResponseEnum;
import com.hlj.proj.enums.StatusEnum;
import com.hlj.proj.exception.BusinessException;
import com.hlj.proj.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DepartmentServiceImpl
 * @Author DYB
 * @Date 2019/6/4 9:51
 * @Description 部门管理实现类
 * @Version V1.0
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private ScfUserDepartmentManager departmentManager;
    @Autowired
    private ScfUserRefUserDepartmentManager refUserDepartmentManager;

    /**
     * 获取部门树形结构
     * @return
     */
    @Override
    public List<DepartmentDTO> getDepartmentTree() {
        ScfUserDepartmentQuery query = new ScfUserDepartmentQuery();
        query.setStatus(StatusEnum.生效.code);
        List<ScfUserDepartment> departments = departmentManager.queryList(query);
        return BeanUtils.departmentListToDTOsTree(departments);
    }

    /**
     * 添加部门
     * @param departmentDTO
     * @return
     */
    @Override
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO, IdentityInfoDTO identityInfoDTO) {
        ScfUserDepartmentQuery query = new ScfUserDepartmentQuery();
        query.setDepartmentName(departmentDTO.getDepartmentName());
        query.setStatus(StatusEnum.生效.code);
        ScfUserDepartment department = departmentManager.findByQueryContion(query);
        if (department != null) {
            throw new BusinessException(ResponseEnum.部门名称已经存在);
        }
        if (departmentDTO.getPid() != null && departmentDTO.getPid() != 0L) {
            department = departmentManager.findById(departmentDTO.getPid());
            if (department == null) {
                throw new BusinessException(ResponseEnum.父级部门不存在);
            }
        }
        department = BeanUtils.departmentDtoToPojo(departmentDTO);
        department.setCreateUser(identityInfoDTO.getUserId());
        department.setCreateName(identityInfoDTO.getUsername());
        departmentManager.insertSelective(department);
        departmentDTO.setId(department.getId());
        return departmentDTO;
    }

    /**
     * 修改部门（不能修改部门父级）
     * @param departmentDTO
     * @return
     */
    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, IdentityInfoDTO identityInfoDTO) {
        ScfUserDepartmentQuery query = new ScfUserDepartmentQuery();
        query.setDepartmentName(departmentDTO.getDepartmentName());
        query.setStatus(StatusEnum.生效.code);
        ScfUserDepartment department = departmentManager.findByQueryContion(query);
        if (department != null && !department.getId().equals(departmentDTO.getId())) {
            throw new BusinessException(ResponseEnum.部门名称已经存在);
        }
        department = BeanUtils.departmentDtoToPojo(departmentDTO);
        department.setPid(null);
        department.setUpdateUser(identityInfoDTO.getUserId());
        department.setUpdateName(identityInfoDTO.getUsername());
        int i = departmentManager.updateSelective(department);
        if (i < 1) {
            throw new BusinessException(ResponseEnum.部门不存在);
        }
        return departmentDTO;
    }

    /**
     * 删除部门（有子部门不可删除）
     * @param departmentDTO
     */
    @Override
    public void deleteDepartment(DepartmentDTO departmentDTO) {
        ScfUserDepartmentQuery query = new ScfUserDepartmentQuery();
        query.setPid(departmentDTO.getId());
        query.setStatus(StatusEnum.生效.code);
        ScfUserDepartment department = departmentManager.findByQueryContion(query);
        if (department != null) {
            throw new BusinessException(ResponseEnum.部门存在子部门_不可删除);
        }

        ScfUserRefUserDepartmentQuery refQuery = new ScfUserRefUserDepartmentQuery();
        refQuery.setRefDepartmentId(departmentDTO.getId());
        refQuery.setStatus(StatusEnum.生效.code);
        List<ScfUserRefUserDepartment> refUserDepartments = refUserDepartmentManager.queryList(refQuery);
        if (refUserDepartments != null && !refUserDepartments.isEmpty()) {
            throw new BusinessException(ResponseEnum.部门存在用户_不可删除);
        }

        int i = departmentManager.deleteById(departmentDTO.getId());
        if (i < 1) {
            throw new BusinessException(ResponseEnum.部门不存在);
        }
    }
}

