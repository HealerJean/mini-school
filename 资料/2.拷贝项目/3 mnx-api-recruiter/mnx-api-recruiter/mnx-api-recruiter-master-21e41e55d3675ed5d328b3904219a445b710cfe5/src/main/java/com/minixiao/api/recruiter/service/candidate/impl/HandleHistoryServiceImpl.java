package com.minixiao.api.recruiter.service.candidate.impl;

import com.minixiao.api.recruiter.dto.candidate.ScheduleDTO;
import com.minixiao.api.recruiter.entity.candidate.CandidateInfo;
import com.minixiao.api.recruiter.entity.candidate.HandleHistory;
import com.minixiao.api.recruiter.repository.candidate.HandleHistoryRepository;
import com.minixiao.api.recruiter.service.candidate.HandleHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class HandleHistoryServiceImpl implements HandleHistoryService {

  @Autowired
  private HandleHistoryRepository dao;

  /**
   * 保存处理历史.
   *
   * @param history 处理历史
   * @return HandleHistory
   */
  @Override
  @Async
  public HandleHistory saveHandleHistory(HandleHistory history) {
    return dao.save(history);
  }


  @Override
  public Page<HandleHistory> findByApplicationId(UUID applicationId, Pageable page) {
    return dao.findByApplicationId(applicationId, page);
  }

  @Override
  public void commonPartForSaveHandleHistory(ScheduleDTO scheduleDTO, String typeString, UUID userId, String disPlayName) {
    CandidateInfo[] candidateInfos = scheduleDTO.getCandidateInfo();
    for (int i = 0; i < candidateInfos.length; i++) {
      CandidateInfo candidateInfo = candidateInfos[i];
      UUID candidateId = candidateInfo.getCandidateId();
      String candidateName = candidateInfo.getCandidateName();
      HandleHistory handleHistory = new HandleHistory();
      handleHistory.setRecId(scheduleDTO.getRecId());
      handleHistory.setRecName(scheduleDTO.getRecName());
      handleHistory.setStatus(scheduleDTO.getType());
      handleHistory.setOptType(typeString);
      handleHistory.setOptUid(userId);
      handleHistory.setOptUname(disPlayName);
      handleHistory.setApplicationId(candidateId);
      handleHistory.setCandidateName(candidateName);
      handleHistory.setDescription(candidateName
          + "在" + handleHistory.getStatus() + "阶段进行" + typeString + "操作");
      dao.save(handleHistory);
    }
  }
}
