package com.yitao.chess.play;

import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.ChessPlayer;
import com.yitao.chess.bean.Chessboard;
import com.yitao.chess.exception.RuleException;
import com.yitao.chess.myenum.ColorEnum;
import com.yitao.chess.util.RuleExecutor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
public class Game2 {
    private ConcurrentHashMap<String, Chessboard> chessboardMap = new ConcurrentHashMap<>();
    boolean flag = false;
    private ColorEnum xe;
    private ColorEnum gt;

    private Chessboard chessboard ;
    private static final String NON_OVER=".";
    private static final String EMPTY="x";

    /**
     * 初始化棋局
     */
    public void kaiJu() {
        int row = 4, col = 8;
        ChessPlayer redPlayer = new ChessPlayer(RED);
        ChessPlayer blackPlayer = new ChessPlayer(BLACK);
//        log.info("红方详情:{}", redPlayer);
//        log.info("黑方详情:{}", blackPlayer);

//        Chessboard chessboard = chessboardMap.get(xionger + guangtouqiang);
//        if (chessboard == null) {
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
//        log.info("最终棋局对弈:{}", chessboard);
//            chessboardMap.put(xionger + guangtouqiang, chessboard);
//        }
    }

    public void display(){
        ChessPieces[] initPieces =chessboard.getBoard();

//        System.out.println(Arrays.toString(initPieces));
        for (int i = 0; i < initPieces.length; i++) {
            ChessPieces p=initPieces[i];
            if(p==null){
                System.out.printf("(%2d)%-8s",i,"x");
            }else {
                if(p.isOver())
                    System.out.printf("(%2d)%-8s",i,initPieces[i]);
                else
                    System.out.printf("(%2d)%-8s",i,".");
            }

            if( (i+1)%8==0) {
                System.out.println();
            }
        }
    }



    public static void main(String[] args) {
        //参考 ：https://zhidao.baidu.com/question/711519871387634325.html
        Scanner sc = new Scanner(System.in);
        System.out.println(".没有翻开的；x空的地方;前面的空号中的数字是位置");
        Game2 g = new Game2();
        g.kaiJu();
        g.display();
        int i=0;
        String type;
        int index,targetIndex;
        while (RuleExecutor.win(g.chessboard.getBoard())){
            if(i%2==0){
                if(g.xe==null){
                    System.out.println("您还没有选择颜色，请选择幡子的位置： ");
                    index=sc.nextInt();
                    if(g.chessboard.getBoard()[i].getColorEnum()==ColorEnum.RED){
                        g.xe=ColorEnum.RED;
                        g.gt=ColorEnum.BLACK;
                    }else{
                        g.xe=ColorEnum.BLACK;
                        g.gt=ColorEnum.RED;
                    }
                    RuleExecutor.action(g.chessboard, index, index, g.xe,"f");
                    System.out.println("xe="+ g.xe+";g.gt="+g.gt);
                }else{
                    boolean d = true;
                    while (d) {
                        System.out.println(g.xe+"xe选择走棋方式 :c-吃子，f-翻子，d-兑子，y-移动 ");
                        type=sc.next();
                        System.out.println("选择棋子，输入数字 : ");
                        index=sc.nextInt();
                        if("f".equals(type)){
                            targetIndex=index;
                        }else {
                            System.out.println("目标位置，输入数字 : ");
                            targetIndex=sc.nextInt();
                        }
                        d = g.loop(g.chessboard,index,targetIndex, g.xe,type);
                    }
                }
                g.display();
            }else {
                if(g.gt==null){
                    System.out.println("您还没有选择颜色，请选择幡子的位置： ");
                    index=sc.nextInt();
                    if(g.chessboard.getBoard()[i].getColorEnum()==ColorEnum.RED){
                        g.xe=ColorEnum.BLACK;
                        g.gt=ColorEnum.RED;
                    }else{
                        g.xe=ColorEnum.RED;
                        g.gt=ColorEnum.BLACK;

                    }
                    RuleExecutor.action(g.chessboard, index, index, g.gt,"f");
                }else{
                    boolean d = true;
                    while (d) {
                        System.out.println(g.gt+"gt选择走棋方式 :c-吃子，f-翻子，d-兑子，y-移动 ");
                        type=sc.next();
                        System.out.println("选择棋子，输入数字 : ");
                        index=sc.nextInt();
                        if("f".equals(type)){
                            targetIndex=index;
                        }else {
                            System.out.println("目标位置，输入数字 : ");
                            targetIndex=sc.nextInt();
                        }
                        d = g.loop(g.chessboard,index,targetIndex, g.gt,type);
                    }
                }
                g.display();
            }
            i++;
        }
        System.out.println("结局：");
        g.display();
    }

    private boolean loop(Chessboard cs,int i,int t, ColorEnum c,String type) {
        try {
            RuleExecutor.action(cs,i,t , c,type);
        } catch (RuleException e) {
            System.out.println(e.getMessage()+",重新操作");
            return true;
        }
        return false;
    }
}
