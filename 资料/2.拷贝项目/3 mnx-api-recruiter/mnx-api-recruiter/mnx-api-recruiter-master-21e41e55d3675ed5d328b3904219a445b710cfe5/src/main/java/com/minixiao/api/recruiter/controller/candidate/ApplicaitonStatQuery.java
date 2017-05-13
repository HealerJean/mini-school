package com.minixiao.api.recruiter.controller.candidate;

import com.minixiao.api.recruiter.dto.candidate.ApplicationStatDTO;
import com.minixiao.api.recruiter.service.candidate.ApplicationStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/3/4  12:17.
 */
@RestController
public class ApplicaitonStatQuery {

  @Autowired
  private ApplicationStatService applicationStatService;

  @GetMapping("/candidate/{recId}/stat")
  public ResponseEntity<ApplicationStatDTO > getRecStat(@PathVariable UUID recId){
    ApplicationStatDTO applicationStatDTO = applicationStatService.applicationStat(recId);
    return ResponseEntity
        .status(HttpStatus.OK)
        .cacheControl(CacheControl.noCache())
        .body(applicationStatDTO);
  }
}
