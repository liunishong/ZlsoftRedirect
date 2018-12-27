package com.zlsoft.barcode.payment.servlets;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class ErrMsg {
	private static Logger logger = Logger.getLogger(ErrMsg.class);  
	public ErrMsg() {
	}
	
	public static String returnErrMsg(String errmsg,String status){
		String msg = "{"
				+ "\"bank_trade_no\":\"\","
				+ "\"trade_msg\":\""+ errmsg +"\","
				+ "\"trade_no\":\"\","
				+ "\"trade_status\":\"" + status + "\","
				+ "\"pay_platform\":\"\","
				+ "\"trade_time\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\"}";
		logger.error(msg);
		return msg;
	}
}
