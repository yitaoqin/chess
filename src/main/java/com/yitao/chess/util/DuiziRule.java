package com.yitao.chess.util;

import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.Chessboard;

/**
 * 兑子
 */
public class DuiziRule extends BaseRule implements Rule {
    @Override
    public void action(Chessboard chessboard, int index, int targetIndex) {
        ChessPieces pieces=chessboard.getBoard()[index];
        ChessPieces targetPieces=chessboard.getBoard()[targetIndex];

        super.checkYidong(pieces,targetPieces,index,targetIndex);
        //兑子
        if(pieces.getChess()==targetPieces.getChess() ){
            chessboard.getBoard()[targetIndex]=null;
            chessboard.getBoard()[index]=null;
        }
    }
}
