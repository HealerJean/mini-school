
package com.minixiao.api.recruiter.repository.candidate;

import com.minixiao.api.recruiter.entity.candidate.HandleHistory;
import com.minixiao.api.recruiter.entity.candidate.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface HandleHistoryRepository extends CrudRepository<HandleHistory, UUID> {

  /**
   * 查询处理历史列表.
   *
   * @param recId    公司Id
   * @param optUname 操作人名称
   * @param optType  操作类型
   * @param optDate  操作时间
   * @param pageable 分页参数
   * @return Page
   */
  @Query(value = "from HandleHistory h where h.recId=?1 and (optUname=?2 or ?2=null) "
      +
      "and ( optType=?3 or ?3=null) and ( optDate=?4) ")
  public Page<HandleHistory> findList(UUID recId, String optUname, String optType,
                                      LocalDate optDate, Pageable pageable);

  /**
   * 查询处理历史列表.
   *
   * @param recId    公司Id
   * @param optUname 操作人名称
   * @param optType  操作类型
   * @param pageable 分页参数
   * @return Page
   */
  @Query(value = "from HandleHistory h where h.recId=?1 and (optUname=?2 or ?2=null) "
      +
      "and ( optType=?3 or ?3=null)")
  public Page<HandleHistory> findListNotOptDate(UUID recId, String optUname, String optType,
                                                Pageable pageable);

  /**
   * 查询处理历史列表.
   *
   * @param recId    公司Id
   * @param optUname 操作人名称
   * @param optType  操作类型
   * @param optDate  操作时间
   * @param pageable 分页参数
   * @return Page
   */
  public Page<HandleHistory> findByRecIdAndOptUnameAndOptTypeAndOptDate(
      UUID recId, String optUname, String optType, LocalDate optDate, Pageable pageable);

  /**
   *
   * @param recId 公司Id
   * @return List
   */
  public Page<HandleHistory> findByRecId(UUID recId, Pageable page);

  /**
   * 查询任务安排列表.
   * @param optUid 操作人Id
   * @return Page
   */
  public List<HandleHistory> findByOptUid(UUID optUid);


  /**
   * 查询
   * @param applicationId 操作人Id
   * @return Page
   */
  public Page<HandleHistory> findByApplicationId(UUID applicationId, Pageable page);
}
