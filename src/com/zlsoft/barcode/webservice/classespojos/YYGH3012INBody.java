package com.zlsoft.barcode.webservice.classespojos;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.zlsoft.barcode.webservice.classes.DateAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class YYGH3012INBody implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date BeginTradeDate;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date EndTradeDate;
	private String PayNO;

	public YYGH3012INBody() {
	}

	public Date getBeginTradeDate() {
		return BeginTradeDate;
	}

	public void setBeginTradeDate(Date beginTradeDate) {
		BeginTradeDate = beginTradeDate;
	}

	public Date getEndTradeDate() {
		return EndTradeDate;
	}

	public void setEndTradeDate(Date endTradeDate) {
		EndTradeDate = endTradeDate;
	}

	public String getPayNO() {
		return PayNO;
	}

	public void setPayNO(String payNO) {
		PayNO = payNO;
	}

	@Override
	public String toString() {
		return "YYGH3012INBody [BeginTradeDate=" + BeginTradeDate + ", EndTradeDate=" + EndTradeDate + ", PayNO="
				+ PayNO + "]";
	}

}
