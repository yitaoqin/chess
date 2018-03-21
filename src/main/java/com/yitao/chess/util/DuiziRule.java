package com.yitao.chess.util;

import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.Chessboard;
import com.yitao.chess.exception.RuleException;

/**
 * 兑子
 * 测试：翻开的、翻开的、相邻、对方的、相等
 */
public class DuiziRule implements Rule {
    @Override
    public void action(Chessboard chessboard, int index, int targetIndex) {
        ChessPieces pieces=chessboard.getBoard()[index];
        ChessPieces targetPieces=chessboard.getBoard()[targetIndex];
        IdUtil.checkYidong(pieces,targetPieces,index,targetIndex);
        //兑子
        if(pieces.getChess()==targetPieces.getChess() ){
            chessboard.getBoard()[targetIndex]=null;
            chessboard.getBoard()[index]=null;
        }else{
            throw new RuleException("只可以兑相等的棋子");
        }
    }
}
