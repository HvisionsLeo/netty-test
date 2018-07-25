package com.unitill.service;

import com.unitill.ibatis.pojo.PospMerShareRecord;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leo
 * @since 2018-04-28
 */
public interface PospMerShareRecordService {

    int insert(PospMerShareRecord pospMerShareRecord);

    int update(PospMerShareRecord pospMerShareRecord);
}
