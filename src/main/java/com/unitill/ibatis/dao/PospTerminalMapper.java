package com.unitill.ibatis.dao;

import com.unitill.ibatis.pojo.PospTerminal;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Leo
 * @since 2018-06-04
 */
public interface PospTerminalMapper {

    List<PospTerminal> selectForList(PospTerminal terminal);
}
