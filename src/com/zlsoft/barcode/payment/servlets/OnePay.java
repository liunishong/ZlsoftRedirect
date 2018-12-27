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

import com.zlsoft.barcode.payment.servletpojos.PayList;

@WebServlet("/fund/onePay")
public class OnePay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(OnePay.class);    
	
    public OnePay() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String payURL = request.getServletContext().getAttribute("PayURL").toString();

		if ("".equals(payURL)){
			response.getWriter().append(ErrMsg.returnErrMsg("代理服务器中配置智康实时支付的URL地址为空", "404"));
			return;
		}
		Integer ConnectTimeout;
		Integer SocketTimeout;

		try{
			ConnectTimeout =Integer.valueOf(request.getServletContext().getAttribute("ConnectTimeout").toString());
			SocketTimeout = Integer.valueOf(request.getServletContext().getAttribute("SocketTimeout").toString());
		}catch(Exception e){
			response.getWriter().append(ErrMsg.returnErrMsg("代理服务器内部错误:\n"+ e.getClass().getName().toString(), "500"));
			return;
		}
		
		PayList paylist = new PayList();
		paylist.setPartner_id(request.getParameter("partner_id"));
		paylist.setOut_trade_no(request.getParameter("out_trade_no"));
		paylist.setService_item(request.getParameter("service_item"));
		paylist.setTerminal_id(request.getParameter("terminal_id"));
		paylist.setTrade_description(request.getParameter("trade_description"));
		paylist.setTrade_fee(request.getParameter("trade_fee"));
		paylist.setOpen_id(request.getParameter("open_id"));
		paylist.setService_provider(request.getParameter("service_provider"));
		paylist.setDept_name(request.getParameter("dept_name"));
		paylist.setDoctor_name(request.getParameter("doctor_name"));
		
		logger.info("转发交易入参-----------------------------------------------------");
		logger.info(paylist.toString());
		logger.info("-------------------------------------------------------------");
		
		String result="";
        CloseableHttpClient  client =null;
        CloseableHttpResponse  rps =null;
        
		try{
			client = HttpClients.createDefault();
    		HttpPost post = new HttpPost(payURL);
    		
            RequestConfig requestConfig = RequestConfig.custom()  
                    .setConnectTimeout(ConnectTimeout)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间  
                    .setSocketTimeout(SocketTimeout)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间  
                    //从连接池获取连接的等待时间
                    //.setConnectionRequestTimeout(5000)  
                    .build();     		
    		
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            parameters.add(new BasicNameValuePair("partner_id", paylist.getPartner_id()));
            parameters.add(new BasicNameValuePair("out_trade_no", paylist.getOut_trade_no()));
            parameters.add(new BasicNameValuePair("service_item", paylist.getService_item()));
            parameters.add(new BasicNameValuePair("terminal_id", paylist.getTerminal_id()));
            parameters.add(new BasicNameValuePair("trade_description", paylist.getTrade_description()));
            parameters.add(new BasicNameValuePair("trade_fee", paylist.getTrade_fee()));
            parameters.add(new BasicNameValuePair("open_id", paylist.getOpen_id()));
            parameters.add(new BasicNameValuePair("service_provider", paylist.getService_provider()));
            parameters.add(new BasicNameValuePair("dept_name", paylist.getDept_name()));
            parameters.add(new BasicNameValuePair("doctor_name", paylist.getDoctor_name()));
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
