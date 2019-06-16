package com.hlj.proj.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName PageDTO
 * @Author TD
 * @Date 2019/5/29 14:20
 * @Description 分页数据传输
 */
@Data
public class PageDTO<T> {

    public PageDTO(Integer pageNo, Integer pageSize, Integer total, Integer totalPage, List<T> datas) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = totalPage;
        this.datas = datas;
    }

    public PageDTO(List<T> datas) {
        this.datas = datas;
    }

    /** 页码，默认是第一页 */
    private Integer pageNo = 1;
    /** 每页显示的记录数，默认是10  */
    private Integer pageSize = 10;
    /** 总记录数  */
    private Integer total;
    /** 总页数  */
    private Integer totalPage;
    /** 对应的当前页记录  */
    private List<T> datas;
}
