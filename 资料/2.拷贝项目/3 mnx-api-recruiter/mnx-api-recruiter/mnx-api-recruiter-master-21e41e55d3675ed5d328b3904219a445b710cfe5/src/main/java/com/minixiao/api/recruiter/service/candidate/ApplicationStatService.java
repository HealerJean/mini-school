package com.minixiao.api.recruiter.service.candidate;

import com.minixiao.api.recruiter.dto.candidate.ApplicationStatDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/3/3  17:24.
 */
public interface ApplicationStatService {
  ApplicationStatDTO applicationStat(UUID recId);
}
