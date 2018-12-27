package com.zlsoft.barcode.webservice.classespojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class YYGH3012OUTBody implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<YYGH3012OUTDetail> detail;

	public YYGH3012OUTBody() {
	}

	public List<YYGH3012OUTDetail> getDetail() {
		if (detail==null) {
			detail = new ArrayList<YYGH3012OUTDetail>();
		}
		return detail;
	}

	public void setDetail(List<YYGH3012OUTDetail> detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "YYGH3012Body [detail=" + detail + "]";
	}

}
