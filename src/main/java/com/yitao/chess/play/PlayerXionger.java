package com.yitao.chess.play;

import com.yitao.chess.bean.Chessboard;
import com.yitao.chess.exception.RuleException;
import com.yitao.chess.myenum.ColorEnum;
import com.yitao.chess.util.FanziRule;
import com.yitao.chess.util.Rule;
import com.yitao.chess.util.RuleExecutor;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 棋手：熊二
 */
public class PlayerXionger implements Runnable {
    private final Game game;
    private final String name;
    private final String duiyishuanfang;
    private ConcurrentHashMap<String, Chessboard> chessboardMap;

    public PlayerXionger(Game g, String name, String duiyishuanfang, ConcurrentHashMap<String, Chessboard> s) {
        game = g;
        chessboardMap = s;
        this.name = name;
        this.duiyishuanfang = duiyishuanfang;
    }

    @Override
    public void run() {
        Chessboard c = chessboardMap.get(duiyishuanfang);
        while (RuleExecutor.win(c.getBoard())) {
            synchronized (game) {
                if (game.flag) {
                    try {
                        game.wait();
                    } catch (Exception e) {
                    }
                } else {
                    System.out.print(name + "执");
                    if(game.xe==null){
                        Rule rule=new FanziRule();
                        Random random=new Random();
                        int i=random.nextInt(31);
                        rule.action(c,i,i);
                        if(c.getBoard()[i].getColorEnum()==ColorEnum.RED){
                            game.xe=ColorEnum.RED;
                            game.gt=ColorEnum.BLACK;
                        }else{
                            game.xe=ColorEnum.BLACK;
                            game.gt=ColorEnum.RED;
                        }
                        System.out.print(game.xe+"|PlayerXionger|"+game.gt);
                    }else {
                        boolean d = true;
                        while (d) {
                            d = dd(c,game.xe);
                        }
                    }
                    System.out.println(game.xe + "方");
                    game.flag = true;
                    game.notifyAll();
                }
            }
        }
    }

    private boolean dd(Chessboard c, ColorEnum f) {
        Random random = new Random();
        try {
            RuleExecutor.action(c, random.nextInt(32), random.nextInt(32), f);
        } catch (RuleException e) {
            return true;
        }
        return false;
    }
}
