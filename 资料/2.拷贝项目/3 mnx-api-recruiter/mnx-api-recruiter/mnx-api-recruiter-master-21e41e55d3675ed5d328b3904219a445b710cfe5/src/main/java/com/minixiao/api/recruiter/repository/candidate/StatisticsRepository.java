
package com.minixiao.api.recruiter.repository.candidate;

import com.minixiao.api.recruiter.entity.candidate.Statistics;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface StatisticsRepository extends CrudRepository<Statistics, UUID> {

  /**
   * 查询指定某一天的日期的统计.
   *
   * @param statDate 统计日期
   * @return Statistics
   */
  public Statistics findByStatDate(LocalDate statDate);

  /**
   * 获取一段时期的统计数据.
   *
   * @param from 统计开始日期
   * @param to   统计结束日期
   * @return List
   */
  public List<Statistics> findByStatDateBetween(LocalDate from, LocalDate to);

  /**
   * 删除一段时期的统计数据.
   *
   * @param from 统计开始日期
   * @param to   统计结束日期
   */
  @Query("DELETE FROM Statistics s WHERE s.statDate between ?1 and ?2")
  @Modifying
  public void deleteByStatDateBetween(LocalDate from, LocalDate to);

}
