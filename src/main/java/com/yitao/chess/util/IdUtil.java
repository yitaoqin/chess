package com.yitao.chess.util;

import com.yitao.chess.bean.ChessEnum;
import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.bean.ColorEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IdUtil {
    public static String id(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 生成一副琪
     * @param colorEnum 颜色
     * @return
     */
    public static List<ChessPieces> pairChess(ColorEnum colorEnum){
        List<ChessPieces> redPiecesList=new ArrayList<>();
        ChessEnum[] values=ChessEnum.values();
        for(ChessEnum v:values){
            switch (v){
                case SHUAI:
                    redPiecesList.add(one(colorEnum,v));
                    break;
                case BING:
                    redPiecesList.add(one(colorEnum,v));
                    redPiecesList.add(one(colorEnum,v));
                    redPiecesList.add(one(colorEnum,v));
                    redPiecesList.add(one(colorEnum,v));
                    redPiecesList.add(one(colorEnum,v));
                    break;
                default:
                    redPiecesList.add(one(colorEnum,v));
                    redPiecesList.add(one(colorEnum,v));
                    break;
            }
        }
        return redPiecesList;
    }

    /**
     * 根据颜色和名字生成一个棋
     * @param colorEnum
     * @param chessEnum
     * @return
     */
    public static ChessPieces one(ColorEnum colorEnum,ChessEnum chessEnum){
        return new ChessPieces(IdUtil.id(),colorEnum,chessEnum,false,false);
    }
}
