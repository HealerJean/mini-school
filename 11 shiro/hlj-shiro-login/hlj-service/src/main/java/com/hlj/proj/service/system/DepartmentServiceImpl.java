package com.hlj.proj.service.system;

import com.hlj.proj.api.system.DepartmentService;
import com.hlj.proj.data.dao.mybatis.manager.system.SysDepartmentManager;
import com.hlj.proj.data.dao.mybatis.manager.user.UserDepartmentManager;
import com.hlj.proj.data.pojo.system.SysDepartment;
import com.hlj.proj.data.pojo.system.SysDepartmentQuery;
import com.hlj.proj.data.pojo.user.UserDepartment;
import com.hlj.proj.data.pojo.user.UserDepartmentQuery;
import com.hlj.proj.dto.system.DepartmentDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;
import com.hlj.proj.enums.ResponseEnum;
import com.hlj.proj.enums.StatusEnum;
import com.hlj.proj.exception.BusinessException;
import com.hlj.proj.utils.BeanUtils;
import com.hlj.proj.utils.EmptyUtil;
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
    private SysDepartmentManager sysDepartmentManager;

    @Autowired
    private UserDepartmentManager userDepartmentManager ;

    /**
     * 获取部门树形结构
     * @return
     */
    @Override
    public List<DepartmentDTO> getDepartmentTree() {
        SysDepartmentQuery query = new SysDepartmentQuery();
        query.setStatus(StatusEnum.生效.code);
        List<SysDepartment> departments = sysDepartmentManager.queryList(query);
        return BeanUtils.departmentListToDTOsTree(departments);
    }

    /**
     * 添加部门
     * @param departmentDTO
     * @return
     */
    @Override
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO, IdentityInfoDTO identityInfoDTO) {
        SysDepartmentQuery query = new SysDepartmentQuery();
        query.setDepartmentName(departmentDTO.getDepartmentName());
        query.setStatus(StatusEnum.生效.code);
        SysDepartment department = sysDepartmentManager.findByQueryContion(query);
        if (department != null) {
            throw new BusinessException(ResponseEnum.部门名称已经存在);
        }
        if (departmentDTO.getPid() != null && departmentDTO.getPid() != 0L) {
            department = sysDepartmentManager.findById(departmentDTO.getPid());
            if (department == null) {
                throw new BusinessException (ResponseEnum.父级部门不存在);
            }
        }
        department = BeanUtils.dtoToDeparment(departmentDTO);
        department.setCreateUser(identityInfoDTO.getUserId());
        department.setCreateName(identityInfoDTO.getUsername());
        sysDepartmentManager.insertSelective(department);
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
        SysDepartmentQuery query = new SysDepartmentQuery();
        query.setDepartmentName(departmentDTO.getDepartmentName());
        query.setStatus(StatusEnum.生效.code);
        SysDepartment department = sysDepartmentManager.findByQueryContion(query);
        if (department != null && !department.getId().equals(departmentDTO.getId())) {
            throw new BusinessException(ResponseEnum.部门名称已经存在);
        }
        department = BeanUtils.dtoToDeparment(departmentDTO);
        department.setPid(null);
        department.setUpdateUser(identityInfoDTO.getUserId());
        department.setUpdateName(identityInfoDTO.getUsername());
        int i = sysDepartmentManager.updateSelective(department);
        if (i < 1) {
            throw new BusinessException(ResponseEnum.部门不存在);
        }
        return departmentDTO;
    }


    /**
     * 删除部门（
     * 1、有子部门不可删除
     * 2、有用户不可删除
     * @param departmentDTO
     */
    @Override
    public void deleteDepartment(DepartmentDTO departmentDTO) {
        SysDepartmentQuery query = new SysDepartmentQuery();
        query.setPid(departmentDTO.getId());
        query.setStatus(StatusEnum.生效.code);
        SysDepartment department = sysDepartmentManager.findByQueryContion(query);
        if (department != null) {
            throw new BusinessException(ResponseEnum.部门存在子部门_不可删除);
        }

        UserDepartmentQuery refQuery = new UserDepartmentQuery();
        refQuery.setRefDepartmentId(departmentDTO.getId());
        refQuery.setStatus(StatusEnum.生效.code);
        List<UserDepartment> userDepartments = userDepartmentManager.queryList(refQuery);
        if (!EmptyUtil.isEmpty(userDepartments)) {
            throw new BusinessException(ResponseEnum.部门存在用户_不可删除);
        }
        int i = sysDepartmentManager.deleteById(departmentDTO.getId());
        if (i < 1) {
            throw new BusinessException(ResponseEnum.部门不存在);
        }
    }
}
