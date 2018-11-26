package com.studyshiro.Shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 FirstSuccessfulStrategy：只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
 AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；
 AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。

自定义
 AtLeastTwoAuthenticatorStrategy 至少有两个个Realm验证成功即可
 OnlyOneAuthenticatorStrategy  仅仅只有一个验证成功才行

 */
public class AuthenticatorTest {
        Logger logger = LoggerFactory.getLogger(AuthenticatorTest.class);
        private void login(String configFile) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        subject.login(token);
    }
    //my my3 都是zhang 123 是正确的
    //    返回两个数据
    // 验证成功
    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();

        logger.info("*************"+principalCollection.asList().size()+"***************");
      //  Assert.assertEquals(2, principalCollection.asList().size());
    }

    //my zhang 123 是正确的 my2 wang 123 错误，所以这个里面跑出了账户的异常 ，但还是能运行的，
    //因为这个本身就是返回所有的正确的 目的就是要让错误的显示异常
    // 验证成功
    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:shiro-authenticator-all-fail.ini");
    }




    //m m3 正确  m2错误
    //抛出了异常，但是结果是正确的。返回了两个数据
    //AtLeastTwo 至少有1个验证成功才行，然后返回所有的验证成功数据
    // 验证成功
     @Test
    public void testAtLeastOneSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-atLeastOne-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
  //      Assert.assertEquals(2, principalCollection.asList().size());

        logger.info("************"+principalCollection.asList().size()+"*************");
    }

    //m m3 正确  m2错误 发生异常
    //这个只是返回一个数据
    //解释：FirstOne 至少有1个验证成功才行，然后返回第一个验证成功的数据
    // 验证成功
    @Test
    public void testFirstOneSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-first-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了第一个Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
   //     Assert.assertEquals(1, principalCollection.asList().size());
        logger.info("************"+principalCollection.asList().size()+"*************");

    }

    //m m3 m4正确  m2错误 发生异常
    //返回结果是两个，因为m 和
    //AtLeastTwo 至少有两个验证成功才行，然后返回所有的验证成功数据
    //验证成功
    @Test
    public void testAtLeastTwoStrategyWithSuccess() {
        login("classpath:shiro-authenticator-atLeastTwo-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
        PrincipalCollection principalCollection = subject.getPrincipals();
      //  Assert.assertEquals(1, principalCollection.asList().size());
        logger.info("************"+principalCollection.asList().size()+"*************");

    }
    //m m3正确 m2错误 发生异常
    //  解释; OnlyOne这个表示我只想要一个正确的。
    //验证失败，但是是我想要的结果
    @Test
    public void testOnlyOneStrategyWithSuccess() {
        login("classpath:shiro-authenticator-onlyone-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
        PrincipalCollection principalCollection = subject.getPrincipals();
      //  Assert.assertEquals(1, principalCollection.asList().size());
        logger.info("************"+principalCollection.asList().size()+"*************");

    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }







}
