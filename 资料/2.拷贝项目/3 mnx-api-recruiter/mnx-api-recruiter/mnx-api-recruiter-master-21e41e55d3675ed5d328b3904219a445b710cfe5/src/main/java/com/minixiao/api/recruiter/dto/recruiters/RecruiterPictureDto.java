package com.minixiao.api.recruiter.dto.recruiters;

/**
 * Created by xiachao on 2017/3/1.
 */
public class RecruiterPictureDto {

  //图片字节码
  private byte[] fileByte;

  //图片后缀
  private String fileSuffix;
  //图片类型
  private String fileType;

  public RecruiterPictureDto() {
  }

  public byte[] getFileByte() {
    return fileByte;
  }

  public void setFileByte(byte[] fileByte) {
    this.fileByte = fileByte;
  }

  public String getFileSuffix() {
    return fileSuffix;
  }

  public void setFileSuffix(String fileSuffix) {
    this.fileSuffix = fileSuffix;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }
}
