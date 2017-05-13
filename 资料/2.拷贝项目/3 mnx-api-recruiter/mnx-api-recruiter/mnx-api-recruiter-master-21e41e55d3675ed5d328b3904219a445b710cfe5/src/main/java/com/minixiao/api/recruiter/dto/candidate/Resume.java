package com.minixiao.api.recruiter.dto.candidate;

/**
 * Created by WangYingjie on 2017/2/13.
 */
public class Resume {
  private String id;

  private String fileURL;

  private String fileName;

  private String contentType;

  private long contentSize;

  /**
   * 空构造函数.
   */
  public Resume() {
  }

  /**
   * 默认构造函数.
   */
  public Resume(String id, String fileURL, String fileName, String contentType, long contentSize) {
    this.id = id;
    this.fileURL = fileURL;
    this.fileName = fileName;
    this.contentType = contentType;
    this.contentSize = contentSize;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFileURL() {
    return fileURL;
  }

  public void setFileURL(String fileURL) {
    this.fileURL = fileURL;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public long getContentSize() {
    return contentSize;
  }

  public void setContentSize(long contentSize) {
    this.contentSize = contentSize;
  }
}
