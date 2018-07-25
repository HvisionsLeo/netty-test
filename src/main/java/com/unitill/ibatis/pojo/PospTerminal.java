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
 * @since 2018-06-04
 */
public class PospTerminal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 终端输入库日志id
     */
    private String terminalJournal;
    /**
     * 商户id
     */
    private String merId;
    /**
     * 商户名称
     */
    private String merName;
    /**
     * 商户编号
     */
    private String merNum;
    /**
     * 厂商id
     */
    private String firmId;
    /**
     * 厂商编号
     */
    private String firmNum;
    /**
     * 厂商名称
     */
    private String firmName;
    /**
     * 终端号
     */
    private String terminalSerialNum;
    /**
     * 终端现在所处的状态(0:正常,1:已报修,2:已报废)
     */
    private String terminalState;
    /**
     * 终端型号(型号名称)
     */
    private String modelType;
    /**
     * 序列号
     */
    private String modelNum;
    /**
     * 设备状态
     */
    private String modelState;
    /**
     * 产权属性(0:自备,1:厂商)
     */
    private String terminalProperty;
    /**
     * 出库时间
     */
    private Date outTime;
    /**
     * 入款时间
     */
    private Date storageTime;
    /**
     * 入库来源
     */
    private String storageSource;
    /**
     * 入库批次号
     */
    private String sourceNum;
    /**
     * 合同编号
     */
    private String contractNum;
    /**
     * 采购价格
     */
    private BigDecimal terminalPrice;
    /**
     * 终端数目
     */
    private String terminalNum;
    private Date terminalCreatTime;
    private Date terminalModifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerminalJournal() {
        return terminalJournal;
    }

    public void setTerminalJournal(String terminalJournal) {
        this.terminalJournal = terminalJournal;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMerNum() {
        return merNum;
    }

    public void setMerNum(String merNum) {
        this.merNum = merNum;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getFirmNum() {
        return firmNum;
    }

    public void setFirmNum(String firmNum) {
        this.firmNum = firmNum;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getTerminalSerialNum() {
        return terminalSerialNum;
    }

    public void setTerminalSerialNum(String terminalSerialNum) {
        this.terminalSerialNum = terminalSerialNum;
    }

    public String getTerminalState() {
        return terminalState;
    }

    public void setTerminalState(String terminalState) {
        this.terminalState = terminalState;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getModelNum() {
        return modelNum;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    public String getModelState() {
        return modelState;
    }

    public void setModelState(String modelState) {
        this.modelState = modelState;
    }

    public String getTerminalProperty() {
        return terminalProperty;
    }

    public void setTerminalProperty(String terminalProperty) {
        this.terminalProperty = terminalProperty;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public String getStorageSource() {
        return storageSource;
    }

    public void setStorageSource(String storageSource) {
        this.storageSource = storageSource;
    }

    public String getSourceNum() {
        return sourceNum;
    }

    public void setSourceNum(String sourceNum) {
        this.sourceNum = sourceNum;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public BigDecimal getTerminalPrice() {
        return terminalPrice;
    }

    public void setTerminalPrice(BigDecimal terminalPrice) {
        this.terminalPrice = terminalPrice;
    }

    public String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum;
    }

    public Date getTerminalCreatTime() {
        return terminalCreatTime;
    }

    public void setTerminalCreatTime(Date terminalCreatTime) {
        this.terminalCreatTime = terminalCreatTime;
    }

    public Date getTerminalModifyTime() {
        return terminalModifyTime;
    }

    public void setTerminalModifyTime(Date terminalModifyTime) {
        this.terminalModifyTime = terminalModifyTime;
    }

    @Override
    public String toString() {
        return "PospTerminal{" +
        ", id=" + id +
        ", terminalJournal=" + terminalJournal +
        ", merId=" + merId +
        ", merName=" + merName +
        ", merNum=" + merNum +
        ", firmId=" + firmId +
        ", firmNum=" + firmNum +
        ", firmName=" + firmName +
        ", terminalSerialNum=" + terminalSerialNum +
        ", terminalState=" + terminalState +
        ", modelType=" + modelType +
        ", modelNum=" + modelNum +
        ", modelState=" + modelState +
        ", terminalProperty=" + terminalProperty +
        ", outTime=" + outTime +
        ", storageTime=" + storageTime +
        ", storageSource=" + storageSource +
        ", sourceNum=" + sourceNum +
        ", contractNum=" + contractNum +
        ", terminalPrice=" + terminalPrice +
        ", terminalNum=" + terminalNum +
        ", terminalCreatTime=" + terminalCreatTime +
        ", terminalModifyTime=" + terminalModifyTime +
        "}";
    }
}
