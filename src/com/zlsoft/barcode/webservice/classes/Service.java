package com.zlsoft.barcode.webservice.classes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zlsoft.barcode.payment.listeners.DbServListener;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012IN;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012INBody;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012OUT;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012OUTBody;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012OUTDetail;
import com.zlsoft.barcode.webservice.classespojos.YYGH3012OUTHead;
import com.zlsoft.barcode.webservice.interf.ServiceInterface;

@WebService(targetNamespace = "http://www.zlsoft.com", name = "BalanceService", serviceName = "ZlsoftService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Service implements ServiceInterface {
	private static Logger logger = Logger.getLogger(Service.class);

	public Service() {
	}

	@SuppressWarnings("static-access")
	@Override
	public String YYGH3012(@WebParam(name = "xmlIn") String xmlIn) {
		try {
			YYGH3012IN yygh3012in = convertToJavaBean(YYGH3012IN.class, xmlIn);
			if (!"YYGH3012".equals(yygh3012in.getHead().getTransID())){
				throw new Exception("TransID为空或不支持的业务");
			}
			YYGH3012INBody yygh3012inbody= yygh3012in.getBody();
			
			if(null ==yygh3012inbody.getBeginTradeDate()){
				throw new Exception("开始时间不能为空或格式错误");
			}
			if(null ==yygh3012inbody.getEndTradeDate()){
				throw new Exception("结束时间不能为空或格式错误");
			}		
			List<YYGH3012OUTDetail> details =null;
			if ("".equals(yygh3012inbody.getPayNO())){
				details = this.getDetailsByTime(yygh3012inbody);
			}else {
				details = this.getDetailsByPayNO(yygh3012inbody);
			}

			YYGH3012OUTHead head = new YYGH3012OUTHead();
			YYGH3012OUTBody body = new YYGH3012OUTBody();
			YYGH3012OUT yygh3012out = new YYGH3012OUT();	
			head.setErrCode("1");
			head.setErrMsg("成功");
			body.setDetail(details);
			yygh3012out.setHead(head);
			yygh3012out.setBody(body);			
			return convertToXml(yygh3012out);
		} catch (Exception e) {
			logger.error("\n传入XML:\n"+xmlIn);
			logger.error("\n系统内部错误:", e);
			return ServiceMsg.getSysErrMsg(e.getMessage());
		}
	}

	/**
	 * JavaBean对象转换成XML
	 * @param obj 对象实例
	 * @throws JAXBException 
	 */
	private static String convertToXml(Object obj) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		// 格式化输出，即按标签自动换行，否则就是一行输出
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// 设置编码（默认编码就是utf-8）
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		// 是否省略xml头信息，默认不省略（false）
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		marshaller.marshal(obj, out);
		return out.toString();
	}

	/**
	 * Xml转成JavaBean
	 * @param clz 类类型
	 * @param xmlin xml内容
	 * @return 对象实例
	 * @throws JAXBException
	 * @throws UnsupportedEncodingException
	 */
	private static <T> T convertToJavaBean(Class<T> clz, String xmlin)
			throws JAXBException, UnsupportedEncodingException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clz);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		ByteArrayInputStream in = new ByteArrayInputStream(xmlin.getBytes("UTF-8"));
		@SuppressWarnings("unchecked")
		T t = (T) unmarshaller.unmarshal(in);
		return t;
	}

	private static List<YYGH3012OUTDetail> getDetailsByTime(YYGH3012INBody yygh3012inbody) throws IOException {
		SqlSession session = DbServListener.getSqlSession();
		String statement = "com.zlsoft.db.mapping.Balance.getBalanceByTime";
		List<YYGH3012OUTDetail> details = session.selectList(statement, yygh3012inbody);
		return details;
	}

	private static List<YYGH3012OUTDetail> getDetailsByPayNO(YYGH3012INBody yygh3012inbody) throws IOException {
		SqlSession session = DbServListener.getSqlSession();
		String statement = "com.zlsoft.db.mapping.Balance.getBalanceByNo";
		List<YYGH3012OUTDetail> details = session.selectList(statement, yygh3012inbody);
		return details;
	}
}
