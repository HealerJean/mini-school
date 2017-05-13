package com.minixiao.api.recruiter.service.candidate.impl;

import com.minixiao.api.recruiter.dto.candidate.AppTagListDTO;
import com.minixiao.api.recruiter.dto.candidate.TagsDTO;
import com.minixiao.api.recruiter.entity.candidate.HandleHistory;
import com.minixiao.api.recruiter.entity.candidate.Tags;
import com.minixiao.api.recruiter.repository.candidate.TagsRepository;
import com.minixiao.api.recruiter.service.candidate.HandleHistoryService;
import com.minixiao.api.recruiter.service.candidate.TagsService;
import mnx.infra.apiauth.server.domain.AuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/22  12:07.
 */
@Service
@Transactional
public class TagsServiceImpl implements TagsService {

  @Autowired
  private TagsRepository tagsRepository;

  @Autowired
  private HandleHistoryService handleHistoryService;

  private Logger logger = LoggerFactory.getLogger(TagsServiceImpl.class);

  /**
   * @Description 删除标签.
   * @Author  wangyj
   * @CreateDate 2017/3/1 9:34
   * @Param
   * @Return
   */
  @Override
  public void deleteTag( UUID id) {
    logger.info("delete {} tag",id);
    tagsRepository.delete(id);

  }

  /**
   * @Description 添加标签.
   * @Author  wangyj
   * @CreateDate 2017/3/1 9:33
   * @Param
   * @Return
   */
  @Override
  public String saveTages(TagsDTO tagsDTO) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      List<AppTagListDTO> appTagList = tagsDTO.getAppTagList();
      UUID optId = authUser.getUid();
      String optName = authUser.getDnm();
      UUID recId = authUser.getOid();
      String recName = authUser.getOnm();
      String tag = tagsDTO.getTags();
      String stage = tagsDTO.getStage();
      //判断将要打标签的候选人中是否有不符合条件的：同一个HR对一个申请表打标签不能超过三个；不能重复打标签
      for (AppTagListDTO appTagListDTO : appTagList) {
        List<Tags> tagsList = tagsRepository.countByOptIdAndAppId(optId, appTagListDTO.getId());
        if (tagsList.size() >= 3) {
          return "NO";
        } else {
          for (Tags tagl : tagsList) {
            if (tagl.getTag().equals(tagsDTO.getTags())) {
              return "REPEAT";
            }
          }
        }
      }

      for (AppTagListDTO appTagListDTO : appTagList) {
        Tags tags = new Tags();
        UUID applicationId =appTagListDTO.getId();
        tags.setApplicationId(applicationId);
        tags.setOptId(optId);
        tags.setOptName(optName);
        tags.setTag(tag);
        //符合打标签规则，保存标签
        logger.info("add application:{} tag",applicationId);
        tagsRepository.save(tags);
        //添加操作历史
        HandleHistory handleHistory = new HandleHistory();
        handleHistory.setApplicationId(appTagListDTO.getId());
        handleHistory.setJobId(appTagListDTO.getJobId());
        handleHistory.setJobName(appTagListDTO.getJobName());
        handleHistory.setCandidateName(appTagListDTO.getCandidateName());
        handleHistory.setStatus(stage);
        handleHistory.setOptUid(optId);
        handleHistory.setOptUname(optName);
        handleHistory.setOptType(stage);
        handleHistory.setRecId(recId);
        handleHistory.setRecName(recName);
        handleHistory.setOptType("打标签");
        handleHistory.setRemark(tag);
        handleHistoryService.saveHandleHistory(handleHistory);
      }
      return "YES";
    }
    return "NO";
  }
}
