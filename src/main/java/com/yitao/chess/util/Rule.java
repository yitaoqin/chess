package com.yitao.chess.util;

import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.Chessboard;

/**
 * 走棋规则
 * creat_user: qinyitao
 * creat_date: 18/3/16 15:00
 **/
public interface Rule {
    /**
     * 执行规则
     * param: pieces 被选中的棋子
     * param: index 被选中的位置
     * param: targetIndex 移到的位置
     * creat_user: qinyitao
     * creat_date: 18/3/19 9:45
     **/
    void action(Chessboard chessboard, int index, int targetIndex);
}
