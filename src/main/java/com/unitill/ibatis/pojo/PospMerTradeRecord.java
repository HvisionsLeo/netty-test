package com.unitill.ibatis.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Leo
 * @since 2018-04-26
 */
public class PospMerTradeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String merId;
    /**
     * 终端号
     */
    private String terminalId;
    /**
     * 商户订单号
     */
    private String merOrderId;
    /**
     * 银行订单号 或 银行流水号
     */
    private String bankOrderId;
    /**
     * 银行卡号
     */
    private String bankCardNo;
    /**
     * 发卡行
     */
    private String bankName;
    /**
     * 持卡人
     */
    private String name;
    /**
     * 交易渠道编码
     */
    private String channelCode;
    /**
     * 手续费表达式, S_money=单笔_价格, P_ratio_low_top=百分比收费_系数_下限_上限,B_[low,top)#S or P reg@[low,top)#S or P reg...
     */
    private String feeReg;
    /**
     * 交易金额
     */
    private BigDecimal money;
    private BigDecimal feeMoney;
    private String feeContent;
    /**
     * 银行手续费
     */
    private BigDecimal bankFeeMoney;
    /**
     * -1=失败,0=成功,1=进行中
     */
    private String orderStatus;
    /**
     * 摘要, 对应出款网关的remark
     */
    private String summary;
    /**
     * 备注
     */
    private String remark;
    /**
     * 终端支付方式：1:微信，2:支付宝，3:银联
     */
    private String paymentType;
    private String retCode;
    private String retData;
    private Date createTime;
    private Date modifyTime;

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId;
    }

    public String getBankOrderId() {
        return bankOrderId;
    }

    public void setBankOrderId(String bankOrderId) {
        this.bankOrderId = bankOrderId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getFeeReg() {
        return feeReg;
    }

    public void setFeeReg(String feeReg) {
        this.feeReg = feeReg;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getFeeMoney() {
        return feeMoney;
    }

    public void setFeeMoney(BigDecimal feeMoney) {
        this.feeMoney = feeMoney;
    }

    public String getFeeContent() {
        return feeContent;
    }

    public void setFeeContent(String feeContent) {
        this.feeContent = feeContent;
    }

    public BigDecimal getBankFeeMoney() {
        return bankFeeMoney;
    }

    public void setBankFeeMoney(BigDecimal bankFeeMoney) {
        this.bankFeeMoney = bankFeeMoney;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetData() {
        return retData;
    }

    public void setRetData(String retData) {
        this.retData = retData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "PospMerTradeRecord{" +
        ", id=" + id +
        ", merId=" + merId +
        ", terminalId=" + terminalId +
        ", merOrderId=" + merOrderId +
        ", bankOrderId=" + bankOrderId +
        ", channelCode=" + channelCode +
        ", feeReg=" + feeReg +
        ", money=" + money +
        ", feeMoney=" + feeMoney +
        ", feeContent=" + feeContent +
        ", bankFeeMoney=" + bankFeeMoney +
        ", orderStatus=" + orderStatus +
        ", summary=" + summary +
        ", remark=" + remark +
        ", paymentType=" + paymentType +
        ", retCode=" + retCode +
        ", retData=" + retData +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
