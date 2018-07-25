package com.unitill.util;

import com.unitill.constants.Constants;
import com.unitill.ibatis.dao.PospMerFeeMapper;
import com.unitill.ibatis.pojo.PospMerFee;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description: 费率计算
 * @Author: Leo
 * @Date: 2018-05-03 下午 3:49
 */
public class FeeUtils {

    private static PospMerFeeMapper pospMerFeeMapper = (PospMerFeeMapper) Constants.CTX.getBean("pospMerFeeMapper");

    /**
     * 费率计算
     * @param fee 费率对象
     * @param money 交易金额
     * @return 平台手续费
     */
    public static BigDecimal toFee(PospMerFee fee, BigDecimal money) {
        //判断是否收取手续费
        if (StringUtils.equals(fee.getFeeType(), "0")) {
            return BigDecimal.ZERO;
        } else {
            //按照固定金额收取
            if (StringUtils.equals(fee.getFeeMethod(), "1")) {
                return new BigDecimal(fee.getFeeName());
            } else { //按照百分比收取
                return money.multiply(new BigDecimal(fee.getFeeName())).setScale(2, RoundingMode.HALF_UP);
            }
        }
    }
}
