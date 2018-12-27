package com.zlsoft.barcode.payment.servletpojos;

public class PayList {
	private String partner_id;
	private String out_trade_no;
	private String service_item;
	private String terminal_id;
	private String trade_description;

	private String trade_fee;
	private String open_id;
	private String service_provider;
	private String dept_name;
	private String doctor_name;
	
	public PayList() {
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getService_item() {
		return service_item;
	}

	public void setService_item(String service_item) {
		this.service_item = service_item;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public String getTrade_description() {
		return trade_description;
	}

	public void setTrade_description(String trade_description) {
		this.trade_description = trade_description;
	}

	public String getTrade_fee() {
		return trade_fee;
	}

	public void setTrade_fee(String trade_fee) {
		this.trade_fee = trade_fee;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getService_provider() {
		return service_provider;
	}

	public void setService_provider(String service_provider) {
		this.service_provider = service_provider;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	@Override
	public String toString() {
		return "PayList [partner_id=" + partner_id + ", out_trade_no=" + out_trade_no + ", service_item=" + service_item
				+ ", terminal_id=" + terminal_id + ", trade_description=" + trade_description + ", trade_fee="
				+ trade_fee + ", open_id=" + open_id + ", service_provider=" + service_provider + ", dept_name="
				+ dept_name + ", doctor_name=" + doctor_name + "]";
	}

}
