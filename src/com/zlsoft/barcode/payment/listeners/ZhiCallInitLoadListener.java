package com.zlsoft.barcode.payment.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.zlsoft.barcode.payment.exceptions.loadUrlException;

/**
 * @author ZDZ
 */

public class ZhiCallInitLoadListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(ZhiCallInitLoadListener.class); 
	private Integer connectTimeout=Integer.valueOf(20000) ;
	private Integer socketTimeout=Integer.valueOf(20000) ;
	
    public ZhiCallInitLoadListener() {
    }

    public void contextDestroyed(ServletContextEvent event)  {
    	event.getServletContext().removeAttribute("PayURL");
    	event.getServletContext().removeAttribute("PaySelectURL");
    	event.getServletContext().removeAttribute("PayBackURL");
    	event.getServletContext().removeAttribute("ConnectTimeout");
    	event.getServletContext().removeAttribute("SocketTimeout");
    }
    
    /**
     * 对加载的context-param数据进行为空校验,并进行转存
     */
    public void contextInitialized(ServletContextEvent event) { 
    	
    	String PayURL =event.getServletContext().getInitParameter("PayURL");
    	String PaySelectURL =event.getServletContext().getInitParameter("PaySelectURL");
    	String PayBackURL =event.getServletContext().getInitParameter("PayBackURL");
    	
    	String ConnectTimeout =event.getServletContext().getInitParameter("ConnectTimeout");
    	String SocketTimeout= event.getServletContext().getInitParameter("SocketTimeout");
    	
    	if ("".equals(PayURL)) {
    		logger.error("\n加载智康实时支付地址-> 地址为空", new loadUrlException("智康实时支付地址为空"));
    	}else{
    		event.getServletContext().setAttribute("PayURL", PayURL);
    		logger.info("\n加载智康实时支付地址-> "+ PayURL);
    	}
    	if ("".equals(PaySelectURL)) {
    		logger.error("\n加载智康支付查询地址-> 地址为空", new loadUrlException("智康支付查询地址为空"));
    	}else{
    		event.getServletContext().setAttribute("PaySelectURL", PaySelectURL);
    		logger.info("\n加载智康支付查询地址-> "+ PaySelectURL);
    	}
    	if ("".equals(PayBackURL)) {
    		logger.error("\n加载智康退费支付地址-> 地址为空", new loadUrlException("智康退费支付地址为空"));
    	}else{
    		event.getServletContext().setAttribute("PayBackURL", PayBackURL);
    		logger.info("\n加载智康退费支付地址-> "+ PayBackURL);
    	}
    	
    	if ("".equals(ConnectTimeout)){
    		logger.info("\n加载智康连接等待超时时间-> 配置时间为空");  
    		logger.info("\n设置智康连接等待超时时间为默认:"+connectTimeout.toString()+" 毫秒");
    		event.getServletContext().setAttribute("ConnectTimeout", connectTimeout);
    	}else{
    		try{
    			connectTimeout = Integer.valueOf(ConnectTimeout);
    		}catch(Exception e){
    			logger.error("\n加载智康连接等待超时时间转换成数字失败");
    			logger.info("\n设置智康连接等待超时时间为默认:"+connectTimeout.toString()+" 毫秒");
    		}
    		event.getServletContext().setAttribute("ConnectTimeout", connectTimeout);
    		logger.info("\n加载智康连接等待超时时间-> "+ connectTimeout.toString()+" 毫秒");    		
    	}
    	
    	if ("".equals(SocketTimeout)){
    		logger.info("\n加载智康返回等待超时时间-> 配置时间为空");  
    		logger.info("\n设置智康返回等待超时时间为默认:"+socketTimeout.toString()+" 毫秒");
    		event.getServletContext().setAttribute("SocketTimeout", socketTimeout);
    	}else{
    		try{
    			socketTimeout = Integer.valueOf(SocketTimeout);
    		}catch(Exception e){
    			logger.error("\n加载智康返回等待超时时间转换成数字失败");
    			logger.info("\n设置智康返回等待超时时间为默认:"+socketTimeout.toString()+" 毫秒");
    		}
    		event.getServletContext().setAttribute("SocketTimeout", socketTimeout);
    		logger.info("\n加载智康返回等待超时时间-> "+ socketTimeout.toString()+" 毫秒");    		
    	}  	
    }
	
}
