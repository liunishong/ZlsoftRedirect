package com.zlsoft.barcode.webservice.classes;

public class ServiceMsg {

	public ServiceMsg() {
	}
	
	public static String getSysErrMsg(String errMsg){
		String errmsg =
			"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
			"<root>\n" + 
			"	<head>\n" + 
			"		<ErrCode>-1</ErrCode>\n" + 
			"		<ErrMsg>"+ errMsg +"</ErrMsg>\n" + 
			"	</head>\n" + 
			"	<body>\n" + 
			"	</body>\n" + 
			"</root>";
		return  errmsg;
	}
}
