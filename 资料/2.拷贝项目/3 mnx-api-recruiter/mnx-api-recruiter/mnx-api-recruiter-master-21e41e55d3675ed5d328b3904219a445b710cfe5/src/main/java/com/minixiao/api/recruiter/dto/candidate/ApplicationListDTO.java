package com.minixiao.api.recruiter.dto.candidate;

import java.util.List;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/28  11:48.
 */
public class ApplicationListDTO {
  private Integer total;

  private Integer pageNumber;

  private Integer pageSize;

  private List<ApplicationDTO> applicationDTOList;

  public ApplicationListDTO() {
  }

  public ApplicationListDTO(Integer total, List<ApplicationDTO> applicationDTOList,
                            Integer pageNumber, Integer pageSize) {
    this.total = total;
    this.applicationDTOList = applicationDTOList;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<ApplicationDTO> getApplicationDTOList() {
    return applicationDTOList;
  }

  public void setApplicationDTOList(List<ApplicationDTO> applicationDTOList) {
    this.applicationDTOList = applicationDTOList;
  }

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}
