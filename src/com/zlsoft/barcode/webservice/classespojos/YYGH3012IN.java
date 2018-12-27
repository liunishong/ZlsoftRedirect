package com.zlsoft.barcode.webservice.classespojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "head", "body" })
public class YYGH3012IN implements Serializable {
	private static final long serialVersionUID = 1L;
	private YYGH3012INHead head;
	private YYGH3012INBody body;

	public YYGH3012IN() {
	}

	public YYGH3012INHead getHead() {
		return head;
	}

	public void setHead(YYGH3012INHead head) {
		this.head = head;
	}

	public YYGH3012INBody getBody() {
		return body;
	}

	public void setBody(YYGH3012INBody body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "YYGH3012IN [head=" + head + ", body=" + body + "]";
	}

}
