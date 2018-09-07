package com.zxl.dao.common;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * mongo 基础dao类
 * @param <T>
 */
public interface IMongoDao<T> {

    //添加一个对象
    void save(T entity);

    T findById(String id);

    List<T> findByQuert(Query query);
}
