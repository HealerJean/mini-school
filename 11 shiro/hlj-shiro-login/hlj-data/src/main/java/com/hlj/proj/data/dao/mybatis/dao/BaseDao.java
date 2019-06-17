package com.hlj.proj.data.dao.mybatis.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据库操作基类，使用时需要注入SqlSessionFactory
 */
@Slf4j
public class BaseDao extends SqlSessionDaoSupport {

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    private static int BATCH_DEAL_NUM = 2000;

    protected static SqlSessionFactory batchSqlSessionFactory;

    /**
     * 批量插入记录
     * 默认500条，也可以通过修改BATCH_DEAL_NUM字段改变记录数量
     *
     * @param statement
     * @param list
     * @return
     */
    public int batchInsert(String statement, List<?> list) {
        SqlSession batchSession = batchSqlSessionFactory.openSession(ExecutorType.BATCH, false);
        int i = 0;
        try {
            for (int cnt = list.size(); i < cnt; i++) {
                try {
                    batchSession.insert(statement, list.get(i));
                } catch (Exception e) {

                    log.error(e.getMessage(), e);
                    return cnt;
                }

                if ((i + 1) % BATCH_DEAL_NUM == 0) {
                    batchSession.commit();
                }
            }
            batchSession.commit();
            batchSession.clearCache();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        } finally {
            batchSession.close();
        }
        return i;
    }

    /**
     * 批量更新记录
     * 默认500条，也可以通过修改BATCH_DEAL_NUM字段改变记录数量
     *
     * @param statement
     * @param list
     * @return
     */
    public int batchUpdate(String statement, List<?> list) {
        SqlSession batchSession = batchSqlSessionFactory.openSession(ExecutorType.BATCH, false);
        int i = 0;
        try {
            for (int cnt = list.size(); i < cnt; i++) {
                batchSession.update(statement, list.get(i));
                if ((i + 1) % BATCH_DEAL_NUM == 0) {
                    batchSession.commit();
                }
            }
            batchSession.commit();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        } finally {
            batchSession.close();
        }
        return i;
    }

    /**
     * 批量删除记录
     * 默认500条，也可以通过修改BATCH_DEAL_NUM字段改变记录数量
     *
     * @param statement
     * @param list
     * @return
     */
    public int batchDelete(String statement, List<?> list) {
        SqlSession batchSession = batchSqlSessionFactory.openSession(ExecutorType.BATCH, false);
        int i = 0;
        try {
            for (int cnt = list.size(); i < cnt; i++) {
                batchSession.delete(statement, list.get(i));
                if ((i + 1) % BATCH_DEAL_NUM == 0) {
                    batchSession.commit();
                }
            }
            batchSession.commit();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        } finally {
            batchSession.close();
        }
        return i;
    }

    @Resource
    public void setBatchSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        batchSqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 手动提交事务
     */
    public void commitTransaction() {
        SqlSession batchSession = batchSqlSessionFactory.openSession(true);
        try {
            batchSession.flushStatements();
            batchSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (batchSession != null) {
                batchSession.close();
            }
        }
    }
}