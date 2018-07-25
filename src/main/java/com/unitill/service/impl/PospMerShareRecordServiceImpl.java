package com.unitill.service.impl;

import com.unitill.ibatis.dao.PospMerShareRecordMapper;
import com.unitill.ibatis.pojo.PospMerShareRecord;
import com.unitill.service.PospMerShareRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leo
 * @since 2018-04-28
 */
@Service("pospMerShareRecordService")
public class PospMerShareRecordServiceImpl implements PospMerShareRecordService {

    @Autowired
    private PospMerShareRecordMapper shareRecordMapper;
    @Override
    public int insert(PospMerShareRecord pospMerShareRecord) {
        return shareRecordMapper.insert(pospMerShareRecord);
    }

    @Override
    public int update(PospMerShareRecord pospMerShareRecord) {
        return shareRecordMapper.update(pospMerShareRecord);
    }
}
