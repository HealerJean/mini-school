package com.minixiao.api.recruiter.controller.candidate;

import com.minixiao.api.recruiter.entity.candidate.Statistics;
import com.minixiao.api.recruiter.service.candidate.StatisticsService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class StatisticsCommand {

  @Autowired
  private StatisticsService service;

  /**
   * 删除一段时期的统计数据.
   *
   * @param dateFrom 该时期的开始时间
   * @param dateTo   该时期的结束时间
   * @return ResponseEntity
   */
  @DeleteMapping(value = "/candidate/statistics/period")
  public ResponseEntity<List<Statistics>> deleteStatisticsForPeriod(
      String dateFrom, String dateTo) {
    try {
      LocalDate from = LocalDate.parse(dateFrom);
      LocalDate to = LocalDate.parse(dateTo);
      service.deleteByStatDateBetween(from, to);
      return ResponseEntity.status(HttpStatus.OK).cacheControl(
          CacheControl.noCache()).body(null);
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(
          CacheControl.noCache()).body(null);
    }
  }

}
