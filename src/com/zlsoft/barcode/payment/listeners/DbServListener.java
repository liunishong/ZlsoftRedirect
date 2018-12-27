package com.zlsoft.barcode.payment.listeners;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.zlsoft.db.algorithm.DESUtils;

public class DbServListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(DbServListener.class);
	private static  SqlSessionFactory sessionFactory = null;
	
	static {
		try{
			logger.info("\n加载数据连接开始");
			String resource = "mybatis-config.xml";
			String dbPoperti = "jdbc.properties";
			InputStream indb = Resources.getResourceAsStream(dbPoperti);
			Properties props = new Properties();
			props.load(indb);
			String driver = props.getProperty("jdbc.driver");
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			props.put("jdbc.driver", DESUtils.getDecryptString(driver));
			props.put("jdbc.url", DESUtils.getDecryptString(url));
			props.put("jdbc.username", DESUtils.getDecryptString(username));
			props.put("jdbc.password", DESUtils.getDecryptString(password));
			InputStream inres = Resources.getResourceAsStream(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(inres, props);
			logger.info("\n加载数据连接成功结束");
		}catch(Exception e){
			logger.error("\n加载mybatis失败:",e);
			e.printStackTrace();
		}
	}
	
	public DbServListener() {
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}

	public static SqlSession getSqlSession(){
		return sessionFactory.openSession();
	}
}
