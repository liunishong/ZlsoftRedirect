package com.zlsoft.barcode.payment.servletpojos;

public class BackList {
	private String partner_id;
	private String out_trade_no;
	private String trade_no;
	private String refund_fee;
	private String delay_refund;
	private String out_refund_no;
	private String his_op_code;
	
	public BackList() {
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

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getDelay_refund() {
		return delay_refund;
	}

	public void setDelay_refund(String delay_refund) {
		this.delay_refund = delay_refund;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getHis_op_code() {
		return his_op_code;
	}

	public void setHis_op_code(String his_op_code) {
		this.his_op_code = his_op_code;
	}

	@Override
	public String toString() {
		return "BackList [partner_id=" + partner_id + ", out_trade_no=" + out_trade_no + ", trade_no=" + trade_no
				+ ", refund_fee=" + refund_fee + ", delay_refund=" + delay_refund + ", out_refund_no=" + out_refund_no
				+ ", his_op_code=" + his_op_code + "]";
	}

}
