package com.yitao.chess.util;

import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.Chessboard;
import com.yitao.chess.exception.RuleException;
import com.yitao.chess.myenum.ChessEnum;

import java.util.Collections;
import java.util.List;

import static com.yitao.chess.myenum.ChessEnum.BING;
import static com.yitao.chess.myenum.ChessEnum.PAO;
import static com.yitao.chess.myenum.ChessEnum.SHUAI;

/**
 * 移动棋子：包含 移动
 * 测试：翻开的、空的位置、相邻
 */
public class Yidong implements Rule {
    @Override
    public void action(Chessboard chessboard, int index, int targetIndex) {
        ChessPieces pieces=chessboard.getBoard()[index];
        ChessPieces targetPieces=chessboard.getBoard()[targetIndex];
        IdUtil.checkPiecess(pieces);
        if(targetPieces!=null)throw new RuleException("只可以移动到空的位置");
        chessboard.getBoard()[targetIndex]=pieces;
        chessboard.getBoard()[index]=null;
    }
}
