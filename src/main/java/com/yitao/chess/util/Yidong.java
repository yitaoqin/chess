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
 * 移动棋子：包含 移动、兑子、吃子
 */
public class Yidong implements Rule {
    @Override
    public void action(Chessboard chessboard, int index, int targetIndex) {
        IdUtil.chessboard(index, targetIndex);
        ChessPieces pieces=chessboard.getBoard()[index];
        ChessPieces targetPieces=chessboard.getBoard()[targetIndex];
        //移动
        if(targetPieces == null){
            chessboard.getBoard()[targetIndex]=pieces;
            chessboard.getBoard()[index]=null;
            return;
        }
        //除了炮以外，其它棋子均需在相邻位置操作,不可以自相残杀,只可以吃已经翻开的子
        if(pieces.getChess()!=PAO ){
            around(index,targetIndex);
            if(pieces.getColorEnum() == targetPieces.getColorEnum())
                throw new RuleException("不可以自相残杀");
            if(!targetPieces.isOver())
                throw new RuleException("只可以吃已经翻开的子");
        }

        //兑子
        if(pieces.getChess()==targetPieces.getChess() ){
            chessboard.getBoard()[targetIndex]=null;
            chessboard.getBoard()[index]=null;
            return;
        }

        //吃子
        switch (pieces.getChess()){
            case BING:
                if(targetPieces.getChess()== SHUAI ){
                    chessboard.getBoard()[targetIndex]=pieces;
                    chessboard.getBoard()[index]=null;
                }else {
                    throw new RuleException("小鱼不可以吃大鱼");
                }
                break;
            case PAO:
                List<Integer> list=IdUtil.jumpPao(index, targetIndex);
                long count=list.stream().filter(integer -> chessboard.getBoard()[integer] != null).count();
                if(count == 1){
                    chizi(chessboard, index, targetIndex);
                }else {
                    throw new RuleException("只可以隔一个子吃");
                }
                break;
            case SHUAI:
                if(targetPieces.getChess()==BING){
                    throw new RuleException("帅不可以吃兵");
                }
                break;
            default:
                chizi(chessboard, index, targetIndex);
                break;
        }
        //TODO 检查是否胜利或者和
    }

    private void chizi(Chessboard chessboard,int index,int targetIndex){
        ChessPieces pieces=chessboard.getBoard()[index];
        ChessPieces targetPieces=chessboard.getBoard()[targetIndex];
        if(pieces.getChess().getLevel() < targetPieces.getChess().getLevel()){
            chessboard.getBoard()[targetIndex]=pieces;
            chessboard.getBoard()[index]=null;
        }else {
            throw new RuleException("小鱼不可以吃大鱼");
        }
    }

    private void around(int index,int target){
        if(!IdUtil.aroundIndex(index).contains(target)){
            throw new RuleException("不是相邻位置");
        }
    }

}
