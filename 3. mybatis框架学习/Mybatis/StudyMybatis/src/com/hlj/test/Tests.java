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
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = Tests.class.getClassLoader().getResourceAsStream(resource);
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        
        SqlSession session = sessionFactory.openSession();
        
        /**
         * ӳ��sql�ı�ʶ�ַ�����
         * com.hlj.domain.UserMapping��userMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
         * getUser��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
         */
        String statement = "com.hlj.domain.UserMapping.getUser";//ӳ��sql�ı�ʶ�ַ���
        //ִ�в�ѯ����һ��Ψһuser�����sql
        User user = session.selectOne(statement, "1123");
        
        System.out.println(user.toString()); 
    }
}