package com.yitao.chess.bean;

import com.yitao.chess.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 棋手
 * creat_user: qinyitao
 * creat_date: 18/3/16 15:00
 **/
@Getter
@ToString()
public class ChessPlayer {

    //ID 空字符意思是没有被选中
    private String Id="";
    //持子颜色
    private ColorEnum colorEnum;//持子颜色
    // 持有棋子列表
    private List<ChessPieces> chessPiecesList;

    public ChessPlayer( ColorEnum colorEnum) {
        this.colorEnum = colorEnum;
        chessPiecesList= IdUtil.pairChess(colorEnum);
    }

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
