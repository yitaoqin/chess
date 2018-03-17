package com.yitao.chess.bean;

import com.yitao.chess.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.yitao.chess.bean.ColorEnum.BLACK;
import static com.yitao.chess.bean.ColorEnum.RED;

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
        //棋子随机分布到棋局上
        List<ChessPieces> chessPiecesList=redPlayer.getChessPiecesList();
        chessPiecesList.addAll(blackPlayer.getChessPiecesList());
        ChessPieces[] initPieces=chessPiecesList.toArray(new ChessPieces[chessPiecesList.size()]);
        log.info("初始棋局对弈:{}", Arrays.toString(initPieces));
        ChessPieces tempPieces;
//参考《Java常用算法手册》--洗牌
        Random random=new Random();
        for (int i = 0; i < row*col ; i++) {
            int j=random.nextInt(row*col);
            tempPieces=initPieces[j];
            initPieces[j]=initPieces[i];
            initPieces[i]=tempPieces;
        }
        chessboard.setBoard(initPieces);
        log.info("最终棋局对弈:{}",chessboard);
    }


    public static void main(String[] args) {
        new Game().kaiJu();
    }
}
