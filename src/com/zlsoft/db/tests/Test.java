package com.zlsoft.db.tests;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.jws.WebParam;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zlsoft.barcode.webservice.classes.ServiceMsg;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012IN;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012INBody;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012OUT;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012OUTBody;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012OUTDetail;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012OUTHead;
import com.zlsoft.db.algorithm.DESUtils;

public class Test {

	public Test() {
	}

	public static void main(String[] args) {
		try {
			
			System.out.println();
			
//			String resource = "mybatis-config.xml";
//			String dbPoperti ="jdbc.properties";
//			InputStream indb = Resources.getResourceAsStream(dbPoperti);
//			Properties props = new Properties();
//			props.load(indb);
//			String driver = props.getProperty("jdbc.driver");
//			String url=props.getProperty("jdbc.url");
//			String username = props.getProperty("jdbc.username");
//			String password=props.getProperty("jdbc.password");
//			props.put("jdbc.driver", DESUtils.getDecryptString(driver));
//			props.put("jdbc.url",DESUtils.getDecryptString(url));
//			props.put("jdbc.username", DESUtils.getDecryptString(username));
//			props.put("jdbc.password", DESUtils.getDecryptString(password));
//			InputStream inres = Resources.getResourceAsStream(resource);
//			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inres,props);
//			SqlSession session = sessionFactory.openSession();
//
//			String statement = "com.zlsoft.db.mapping.Balance.getBalanceByTime";// 映射sql的标识字符串
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			
//			Date dateBegin = sdf.parse("2018-12-25 00:00:00"); 
//			System.out.println(dateBegin);
//			Date dateEnd = new Date();
//			YYGH3012INBody yygh3012inbody = new YYGH3012INBody();
//			yygh3012inbody.setBeginTradeDate(dateBegin);
//			yygh3012inbody.setEndTradeDate(dateEnd);
//			
//			List<YYGH3012OUTDetail> details=  session.selectList(statement,yygh3012inbody);
//			for (YYGH3012OUTDetail detail : details) {
//				System.out.println(detail);
//			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
//	public String YYGH3012(@WebParam(name = "xmlIn") String xmlIn) {
//		try {
//			YYGH3012IN yygh3012in = convertToJavaBean(YYGH3012IN.class, xmlIn);
//
//			YYGH3012OUT yygh3012 = new YYGH3012OUT();
//			YYGH3012OUTHead head = new YYGH3012OUTHead();
//			head.setErrCode("1");
//			head.setErrMsg("成功");
//			yygh3012.setHead(head);
//
//			YYGH3012OUTDetail detail = new YYGH3012OUTDetail();
//			detail.setTradeDate("1");
//			detail.setTradeSource("2");
//			detail.setChannel("3");
//			detail.setMedCardNO("4");
//			detail.setUserName("5");
//			detail.setRecordType("6");
//			detail.setServiceType("7");
//			detail.setTradeFee("8");
//			detail.setHisTradeID("y9");
//			detail.setPayNO("10");
//			detail.setRePayNO("11");
//			detail.setTradeID("12");
//			detail.setThirdSerialNO("13");
//			detail.setRefundType("14");
//			YYGH3012OUTDetail detail2 = new YYGH3012OUTDetail();
//			detail2.setTradeDate("11");
//			detail2.setTradeSource("2");
//			detail2.setChannel("3");
//			detail2.setMedCardNO("4");
//			detail2.setUserName("5");
//			detail2.setRecordType("6");
//			detail2.setServiceType("7");
//			detail2.setTradeFee("8");
//			detail2.setHisTradeID("y9");
//			detail2.setPayNO("10");
//			detail2.setRePayNO("11");
//			detail2.setTradeID("12");
//			detail2.setThirdSerialNO("13");
//			detail2.setRefundType("14");
//
//			YYGH3012OUTBody body = new YYGH3012OUTBody();
//
//			body.getDetail().add(detail);
//			body.getDetail().add(detail2);
//
//			yygh3012.setBody(body);
//
//			return convertToXml(yygh3012);
//		} catch (Exception e) {
//			logger.error("系统内部错误:", e);
//			return ServiceErrMsg.getSysErrMsg(e.getMessage());
//		}
//	}
}
