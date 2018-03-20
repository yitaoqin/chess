package com.yitao.chess.util;


import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.exception.RuleException;

import static com.yitao.chess.myenum.ChessEnum.PAO;

/**
 * 规则
 */
public class BaseRule {

    /**
     * 除了炮以外，其它棋子均需在相邻位置操作,不可以自相残杀,只可以吃已经翻开的子
     * @param pieces
     * @param targetPieces
     * @param index
     * @param targetIndex
     * @throws RuleException 不可以自相残杀 || 只可以吃已经翻开的子
     */
    protected void checkYidong(ChessPieces pieces,ChessPieces targetPieces,int index, int targetIndex){
        if(pieces.getChess()!=PAO ){
            around(index,targetIndex);
            if(pieces.getColorEnum() == targetPieces.getColorEnum())
                throw new RuleException("不可以自相残杀");
            if(!targetPieces.isOver())
                throw new RuleException("只可以吃已经翻开的子");
        }
    }

    //相邻位置
    private void around(int index,int target){
        if(!IdUtil.aroundIndex(index).contains(target)){
            throw new RuleException("不是相邻位置");
        }
    }
}
