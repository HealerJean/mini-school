package com.minixiao.api.recruiter.entity.recruiters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description 公司基本信息实体类.
 * @Author xiachao
 * @CreateTime 2017/2/13 11:25
 */
@Entity
@Table(name = "tb_rec_info")
public class RecruiterInfo implements Serializable {
  private static final long serialVersionUID = -7094185281747582938L;
  //发布公司的唯一识别ID
  @Id
  @GenericGenerator(name = "recInfoIdGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "recInfoIdGenerator")
  private UUID id;

  //公司全称
  @Column(length = 100, nullable = false, unique = true)
  private String fullName;

  //公司简称
  @Column(length = 50)
  private String shortName;

  //公司一句话介绍
  @Column(length = 128)
  private String slogan;

  //公司描述
  @Column(columnDefinition = "text")
  private String description;

  //公司子域名
  @Column(length = 50)
  private String subdomain;

  //招聘企业定制页面链接
  @Column(length = 50)
  private String recHref;

  //公司性质
  @Column(length = 40)
  private String nature;

  //公司规模
  @Column(length = 30)
  private String size;

  //公司一级行业
  @Column(length = 100)
  private String primaryIndustry;

  //公司二级行业
  @Column(length = 100)
  private String slaveIndustry;

  //公司省市
  @Column(length = 100)
  private String city;

  //公司详细地址
  @Column
  private String detailAddress;

  //公司官方网站
  @Column(length = 50)
  private String website;

  //公司标签--以英文状态逗号切分
  @Column(length = 100)
  private String tags;

  //公司logo地址
  @Column(length = 200)
  private String logo;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruiterInfo", cascade = {CascadeType.ALL})
  List<RecruiterPicture> pictures = new ArrayList<RecruiterPicture>();

  //销售信息
  @Column
  private String salesInfo;

  //客户级别
  @Column(length = 100)
  private String scale;

  //公司开始时间 默认为创建时间 不可修改
  @Column(updatable = false)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate validityStart;


  //公司有效期 不可修改
  @Column
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate validityEnd;

  //公司创建时间
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime createdOn;

  // 公司修改时间
  @UpdateTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime updatedOn;

  //公司规模数据项
  public static enum Size {
    SIZE_A("1-49人"), SIZE_B("50-99人"), SIZE_C("100-499人"), SIZE_D("500-999人"),
    SIZE_E("1000-2000人"), SIZE_F("2000-5000人"), SIZE_G("5000-10000人");
    private String value;

    /**
     * .
     */
    private Size(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  //公司性质数据项
  public static enum Nature {
    NATURE_PRIVATE_ENTERPRISE("私营/民营企业"), NATURE_SINOFOREIGN_ENTERPRISE("中外合资企业"),
    NATURE_WFOE_ENTERPRISE("外商独资企业"), NATURE_QUOTED_COMPANY("上市公司"),
    NATURE_STATEOWNED_ENTERPRISE("国有企业"), NATURE_GOVERNMENT_ENTERPRISE("政府/事业单位"),
    NATURE_NONPROFIT_ORGANIZATION("非营利性组织");
    private String value;

    /**
     * .
     */
    private Nature(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(this.value);
    }
  }

  //公司行业数据项
  public static enum RecIndustry {
    INDUSTRY_HULIANWANG("互联网·游戏·软件", "移动互联网,电子商务,O2O,游戏,数据服务,社交网络,计算机软件"
        +
        ",门户/搜索,分类信息,IT服务"),
    INDUSTRY_DIANZI("电子·通信·硬件", "计算机硬件,通信设备,运营商/增值服务,电子/半导体"),
    INDUSTRY_JINGRONG("金融", "互联网金融,银行,保险,证券,基金,投资,会计/审计,担保/典当/拍卖"),
    INDUSTRY_SHENGCHAN("生产·机械·制造", "汽车/摩托车,机械/设备/重工,印刷/包装/造纸,办公用品,"
        +
        "加工/模具,家具/家电/玩具/礼品,服装/纺织/皮革"),
    INDUSTRY_FANGDICHAN("房地产·建筑·物业", "房产经纪/中介,物业管理/商业中心,规划设计,建筑工程,房地产开发"),
    INDUSTRY_FUWU("服务·咨询·外包", "酒店/旅游,餐饮,娱乐/休闲/体育,生活服务,企业咨询,外包服务,中介服务,"
        +
        "租赁服务,人力资源/财会,检测/认证,美容/保健"),
    INDUSTRY_ZHIYAO("制药·医疗", "制药/生物工程,医疗/护理/卫生,医疗设备/器械"),
    INDUSTRY_GUANGGAO("广告·媒体", "广告/公关,市场推广/会展,影视/媒体/艺术/文化传播,文字媒体/出版,"
        +
        "印刷/包装/造纸"),
    INDUSTRY_JIAOYU("教育·培训", "法律,教育/培训/院校,学术/科研"),
    INDUSTRY_WULIU("物流·运输", "交通/运输/物流,航天/航空"),
    INDUSTRY_MAOYI("贸易·消费", "贸易/进出口,批发/零售,快速消费品,奢侈品/收藏品/工艺品/珠宝"),
    INDUSTRY_NENGYUAN("能源·原材料", "石油/化工,矿产/地质,采掘业/冶炼,电气/电力/水利/新能源,原材料和加工"),
    INDUSTRY_ZHENGFU("政府·非赢利机构·其他", "政府/公共事业,非盈利机构,环保,农/林/牧/渔,多元化业务集团公司");

    //一级行业
    private String primary;
    //二级行业
    private String slave;

    /**
     * .
     */
    private RecIndustry(String primary, String slave) {
      this.primary = primary;
      this.slave = slave;
    }

    public String getPrimary() {
      return primary;
    }

    public String getSlave() {
      return slave;
    }

    public void setSlave(String slave) {
      this.slave = slave;
    }

    /**
     * .
     */
    @Override
    public String toString() {
      return this.primary + ":" + this.slave;
    }

  }

  /**
   * .
   */
  public RecruiterInfo() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getSlogan() {
    return slogan;
  }

  public void setSlogan(String slogan) {
    this.slogan = slogan;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSubdomain() {
    return subdomain;
  }

  public void setSubdomain(String subdomain) {
    this.subdomain = subdomain;
  }

  public String getRecHref() {
    return recHref;
  }

  public void setRecHref(String recHref) {
    this.recHref = recHref;
  }

  public String getNature() {
    return nature;
  }

  public void setNature(String nature) {
    this.nature = nature;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getPrimaryIndustry() {
    return primaryIndustry;
  }

  public void setPrimaryIndustry(String primaryIndustry) {
    this.primaryIndustry = primaryIndustry;
  }

  public String getSlaveIndustry() {
    return slaveIndustry;
  }

  public void setSlaveIndustry(String slaveIndustry) {
    this.slaveIndustry = slaveIndustry;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDetailAddress() {
    return detailAddress;
  }

  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public List<RecruiterPicture> getPictures() {
    return pictures;
  }

  public void setPictures(List<RecruiterPicture> pictures) {
    this.pictures = pictures;
  }

  public String getSalesInfo() {
    return salesInfo;
  }

  public void setSalesInfo(String salesInfo) {
    this.salesInfo = salesInfo;
  }

  public String getScale() {
    return scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }

  public LocalDate getValidityStart() {
    return validityStart;
  }

  public void setValidityStart(LocalDate validityStart) {
    this.validityStart = validityStart;
  }

  public LocalDate getValidityEnd() {
    return validityEnd;
  }

  public void setValidityEnd(LocalDate validityEnd) {
    this.validityEnd = validityEnd;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public LocalDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(LocalDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }
}
