package com.hlj.test;


import java.io.InputStream;
 


import org.apache.ibatis.io.ResolverUtil.Test;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.hlj.domain.User;

 
public class Tests {
    
	public static void main(String[] args) {
		Tests tests = new Tests();
		tests.test();
	}
    public void test(){
        String resource = "mybatis.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Tests.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        
        SqlSession session = sessionFactory.openSession();
        
        /**
         * 映射sql的标识字符串，
         * com.hlj.domain.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hlj.domain.UserMapping.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, "1123");
        
        System.out.println(user.toString()); 
    }
}