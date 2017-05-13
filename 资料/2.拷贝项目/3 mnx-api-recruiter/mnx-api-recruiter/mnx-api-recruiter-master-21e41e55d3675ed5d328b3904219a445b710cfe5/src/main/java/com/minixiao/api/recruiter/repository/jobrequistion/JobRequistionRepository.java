package com.minixiao.api.recruiter.repository.jobrequistion;

import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.Department;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/14 17:44
 */
public interface JobRequistionRepository extends JpaRepository<JobRequistion, UUID> {

  /**
   * 根据id查找职位.
   *
   * @param id id
   */
  JobRequistion findById(UUID id);

  /**
   * @Description
   * @param title 职位名称
   * @param jobArea 工作地区
   */
  @Query(value = "SELECT COUNT(*) FROM JobRequistion WHERE recruiter = ?1 AND title = ?2 AND jobArea = ?3")
  public Integer findCountByTitleAndJobArea(RecruiterInfo recruiter, String title, String jobArea);

  /**
   * @Description
   * @param recruiter 公司
   * @param status 状态
   */
  @Query(value = "from JobRequistion j where j.recruiter=?1 and (status=?2 or ?2=null) and (j.title like (%?3%) or ?3=null) and ( jobCategory=?4 or ?4=null) and ( jobArea in ?5 or ?5=null) and ( applyPeriod.applyBeginDate>=?6) and ( applyPeriod.applyBeginDate<=?7) and ( applyPeriod.applyEndDate>=?8) and ( applyPeriod.applyEndDate<=?9) and (j.department.name in ?10 or ?10=null)")
  public Page<JobRequistion> findListByCondition(RecruiterInfo recruiter, String status, String jobTitle, String jobCategory, String[] jobArea, LocalDate beginDateFrom, LocalDate beginDateTo, LocalDate endDateFrom, LocalDate endDateTo, String[] departmentNames, Pageable page);

  /**
   * @Description
   * @param recruiter 公司
   * @param status 状态
   */
  @Query(value = "from JobRequistion j where j.recruiter=?1 and (status=?2 or ?2=null) and (j.title like (%?3%) or ?3=null) and ( jobCategory=?4 or ?4=null) and ( jobArea in ?5 or ?5=null) and ( applyPeriod.applyBeginDate>=?6) and ( applyPeriod.applyBeginDate<=?7) and (j.department.name in ?8 or ?8=null)")
  public Page<JobRequistion> findListByConditionForBeginDate(RecruiterInfo recruiter, String status, String jobTitle, String jobCategory, String[] jobArea, LocalDate beginDateFrom, LocalDate beginDateTo, String[] departmentNames, Pageable page);

  /**
   * @Description
   * @param recruiter 公司
   * @param status 状态
   */
  @Query(value = "from JobRequistion j where j.recruiter=?1 and (status=?2 or ?2=null) and (j.title like (%?3%) or ?3=null) and ( jobCategory=?4 or ?4=null) and ( jobArea in ?5 or ?5=null) and ( applyPeriod.applyEndDate>=?6) and ( applyPeriod.applyEndDate<=?7) and (j.department.name in ?8 or ?8=null)")
  public Page<JobRequistion> findListByConditionForEndDate(RecruiterInfo recruiter, String status, String jobTitle, String jobCategory, String[] jobArea, LocalDate endDateFrom, LocalDate endDateTo, String[] departmentNames, Pageable page);

  /**
   * @Description
   * @param recruiter 公司
   * @param status 状态
   */
  @Query(value = "from JobRequistion j where j.recruiter=?1 and (status=?2 or ?2=null) and (j.title like (%?3%) or ?3=null) and ( jobCategory=?4 or ?4=null) and ( jobArea in ?5 or ?5=null) and (j.department.name in ?6 or ?6=null)")
  public Page<JobRequistion> findListByConditionNoDate(RecruiterInfo recruiter, String status, String jobTitle, String jobCategory, String[] jobArea, String[] departmentNames, Pageable page);


  /**
   * @Description
   * @param recruiter 职位名称
   */
  @Query(value = "SELECT COUNT(*) FROM JobRequistion WHERE recruiter = ?1")
  public Integer findCountByRecruiterInfo(RecruiterInfo recruiter);

  /**
   * @Description
   * @param recruiter 公司
   */
  @Query(value = "from JobRequistion j where j.recruiter=?1 and ( audit.status=?2 or ?2=null) and (j.title like (%?3%) or ?3=null) and ( jobCategory=?4 or ?4=null) and ( jobArea in ?5 or ?5=null) and ( applyPeriod.applyBeginDate>=?6) and ( applyPeriod.applyBeginDate<=?7) and ( applyPeriod.applyEndDate>=?8) and ( applyPeriod.applyEndDate<=?9) and j.status=null and (j.department.name in ?10 or ?10=null)")
  public Page<JobRequistion> findListByConditionForAuditStatus(RecruiterInfo recruiter,
                                                               String auditStatus, String jobTitle,
                                                               String jobCategory, String[] jobArea,
                                                               LocalDate beginDateFrom,
                                                               LocalDate beginDateTo,
                                                               LocalDate endDateFrom,
                                                               LocalDate endDateTo, String[] departmentNames, Pageable page);

  /**
   * @Description
   * @param recruiter 公司
   */
  @Query(value = "from JobRequistion j where j.recruiter=?1 and ( audit.status=?2 or ?2=null) and (j.title like (%?3%) or ?3=null) and ( jobCategory=?4 or ?4=null) and ( jobArea in ?5 or ?5=null) and j.status=null and (j.department.name in ?6 or ?6=null)")
  public Page<JobRequistion> findListByConditionForAuditStatusNoDate(RecruiterInfo recruiter,
                                                               String auditStatus, String jobTitle,
                                                               String jobCategory, String[] jobArea,
                                                               String[] departmentNames, Pageable page);

  /**
   * @Description
   * @param recruiter 公司
   */
  @Query(value = "from JobRequistion j where j.recruiter=?1 and ( audit.status=?2 or ?2=null) and (j.title like (%?3%) or ?3=null) and ( jobCategory=?4 or ?4=null) and ( jobArea in ?5 or ?5=null) and ( applyPeriod.applyBeginDate>=?6) and ( applyPeriod.applyBeginDate<=?7) and j.status=null and (j.department.name in ?8 or ?8=null)")
  public Page<JobRequistion> findListByConditionForAuditStatusAndBeginDate(RecruiterInfo recruiter,
                                                               String auditStatus, String jobTitle,
                                                               String jobCategory, String[] jobArea,
                                                               LocalDate beginDateFrom,
                                                               LocalDate beginDateTo,
                                                               String[] departmentNames, Pageable page);

  /**
   * @Description
   * @param recruiter 公司
   */
  @Query(value = "from JobRequistion j where j.recruiter=?1 and ( audit.status=?2 or ?2=null) and (j.title like (%?3%) or ?3=null) and ( jobCategory=?4 or ?4=null) and ( jobArea in ?5 or ?5=null)  and ( applyPeriod.applyEndDate>=?6) and ( applyPeriod.applyEndDate<=?7) and j.status=null and (j.department.name in ?8 or ?8=null)")
  public Page<JobRequistion> findListByConditionForAuditStatusAndEndDate(RecruiterInfo recruiter,
                                                               String auditStatus, String jobTitle,
                                                               String jobCategory, String[] jobArea,
                                                               LocalDate endDateFrom,
                                                               LocalDate endDateTo, String[] departmentNames, Pageable page);


  /**
   *
   *
   * @Description
   * @param recruiter 公司名称
   */
  @Query(value = "SELECT DISTINCT jobArea FROM JobRequistion WHERE recruiter = ?1")
  public String[] findJobAreaByRecruiterInfo(RecruiterInfo recruiter);

  /**
   *
   *
   * @Description
   * @param recruiter 公司名称
   */
  @Query(value = "SELECT count(*) FROM JobRequistion WHERE recruiter = ?1 and status= open")
  public Integer findOpenCount(RecruiterInfo recruiter);

  /**
   *
   *
   * @Description
   * @param recruiter 公司名称
   */
  @Query(value = "SELECT count(*) FROM JobRequistion WHERE recruiter = ?1 and status= on_hold")
  public Integer findOnHoldCount(RecruiterInfo recruiter);

  /**
   *
   *
   * @Description
   * @param recruiter 公司名称
   */
  @Query(value = "SELECT count(*) FROM JobRequistion WHERE recruiter = ?1 and audit.status= waiting and status=null")
  public Integer findWaitingCount(RecruiterInfo recruiter);

  /**
   *
   *
   * @Description
   * @param recruiter 公司名称
   */
  //@Query(value = "SELECT count(*) FROM JobRequistion WHERE recruiter = ?1 and audit.status= notThrough and status=null")
  //public Integer findNotThroughCount(RecruiterInfo recruiter);


  /**
   *
   *
   * @Description
   * @param recruiter 公司名称
   */
  @Query(value = "SELECT DISTINCT title FROM JobRequistion WHERE recruiter = ?1")
  public List<String> findTitleByRecruiterInfo(RecruiterInfo recruiter);

  /**
   *
   *
   * @Description
   * @param departmentId 公司名称
   */
  @Query(value = "SELECT count(*) FROM JobRequistion WHERE department.id = ?1")
  public int findByDepartment(UUID departmentId);
}
