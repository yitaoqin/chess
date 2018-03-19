package com.yitao.chess.bean;

import com.yitao.chess.util.FanziRule;
import com.yitao.chess.util.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static com.yitao.chess.myenum.ColorEnum.BLACK;
import static com.yitao.chess.myenum.ColorEnum.RED;

/**
 * 光头强和熊二下象棋
 */
@Slf4j
public class Game {

    String xionger="xionger";
    String guangtouqiang="guangtouqiang";
    /**
     * 初始化棋局
     */
    public void kaiJu(){
        int row=4,col=8;
        ChessPlayer redPlayer=new ChessPlayer(RED);
        ChessPlayer blackPlayer=new ChessPlayer(BLACK);
        log.info("红方详情:{}",redPlayer);
        log.info("黑方详情:{}",blackPlayer);

        //这是棋盘
        Chessboard chessboard=new Chessboard(row,col);
        //合并双方棋子
        List<ChessPieces> chessPiecesList=redPlayer.getChessPiecesList();
        chessPiecesList.addAll(blackPlayer.getChessPiecesList());
        //log.info("初始棋局对弈:{}", chessPiecesList);
        //随机分布
        Collections.shuffle(chessPiecesList);
        ChessPieces[] initPieces=chessPiecesList.toArray(new ChessPieces[chessPiecesList.size()]);
        chessboard.setBoard(initPieces);
        log.info("最终棋局对弈:{}",chessboard);

        Rule  rule=new FanziRule();
        rule.action(chessboard,4,4);
        log.info("翻子:{}",chessboard);
    }


    public static void main(String[] args) {
        new Game().kaiJu();
    }
}
