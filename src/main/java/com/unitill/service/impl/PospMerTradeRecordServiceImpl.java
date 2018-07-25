package com.unitill.service.impl;

import com.unitill.ibatis.dao.PospMerTradeRecordMapper;
import com.unitill.ibatis.pojo.PospMerTradeRecord;
import com.unitill.service.PospMerTradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leo
 * @since 2018-04-26
 */
@Service("pospMerTradeRecordService")
public class PospMerTradeRecordServiceImpl implements PospMerTradeRecordService {

    @Autowired
    private PospMerTradeRecordMapper pospMerTradeRecordMapper;

    @Override
    public int insert(PospMerTradeRecord pospMerTradeRecord) {
        return pospMerTradeRecordMapper.insert(pospMerTradeRecord);
    }

    @Override
    public int update(PospMerTradeRecord pospMerTradeRecord) {
        return pospMerTradeRecordMapper.update(pospMerTradeRecord);
    }
}
