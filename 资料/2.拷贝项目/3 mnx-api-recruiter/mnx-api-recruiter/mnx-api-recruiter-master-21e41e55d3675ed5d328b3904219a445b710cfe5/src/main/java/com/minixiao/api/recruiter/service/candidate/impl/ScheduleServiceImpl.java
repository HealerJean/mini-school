package com.minixiao.api.recruiter.service.candidate.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minixiao.api.recruiter.dto.candidate.ScheduleDTO;
import com.minixiao.api.recruiter.entity.candidate.CandidateInfo;
import com.minixiao.api.recruiter.entity.candidate.HandleHistory;
import com.minixiao.api.recruiter.entity.candidate.Schedule;
import com.minixiao.api.recruiter.repository.candidate.ScheduleRepository;
import com.minixiao.api.recruiter.service.candidate.HandleHistoryService;
import com.minixiao.api.recruiter.service.candidate.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private HandleHistoryService handleHistoryService;

  /**
   * 保存任务安排.
   *
   * @param scheduleDTO 任务安排DTO对象
   * @return Schedule
   */
  @Override
  public UUID saveSchedule(ScheduleDTO scheduleDTO, UUID userId, String disPlayName) {
    Schedule schedule = new Schedule();
    schedule = commonPartForSaveSchedule(scheduleDTO, schedule);
    scheduleRepository.save(schedule);
    String typeString = "安排" + scheduleDTO.getType();
    handleHistoryService.commonPartForSaveHandleHistory(scheduleDTO,typeString,userId,disPlayName);
    return schedule.getId();
  }

  /**
   * 通过公司Id查询任务安排列表.
   *
   * @param recId    公司Id
   * @param pageable 分页参数
   * @return Page
   */
  @Override
  public Page<Schedule> findByRecId(UUID recId, Pageable pageable) {
    try {
      Page<Schedule> page = scheduleRepository.findByRecId(recId, pageable);
      List<Schedule> list = page.getContent();
      List<ScheduleDTO> scheduleDTOS = new ArrayList<ScheduleDTO>();
      ObjectMapper objectMapper = new ObjectMapper();
      for (int i = 0; i < list.size(); i++) {
        Schedule schedule = list.get(i);
        String candidateInfo = list.get(i).getCandidateInfo();
        CandidateInfo[] candidateInfos = objectMapper.readValue(candidateInfo, CandidateInfo[].class);
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setType(schedule.getType());
        scheduleDTO.setCandidateInfo(candidateInfos);
        scheduleDTO.setAccommodateNo(schedule.getAccommodateNo());
        scheduleDTO.setRecName(schedule.getRecName());
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDuration(schedule.getDuration());
        scheduleDTO.setExamManagerId(schedule.getExamManagerId());
        scheduleDTO.setInterviewer(schedule.getInterviewer());
        scheduleDTO.setLocation(schedule.getLocation());
        scheduleDTO.setCity(schedule.getCity());
        scheduleDTOS.add(scheduleDTO);
      }
      return new PageImpl(scheduleDTOS, pageable,page.getTotalElements());
    }catch (IOException e) {
        e.printStackTrace();
      }
    return null;
  }

  /**
   * 修改任务安排.
   *
   * @param scheduleDTO 任务安排DTO对象
   * @return Schedule
   */
  @Override
  public UUID modifySchedule(ScheduleDTO scheduleDTO, UUID scheduleId, UUID userId, String disPlayName) {
    Schedule schedule = scheduleRepository.findById(scheduleId);
    if (schedule != null) {
      commonPartForSaveSchedule(scheduleDTO, schedule);
      scheduleRepository.save(schedule);
      String typeString = "修改" + scheduleDTO.getType() + "安排";
      handleHistoryService.commonPartForSaveHandleHistory(scheduleDTO, typeString, userId, disPlayName);
      return schedule.getId();
    } else {
      return saveSchedule(scheduleDTO, userId, disPlayName);
    }
  }
  @Override
  public void deleteSchedule(UUID scheduleId, UUID userId, String disPlayName) {
    Schedule schedule = scheduleRepository.findById(scheduleId);
    try {
      if (schedule != null) {
        UUID recId = schedule.getRecId();
        String recName = schedule.getRecName();
        String type = schedule.getType();
        String candidateInfo = schedule.getCandidateInfo();
        String typeString = "取消" + type + "安排";
        scheduleRepository.delete(schedule);
        ObjectMapper objectMapper = new ObjectMapper();
        CandidateInfo[] candidateInfos = objectMapper.readValue(candidateInfo, CandidateInfo[].class);
        for (int i = 0; i < candidateInfos.length; i++) {
          CandidateInfo candidate = candidateInfos[i];
          UUID candidateId = candidate.getCandidateId();
          String candidateName = candidate.getCandidateName();
          HandleHistory handleHistory = new HandleHistory();
          handleHistory.setRecId(recId);
          handleHistory.setRecName(recName);
          handleHistory.setStatus(type);
          handleHistory.setOptType(typeString);
          handleHistory.setOptUid(userId);
          handleHistory.setOptUname(disPlayName);
          handleHistory.setApplicationId(candidateId);
          handleHistory.setCandidateName(candidateName);
          handleHistory.setDescription(candidateName
              + "在" + handleHistory.getStatus() + "阶段进行" + typeString + "操作");
          handleHistoryService.saveHandleHistory(handleHistory);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private Schedule commonPartForSaveSchedule(ScheduleDTO scheduleDTO, Schedule schedule) {
    try {
      ObjectMapper jsonCandidateInfo = new ObjectMapper();
      schedule.setAccommodateNo(scheduleDTO.getAccommodateNo());
      String recName = scheduleDTO.getRecName();
      UUID recId = scheduleDTO.getRecId();
      String type = scheduleDTO.getType();
      schedule.setCandidateInfo(jsonCandidateInfo.writeValueAsString(
          scheduleDTO.getCandidateInfo()));
      schedule.setCity(scheduleDTO.getCity());
      schedule.setDuration(scheduleDTO.getDuration());
      schedule.setExamManagerId(scheduleDTO.getExamManagerId());
      schedule.setInterviewer(scheduleDTO.getInterviewer());
      schedule.setLocation(scheduleDTO.getLocation());
      schedule.setRecId(recId);
      schedule.setRecName(recName);
      schedule.setType(type);
      return schedule;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
