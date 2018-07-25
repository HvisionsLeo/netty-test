package com.unitill.util;

import java.util.Calendar;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

public class OrderIdGenUtils {

	/**
	 * 充值订单号生成
	 * 
	 * @return
	 */
	public static String genRechargeOrderId() {
		Calendar cal = Calendar.getInstance();
		String date = DateFormatUtils.format(cal, "yyyyMMddHHmmssSSS");
		String random = RandomStringUtils.randomNumeric(5);
		String orderID = "S" + date + random;
		return orderID;
	}

	/**
	 * 出款订单号生成
	 * 
	 * @return
	 */
	public static String genPaymentOrderId() {
		Calendar cal = Calendar.getInstance();
		String date = DateFormatUtils.format(cal, "yyyyMMddHHmmssSSS");
		String random = RandomStringUtils.randomNumeric(12);
		String orderID = "E" + date + random;
		return orderID;
	}

	/**
	 * 退款/退汇订单号生成
	 * 
	 * @return
	 */
	public static String genChargebackOrderId() {
		Calendar cal = Calendar.getInstance();
		String date = DateFormatUtils.format(cal, "yyyyMMddHHmmssSSS");
		String random = RandomStringUtils.randomNumeric(5);
		String orderID = "T" + date + random;
		return orderID;
	}

	/**
	 * 手动调账订单号生成
	 * 
	 * @return
	 */
	public static String genManualRegulationOrderId() {
		Calendar cal = Calendar.getInstance();
		String date = DateFormatUtils.format(cal, "yyyyMMddHHmmssSSS");
		String random = RandomStringUtils.randomNumeric(5);
		String orderID = "C" + date + random;
		return orderID;
	}

	/**
	 * 自动调账订单号生成
	 * 
	 * @return
	 */
	public static String genAutoRegulationOrderId() {
		Calendar cal = Calendar.getInstance();
		String date = DateFormatUtils.format(cal, "yyyyMMddHHmmssSSS");
		String random = RandomStringUtils.randomNumeric(5);
		String orderID = "U" + date + random;
		return orderID;
	}
	
	/**
	 * pay_mer_payment_record表中订单号规则
	 * 
	 * @return
	 */
	public static String genOutPayment(){
		Calendar cal = Calendar.getInstance();
		String date = DateFormatUtils.format(cal, "yyyyMMddHHmmssSSS");
		String random = RandomStringUtils.randomNumeric(5);
		String orderID = "YJ" + date + random;
		return orderID;
	}

}
