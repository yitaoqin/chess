package com.yitao.chess.util;

import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.Chessboard;
import com.yitao.chess.exception.RuleException;

import java.util.List;

import static com.yitao.chess.myenum.ChessEnum.BING;
import static com.yitao.chess.myenum.ChessEnum.SHUAI;

/**
 * 吃子
 * 测试：翻开的、翻开的、相邻、对方的、大吃小；
 * 特殊情况：兵吃帅，帅不可以吃兵；炮隔子吃未翻的，隔子吃已翻的对方的小的；
 */
public class ChiziRule implements Rule {
    @Override
    public void action(Chessboard chessboard, int index, int targetIndex) {
        ChessPieces pieces=chessboard.getBoard()[index];
        ChessPieces targetPieces=chessboard.getBoard()[targetIndex];
        IdUtil.checkYidong(pieces,targetPieces,index,targetIndex);
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
                    if(targetPieces.isOver())
                        chizi(chessboard, index, targetIndex);
                    else{
                        chessboard.getBoard()[targetIndex]=pieces;
                        chessboard.getBoard()[index]=null;
                    }

                }else {
                    throw new RuleException("只可以隔一个子吃");
                }
                break;
            case SHUAI:
                if(targetPieces.getChess()==BING){
                    throw new RuleException("帅不可以吃兵");
                }
                chizi(chessboard, index, targetIndex);
                break;
            default:
                chizi(chessboard, index, targetIndex);
                break;
        }
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
}
