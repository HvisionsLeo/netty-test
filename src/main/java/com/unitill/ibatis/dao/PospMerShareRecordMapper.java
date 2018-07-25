package com.unitill.ibatis.dao;

import com.unitill.ibatis.pojo.PospMerShareRecord; /**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Leo
 * @since 2018-04-28
 */
public interface PospMerShareRecordMapper{

    int insert(PospMerShareRecord pospMerShareRecord);

    int update(PospMerShareRecord pospMerShareRecord);
}
