### 给申请表添加标签
#### HTTP 请求
**POST**  /candidate/{applicationId}/tags

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明    |
|:----|:-------:|:--------:|:----:|:--------------|
| applicationId | URL | UUID | true |申请表的唯一标识|
| tags | body | json | true |标签实体|

#### tags示例：
```JSON
{
    "tag":"候选",
    "optId":"693f8256-082b-46c9-b9e4-f24aad84b911",
    "optName":"王迎接"
}
```

#### 返回结果
```
"ok"   //添加成功
"You have added three tags!"     //当前用户已经添加三次，添加失败
"The tag you have been added!"     //该标签当前用户已经添加过，添加失败
```


### 删除申请表标签
#### HTTP 请求
**DELETE**  /candidate/tags/{id}

#### 参数
| 参数 | 参数类型 | 数据类型 | 必填 | 备注说明    |
|:----|:-------:|:--------:|:----:|:--------------|
| applicationId | URL | UUID | true |申请表的唯一标识|
| tags | body | json | true |标签实体|

#### tags示例：
```JSON
{
    "tag":"候选",
    "optId":"693f8256-082b-46c9-b9e4-f24aad84b911",
    "optName":"王迎接"
}
```

#### 返回结果
```
"ok"   //添加成功
"You have added three tags!"     //当前用户已经添加三次，添加失败
"The tag you have been added!"     //该标签当前用户已经添加过，添加失败
```
