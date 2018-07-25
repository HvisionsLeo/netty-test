package com.unitill.service.impl;

import com.unitill.ibatis.dao.PospTerminalMapper;
import com.unitill.ibatis.pojo.PospTerminal;
import com.unitill.service.PospTerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leo
 * @since 2018-06-04
 */
@Service("pospTerminalService")
public class PospTerminalServiceImpl implements PospTerminalService {

    @Autowired
    private PospTerminalMapper pospTerminalMapper;

    @Override
    public List<PospTerminal> findTerByMerId(String merId, String terminalId) {
        PospTerminal terminal = new PospTerminal();
        terminal.setMerId(merId);
        terminal.setTerminalNum(terminalId);
        terminal.setTerminalState("0");
        return pospTerminalMapper.selectForList(terminal);
    }
}
