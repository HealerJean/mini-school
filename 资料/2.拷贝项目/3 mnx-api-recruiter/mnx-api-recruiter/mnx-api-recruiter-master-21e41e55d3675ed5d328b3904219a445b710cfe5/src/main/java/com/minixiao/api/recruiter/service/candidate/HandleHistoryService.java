package com.minixiao.api.recruiter.service.candidate;

import com.minixiao.api.recruiter.dto.candidate.ScheduleDTO;
import com.minixiao.api.recruiter.entity.candidate.HandleHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface HandleHistoryService {

  /**
   * 保存处理历史.
   * @param history 处理历史
   * @return HandleHistory
   */
  public HandleHistory saveHandleHistory(HandleHistory history);

  public Page<HandleHistory> findByApplicationId(UUID applicationId, Pageable page);

  public void commonPartForSaveHandleHistory(ScheduleDTO scheduleDTO, String typeString, UUID userId, String disPlayName);

  }
