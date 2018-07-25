package com.unitill.ibatis.dao;


import com.unitill.ibatis.pojo.PospMerTradeRecord;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Leo
 * @since 2018-04-26
 */
public interface PospMerTradeRecordMapper {

    int insert(PospMerTradeRecord pospMerTradeRecord);

    int update(PospMerTradeRecord pospMerTradeRecord);
}
