package com.minixiao.api.recruiter.service.candidate;

import com.minixiao.api.recruiter.dto.candidate.ScheduleDTO;
import com.minixiao.api.recruiter.entity.candidate.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ScheduleService {

  /**
   * 保存任务安排.
   *
   * @param scheduleDTO 任务安排DTO对象
   * @param userId 操作人Id
   * @param disPlayName 操作人名称
   * @return Schedule
   */
  public UUID saveSchedule(ScheduleDTO scheduleDTO, UUID userId,String disPlayName);

  /**
   * 通过公司Id查询任务安排列表.
   *
   * @param recId    公司Id
   * @param pageable 分页参数
   * @return Page
   */
  public Page<Schedule> findByRecId(UUID recId, Pageable pageable);

  /**
   * 修改任务安排.
   *
   * @param scheduleDTO 任务安排DTO对象
   * @param scheduleId  任务安排ID
   * @return Schedule
   */
  public UUID modifySchedule(ScheduleDTO scheduleDTO, UUID scheduleId, UUID userId,String disPlayName);

  /**
   * 修改任务安排.
   *
   * @param scheduleId 任务安排ID
   * @return Schedule
   */
  public void deleteSchedule(UUID scheduleId, UUID optUid, String optUname);
}
