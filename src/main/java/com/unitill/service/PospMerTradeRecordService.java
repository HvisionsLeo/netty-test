package com.unitill.service;

import com.unitill.ibatis.pojo.PospMerTradeRecord; /**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leo
 * @since 2018-04-26
 */
public interface PospMerTradeRecordService {

    int insert(PospMerTradeRecord pospMerTradeRecord);

    int update(PospMerTradeRecord pospMerTradeRecord);
}
