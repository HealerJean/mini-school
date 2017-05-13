package com.minixiao.api.recruiter.entity.candidate;

import org.hibernate.dialect.PostgreSQL9Dialect;

import java.sql.Types;

public class JsonPostgreSQLDialect extends PostgreSQL9Dialect {

  /**
<<<<<<< HEAD
   * @Description 添加方言.
   * @Author  王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/15 15:24
   * @Param
   * @Return
=======
   *数据库方言.
>>>>>>> origin/wangsy
   */
  public JsonPostgreSQLDialect() {
    super();
    this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    this.registerHibernateType(Types.OTHER,
        "com.minixiao.api.recruiter.entity.candidate.StringJsonUserType");
  }
}
