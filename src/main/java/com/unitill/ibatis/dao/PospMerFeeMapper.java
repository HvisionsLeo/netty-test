package com.unitill.ibatis.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.unitill.ibatis.pojo.PospMerFee;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author springboot
 * @since 2018-03-09
 */
@Service("pospMerFeeMapper")
public interface PospMerFeeMapper extends BaseMapper<PospMerFee> {

	PospMerFee selectByMerId(String merId);

	PospMerFee selectByMerNo(String merNo);

}
