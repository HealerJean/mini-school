
package com.minixiao.api.recruiter.repository.candidate;

import com.minixiao.api.recruiter.entity.candidate.HandleHistory;
import com.minixiao.api.recruiter.entity.candidate.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;


public interface ScheduleRepository extends CrudRepository<Schedule, UUID>,
    PagingAndSortingRepository<Schedule, UUID> {

  /**
   * 查询任务安排列表.
   * @param recId 公司Id
   * @param pageable 分页参数
   * @return Page
   */
  public Page<Schedule> findByRecId(UUID recId, Pageable pageable);

  /**
   * 查询任务安排.
   * @param scheduleId 公司Id
   * @return Page
   */
  public Schedule findById(UUID scheduleId);
}
