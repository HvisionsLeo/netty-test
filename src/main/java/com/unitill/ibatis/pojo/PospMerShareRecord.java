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
 * @since 2018-04-28
 */
public class PospMerShareRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户交易流水表
     */
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
     * 交易渠道编码
     */
    private String channelCode;
    /**
     * 是否已分润
     */
    private BigDecimal shareIs;
    /**
     * 交易金额
     */
    private BigDecimal money;
    /**
     * posp平台收取手续费
     */
    private BigDecimal feeMoney;
    /**
     * 银行手续费
     */
    private BigDecimal bankFeeMoney;
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
    private Date createTime;
    private Date modifyTime;


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

    public BigDecimal getShareIs() {
        return shareIs;
    }

    public void setShareIs(BigDecimal shareIs) {
        this.shareIs = shareIs;
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

    public BigDecimal getBankFeeMoney() {
        return bankFeeMoney;
    }

    public void setBankFeeMoney(BigDecimal bankFeeMoney) {
        this.bankFeeMoney = bankFeeMoney;
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
        return "PospMerShareRecord{" +
        ", id=" + id +
        ", merId=" + merId +
        ", terminalId=" + terminalId +
        ", merOrderId=" + merOrderId +
        ", bankOrderId=" + bankOrderId +
        ", channelCode=" + channelCode +
        ", shareIs=" + shareIs +
        ", money=" + money +
        ", feeMoney=" + feeMoney +
        ", bankFeeMoney=" + bankFeeMoney +
        ", summary=" + summary +
        ", remark=" + remark +
        ", paymentType=" + paymentType +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
