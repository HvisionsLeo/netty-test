package com.unitill.service;


import com.unitill.ibatis.pojo.PospTerminal;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leo
 * @since 2018-06-04
 */
public interface PospTerminalService {
    List<PospTerminal> findTerByMerId(String merId, String terminalId);
}
