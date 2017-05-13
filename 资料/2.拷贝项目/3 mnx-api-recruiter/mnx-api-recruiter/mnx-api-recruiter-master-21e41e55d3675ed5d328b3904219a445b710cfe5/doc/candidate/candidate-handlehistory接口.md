

## 接口定义

### 获取候选人处理历史
#### HTTP 请求
**GET** /candidate/history
#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明       |
|:----|:-------:|:--------:|:----:|:--------------|
| optUname | params | String | false | 操作人 |
| optType | params | String | false | 操作类型 |
| optDate | params | String | false | 操作时间 |
| sort | params | String | false | 列表根据哪些属性进行排序，以及是正序（ASC）还是倒序（DESC）排列。默认是按照什么排序？可以使用多个 sort 参数来定义根据多个属性值进行排序。例如： `?sort=firstname&sort=lastname,asc`. |
| page | params | uint | false | 当前页数，从1开始的索引值。默认值为1。 |
| size | params | uint | false | 每页显示数量。默认值为20。 |

返回数据格式：

```JSON
{
  "content": [
    {
      "id": "354bdb8f-ecd6-4c82-80fe-5f42db62d5a1",
      "candidateName": "王诗语",
      "recId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "recName": "迷你校",
      "jobId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "jobName": "java开发",
      "optUid": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "optUname": "小语",
      "optType": "安排笔试",
      "description": "“周璐”在“面试”阶段进行“安排面试”操作",
      "status": "面试",
      "optDate": "20170216"
    },
    {
      "id": "354bdb8f-ecd6-4c82-80fe-5f42db62d6a1",
      "candidateName": "王诗语",
      "recId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "recName": "迷你校",
      "jobId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "jobName": "java开发",
      "optUid": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "optUname": "小语",
      "optType": "安排笔试",
      "description": "“周璐”在“面试”阶段进行“安排面试”操作",
      "status": "面试",
      "optDate": "20170216"
    }
  ],
  "last": true,
  "totalElements": 3,
  "totalPages": 1,
  "number": 0,
  "size": 20,
  "sort": null,
  "numberOfElements": 3,
  "first": true
}
    
```
#### 权限
- role=rec* 企业用户可以获取到所有的处理历史。
- role=* 其他用户不能获取任何记录。


### 创建候选人任务安排
#### HTTP 请求
**POST** /candidate/schedule

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明       |
|:----|:-------:|:--------:|:----:|:--------------|
| scheduleDTO | body | json | true | 候选人任务安排的内容。 |

#### 权限
- role=rec* 企业用户可以创建候选人任务安排
#### requestBody示例
```JSON
{
   "candidateInfo":[{
        "candidateName": "王诗语",
        "candidateId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1"
    }],
    "recId":"354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
    "recName":"mnx",
    "examManagerId":"354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
    "jobId":"354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
    "jobName":"java开发",
    "location":"北大",
    "city":"北京",
    "duration":"120",
    "accommodateNo":"50",
    "interviewer":"王迎接",
    "type":"面试"
}
```



### 获取候选人任务安排
#### HTTP 请求
**GET** /candidate/schedule

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明       |
|:----|:-------:|:--------:|:----:|:--------------|
| sort | params | String | false | 列表根据哪些属性进行排序，以及是正序（ASC）还是倒序（DESC）排列。默认是按照什么排序？可以使用多个 sort 参数来定义根据多个属性值进行排序。例如： `?sort=firstname&sort=lastname,asc`. |
| page | params | uint | false | 当前页数，从1开始的索引值。默认值为1。 |
| size | params | uint | false | 每页显示数量。默认值为20。 |

返回数据格式：

```JSON
{
  "content": [
    {
      "id": "7268ba57-7523-48d6-9af9-78bfc8d9cf01",
      "candidateInfo": "[{\"candidateId\": \"354bdb8f-ecd6-4c82-80fe-5f42db62d0a1\", \"candidateName\": \"王诗语\"}]",
      "examManagerId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "recId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "recName": "mnx",
      "jobId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "jobName": "java开发",
      "location": "北大",
      "city": "北京",
      "startTime": "20170216",
      "duration": "120",
      "accommodateNo": "50",
      "interviewer": "王迎接",
      "type": "面试"
    },
    {
      "id": "812ec4e5-cb46-4357-bbaa-e6bd1061d4f5",
      "candidateInfo": "[{\"candidateId\": \"354bdb8f-ecd6-4c82-80fe-5f42db62d0a1\", \"candidateName\": \"王诗语\"}]",
      "examManagerId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "recId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "recName": "mnx",
      "jobId": "354bdb8f-ecd6-4c82-80fe-5f42db62d0a1",
      "jobName": "java开发",
      "location": "北大",
      "city": "北京",
      "startTime": "20170216",
      "duration": "120",
      "accommodateNo": "50",
      "interviewer": "王迎接",
      "type": "面试"
    }
  ],
  "last": false,
  "totalElements": 10,
  "totalPages": 5,
  "number": 0,
  "size": 2,
  "sort": null,
  "first": true,
  "numberOfElements": 2
}
    
```
#### 权限
- role=rec* 企业用户可以获取到所有的候选人任务安排。
- role=* 其他用户不能获取记录。
- 
### 新增统计（定时任务，不需要暴露接口）

#### 权限
- role=sys* 系统会定时统计
#### 统计结果示例
```JSON
{
    "id":"123456",
    "applyCount":123,
    "perJobCount":123,
    "perRecCount":123,
   "statDate": "20170216"
}
```



### 获取某一天的统计数据
#### HTTP 请求
**GET**  /candidate/statistics

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明    |
|:----|:-------:|:--------:|:----:|:--------------|
| statDate | params | String | true |指定某一天的日期|

#### 返回结果
```JSON
{
    "id":"123456",
    "applyCount":123,
    "perJobCount":123,
    "perRecCount":123,
    "statDate": "20170216"
}
```

### 获取一段时期的统计数据
#### HTTP 请求
**GET**  /candidate/statistics/period

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明    |
|:----|:-------:|:--------:|:----:|:--------------|
| dateFrom | params | Date | true |该时期的开始时间|
| dateTo | params | Date | true |该时期的结束时间|

#### 返回结果
```JSON
{
    "date":[{
        "id":"123456",
        "applyCount":123,
        "perJobCount":123,
        "perRecCount":123,
        "statDate": "20170216"
    }],
    "applyTotal":12345,
    "jobTotal":12345,
    "recTotal":12345
}
```

### 删除一段时期的统计数据
#### HTTP 请求
**DELETE**  /candidate/statistics/period

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明    |
|:----|:-------:|:--------:|:----:|:--------------|
| dateFrom | params | Date | true |该时期的开始时间|
| dateTo | params | Date | true |该时期的结束时间|

#### 返回结果