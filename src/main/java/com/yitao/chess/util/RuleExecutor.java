package com.yitao.chess.util;

import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.ChessPlayer;
import com.yitao.chess.bean.Chessboard;
import com.yitao.chess.exception.RuleException;
import com.yitao.chess.myenum.ChessEnum;
import com.yitao.chess.myenum.ColorEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Stream;

import static com.yitao.chess.myenum.ColorEnum.BLACK;
import static com.yitao.chess.myenum.ColorEnum.RED;

/**
 * 规则执行者
 */
@Slf4j
public class RuleExecutor {
    /**
     * 执行规则
     *
     * @param chessboard
     * @param index
     * @param targetIndex
     */
    public static void action(Chessboard chessboard, int index, int targetIndex, ColorEnum st,String type) {
        IdUtil.chessboard(index, targetIndex);
        ChessPieces pieces = chessboard.getBoard()[index];
        if (pieces == null) throw new RuleException("请选择一个棋子");
//        ChessPieces targetPieces = chessboard.getBoard()[targetIndex];
        Rule rule;
        switch (type){
            case "f":
                rule = RuleFactory.fanzi();
                break;
            case "d":
                if (st != pieces.getColorEnum()) throw new RuleException("走自己的子");
                rule = RuleFactory.duizi();
                break;
            case "c":
                if (st != pieces.getColorEnum()) throw new RuleException("走自己的子");
                rule = RuleFactory.chizi();
                break;
            case "y":
                if (st != pieces.getColorEnum()) throw new RuleException("走自己的子");
                rule = RuleFactory.yidong();
                break;
            default:
                throw new RuleException("无此操作");
        }
       /* if (index == targetIndex) {//判断翻子
            rule = RuleFactory.fanzi();
        } else if (targetPieces == null) {//判断走子
            if (st != pieces.getColorEnum()) throw new RuleException("走自己的子");
            rule = RuleFactory.yidong();
        } else if (pieces.getChess() == targetPieces.getChess()) {//判断兑子
            if (st != pieces.getColorEnum()) throw new RuleException("走自己的子");
            rule = RuleFactory.duizi();
        } else {//判断吃子
            if (st != pieces.getColorEnum()) throw new RuleException("走自己的子");
            rule = RuleFactory.chizi();
        }*/
        rule.action(chessboard, index, targetIndex);
        /*//判断胜负
        win(chessboard.getBoard());*/
    }

    public static boolean win(ChessPieces[] chessPieces) {
        boolean redWin = Arrays.stream(chessPieces).allMatch(cp -> {
            return cp == null || cp.getColorEnum() == ColorEnum.RED;
        });
        if (redWin) {
            System.out.println("红方胜");
            return false;
        }
        boolean blackWin = Arrays.stream(chessPieces).allMatch(cp -> {
            return cp == null || cp.getColorEnum() == ColorEnum.BLACK;
        });
        if (blackWin) {
            System.out.println("黑方胜");
            return false;
        }

        long redCount = Arrays.stream(chessPieces).filter(cp -> {
            return cp != null && cp.getColorEnum() == ColorEnum.RED;
        }).count();
        long blackCount = Arrays.stream(chessPieces).filter(cp -> {
            return cp != null && cp.getColorEnum() == ColorEnum.BLACK;
        }).count();
        if (redCount == 1 && redCount == blackCount) {
            System.out.println("和局");
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        /* 胜利条件
        ChessPlayer redPlayer=new ChessPlayer(RED);
        List<ChessPieces> chessPiecesList=redPlayer.getChessPiecesList();
        ChessPieces[] chessPieces= chessPiecesList.toArray(new ChessPieces[chessPiecesList.size()]);
        Arrays.fill(chessPieces,null);
        chessPieces[1]=IdUtil.one(ColorEnum.BLACK, ChessEnum.SHUAI);
        chessPieces[0]=IdUtil.one(ColorEnum.RED, ChessEnum.SHUAI);
        chessPieces[2]=IdUtil.one(ColorEnum.RED, ChessEnum.SHUAI);
        win(chessPieces);*/
    }
}
