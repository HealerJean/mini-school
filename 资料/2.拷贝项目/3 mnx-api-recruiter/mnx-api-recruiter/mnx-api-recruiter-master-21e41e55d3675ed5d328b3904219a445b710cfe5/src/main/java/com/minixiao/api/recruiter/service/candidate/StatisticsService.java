package com.minixiao.api.recruiter.service.candidate;

import com.minixiao.api.recruiter.entity.candidate.Statistics;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticsService {

  /**
   * 保存统计数据.
   *
   * @return Statistics
   */
  //public Statistics saveStatistics();

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
   * @return Map
   */
  public Map<String, Object> findByStatDateBetween(LocalDate from, LocalDate to);

  /**
   * 删除一段时期的统计数据.
   *
   * @param from 统计开始日期
   * @param to   统计结束日期
   */
  public void deleteByStatDateBetween(LocalDate from, LocalDate to);
}
