package com.minixiao.api.recruiter.repository.candidate;

import com.minixiao.api.recruiter.entity.candidate.Applications;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


/**
 * Created by WangYingjie on 2017/2/13.
 */
@Repository
public interface ApplicationRepository extends CrudRepository<Applications, UUID> {

  Applications findById(UUID id);

  /**
   * @Description 修改申请表状态.
   * @Author  wangyj
   * @CreateDate 2017/3/6 14:56
   * @Param
   * @Return
   */
  @Modifying
  @Query(value = "update Applications set status = ?2 where id = ?1")
  int updateApplicationStatus(UUID id, String status);

  /**
   * @Description 修改申请表阶段.
   * @Author  wangyj
   * @CreateDate 2017/3/6 14:55
   * @Param
   * @Return
   */
  @Modifying
  @Query(value = "update Applications set stage = ?2 ,stage_id = ?3 where id = ?1")
  int updateApplicationStage(UUID id, String stage, UUID stageId);

  /**
   * @Description 动态获取申请表总数.
   * @Author  wangyj
   * @CreateDate 2017/3/8 15:08
   * @Param
   * @Return
   */
  @Query(value = "SELECT * from func_candidate_application_count(:recId,:position,:degree,:school," +
      ":major,:stageId,:status,:labels,:gender,:department,:rank,:englishLevel,:reward, :attachment,:name," +
      ":mobile,:city,:birthFrom,:birthTo,:graduateFrom,:graduateTo,:deliveryFrom,:deliveryTo)",
      nativeQuery = true)
  Integer countApplicaiton(@Param("recId") UUID recId,
                           @Param("position") String position,
                           @Param("degree") String degree,
                           @Param("school") String school,
                           @Param("major") String major,
                           @Param("stageId") UUID stageId,
                           @Param("status") String status,
                           @Param("labels") String labels,
                           @Param("gender") String gender,
                           @Param("department") String department,
                           @Param("rank") String rank,
                           @Param("englishLevel") String englishLevel,
                           @Param("reward") String reward,
                           @Param("attachment") String attachment,
                           @Param("name") String name,
                           @Param("mobile") String mobile,
                           @Param("city") String city,
                           @Param("birthFrom") String birthFrom,
                           @Param("birthTo") String birthTo,
                           @Param("graduateFrom") String graduateFrom,
                           @Param("graduateTo") String graduateTo,
                           @Param("deliveryFrom") String deliveryFrom,
                           @Param("deliveryTo") String deliveryTo);

  /**
   * @Description 动态查询申请表列表.
   * @Author  wangyj
   * @CreateDate 2017/3/6 14:56
   * @Param
   * @Return
   */
  @Query(value = "SELECT * from func_candidate_application_list(:recId,:position,:degree,:school," +
      ":major,:stageId,:status,:labels,:gender,:department,:rank,:englishLevel,:reward, :attachment,:name," +
      ":mobile,:city,:birthFrom,:birthTo,:graduateFrom,:graduateTo,:deliveryFrom," +
      ":deliveryTo,:number,:size,:sort) AS app (id uuid,rec_id uuid,rec_name VARCHAR,basic jsonb," +
      "education jsonb,job_target jsonb,language jsonb,meta_data jsonb,practices VARCHAR," +
      "resume jsonb,stage VARCHAR,club VARCHAR,skills jsonb,reward VARCHAR,certificate VARCHAR," +
      "stage_id uuid, status VARCHAR,stu_id uuid,update_time TIMESTAMP,work VARCHAR,apply_date TIMESTAMP )",
      nativeQuery = true)
  List<Applications> listApplication(@Param("recId") UUID recId,
                                     @Param("position") String position,
                                     @Param("degree") String degree,
                                     @Param("school") String school,
                                     @Param("major") String major,
                                     @Param("stageId") UUID stageId,
                                     @Param("status") String status,
                                     @Param("labels") String labels,
                                     @Param("gender") String gender,
                                     @Param("department") String department,
                                     @Param("rank") String rank,
                                     @Param("englishLevel") String englishLevel,
                                     @Param("reward") String reward,
                                     @Param("attachment") String attachment,
                                     @Param("name") String name,
                                     @Param("mobile") String mobile,
                                     @Param("city") String city,
                                     @Param("birthFrom") String birthFrom,
                                     @Param("birthTo") String birthTo,
                                     @Param("graduateFrom") String graduateFrom,
                                     @Param("graduateTo") String graduateTo,
                                     @Param("deliveryFrom") String deliveryFrom,
                                     @Param("deliveryTo") String deliveryTo,
                                     @Param("number") Integer number,
                                     @Param("size") Integer size,
                                     @Param("sort") String sort);



  @Query("select count(*) from Applications a WHERE a.applyDate between ?1 and ?2")
  public int findForPerRecCount(LocalDateTime from, LocalDateTime to);

  /**
   * @Description 通过Id查询申请表状态.
   * @Author  wangyj
   * @CreateDate 2017/3/6 14:57
   * @Param
   * @Return
   */
  @Query("select a.status from Applications a where a.id = ?1")
  String findStatusById(UUID id);

  /**
   * @Description 查询申请表总数.
   * @Author  wangyj
   * @CreateDate 2017/3/6 14:58
   * @Param
   * @Return
   */
  @Query("select count(*) from Applications a where a.recId =?1")
  Integer applicationCount(UUID recId);

  /**
   * @Description 已处理的申请表总数.
   * @Author  wangyj
   * @CreateDate 2017/3/6 14:58
   * @Param
   * @Return
   */
  @Query(value = "select count(*) from tb_candidate_application a where a.rec_id = ?1 and a.status not like '1___'", nativeQuery = true)
  Integer dealedCount(UUID recId);

  /**
   * @Description 已发offer总数.
   * @Author  wangyj
   * @CreateDate 2017/3/6 14:59
   * @Param
   * @Return
   */
  @Query(value = "select count(*) from tb_candidate_application a where a.rec_id = ?1 and a.status like '5___'", nativeQuery = true)
  Integer offerCount(UUID recId);

  /**
   * @Description 近7天申请表投递数量.
   * @Author  wangyj
   * @CreateDate 2017/3/6 14:59
   * @Param
   * @Return
   */
  @Query(value = "SELECT d,COALESCE(count,0) FROM generate_series( ?1\\:\\:date,?2, '1 days') d " +
      "LEFT JOIN (SELECT date_trunc('day', a.apply_date ) date,  count(*)" +
      " FROM tb_candidate_application a WHERE a.rec_id=?3 and date_trunc('day', a.apply_date ) BETWEEN ?1 and ?2 " +
      "GROUP BY date_trunc('day', a.apply_date ) order by date_trunc('day', a.apply_date ) asc ) b" +
      " ON d=b.date", nativeQuery = true)
  List<Object> sevenDayStat(LocalDate fromDate, LocalDate toDate, UUID recId);

  /**
   * @Description 根据职位id查询该职位投递总数.
   * @Author  wangyj
   * @CreateDate 2017/3/6 15:00
   * @Param
   * @Return
   */
  @Query(value = "select count(*) from tb_candidate_application a, jsonb_to_recordset(a.job_target) as job(id uuid,is_current BOOLEAN) where rec_id=?1 and job.id=?2",nativeQuery = true)
  Integer countByJobId(UUID recId,UUID jobId);
}
