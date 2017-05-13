package com.minixiao.api.recruiter.entity.candidate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class StringJsonUserType implements UserType {

  public static final int[] TYPES = {Types.JAVA_OBJECT};

  @Override
  public int[] sqlTypes() {
    return TYPES;
  }

  @Override
  public Class returnedClass() {
    return String.class;
  }

  @Override
  public boolean equals(Object o1, Object o2) throws HibernateException {
    if (o1 == null) {
      return o2 == null;
    }
    return o1.equals(o2);
  }

  @Override
  public int hashCode(Object o1) throws HibernateException {
    return o1.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session,
                            Object owner) throws HibernateException, SQLException {
    return rs.getString(names[0]);
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index,
                          SharedSessionContractImplementor session)
      throws HibernateException, SQLException {
    if (value == null) {
      st.setNull(index, Types.OTHER);
      return;
    }
    st.setObject(index, value, Types.OTHER);
  }


  @Override
  public Object deepCopy(Object value) throws HibernateException {
    return value;
  }

  @Override
  public boolean isMutable() {
    return true;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return (String) this.deepCopy(value);
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return this.deepCopy(cached);
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }

}
