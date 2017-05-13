package com.minixiao.dao;

import com.minixiao.domain.Address;
import com.minixiao.domain.Industry;
import com.minixiao.domain.Recruiter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotSame;

/**
 * RecruiterDaoTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecruiterDaoTest {
  @Resource
  RecruiterDao recruiterDao;

  @Test
  public void save() throws Exception {
    Recruiter recruiter = new Recruiter();
    recruiter.setFullName("天津众维计算机培训学校");
    recruiter.setShortName("天津众维");
    recruiter.setSlogan("天津最资深的高端设计培训机构");
    recruiter.setDescription("众维教育是天津最资深的高端设计培训机构，国内首家营销型设计师培训"
        +
        "基地、天津大学生实训基地、天津大学生创业孵化基地。人社部动漫人才测评中心，美国ADOBE"
        +
        "创意大学培训基地。学校在和平、河北、津南拥有三个校区，教学及管理人员80余人。学校主要"
        +
        "开展大学生创意设计培训、设计类岗前实训、大学生创业孵化等创业、就业保障项目。");
    recruiter.setNature("民营企业");
    recruiter.setSize("100-499人");
    recruiter.setIndustry(new Industry("互联网·游戏·软件", "移动互联网"));
    recruiter.setAddress(new Address("北京", "海逸创业园4号楼2-132"));
    recruiter.setWebsite("http://www.microsoft.com/");
    recruiter.setTags("股票期权,绩效奖金,五险一金");
    recruiter.setLogo("http://www.minixiao"
        +
        ".com/st/images/corpLogo/201612"
        +
        "/23270190d9e17648648cc197fd315724c3_1481268385532.png");
    recruiter.setSource("迷你校");
    Recruiter savedRecruiter = recruiterDao.save(recruiter);
    //保存后id不会是默认的0
    assertNotSame(recruiter.getId(), savedRecruiter.getId());
  }
}