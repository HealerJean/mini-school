package com.minixiao.api.recruiter.service.candidate;

import com.minixiao.api.recruiter.dto.candidate.TagsDTO;

import java.util.UUID;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/22  12:07.
 */
public interface TagsService {

  void deleteTag( UUID id);

  String saveTages(TagsDTO tagsDTO);
}
