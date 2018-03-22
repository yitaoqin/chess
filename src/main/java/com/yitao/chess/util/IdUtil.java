package com.yitao.chess.util;

import com.yitao.chess.bean.Chessboard;
import com.yitao.chess.exception.ChessException;
import com.yitao.chess.exception.RuleException;
import com.yitao.chess.myenum.ChessEnum;
import com.yitao.chess.bean.ChessPieces;
import com.yitao.chess.myenum.ColorEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.yitao.chess.myenum.ChessEnum.PAO;

public class IdUtil {



    public static String id(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 生成一方的一副琪
     * @param colorEnum 颜色
     * @return List<ChessPieces> 一方的一副琪
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
     * @return ChessPieces 一个棋
     */
    public static ChessPieces one(ColorEnum colorEnum,ChessEnum chessEnum){
        return new ChessPieces(IdUtil.id(),colorEnum,chessEnum,false);
    }

    /**
     * 判断targetIndex是否在index竖直方向
     * param: index
     * param: targetIndex
     * @return true 是，false 否
     * creat_user: qinyitao
     * creat_date: 18/3/19 11:44
     **/
    public static boolean upOrDown(int index,int targetIndex){
        validIndex(index,targetIndex);
        int mod=index%Const.CHESSBOARD_COLUMN;
        int targetMod=targetIndex%Const.CHESSBOARD_COLUMN;
        return mod==targetMod;
    }
    /**
     * 判断targetIndex是否在index水平方向
     * param: index
     * param: targetIndex
     * @return true 是，false 否
     * creat_user: qinyitao
     * creat_date: 18/3/19 11:44
     **/
    public static boolean leftOrRight(int index,int targetIndex){
        validIndex(index,targetIndex);
        int row=index/Const.CHESSBOARD_COLUMN;
        int targetRow=targetIndex/Const.CHESSBOARD_COLUMN;
        return row==targetRow;
    }

    /**
     * 一条直线
     * @param index
     * @param targetIndex
     * @throws RuleException 不在一条直线
     */
    public static void chessboard(int index,int targetIndex){
        if(!IdUtil.leftOrRight(index,targetIndex) && !IdUtil.upOrDown(index, targetIndex)){
            throw new RuleException("只可竖直/水平直线上移动");
        }
    }


    /**
     * 间隔的位置值
     * @param index
     * @param targetIndex
     * @return List<Integer> 间隔的位置值
     */
    public static List<Integer> jumpPao(int index,int targetIndex){
        List<Integer> list=new ArrayList<>();
        int jump;
        int absJump;
        //水平方向隔子位置
        if(leftOrRight(index,targetIndex)){
            jump=index-targetIndex;//间隔个值
            absJump=Math.abs(jump);
            if(absJump!=1){
                for (int i = 1; i < absJump; i++) {
                    if(jump<0){
                        list.add(index+i);
                    }else{
                        list.add(index-i);
                    }
                }
            }
        }
        //竖直方向隔子位置
        if(upOrDown(index,targetIndex)){
            int rowNum=index/Const.CHESSBOARD_COLUMN;
            int targetRowNum=targetIndex/Const.CHESSBOARD_COLUMN;
            jump=rowNum-targetRowNum;//间隔行
            absJump=Math.abs(jump);
            for (int i = 1; i < absJump; i++) {
                if(jump<0){
                    list.add(index+i*Const.CHESSBOARD_COLUMN);
                }else{
                    list.add(index-i*Const.CHESSBOARD_COLUMN);
                }
            }
        }
        return list;
    }

    /**
     * 当前位置在棋盘内
     * @param index
     * @throws ChessException() 位置越界
     */
    public static void validIndex(int ...index){
        for(int i:index){
            if(i <0 || i>=Const.CHESSBOARD_COLUMN*Const.CHESSBOARD_ROW){
                throw new ChessException("位置越界");
            }
        }
    }
    /**
     * 获取目标点 上 下 左 右 的位置
     *  00 01 02 03 04 05 06 07  - 0行
     *  08 09 10 11 12 13 14 15  - 1行
     *  16 17 18 19 20 21 22 23  - 2行
     *  24 25 26 27 28 29 30 31  - 3行
     * @param index 目标点
     * @return
     */
      public static List<Integer> aroundIndex(int index){
        List<Integer> list=new ArrayList<>();
        int left=index-1;
        int right=index+1;
        //up<0 上边界
        int up=index- Const.CHESSBOARD_COLUMN;
        //down >=32 下边界
        int down=index+Const.CHESSBOARD_COLUMN;
        //mod=0 左边界，mod=7 右边界
        int mod=index%Const.CHESSBOARD_COLUMN;
        if(mod==0){
            list.add(right);
        }else if(mod==Const.CHESSBOARD_COLUMN-1){
            list.add(left);
        }else {
            list.add(left);
            list.add(right);
        }
        if(up>=0){
            list.add(up);
        }
        if(down<Const.CHESSBOARD_COLUMN*Const.CHESSBOARD_ROW){
            list.add(down);
        }
        return list;
    }

    /**
     * 检查棋子翻开的
     * @param pieces
     */
    public static void checkPiecess(ChessPieces pieces){
        if(!pieces.isOver())throw new RuleException("请选择翻开的棋子");

    }

    /**
     * 除了炮以外，其它棋子均需在相邻位置操作,不可以自相残杀,只可以吃已经翻开的子
     * @param pieces
     * @param targetPieces
     * @param index
     * @param targetIndex
     * @throws RuleException 不可以自相残杀 || 只可以吃已经翻开的子
     */
    public static void checkYidong(ChessPieces pieces,ChessPieces targetPieces,int index, int targetIndex){
        checkPiecess(pieces);
        if(pieces.getChess()!=PAO ){
            around(index,targetIndex);
            if(targetPieces ==null)throw new RuleException("请选择目标棋子");
            if(!targetPieces.isOver())
                throw new RuleException("只可以吃已经翻开的子");
            if(pieces.getColorEnum() == targetPieces.getColorEnum())
                throw new RuleException("不可以自相残杀");
        }
    }

    //相邻位置
    public static void around(int index,int target){
        if(!IdUtil.aroundIndex(index).contains(target)){
            throw new RuleException("不是相邻位置");
        }
    }

    public static void main(String[] args) {
        System.out.println(jumpPao(31,7));
    }
}
