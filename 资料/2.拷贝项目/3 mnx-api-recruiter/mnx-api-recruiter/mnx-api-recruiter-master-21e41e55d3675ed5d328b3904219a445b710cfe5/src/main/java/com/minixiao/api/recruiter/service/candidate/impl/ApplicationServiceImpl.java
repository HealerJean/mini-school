package com.minixiao.api.recruiter.service.candidate.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minixiao.api.recruiter.dto.candidate.AppStageListDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationFilterDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationListDTO;
import com.minixiao.api.recruiter.dto.candidate.Basic;
import com.minixiao.api.recruiter.dto.candidate.Education;
import com.minixiao.api.recruiter.dto.candidate.JobTarget;
import com.minixiao.api.recruiter.dto.candidate.Language;
import com.minixiao.api.recruiter.dto.candidate.Resume;
import com.minixiao.api.recruiter.dto.candidate.Skills;
import com.minixiao.api.recruiter.dto.candidate.UpdateStatusDTO;
import com.minixiao.api.recruiter.dto.notification.Notifications;
import com.minixiao.api.recruiter.dto.notification.Sender;
import com.minixiao.api.recruiter.dto.notification.Target;
import com.minixiao.api.recruiter.dto.notification.To;
import com.minixiao.api.recruiter.entity.candidate.Applications;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterFlow;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.candidate.ApplicationRepository;
import com.minixiao.api.recruiter.repository.recruiters.RecruiterFlowRepository;
import com.minixiao.api.recruiter.service.candidate.ApplicationService;
import com.minixiao.apiauth.client.HeaderUtil;
import mnx.infra.apiauth.server.domain.AuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by WangYingjie on 2017/2/13.
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

  @Autowired
  private ApplicationRepository applicationRepository;

  @Autowired
  private RecruiterFlowRepository recruiterFlowRepository;

  private Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

  private RestTemplate restTemplate = new RestTemplate();

  private final String NOTIFICATION_URL = "http://192.168.1.69:9527/";

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * @Description 创建一个申请表.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/15 15:32
   * @Param
   * @Return
   */
  @Override
  public String saveApplication(ApplicationDTO applicationDTO) throws JsonProcessingException {


    UUID stuId = applicationDTO.getStuId();
    logger.info("{} creat a application", stuId);
    Applications applications = new Applications();
    applications.setStuId(stuId);
    applications.setRecId(applicationDTO.getRecId());
    applications.setRecName(applicationDTO.getRecName());
    //将jobTarget转换为josn
    String jobTarget = objectMapper.writeValueAsString(applicationDTO.getJobTarget());
    applications.setJobTarget(jobTarget);
    //将resume转换为josn
    String resume = objectMapper.writeValueAsString(applicationDTO.getResume());
    applications.setResume(resume);
    //将basic对象转换为json
    String basic = objectMapper.writeValueAsString(applicationDTO.getBasic());
    applications.setBasic(basic);
    //将education转换为json
    String education = objectMapper.writeValueAsString(applicationDTO.getEducation());
    applications.setEducation(education);
    //将skills转换为json
    String skills = objectMapper.writeValueAsString(applicationDTO.getSkills());
    applications.setSkills(skills);
    //将metaDate转换为json
    String metaData = objectMapper.writeValueAsString(applicationDTO.getMetaData());
    applications.setMetaData(metaData);
    //将language转换为json
    String language = objectMapper.writeValueAsString(applicationDTO.getLanguage());
    applications.setLanguage(language);
    applications.setWork(applicationDTO.getWork());
    applications.setPractices(applicationDTO.getPractices());
    applications.setCertificate(applicationDTO.getCertificate());
    applications.setClub(applicationDTO.getClub());
    applications.setReward(applicationDTO.getReward());
    RecruiterInfo recruiterInfo = new RecruiterInfo();
    recruiterInfo.setId(applicationDTO.getRecId());
    List<RecruiterFlow> recruiterFlowList = recruiterFlowRepository.findByRecruiterInfoOrderByFlowOrderAsc(recruiterInfo);
    RecruiterFlow recruiterFlow = recruiterFlowList.get(0);
    applications.setStageId(recruiterFlow.getId());
    applications.setStage(recruiterFlow.getName());
    applications.setStatus(applicationDTO.getStatus());//通过前段传入还是直接设置？？

    applicationRepository.save(applications);
    //更改阶段对应的候选人人数
    logger.info("update reference stage candidateCount");
    recruiterFlowRepository.updateCandidateCount(recruiterFlow.getCandidateCount() + 1,
        recruiterFlow.getId());
    //初始化时向用户发送消息
    Subject currentUser = SecurityUtils.getSubject();
    AuthUser authUser = (AuthUser) currentUser.getPrincipal();
    UUID recId = authUser.getOid();
    UUID optUid = authUser.getUid();
    String recName = authUser.getOnm();
    String optName = authUser.getDnm();

    Sender sender = new Sender();
    sender.setNickname(recName);
    sender.setUid(recId);
    sender.setUtype("COMPANY");
    To to = new To();
    to.setNickname(applicationDTO.getBasic().getName());
    to.setUid(applicationDTO.getStuId());
    to.setUtype("STUDENT");
    Target target = new Target();
    target.setOid((applicationDTO.getJobTarget())[0].getId());
    target.setOtype("STUDENT");
    Notifications notifications = new Notifications();
    notifications.setSender(sender);
    notifications.setTo(to);
    notifications.setTarget(target);
    notifications.setAction("initia");
    notifications.setType("remind");
    Map<String, String> map = new HashMap<String, String>();
    map.put("s", "1");
    map.put("cs", "4");
    map.put("companyName", applicationDTO.getRecName());
    map.put("jobName", (applicationDTO.getJobTarget())[0].getJobName());
    notifications.setVars(map);
    HttpHeaders requestHeaders = HeaderUtil.getHeader(optUid, optName, "COMPANY", recId,
        recName);
    try {
      HttpEntity requestEntity = new HttpEntity(notifications, requestHeaders);
      logger.info("call interface to add notification");
      ResponseEntity<String> responseEntity = restTemplate.exchange(
          NOTIFICATION_URL + "notifications", HttpMethod.POST, requestEntity,
          String.class);
    } catch (RestClientException e) {
      logger.error("call interface to add notification", e);
      e.printStackTrace();
    }

    return "ok";
  }

  /**
   * @Description 根据id查询申请表.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/16 15:07
   * @Param
   * @Return
   */
  @Override
  public Applications findById(UUID id) {
    logger.info("query applicaiton by id");
    return applicationRepository.findById(id);
  }

  /**
   * @Description 修改申请表.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/16 15:07
   * @Param
   * @Return
   */
  @Override
  public String updateApplication(Applications applications, ApplicationDTO applicationDTO)
      throws JsonProcessingException {
    UUID stuId = applicationDTO.getStuId();
    logger.info("{} update applicaiton", stuId);
    applications.setStuId(stuId);
    applications.setRecId(applicationDTO.getRecId());
    applications.setRecName(applicationDTO.getRecName());
    //将jobTarget转换为josn
    String jobTarget = objectMapper.writeValueAsString(applicationDTO.getJobTarget());
    applications.setJobTarget(jobTarget);
    //将resume转换为josn
    String resume = objectMapper.writeValueAsString(applicationDTO.getResume());
    applications.setResume(resume);
    //将basic对象转换为json
    String basic = objectMapper.writeValueAsString(applicationDTO.getBasic());
    applications.setBasic(basic);
    //将education转换为json
    String education = objectMapper.writeValueAsString(applicationDTO.getEducation());
    applications.setEducation(education);
    //将skills转换为json
    String skills = objectMapper.writeValueAsString(applicationDTO.getSkills());
    applications.setSkills(skills);
    //将metaDate转换为json
    String metaData = objectMapper.writeValueAsString(applicationDTO.getMetaData());
    applications.setMetaData(metaData);
    //将language转换为json
    String language = objectMapper.writeValueAsString(applicationDTO.getLanguage());
    applications.setLanguage(language);
    applications.setWork(applicationDTO.getWork());
    applications.setPractices(applicationDTO.getPractices());
    applications.setCertificate(applicationDTO.getCertificate());
    applications.setClub(applicationDTO.getClub());
    applications.setReward(applicationDTO.getReward());
    applications.setStage(applicationDTO.getStage());
    applications.setStatus(applicationDTO.getStatus());
    applicationRepository.save(applications);
    return "ok";
  }

  /**
   * @Description 更改申请表阶段.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/16 15:08
   * @Param
   * @Return
   */
  @Async
  @Override
  public String updateApplicationStage(UUID id, String stage, UUID stageId) {
    logger.info("update id:{} applicaiton stage", id);
    int result = applicationRepository.updateApplicationStage(id, stage, stageId);
    if (result > 0) {
      return "ok";
    } else {
      return "failed";
    }
  }


  /**
   * @Description 更改申请表状态.
   * @Author wangyj
   * @CreateDate 2017/3/1 11:44
   * @Param
   * @Return
   */
  @Override
  public String updateApplicationStatus(AppStageListDTO dto, String status) {
    UUID id = dto.getId();
    logger.info("update id:{} application status");
    String currStatus = applicationRepository.findStatusById(id);
    char[] statusArray = currStatus.toCharArray();
    if ("待定".equals(status)) {
      statusArray[0] = '2';
    } else if ("不通过".equals(status)) {
      statusArray[0] = '3';
    } else if ("淘汰".equals(status)) {
      statusArray[0] = '4';
    } else if ("确定OFFER".equals(status)) {
      statusArray[0] = '5';
    } else if ("未安排".equals(status)) {
      statusArray[1] = '1';
    } else if ("已安排".equals(status)) {
      statusArray[1] = '2';
    } else if ("未通知".equals(status)) {
      statusArray[2] = '1';
    } else if ("已通知".equals(status)) {
      statusArray[2] = '2';
    } else if ("未查看".equals(status)) {
      statusArray[3] = '1';
    } else if ("已查看".equals(status)) {
      statusArray[3] = '2';
    } else {
      return "param not match";
    }
    currStatus = String.valueOf(statusArray);
    int result = applicationRepository.updateApplicationStatus(id, currStatus);
    if (("不通过".equals(status)) && (result > 0)) {
      Subject currentUser = SecurityUtils.getSubject();
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      UUID recId = authUser.getOid();
      UUID optUid = authUser.getUid();
      String recName = authUser.getOnm();
      String optName = authUser.getDnm();

      Sender sender = new Sender();
      sender.setNickname(recName);
      sender.setUid(recId);
      sender.setUtype("COMPANY");
      To to = new To();
      to.setNickname(dto.getCandidateName());
      to.setUid(dto.getStuId());
      to.setUtype("STUDENT");
      Target target = new Target();
      target.setOid(dto.getJobId());
      target.setOtype("STUDENT");
      Notifications notifications = new Notifications();
      notifications.setSender(sender);
      notifications.setTo(to);
      notifications.setTarget(target);
      notifications.setAction("refuse");
      notifications.setType("remind");
      Map<String, String> map = new HashMap<String, String>();
      map.put("cs", "2");
      map.put("companyName", recName);
      map.put("jobName", dto.getJobName());
      notifications.setVars(map);
      HttpHeaders requestHeaders = HeaderUtil.getHeader(optUid, optName, "COMPANY", recId,
          recName);
      HttpEntity requestEntity = new HttpEntity(notifications, requestHeaders);
      try {
        logger.info("call interface to add notification");
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            NOTIFICATION_URL + "notifications", HttpMethod.POST, requestEntity,
            String.class);
      } catch (RestClientException e) {
        logger.error("call interface to add notification error", e);
        e.printStackTrace();
      }
    }
    if (("确定OFFER".equals(status)) && (result > 0)) {
      Subject currentUser = SecurityUtils.getSubject();
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      UUID recId = authUser.getOid();
      UUID optUid = authUser.getUid();
      String recName = authUser.getOnm();
      String optName = authUser.getDnm();

      Sender sender = new Sender();
      sender.setNickname(recName);
      sender.setUid(recId);
      sender.setUtype("COMPANY");
      To to = new To();
      to.setNickname(dto.getCandidateName());
      to.setUid(dto.getStuId());
      to.setUtype("STUDENT");
      Target target = new Target();
      target.setOid(dto.getJobId());
      target.setOtype("STUDENT");
      Notifications notifications = new Notifications();
      notifications.setSender(sender);
      notifications.setTo(to);
      notifications.setTarget(target);
      notifications.setAction("offer");
      notifications.setType("remind");
      Map<String, String> map = new HashMap<String, String>();
      map.put("s", "7");
      map.put("companyName", recName);
      map.put("jobName", dto.getJobName());
      notifications.setVars(map);
      HttpHeaders requestHeaders = HeaderUtil.getHeader(optUid, optName, "COMPANY", recId,
          recName);
      HttpEntity requestEntity = new HttpEntity(notifications, requestHeaders);
      try {
        logger.info("call interface to add notification");
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            NOTIFICATION_URL + "notifications", HttpMethod.POST, requestEntity,
            String.class);
      } catch (RestClientException e) {
        logger.error("call interface to add notification error", e);
        e.printStackTrace();
      }
    }
    if (result > 0) {
      return "ok";
    } else {
      return "failed";
    }
  }

  /**
   * @Description 更改阶段后初始化状态.
   * @Author wangyj
   * @CreateDate 2017/3/9 10:52
   * @Param
   * @Return
   */
  @Override
  public void updateAppStatusInit(UUID id, String s) {
    logger.info("init id:{} application status after update stage",id);
    int result = applicationRepository.updateApplicationStatus(id, s);
  }

  /**
   * @Description 获取申请表详情.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/16 17:11
   * @Param
   * @Return
   */
  @Override
  public ApplicationDTO getApplicaiton(UUID id) throws IOException {
    logger.info("get id:{} application",id);
    Applications applications = applicationRepository.findById(id);
    ApplicationDTO applicationDTO = new ApplicationDTO();
    applicationDTO.setId(applications.getId());
    applicationDTO.setRecId(applications.getRecId());
    applicationDTO.setRecName(applications.getRecName());
    applicationDTO.setStuId(applications.getStuId());
    //将数字映射为文字状态
    String dealStatus = "";
    String statusNum = applications.getStatus();
    char[] statsuChar = statusNum.toCharArray();
    if ('1' == statsuChar[0]) {
      dealStatus = "未处理";
    } else if ('2' == statsuChar[0]) {
      dealStatus = "待定";
    } else if ('3' == statsuChar[0]) {
      dealStatus = "不通过";
    } else if ('4' == statsuChar[0]) {
      dealStatus = "已淘汰";
    } else if ('5' == statsuChar[0]) {
      dealStatus = "确定OFFER";
    } else {
      dealStatus = "";
    }
    applicationDTO.setStatus(dealStatus);
    applicationDTO.setWork(applications.getWork());
    if (applications.getClub() != null) {
      applicationDTO.setClub(applications.getClub());
    }
    applicationDTO.setPractices(applications.getPractices());
    applicationDTO.setStage(applications.getStage());
    if (applications.getReward() != null) {
      applicationDTO.setReward(applications.getReward());
    }
    if (applications.getCertificate() != null) {
      applicationDTO.setCertificate(applications.getCertificate());
    }
    Basic basic = objectMapper.readValue(applications.getBasic(), Basic.class);
    applicationDTO.setBasic(basic);

    Education[] education = objectMapper.readValue(applications.getEducation(), Education[].class);
    for (Education edu : education) {
      if ("1".equals(edu.getDegree())) {
        edu.setDegree("专科");
      } else if ("2".equals(edu.getDegree())) {
        edu.setDegree("本科");
      } else if ("3".equals(edu.getDegree())) {
        edu.setDegree("硕士");
      } else if ("4".equals(edu.getDegree())) {
        edu.setDegree("博士");
      } else {
        edu.setDegree("");
      }
      if ("1".equals(edu.getRank())) {
        edu.setRank("前5%");
      } else if ("2".equals(edu.getRank())) {
        edu.setRank("前10%");
      } else if ("3".equals(edu.getRank())) {
        edu.setRank("前20%");
      } else if ("4".equals(edu.getRank())) {
        edu.setRank("前50%");
      }
    }
    applicationDTO.setEducation(education);

    if (applications.getSkills() != null) {
      Skills[] skills = objectMapper.readValue(applications.getSkills(), Skills[].class);
      applicationDTO.setSkills(skills);
    }

    JobTarget[] jobTarget = objectMapper.readValue(applications.getJobTarget(), JobTarget[].class);
    applicationDTO.setJobTarget(jobTarget);

    Language[] languages = objectMapper.readValue(applications.getLanguage(), Language[].class);
    applicationDTO.setLanguage(languages);

    if (applications.getResume() != null) {
      Resume resume = objectMapper.readValue(applications.getResume(), Resume.class);
      applicationDTO.setResume(resume);
    }

    applicationDTO.setTags(applications.getTags());

    if (applications.getMetaData() != null) {
      Object metaData = objectMapper.readValue(applications.getMetaData(), Object.class);
      applicationDTO.setMetaData(metaData);
    }
    applicationDTO.setApplyDate(applications.getApplyDate());
    return applicationDTO;
  }

  /**
   * @Description 获取申请表列表.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/21 10:48
   * @Param
   * @Return
   */
  @Override
  public ApplicationListDTO listApplication(ApplicationFilterDTO applicationFilterDTO) throws IOException {
    //获取过滤条件
    UUID recId = applicationFilterDTO.getRid();
    logger.info("recId:{} get application list");
    String position = applicationFilterDTO.getPst();
    String degree = applicationFilterDTO.getDgr();
    String school = applicationFilterDTO.getScl();
    String major = applicationFilterDTO.getMj();
    UUID stageId = applicationFilterDTO.getStg();
    String status = applicationFilterDTO.getSta();
    String labels = applicationFilterDTO.getLb();
    String gender = applicationFilterDTO.getGd();
    String department = applicationFilterDTO.getDp();
    String rank = applicationFilterDTO.getRank();
    String englishLevel = applicationFilterDTO.getEgL();
    String reward = applicationFilterDTO.getRd();
    String attachment = applicationFilterDTO.getAtt();
    String name = applicationFilterDTO.getNm();
    String mobile = applicationFilterDTO.getMb();
    String city = applicationFilterDTO.getCt();
    String birthFrom = applicationFilterDTO.getBrF();
    String birthTo = applicationFilterDTO.getBrT();
    String graduateFrom = applicationFilterDTO.getGdF();
    String graduateTo = applicationFilterDTO.getGdT();
    String deliveryFrom = applicationFilterDTO.getDlF();
    String deliveryTo = applicationFilterDTO.getDlT();
    Integer size = applicationFilterDTO.getSz();
    Integer number = (applicationFilterDTO.getNo() - 1) * size;
    String sort = applicationFilterDTO.getSt();
    //传入参数调用applicationRepository
    logger.info("dynamic query application list");
    List<Applications> applicationsList = applicationRepository.listApplication(recId, position,
        degree, school, major, stageId, status, labels, gender, department, rank, englishLevel, reward,
        attachment, name, mobile, city, birthFrom, birthTo, graduateFrom, graduateTo,
        deliveryFrom, deliveryTo, number, size, sort);
    logger.info("dynamic query application count");
    Integer total = applicationRepository.countApplicaiton(recId, position,
        degree, school, major, stageId, status, labels, gender, department, rank, englishLevel, reward,
        attachment, name, mobile, city, birthFrom, birthTo, graduateFrom, graduateTo,
        deliveryFrom, deliveryTo);
    List<ApplicationDTO> applicationDTOList = new ArrayList<>();
    //将Application转化为applicationDTO
    for (Applications applications : applicationsList) {
      ApplicationDTO applicationDTO = new ApplicationDTO();
      applicationDTO.setId(applications.getId());
      applicationDTO.setRecId(applications.getRecId());
      applicationDTO.setRecName(applications.getRecName());
      applicationDTO.setStuId(applications.getStuId());
      //将数字映射为文字状态
      String dealStatus = "";
      String statusNum = applications.getStatus();
      char[] statsuChar = statusNum.toCharArray();
      if ('1' == statsuChar[0]) {
        dealStatus = "未处理";
      } else if ('2' == statsuChar[0]) {
        dealStatus = "待定";
      } else if ('3' == statsuChar[0]) {
        dealStatus = "不通过";
      } else if ('4' == statsuChar[0]) {
        dealStatus = "已淘汰";
      } else if ('5' == statsuChar[0]) {
        dealStatus = "确定OFFER";
      } else {
        dealStatus = "";
      }
      applicationDTO.setStatus(dealStatus);
      applicationDTO.setWork(applications.getWork());
      if (applications.getClub() != null) {
        applicationDTO.setClub(applications.getClub());
      }
      applicationDTO.setPractices(applications.getPractices());
      if (applications.getReward() != null) {
        applicationDTO.setReward(applications.getReward());
      }
      if (applications.getCertificate() != null) {
        applicationDTO.setCertificate(applications.getCertificate());
      }
      applicationDTO.setStage(applications.getStage());
      Basic basic = objectMapper.readValue(applications.getBasic(), Basic.class);
      applicationDTO.setBasic(basic);

      Education[] education = objectMapper.readValue(applications.getEducation(), Education[].class);
      for (Education edu : education) {
        if ("1".equals(edu.getDegree())) {
          edu.setDegree("专科");
        } else if ("2".equals(edu.getDegree())) {
          edu.setDegree("本科");
        } else if ("3".equals(edu.getDegree())) {
          edu.setDegree("硕士");
        } else if ("4".equals(edu.getDegree())) {
          edu.setDegree("博士");
        } else {
          edu.setDegree("");
        }
      }
      applicationDTO.setEducation(education);

      if (applications.getSkills() != null) {
        Skills[] skills = objectMapper.readValue(applications.getSkills(), Skills[].class);
        applicationDTO.setSkills(skills);
      }

      JobTarget[] jobTarget = objectMapper.readValue(applications.getJobTarget(), JobTarget[].class);
      applicationDTO.setJobTarget(jobTarget);

      Language[] languages = objectMapper.readValue(applications.getLanguage(), Language[].class);
      applicationDTO.setLanguage(languages);

      if (applications.getResume() != null) {
        Resume resume = objectMapper.readValue(applications.getResume(), Resume.class);
        applicationDTO.setResume(resume);
      }

      applicationDTO.setTags(applications.getTags());

      if (applications.getMetaData() != null) {
        Object metaData = objectMapper.readValue(applications.getMetaData(), Object.class);
        applicationDTO.setMetaData(metaData);
      }

      applicationDTO.setApplyDate(applications.getApplyDate());
      applicationDTOList.add(applicationDTO);
    }
    ApplicationListDTO applicationListDTO = new ApplicationListDTO();
    applicationListDTO.setApplicationDTOList(applicationDTOList);
    applicationListDTO.setTotal(total);
    applicationListDTO.setPageNumber(number);
    applicationListDTO.setPageSize(size);
    return applicationListDTO;
  }

  /**
   * @Description 删除申请表.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/27 15:28
   * @Param
   * @Return
   */
  @Override
  public void deleteApplication(UUID id) {
    logger.info("delete id:{} application",id);
    applicationRepository.delete(id);
  }

  /**
   * @Description 根据职位id查投递该职位的简历数量.
   * @Author wangyj
   * @CreateDate 2017/3/6 11:13
   * @Param
   * @Return
   */
  @Override
  public Integer countByJobId(UUID recId, UUID jobId) {
    logger.info("get jobId:{} application count");
    Integer count = applicationRepository.countByJobId(recId, jobId);
    return null;
  }
}
