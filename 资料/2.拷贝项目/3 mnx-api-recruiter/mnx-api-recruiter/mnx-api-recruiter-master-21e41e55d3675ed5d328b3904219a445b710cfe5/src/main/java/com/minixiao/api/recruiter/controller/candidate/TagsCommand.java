package com.minixiao.api.recruiter.controller.candidate;

import com.minixiao.api.recruiter.dto.candidate.TagsDTO;
import com.minixiao.api.recruiter.service.candidate.HandleHistoryService;
import com.minixiao.api.recruiter.service.candidate.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/2/22  12:49.
 */
@Controller
public class TagsCommand {
  @Autowired
  private TagsService tagsService;

  @Autowired
  private HandleHistoryService handleHistoryService;

  /**
   * @Description 给申请表添加标签.
   * @Author wangyj
   * @CreateDate 2017/2/22 12:54
   * @Param
   * @Return
   */
  @PostMapping("candidate/tags")
  public ResponseEntity<String> saveTags(@RequestBody TagsDTO tagsDTO) {
    String result = tagsService.saveTages(tagsDTO);
    if ("NO".equals(result)) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .cacheControl(CacheControl.noCache())
          .body("onlythree");
    } else if ("REPEAT".equals(result)) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .cacheControl(CacheControl.noCache())
          .body("repeat");

    } else {
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .cacheControl(CacheControl.noCache())
          .body("ok");
    }

  }

  /**
   * @Description 删除标签.
   * @Author wangyj
   * @CreateDate 2017/3/1 9:21
   * @Param
   * @Return
   */
  @DeleteMapping("candidate/tags/{id}")
  public ResponseEntity<String> deleteTag(@PathVariable UUID id) {
    tagsService.deleteTag(id);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .cacheControl(CacheControl.noCache())
        .body("ok");
  }
}
