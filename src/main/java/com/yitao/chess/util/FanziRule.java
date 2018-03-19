package com.yitao.chess.util;

import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.Chessboard;
import com.yitao.chess.exception.RuleException;

/**
 * 翻子
 */
public class FanziRule implements Rule {
    @Override
    public void action(Chessboard chessboard, int index, int targetIndex) {
        IdUtil.chessboard(index, targetIndex);
        if(index != targetIndex)throw new RuleException("请在同一位置上翻子。");
        ChessPieces pieces=chessboard.getBoard()[index];
        if(pieces.isOver())throw new RuleException("子已翻，选择其它走棋方式");
        pieces.setOver(true);


    }
}
