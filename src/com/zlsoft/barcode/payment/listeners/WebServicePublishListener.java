package com.zlsoft.barcode.payment.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;

import com.zlsoft.barcode.webservice.classes.Service;

public class WebServicePublishListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(WebServicePublishListener.class);
    public WebServicePublishListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent event)  { 
    	String address = event.getServletContext().getInitParameter("ServiceAddress");
    	logger.info("\n加载对账地址-->" + address);
    	//String address = "http://localhost:8081/ZlsoftRedirect/WebService";
    	//发布WebService，WebServiceImpl类是WebServie接口的具体实现类
    	logger.info("\n加载条码付对账服务开始----------------------------------------");
    	Endpoint.publish(address , new Service());
    	logger.info("\n加载条码付对账服务成功----------------------------------------");
    }
	
}
