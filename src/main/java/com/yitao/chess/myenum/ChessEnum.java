package com.yitao.chess.myenum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 棋子名称，帅*1 仕相车炮马*2 兵*5
 * creat_user: qinyitao
 * creat_date: 18/3/16 16:08
 **/
@ToString
@Getter
@AllArgsConstructor
public enum ChessEnum {
    SHUAI("帅",1),SHI("仕",2),XIANG("相",3),CHE("车",4),PAO("炮",5),MA("马",6),BING("兵",7);
    private String name;
    private int level;
}
