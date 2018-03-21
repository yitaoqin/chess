package com.yitao.chess.bean;

import com.yitao.chess.myenum.ChessEnum;
import com.yitao.chess.myenum.ColorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 棋子
 * creat_user: qinyitao
 * creat_date: 18/3/16 14:59
 **/
@Getter
@AllArgsConstructor
public class ChessPieces {
    //ID、颜色、名称、等级、是否选中、是否翻开
    private String Id;
    private ColorEnum colorEnum;
    private ChessEnum chess;


    //true 正面，false 背面
    @Setter
    private boolean over;

    @Override
    public String toString() {
        return colorEnum.name()+""+chess.getName();
    }
}
