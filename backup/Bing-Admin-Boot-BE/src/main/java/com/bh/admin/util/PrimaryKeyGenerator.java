package com.bh.admin.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class PrimaryKeyGenerator implements IdentifierGenerator, Configurable {

	String prefix;
	String tmpPrefix;
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		try {
			if(StringUtils.isEmpty(prefix)) {
				tmpPrefix = BeanUtils.getProperty(object, "tmpPrefix");
				System.out.println(">>>>>>>> tmpPrefix = "+tmpPrefix);
				prefix = tmpPrefix;
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = String.format("SELECT %s FROM %s WHERE %s LIKE :con",
				session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName(),
				object.getClass().getSimpleName(),
				session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName());
		Query<String> query = session.createQuery(sql);
		query.setParameter("con", "%" + prefix + "%");

		Stream<String> result = query.stream();

		Long maxIdx = result.map(c -> c.replace(prefix, "")).mapToLong(Long::parseLong).max().orElse(0L);
		String nextId = String.format("%04d", (maxIdx + 1));
		return (prefix + nextId);
	}

//	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		// TODO Auto-generated method stub
		prefix = params.getProperty("prefix");
	}
}
