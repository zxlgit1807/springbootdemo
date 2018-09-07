/*  
 * Copyright (c) 2016-9999, ShiXiaoyong. All rights reserved. 
 */
package com.zxl.dao.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class MongoDao<T> implements IMongoDao<T> {
	private Class<T> clazz; //T的具体类
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	public MongoDao() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		mongoTemplate.save(entity);
	}

	@Override
	public T findById(String id) {
		return mongoTemplate.findById(id, clazz);
	}
	@Override
	public List<T> findByQuert(Query query) {
		return mongoTemplate.find(query,clazz);
	}
	
}
