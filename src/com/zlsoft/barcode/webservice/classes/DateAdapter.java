package com.zlsoft.barcode.webservice.classes;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

	private static final DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public String marshal(Date arg0) throws Exception{
		return dfs.format(arg0);
	}

	@Override
	public Date unmarshal(String arg0) throws Exception {
		return dfs.parse(arg0);
	}

}
