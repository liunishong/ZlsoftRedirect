package com.zlsoft.barcode.payment.servlets;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.zlsoft.barcode.payment.servletpojos.SelectList;

@WebServlet("/fund/getOrderInfo")
public class OrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(OrderInfo.class); 
	
    public OrderInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String paySelectURL = request.getServletContext().getAttribute("PaySelectURL").toString();
		if ("".equals(paySelectURL)){
			response.getWriter().append(ErrMsg.returnErrMsg("代理服务器中配置智康支付查询的URL地址为空", "404"));
			return;
		}
		
		Integer ConnectTimeout =Integer.valueOf(request.getServletContext().getAttribute("ConnectTimeout").toString());
		Integer SocketTimeout = Integer.valueOf(request.getServletContext().getAttribute("SocketTimeout").toString());
		
		SelectList selectList = new SelectList();
		selectList.setOut_trade_no(request.getParameter("out_trade_no"));
		selectList.setTrade_no(request.getParameter("trade_no"));

		
		logger.info("转发交易入参-----------------------------------------------------");
		logger.info(selectList.toString());
		logger.info("-------------------------------------------------------------");
		
		String result="";
        CloseableHttpClient  client =null;
        CloseableHttpResponse  rps =null;
        
		try{
			client = HttpClients.createDefault();
    		HttpPost post = new HttpPost(paySelectURL);
    		
            RequestConfig requestConfig = RequestConfig.custom()  
                    .setConnectTimeout(ConnectTimeout)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间  
                    .setSocketTimeout(SocketTimeout)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间  
                    //从连接池获取连接的等待时间
                    //.setConnectionRequestTimeout(5000)  
                    .build();     		
    		
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            parameters.add(new BasicNameValuePair("out_trade_no", selectList.getOut_trade_no()));
            parameters.add(new BasicNameValuePair("trade_no", selectList.getTrade_no()));
            post.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
            
            post.setConfig(requestConfig);
            
            try {
            	rps = client.execute(post);
	            if (rps.getStatusLine().getStatusCode() == 200) {  
		            HttpEntity entity = rps.getEntity();
		            result = EntityUtils.toString(entity, "UTF-8");
		            logger.info("转发交易出参-----------------------------------------------------");
		            logger.info(result);
		            logger.info("-------------------------------------------------------------");
		            EntityUtils.consume(entity);
		            response.getWriter().append(result);
		            return;
	            }else {
	            	response.getWriter().append(ErrMsg.returnErrMsg("代理服务器转发数据请求失败", "404"));
	            	return;
	            }
            }catch(Exception e){
            	if (e instanceof ConnectTimeoutException) {
            		response.getWriter().append(ErrMsg.returnErrMsg("通过代理服务器转发,链接智康服务器超时.", "404"));
            	}else if(e instanceof SocketTimeoutException){  
            		response.getWriter().append(ErrMsg.returnErrMsg("通过代理服务器转发,等待智康服务器返回超时.", "404"));
            	}else { 
            		response.getWriter().append(ErrMsg.returnErrMsg("代理服务器内部错误:\n"+ e.getClass().getName().toString(), "500"));
            	}  
            	return;
            }finally{
            	if (rps!=null) {
            		rps.close();
            	}
            }   
		}catch(Exception e){
			response.getWriter().append(ErrMsg.returnErrMsg("代理服务器内部错误:\n"+ e.getClass().getName().toString(), "500"));
			return;
		}finally{ 
        	if (client!=null){
        		client.close();
        	}
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
