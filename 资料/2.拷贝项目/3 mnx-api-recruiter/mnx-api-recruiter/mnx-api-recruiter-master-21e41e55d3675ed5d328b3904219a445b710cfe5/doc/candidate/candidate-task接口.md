


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
    "statDate":"",
}
```



### 获取某一天的统计数据
#### HTTP 请求
**GET**  /candidate/statistics

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明    |
|:----|:-------:|:--------:|:----:|:--------------|
| date | params | String | true |指定某一天的日期|

#### 返回结果
```JSON
{
    "id":"123456",
    "applyCount":123,
    "perJobCount":123,
    "perRecCount":123,
    "statDate":""
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
        "statDate":""
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
```JSON
{"result":"ok"}
```