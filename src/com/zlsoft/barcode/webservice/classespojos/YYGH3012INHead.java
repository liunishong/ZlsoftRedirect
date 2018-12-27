package com.zlsoft.barcode.webservice.classespojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class YYGH3012INHead implements Serializable {

	private static final long serialVersionUID = 1L;
	private String TransID;

	public YYGH3012INHead() {
	}

	public String getTransID() {
		return TransID;
	}

	public void setTransID(String transID) {
		TransID = transID;
	}

	@Override
	public String toString() {
		return "YYGH3012INHead [TransID=" + TransID + "]";
	}

}
