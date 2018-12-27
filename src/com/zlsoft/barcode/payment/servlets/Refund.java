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

import com.zlsoft.barcode.payment.servletpojos.BackList;

@WebServlet("/refund/refunding")
public class Refund extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Refund.class); 
	
    public Refund() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String payBackURL = request.getServletContext().getAttribute("PayBackURL").toString();
		if ("".equals(payBackURL)){
			response.getWriter().append(ErrMsg.returnErrMsg("代理服务器中配置智康支付查询的URL地址为空", "404"));
			return;
		}
		
		Integer ConnectTimeout =Integer.valueOf(request.getServletContext().getAttribute("ConnectTimeout").toString());
		Integer SocketTimeout = Integer.valueOf(request.getServletContext().getAttribute("SocketTimeout").toString());
		
		BackList backList = new BackList();
		backList.setPartner_id(request.getParameter("partner_id"));
		backList.setOut_trade_no(request.getParameter("out_trade_no"));
		backList.setTrade_no(request.getParameter("trade_no"));
		backList.setRefund_fee(request.getParameter("refund_fee"));
		backList.setDelay_refund(request.getParameter("delay_refund"));
		backList.setOut_refund_no(request.getParameter("out_refund_no"));
		backList.setHis_op_code(request.getParameter("his_op_code"));

		logger.info("转发交易入参-----------------------------------------------------");
		logger.info(backList.toString());
		logger.info("-------------------------------------------------------------");
		
		String result="";
        CloseableHttpClient  client =null;
        CloseableHttpResponse  rps =null;
        
		try{
			client = HttpClients.createDefault();
    		HttpPost post = new HttpPost(payBackURL);
    		
            RequestConfig requestConfig = RequestConfig.custom()  
                    .setConnectTimeout(ConnectTimeout)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间  
                    .setSocketTimeout(SocketTimeout)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间  
                    //从连接池获取连接的等待时间
                    //.setConnectionRequestTimeout(5000)  
                    .build();     		
    		
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            parameters.add(new BasicNameValuePair("partner_id", backList.getPartner_id()));
            parameters.add(new BasicNameValuePair("out_trade_no", backList.getOut_trade_no()));
            parameters.add(new BasicNameValuePair("trade_no", backList.getTrade_no()));
            parameters.add(new BasicNameValuePair("refund_fee",backList.getRefund_fee()));
            parameters.add(new BasicNameValuePair("delay_refund",backList.getDelay_refund()));
            parameters.add(new BasicNameValuePair("out_refund_no", backList.getOut_refund_no()));
            parameters.add(new BasicNameValuePair("his_op_code", backList.getHis_op_code()));
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
