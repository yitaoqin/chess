package com.yitao.chess.bean;

import lombok.Data;

/**
 * 棋子
 * creat_user: qinyitao
 * creat_date: 18/3/16 14:59
 **/
@Data
public class ChessPieces {
    //ID、颜色、名称、等级、是否选中、是否翻开
    private String Id;
    private ColorEnum colorEnum;
    private ChessEnum chess;

    //true 选中，false 未选中
    private boolean checked;
    //true 正面，false 背面
    private boolean over;


}
