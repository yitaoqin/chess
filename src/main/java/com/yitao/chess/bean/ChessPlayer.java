package com.yitao.chess.bean;

import lombok.Data;

import java.util.List;

/**
 * 棋手
 * creat_user: qinyitao
 * creat_date: 18/3/16 15:00
 **/
@Data
public class ChessPlayer {

    //ID、是否可走（走棋方式待定？）、持子颜色、持有棋子列表
    private String Id;
    //持子颜色
    private ColorEnum colorEnum;
    // 持有棋子列表
    private List<ChessPieces> chessPiecesList;

    /**
     * 棋手走子，由currentIndex走到targetIndex，选择的棋子来决定怎么走，targetIndex上有无棋子，共同决定走子规则。
     * param: currentIndex 当前棋子所在位置
     * param: pieces 要走的棋子
     * param: targetIndex 棋盘上的位置
     * creat_user: qinyitao
     * creat_date: 18/3/16 16:49
     **/
    public void qiShouZouZi(int currentIndex,ChessPieces pieces,int targetIndex){

    }
}
