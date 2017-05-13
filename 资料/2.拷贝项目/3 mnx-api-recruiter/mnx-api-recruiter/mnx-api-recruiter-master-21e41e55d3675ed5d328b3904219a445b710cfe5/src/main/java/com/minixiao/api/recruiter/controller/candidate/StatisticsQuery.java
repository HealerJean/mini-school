package com.minixiao.api.recruiter.controller.candidate;

import com.minixiao.api.recruiter.entity.candidate.Statistics;
import com.minixiao.api.recruiter.service.candidate.StatisticsService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class StatisticsQuery {

  @Autowired
  private StatisticsService service;

  /**
   * 查询所有公司指定某一天的日期的统计.
   *
   * @param statDate 统计日期
   * @return Statistics
   */
  @GetMapping(value = "/candidate/statistics")
  public ResponseEntity<Statistics> findByStatDate(String statDate) {
    try {
      LocalDate statDate2 = LocalDate.parse(statDate);
      Statistics result = service.findByStatDate(statDate2);
      return ResponseEntity.ok(result);
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(
          CacheControl.noCache()).body(null);
    }
  }

  /**
   * 获取一段时期的所有公司统计数据.
   *
   * @param dateFrom 该时期的开始时间
   * @param dateTo   该时期的结束时间
   * @return ResponseEntity
   */
  @RequiresRoles("ADMIN")
  @GetMapping(value = "/candidate/statistics/period")
  public ResponseEntity<Map<String, Object>> findStatisticsForPeriod(
      String dateFrom, String dateTo) {
    try {
      LocalDate from = LocalDate.parse(dateFrom);
      LocalDate to = LocalDate.parse(dateTo);
      Map<String, Object> result = service.findByStatDateBetween(from, to);
      return ResponseEntity.ok(result);
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(
          CacheControl.noCache()).body(null);
    }
  }
}
