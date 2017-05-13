

## 接口定义

### 创建新申请表
#### HTTP 请求
**POST**   /candidate

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明       |
|:----|:-------:|:--------:|:----:|:--------------|
| application | body | json | true | 申请表的内容。 |

#### 权限
- role=stu* 学生用户可以创建申请表。
- role=* 其他用户不能创建申请表。
#### requestBody示例
```JSON
{
        "stuId":"354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
        "recId":"354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
        "recName":"今日头条",
        "jobTarget":[{"id":"354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
                      "job_name":"Java工程师",
                      "area":"上海",
                      "department":"北京分公司",
                      "job_category":"技术开发类",
                      "is_current":1,
                      "current_status":"init",
                      "score":20.0
        }],
        "resume":{ "resumeId":"354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
                    "fileURL":"www.bai.com",
                    "fileName":"我的技术类简历.doc",
                    "contentType":"application/ms-word",
                    "contentSize":"123456"
        },
        "basic":{
            "gender": "男",
            "city": "内蒙古-阿拉善盟",
            "name": "李白",
            "email": "1126971653@qq.com",
            "mobile": "13520115645",
            "birthday": "2016-12-14",
            "province": "澳门",
            "political": "共青团员"
        },
        "education":[{
            "rank": "2",
            "major": "计算机系统结构",
            "degree": "3",
            "school": "北京工业大学",
            "end_date": "2002-11",
            "is_highest": true,
            "start_date": "2016-10"
        }],
        "language":[{
            "type": "英语",
            "level": "六级"
        }],
        "skills":[{
        	"type":"office办公软件",
        	"level":"熟练使用"
        }],
        "work":"我曾在迷你校实习",
        "club":"大一参加了计算机协会",
        "reward":"演讲比赛第一名",
        "certificate":"国家一级运动员证书",
        "practices":"我参与迷你校Sass项目重建的工作",
        "metaData":{"test":123},
        "stage":"init",
        "status":"54321"
}
```


#### 返回值：
```JSON
{ "result":"ok"}
```

### 获取申请表列表
#### HTTP 请求
**GET**  /candidate

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明    |
|:----|:-------:|:--------:|:----:|:--------------|
| filter | body | String | false |过滤条件，包括：职位、学校、学历... |


filter的格式：
```JSON
{
    "rid": "1bf972f1-a6b3-4f9b-8577-56f5820ade19",
    "pst": "高级算法工程师",
    "dgr": "2",
    "scl": "郑轻",
    "mj": "计算机视觉神经",
    "sta": "待筛选",
    "lb": "尖子标签示例,录用标签示例,我就是一个",
    "gd": "男",
    "dp": "研发部",
    "rank": "2",
    "egL": "英语六级",
    "rd": "",
    "att": "",
    "nm": "张三",
    "mb": "13090909900",
    "ct": "北京",
    "brF": "2003-11",
    "brT": "2003-11",
    "gdF": "2003-11",
    "gdT": "2003-11",
    "dlF": 1480736944000,
    "dlT": 1491936944000,
    "no": 0,
    "sz": 20,
    "st": "applicate_date DESC, OrderNumber ASC"
}    
```

备注：
- 没有当前字段的筛选条件时，对应的值应为空字符串（""）
- dlF/dlT两个字段为空时需要传入null
- 排序参数"st" 的格式：applicate_date DESC, ... ASC

为缩短GET请求的URL的长度,对变量名进行精简，各字段意义如下：
```Java
 //企业Id
  private UUID rid;

  //职位名称
  private String pst;

  //最高学历
  private String dgr;

  //毕业学校(英文状态逗号切分)
  private String scl;

  //专业名称(英文状态逗号切分)
  private String mj;

  //处理状态
  private String sta;

  //简历标签(英文状态逗号切分)
  private String lb;

  //性别
  private String gd;

  //聘用部门
  private String dp;

  //专业排名
  private String rank;

  //英语等级(英文状态逗号切分)
  private String egL;

  //获奖荣誉
  private String rd;

  //附件简历
  private String att;

  //候选人姓名
  private String nm;

  //手机号码
  private String mb;

  //家庭所在地(英文状态逗号切分)
  private String ct;

  //出生年份大于
  private String brF;

  //出生年份小于
  private String brT;

  //毕业年份大于
  private String gdF;

  //毕业年份小于
  private String gdT;

  //投递时间大于
  private Date dlF;

  //投递时间小于
  private Date dlT;

  //分页参数 页码
  private Integer no;

  //分页参数 每页数量
  private Integer sz;

  //排序参数
  private String st;
```
返回数据格式：

```JSON
{
  "total": 5,
  "pageNumber": 0,
  "pageSize": 15,
  "applicationDTOList": [
    {
      "id": "3e254929-e374-45fb-bc65-501e5b6ecdda",
      "stuId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "recId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "recName": "今日头条",
      "jobTarget": [
        {
          "id": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
          "stuId": null,
          "area": "上海",
          "department": "北京分公司",
          "score": 20,
          "job_name": "Java工程师",
          "job_category": "技术开发类",
          "is_current": true,
          "current_status": "init"
        }
      ],
      "basic": {
        "name": "李4",
        "gender": "男",
        "email": "1126971653@qq.com",
        "mobile": "13520115645",
        "birthday": "1992-12-14",
        "province": "澳门",
        "city": "内蒙古-阿拉善盟",
        "political": "共青团员"
      },
      "education": [
        {
          "rank": "2",
          "major": "计算机系统结构",
          "degree": "4",
          "school": "北京工业大学",
          "end_date": "2002-11",
          "is_highest": true,
          "start_date": "2000-10"
        }
      ],
      "work": "我曾在迷你校实习",
      "practices": "我参与项目重建的工作",
      "resume": {
        "id": null,
        "fileURL": "www.bai.com",
        "fileName": "我的技术类简历.doc",
        "contentType": "application/ms-word",
        "contentSize": 123456
      },
      "club": "大一参加了计算机协会",
      "reward": "演讲比赛第一名",
      "certificate": "国家一级运动员证书",
      "metaData": {
        "test": 123
      },
      "language": [
        {
          "type": "英语",
          "level": "六级"
        }
      ],
      "skills": [
        {
          "type": "office办公软件",
          "level": "熟练使用"
        }
      ],
      "stage": "init",
      "status": "54321",
      "tags": [
        {
          "id": "b085d951-d2a1-4bf7-88ed-0591c6fe3011",
          "applicationId": "3e254929-e374-45fb-bc65-501e5b6ecdda",
          "tag": "优秀",
          "optId": "693f8256-082b-46c9-b9e4-f24aad84b911",
          "optName": "王迎接",
          "creatDate": "20170224054800"
        }
      ],
      "applyDate": "20170225023835"
    },...
   }
```
#### 权限
- role=rec* 企业用户可以获取本公司的申请表列表。
- role=stu* 学生用户可以获取自己的申请表列表。
- role=* 其他用户不能获取任何消息通知。

### 获取申请表详情
#### HTTP 请求
**GET**  /candidate/{id}

| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明       |
|:----|:-------:|:--------:|:----:|:--------------|
| id | URLparams | String | true | 申请表id |
| roleType | params | String | true | 当前用户身份类型，学生还是hr|

返回数据格式：

```JSON
{
  "id": "7c2064ce-62f9-440d-bc29-544de5a8222f",
  "stuId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
  "recId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
  "recName": "今日头条",
  "jobTarget": [
    {
      "id": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "stuId": null,
      "area": "上海",
      "department": "北京分公司",
      "score": 20,
      "job_name": "Java工程师",
      "job_category": "技术开发类",
      "is_current": true,
      "current_status": "init"
    }
  ],
  "basic": {
    "name": "李白",
    "gender": "男",
    "email": "1126971653@qq.com",
    "mobile": "13520115645",
    "birthday": "1992-12-14",
    "province": "澳门",
    "city": "内蒙古-阿拉善盟",
    "political": "共青团员"
  },
  "education": [
    {
      "rank": "2",
      "major": "计算机系统结构",
      "degree": "3",
      "school": "北京工业大学",
      "end_date": "2002-11",
      "is_highest": "true",
      "start_date": "2000-10"
    }
  ],
  "work": "我曾在迷你校实习",
  "practices": "我参与项目重建的工作",
  "resume": {
    "id": null,
    "fileURL": "www.bai.com",
    "fileName": "我的技术类简历.doc",
    "contentType": "application/ms-word",
    "contentSize": 123456
  },
  "club": "大一参加了计算机协会",
  "reward": null,
  "certificate": null,
  "metaData": {
    "test": 123
  },
  "language": null,
  "skills": [
    {
      "type": "office办公软件",
      "level": "熟练使用"
    }
  ],
  "stage": "init",
  "status": "54321",
  "tags": [
    {
      "id": "b085d951-d2a1-4bf7-88ed-0591c6fe3011",
      "applicationId": "7c2064ce-62f9-440d-bc29-544de5a8222f",
      "tag": "优秀",
      "optId": "693f8256-082b-46c9-b9e4-f24aad84b911",
      "optName": "王迎接",
      "creatDate": "20170224054800"
    }
  ],
  "applyDate": "20170228023836"
}
```
#### 权限
- role=rec* 企业用户可以获取申请表详情。
- role=stu* 学生用户可以获取自己的申请表详情。
- role=* 其他用户不可以获取申请表详情。


### 更改申请表状态
#### HTTP 请求
**PUT** /candidate/{id}/status

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明       |
|:----|:-------:|:--------:|:----:|:--------------|
| id | URLparams | String | true | 申请表id |
| status | params | bool | false | 将申请表置为该状态 |

返回数据格式：

```JSON
{
    "result": "ok"
}
```

#### 权限
- role=rec* 企业用户可以更改申请表状态。
- role=* 其他用户不能更改申请表状态。

### 修改申请表
#### HTTP 请求
**PUT** /candidate/{id}

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明       |
|:----|:-------:|:--------:|:----:|:--------------|
| id | URLparams | String | true | 申请表id |
| application | body | json | true | 申请表的内容。 |

#### 权限
- role=stu* 学生用户可以在申请表没有看过的前提下修改申请表。
- role=* 其他用户不能修改申请表。
#### requestBody示例
```JSON
{
  "id": "7c2064ce-62f9-440d-bc29-544de5a8222f",
  "stuId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
  "recId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
  "recName": "今日头条",
  "jobTarget": [
    {
      "id": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "stuId": null,
      "area": "上海",
      "department": "北京分公司",
      "score": 20,
      "job_name": "Java工程师",
      "job_category": "技术开发类",
      "is_current": true,
      "current_status": "init"
    }
  ],
  "basic": {
    "name": "李白",
    "gender": "男",
    "email": "1126971653@qq.com",
    "mobile": "13520115645",
    "birthday": "1992-12-14",
    "province": "澳门",
    "city": "内蒙古-阿拉善盟",
    "political": "共青团员"
  },
  "education": [
    {
      "rank": "2",
      "major": "计算机系统结构",
      "degree": "3",
      "school": "北京工业大学",
      "end_date": "2002-11",
      "is_highest": "true",
      "start_date": "2000-10"
    }
  ],
  "work": "我曾在迷你校实习",
  "practices": "我参与项目重建的工作",
  "resume": {
    "id": null,
    "fileURL": "www.bai.com",
    "fileName": "我的技术类简历.doc",
    "contentType": "application/ms-word",
    "contentSize": 123456
  },
  "club": "大一参加了计算机协会",
  "reward": null,
  "certificate": null,
  "metaData": {
    "test": 123
  },
  "language": null,
  "skills": [
    {
      "type": "office办公软件",
      "level": "熟练使用"
    }
  ],
  "stage": "init",
  "status": "54321",
  "applyDate": "20170228023836"
}
```

### 删除申请表
#### HTTP 请求
**DELETE** /candidate/{id}

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明       |
|:----|:-------:|:--------:|:----:|:--------------|
| id | URLparams | String | true | 申请表id |

#### 权限
- role=stu* 学生用户可以删除自己的申请表
- role=admin* 管理员用户可以删除自己的申请表
- role=* 其他用户没有权限

返回数据格式：

```JSON
{
    "result": "ok"
}
```





