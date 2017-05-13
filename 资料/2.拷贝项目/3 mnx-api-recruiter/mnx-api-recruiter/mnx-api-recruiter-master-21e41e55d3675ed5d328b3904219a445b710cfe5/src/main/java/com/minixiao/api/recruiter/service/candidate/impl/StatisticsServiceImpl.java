package com.minixiao.api.recruiter.service.candidate.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minixiao.api.recruiter.entity.candidate.Applications;
import com.minixiao.api.recruiter.entity.candidate.Statistics;
import com.minixiao.api.recruiter.repository.candidate.ApplicationRepository;
import com.minixiao.api.recruiter.repository.candidate.StatisticsRepository;
import com.minixiao.api.recruiter.service.candidate.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

  @Autowired
  private StatisticsRepository statisticsRepository;
  @Autowired
  private ApplicationRepository applicationRepository;

  /**
   * 保存统计数据.
   *
   * @return Statistics
   */
  /*@Override
  public Statistics saveStatistics() {
    Statistics statistics = new Statistics();
    LocalDate applyDate = LocalDate.now();
    String dateString = applyDate.toString();
    String dateStringFrom = dateString.concat("T00:00:00.000");
    String dateStringTo = dateString.concat("T23:59:59.999");
    LocalDateTime from = LocalDateTime.parse(dateStringFrom);
    LocalDateTime to = LocalDateTime.parse(dateStringTo);
    int[] countInt = applicationRepository.findCount(from, to);
    statistics.setPerRecCount(countInt[0]);
    statistics.setApplyCount(countInt[1]);
    statistics.setPerJobCount(countInt[2]);
    return  statisticsRepository.save(statistics);
  }*/

  /**
   * 查询指定某一天的日期的统计.
   *
   * @param statDate 统计日期
   * @return Statistics
   */
  @Override
  public Statistics findByStatDate(LocalDate statDate) {
    return statisticsRepository.findByStatDate(statDate);
  }

  /**
   * 获取一段时期的统计数据.
   *
   * @param from 统计开始日期
   * @param to   统计结束日期
   * @return Map
   */
  @Override
  public Map<String, Object> findByStatDateBetween(LocalDate from, LocalDate to) {
    List<Statistics> list = statisticsRepository.findByStatDateBetween(from, to);
    Map<String, Object> map = new HashMap<String, Object>();
    int applyTotal = 0;
    int jobTotal = 0;
    int recTotal = 0;
    for (int i = 0; i < list.size(); i++) {
      Statistics statistics = list.get(i);
      applyTotal += statistics.getApplyCount();
      jobTotal += statistics.getPerJobCount();
      recTotal += statistics.getPerRecCount();
    }
    map.put("data", list);
    map.put("applyTotal", applyTotal);
    map.put("jobTotal", jobTotal);
    map.put("recTotal", recTotal);
    return map;
  }

  /**
   * 删除一段时期的统计数据.
   *
   * @param from 统计开始日期
   * @param to   统计结束日期
   */
  public void deleteByStatDateBetween(LocalDate from, LocalDate to) {
    statisticsRepository.deleteByStatDateBetween(from, to);
  }
}
