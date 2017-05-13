package com.minixiao.api.recruiter.utils;

import org.hibernate.dialect.PostgreSQL9Dialect;

import java.sql.Types;

public class JsonPostgreSQLDialect extends PostgreSQL9Dialect {

  /**
   * .
   */
  public JsonPostgreSQLDialect() {
    super();
    this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    this.registerHibernateType(Types.OTHER,
        "com.minixiao.api.recruiter.utils.StringJsonUserType");
  }
}
