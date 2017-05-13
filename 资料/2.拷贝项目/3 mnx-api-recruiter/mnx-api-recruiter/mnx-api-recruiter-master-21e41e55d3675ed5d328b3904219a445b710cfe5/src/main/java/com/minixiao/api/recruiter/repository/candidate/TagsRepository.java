package com.minixiao.api.recruiter.repository.candidate;

import com.minixiao.api.recruiter.entity.candidate.Tags;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/22  12:06.
 */
public interface TagsRepository extends CrudRepository<Tags,UUID>{

  @Query(value = "from Tags t where t.optId =?1 and t.applicationId =?2")
  List<Tags> countByOptIdAndAppId(UUID optId, UUID applicationId);

}
