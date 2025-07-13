package com.bh.admin.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class PrimaryKeyGeneratorByDate implements IdentifierGenerator, Configurable {

	String prefix = "";
	String tempPrefix = "";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		String temp_prefix;
		try {
			if(StringUtils.isEmpty(prefix)) {
				tempPrefix = BeanUtils.getProperty(object, "tmpPrefix");
				System.out.println(">>>>>>>> tmpPrefix = "+tempPrefix);
				prefix = tempPrefix;
			}
			System.out.println("----------------------------------------------temp_prefix = " + prefix);

			String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
			tempPrefix = prefix;
			prefix = prefix.concat(currentDate);
//			String query = String.format("SELECT %s FROM %s ORDER BY %s DESC",
//					session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName(),
//					object.getClass().getSimpleName(),
//					session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName()
//					
//			);

			String sql = String.format("SELECT %s FROM %s WHERE SUBSTRING(%s,3,8) = :currentDate ORDER BY %s DESC",
					session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName(),
					object.getClass().getSimpleName(),
					session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName(),
					session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName()
					
			);
			try {
				Query query = session.createQuery(sql);
				query.setParameter("currentDate", currentDate);
				Stream<String> ids = query.stream();
				Long maxIds = ids.findFirst().map(result -> {
					return result.replace(prefix, "");
				}).stream().mapToLong(Long::parseLong).max().orElse(0L);
				
				String result = String.format("%02d", (maxIds + 1));
				result = prefix.concat(result);
				prefix = tempPrefix;

				return result;
			} catch (HibernateException e) {
				// TODO: handle exception
				tempPrefix = "";
				prefix = "";
				e.printStackTrace();
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
		return null;
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		// TODO Auto-generated method stub
		prefix = params.getProperty("prefix");
	}
}
