package com.zlsoft.db.jdbc;

import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.zlsoft.db.algorithm.DESUtils;

public class DbUtils {

	private static SqlSessionFactory sqlsessionfactory;
	private static Logger logger = Logger.getLogger(DbUtils.class);
	static{
		try{
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		InputStream in = Resources.getResourceAsStream("jdbc.properties");
		Properties props = new Properties();
		props.load(in);
		String driver = props.getProperty("driver");
		String url= props.getProperty("url");
		String username = props.getProperty("username");
		String password= props.getProperty("password");
		props.put("driver",DESUtils.getDecryptString(driver));
		props.put("url",DESUtils.getDecryptString(url));
		props.put("username",DESUtils.getDecryptString(username));
		props.put("password",DESUtils.getDecryptString(password));
		inputStream = Resources.getResourceAsStream(resource );
		sqlsessionfactory = new SqlSessionFactoryBuilder().build(inputStream ,props);
		logger.info("初始化SqlSessionFactory成功.");
		}catch(Exception e){
			logger.error("初始化SqlSessionFactory失败.", e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory(){
		return sqlsessionfactory;
	}

}
