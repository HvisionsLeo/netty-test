package com.unitill.ibatis.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author springboot
 * @since 2018-04-13
 */
@TableName("posp_mer_fee")
public class PospMerFee extends Model<PospMerFee> {

    private static final long serialVersionUID = 1L;

    /**
     * 商户费率
     */
	private String id;
    /**
     * 代码编号
     */
	@TableField("fee_no")
	private String feeNo;
    /**
     * 代码名称
     */
	@TableField("fee_name")
	private String feeName;
    /**
     * 收费方式：1实施单笔0不收取
     */
	@TableField("fee_type")
	private String feeType;
    /**
     * 费用计算依据：1笔数2金额
     */
	@TableField("fee_basis")
	private String feeBasis;
    /**
     * 计费方法：1按固定金额收取2按金额的固定百分比收取
     */
	@TableField("fee_method")
	private String feeMethod;
	@TableField("create_time")
	private Date createTime;
	@TableField("modify_time")
	private Date modifyTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFeeNo() {
		return feeNo;
	}

	public void setFeeNo(String feeNo) {
		this.feeNo = feeNo;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFeeBasis() {
		return feeBasis;
	}

	public void setFeeBasis(String feeBasis) {
		this.feeBasis = feeBasis;
	}

	public String getFeeMethod() {
		return feeMethod;
	}

	public void setFeeMethod(String feeMethod) {
		this.feeMethod = feeMethod;
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
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "PospMerFee{" +
			", id=" + id +
			", feeNo=" + feeNo +
			", feeName=" + feeName +
			", feeType=" + feeType +
			", feeBasis=" + feeBasis +
			", feeMethod=" + feeMethod +
			", createTime=" + createTime +
			", modifyTime=" + modifyTime +
			"}";
	}
}
