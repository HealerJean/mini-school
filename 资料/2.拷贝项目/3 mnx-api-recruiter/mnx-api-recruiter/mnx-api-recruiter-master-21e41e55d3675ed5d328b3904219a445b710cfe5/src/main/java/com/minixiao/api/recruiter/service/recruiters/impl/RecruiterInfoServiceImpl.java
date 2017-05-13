package com.minixiao.api.recruiter.service.recruiters.impl;

import com.minixiao.api.recruiter.dto.recruiters.RecruiterPictureDto;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.recruiters.RecruiterInfoRepository;
import com.minixiao.api.recruiter.service.recruiters.RecruiterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/14 17:42
 */
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "recruiterInfos")
public class RecruiterInfoServiceImpl implements RecruiterInfoService {
  @Autowired
  private RecruiterInfoRepository recruiterInfoRepository;

  @Value("${logo_path}")
  private String logoPath;

  @Override
  @Transactional(readOnly = false)
  @CachePut(value = "recruiterInfo")
  public void saveRecruiterInfo(RecruiterInfo recruiterInfo) {
    recruiterInfoRepository.save(recruiterInfo);
  }

  @Override
  @Cacheable
  public RecruiterInfo findById(UUID id) {
    RecruiterInfo recruiterInfo = recruiterInfoRepository.findById(id);
    recruiterInfo.setPictures(recruiterInfo.getPictures());
    return recruiterInfo;
  }

  @Override
  @Transactional(readOnly = false)
  @CacheEvict(value = "recruiterInfo")
  public void deleteRecruiter(UUID id) {
    recruiterInfoRepository.delete(id);
  }

  @Override
  public Page<RecruiterInfo> findAll(Pageable pageable) {
    return recruiterInfoRepository.findAll(pageable);
  }

  /**
   * 上传公司logo .
   *
   * @param recId      recId
   * @param pictureDto pictureDto
   */
  @Override
  public String uploadLogo(UUID recId, RecruiterPictureDto pictureDto) {
    RecruiterInfo recruiterInfo = findById(recId);
    //判断是否已上传logo 如果有 则删除之
    if (recruiterInfo.getLogo() != null) {
      String oldLogoPath = recruiterInfo.getLogo();
      if (oldLogoPath != null && oldLogoPath != "") {
        File file1 = new File(logoPath + oldLogoPath.substring(oldLogoPath.
            lastIndexOf("/")));
        if (file1.exists()) {
          file1.delete();
        }
      }
    }
    //图片字节数组
    byte[] fileByte = pictureDto.getFileByte();
    String filename = UUID.randomUUID().toString() + pictureDto.getFileSuffix();
    File fileDir = new File(logoPath);
    if (!fileDir.exists()) {
      fileDir.mkdirs();
    }
    try {
      File file = new File(fileDir + File.separator + filename);
      FileImageOutputStream imageOutputStream = new FileImageOutputStream(file);
      //写入文件
      imageOutputStream.write(fileByte, 0, fileByte.length);
      imageOutputStream.close();
      //这里暂时写死
      //String logoUrl = "http://www.minixiao.com/st/images/"+fileDir + File.separator + filename;
      String logoUrl = "http://corp.minixiao.com/st/images/corpLogo/201702/236d113675fdf94ac7"
          +
          "9ebb2096f885d363_1486458924935.png";
      recruiterInfo.setLogo(logoUrl);
      saveRecruiterInfo(recruiterInfo);
      return logoUrl;
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
