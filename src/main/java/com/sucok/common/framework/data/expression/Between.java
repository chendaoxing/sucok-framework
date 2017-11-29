/*
 * Copyright 2016 - 2017 suoke & Co., Ltd.
 */
package com.sucok.common.framework.data.expression;

import java.lang.reflect.Array;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sucok.common.framework.data.AbstractExpression;
import com.sucok.common.framework.data.QueryFormHelper;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * @author chendx
 * @version 1.0 created at 2017年5月26日 下午1:08:21
 *
 */
public class Between extends AbstractExpression {

	public Between(String field, Object value1,Object value2) {
		super(field ,new Object[] { value1, value2 });
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Predicate buildJpaPredicate(CriteriaBuilder builder, Root root) {
		Expression<Comparable> path = QueryFormHelper.getPath(root, field);
		Comparable value1 = (Comparable) Array.get(value, 0);
		Comparable value2 = (Comparable) Array.get(value, 1);
		return builder.between(path, value1, value2);
	}

	@Override
	public Criteria buildMongoCriteria(Object expression) {
		String path = expression.toString();
		Object value1 = Array.get(value, 0);
		Object value2 = Array.get(value, 1);
		return Criteria.where(path).gte(value1).and(path).lte(value2);
	}
}
