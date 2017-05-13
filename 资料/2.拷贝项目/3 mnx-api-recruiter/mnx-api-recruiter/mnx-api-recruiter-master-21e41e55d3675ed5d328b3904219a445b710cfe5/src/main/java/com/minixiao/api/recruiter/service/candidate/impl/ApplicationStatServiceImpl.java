package com.minixiao.api.recruiter.service.candidate.impl;

import com.minixiao.api.recruiter.dto.candidate.ApplicationStatDTO;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.candidate.ApplicationRepository;
import com.minixiao.api.recruiter.repository.jobrequistion.JobRequistionRepository;
import com.minixiao.api.recruiter.service.candidate.ApplicationStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/3/3  17:25.
 */
@Service
public class ApplicationStatServiceImpl implements ApplicationStatService {

  @Autowired
  private ApplicationRepository applicationRepository;

  @Autowired
  private JobRequistionRepository jobRequistionRepository;

  private Logger logger = LoggerFactory.getLogger(ApplicationStatServiceImpl.class);

  /**
   * @Description 申请表统计相关.
   * @Author  wangyj
   * @CreateDate 2017/3/16 14:36
   * @Param
   * @Return
   */
  @Override
  public ApplicationStatDTO applicationStat(UUID recId) {
    logger.info("get recId:{} statistic info",recId);
    Integer applicationCount = applicationRepository.applicationCount(recId);
    Integer dealedCount = applicationRepository.dealedCount(recId);
    Integer offerCount = applicationRepository.offerCount(recId);
    RecruiterInfo recruiterInfo = new RecruiterInfo();
    recruiterInfo.setId(recId);
    Integer jobCount = jobRequistionRepository.findCountByRecruiterInfo(recruiterInfo);
    LocalDate toDate = LocalDateTime.now().toLocalDate();
    LocalDate fromDate = toDate.plusDays(-6);
    List<Object> list = applicationRepository.sevenDayStat(fromDate, toDate, recId);
    ApplicationStatDTO applicationStatDTO = new ApplicationStatDTO();
    applicationStatDTO.setApplicationCount(applicationCount);
    applicationStatDTO.setDealedCount(dealedCount);
    applicationStatDTO.setOfferCount(offerCount);
    applicationStatDTO.setJobCout(jobCount);
    applicationStatDTO.setSevenDayStat(list);
    return applicationStatDTO;
  }
}
