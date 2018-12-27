package com.zlsoft.barcode.webservice.classespojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class YYGH3012OUTHead implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ErrCode;
	private String ErrMsg;

	public YYGH3012OUTHead() {
	}

	public String getErrCode() {
		return ErrCode;
	}

	public void setErrCode(String errCode) {
		ErrCode = errCode;
	}

	public String getErrMsg() {
		return ErrMsg;
	}

	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}

	@Override
	public String toString() {
		return "YYGH3012Head [ErrCode=" + ErrCode + ", ErrMsg=" + ErrMsg + "]";
	}

}
