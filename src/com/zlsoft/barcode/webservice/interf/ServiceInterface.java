package com.zlsoft.barcode.webservice.interf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface ServiceInterface {
	@WebMethod
	String YYGH3012(@WebParam(name="xmlIn") String xmlIn);
}
