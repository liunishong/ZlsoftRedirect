package com.zlsoft.barcode.webservice.classespojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "head", "body"})
public class YYGH3012OUT {
	private YYGH3012OUTHead head;
	private YYGH3012OUTBody body;

	public YYGH3012OUT() {
	}

	public YYGH3012OUTBody getBody() {
		return body;
	}

	public void setBody(YYGH3012OUTBody body) {
		this.body = body;
	}

	public YYGH3012OUTHead getHead() {
		return head;
	}

	public void setHead(YYGH3012OUTHead head) {
		this.head = head;
	}

	@Override
	public String toString() {
		return "YYGH3012OUT [head=" + head + ", body=" + body + "]";
	}

}
