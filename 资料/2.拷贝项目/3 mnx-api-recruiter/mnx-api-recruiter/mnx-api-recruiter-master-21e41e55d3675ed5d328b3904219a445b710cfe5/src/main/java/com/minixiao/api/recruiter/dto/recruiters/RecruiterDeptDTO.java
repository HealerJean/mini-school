package com.minixiao.api.recruiter.dto.recruiters;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/19 14:25
 */

public class RecruiterDeptDTO {
    //部门名称
    private String name;

    //上级部门Id
    private String parentId;

    /**
     * .
     */
    public RecruiterDeptDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
