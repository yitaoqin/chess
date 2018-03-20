package com.yitao.chess.play;

import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.ChessPlayer;
import com.yitao.chess.bean.Chessboard;
import com.yitao.chess.myenum.ColorEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.yitao.chess.myenum.ColorEnum.BLACK;
import static com.yitao.chess.myenum.ColorEnum.RED;

/**
 * 光头强和熊二下象棋
 */
@Slf4j
public class Game {
    private ConcurrentHashMap<String, Chessboard> chessboardMap = new ConcurrentHashMap<>();
    boolean flag = false;
    ColorEnum xe;
    ColorEnum gt;

    /**
     * 初始化棋局
     */
    public void kaiJu(String xionger, String guangtouqiang) {
        int row = 4, col = 8;
        ChessPlayer redPlayer = new ChessPlayer(RED);
        ChessPlayer blackPlayer = new ChessPlayer(BLACK);
        log.info("红方详情:{}", redPlayer);
        log.info("黑方详情:{}", blackPlayer);

        Chessboard chessboard = chessboardMap.get(xionger + guangtouqiang);
        if (chessboard == null) {
            //这是棋盘
            chessboard = new Chessboard(row, col);
            //合并双方棋子
            List<ChessPieces> chessPiecesList = redPlayer.getChessPiecesList();
            chessPiecesList.addAll(blackPlayer.getChessPiecesList());
            //log.info("初始棋局对弈:{}", chessPiecesList);
            //随机分布
            Collections.shuffle(chessPiecesList);
            ChessPieces[] initPieces = chessPiecesList.toArray(new ChessPieces[chessPiecesList.size()]);
            chessboard.setBoard(initPieces);
            log.info("最终棋局对弈:{}", chessboard);
            chessboardMap.put(xionger + guangtouqiang, chessboard);
        }
    }


    public static void main(String[] args) {
        String xionger = "xionger";
        String guangtouqiang = "guangtouqiang";

        Game g = new Game();
        g.kaiJu(xionger, guangtouqiang);

        //交替执行参考 http://blog.csdn.net/woainiwss/article/details/52013810
        PlayerXionger c = new PlayerXionger(g, xionger, xionger + guangtouqiang, g.chessboardMap);
        PlayerGuang s = new PlayerGuang(g, guangtouqiang, xionger + guangtouqiang, g.chessboardMap);

        ExecutorService d = Executors.newCachedThreadPool();
        d.submit(c);
        d.submit(s);

    }
}
