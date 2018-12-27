package com.zlsoft.barcode.webservice.classespojos;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "TradeDate", "TradeSource", "Channel", "MedCardNO", "UserName", "RecordType",
		"ServiceType", "TradeFee", "HisTradeID","PayNO","RePayNO","TradeID","ThirdSerialNO","RefundType" })
public class YYGH3012OUTDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	private String TradeDate="";
	private String TradeSource="";
	private String Channel="";
	private String MedCardNO="";
	private String UserName="";
	private String RecordType="";
	private String ServiceType="";
	private String TradeFee="";
	private String HisTradeID="";
	private String PayNO="";
	private String RePayNO="";
	private String TradeID="";
	private String ThirdSerialNO="";
	private String RefundType="";

	public YYGH3012OUTDetail() {
	}

	public String getTradeDate() {
		return TradeDate;
	}

	public void setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
	}

	public String getTradeSource() {
		return TradeSource;
	}

	public void setTradeSource(String tradeSource) {
		TradeSource = tradeSource;
	}

	public String getChannel() {
		return Channel;
	}

	public void setChannel(String channel) {
		Channel = channel;
	}

	public String getMedCardNO() {
		return MedCardNO;
	}

	public void setMedCardNO(String medCardNO) {
		MedCardNO = medCardNO;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName= userName;
	}

	public String getRecordType() {
		return RecordType;
	}

	public void setRecordType(String recordType) {
		RecordType = recordType;
	}

	public String getServiceType() {
		return ServiceType;
	}

	public void setServiceType(String serviceType) {
		ServiceType = serviceType;
	}

	public String getTradeFee() {
		return TradeFee;
	}

	public void setTradeFee(String tradeFee) {
		TradeFee = tradeFee;
	}

	public String getHisTradeID() {
		return HisTradeID;
	}

	public void setHisTradeID(String hisTradeID) {
		HisTradeID = hisTradeID;
	}

	public String getPayNO() {
		return PayNO;
	}

	public void setPayNO(String payNO) {
		PayNO = payNO;
	}

	public String getRePayNO() {
		return RePayNO;
	}

	public void setRePayNO(String rePayNO) {
		RePayNO = rePayNO;
	}

	public String getTradeID() {
		return TradeID;
	}

	public void setTradeID(String tradeID) {
		TradeID = tradeID;
	}

	public String getThirdSerialNO() {
		return ThirdSerialNO;
	}

	public void setThirdSerialNO(String thirdSerialNO) {
		ThirdSerialNO = thirdSerialNO;
	}

	public String getRefundType() {
		return RefundType;
	}

	public void setRefundType(String refundType) {
		RefundType = refundType;
	}

	@Override
	public String toString() {
		return "YYGH3012Detail [TradeDate=" + TradeDate + ", TradeSource=" + TradeSource + ", Channel=" + Channel
				+ ", MedCardNO=" + MedCardNO + ", UserName=" + UserName + ", RecordType=" + RecordType
				+ ", ServiceType=" + ServiceType + ", TradeFee=" + TradeFee + ", HisTradeID=" + HisTradeID + ", PayNO="
				+ PayNO + ", RePayNO=" + RePayNO + ", TradeID=" + TradeID + ", ThirdSerialNO=" + ThirdSerialNO
				+ ", RefundType=" + RefundType + "]";
	}

}
