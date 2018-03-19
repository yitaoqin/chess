package com.yitao.chess.bean;

import com.yitao.chess.exception.ChessException;
import lombok.Data;

/**
 * 棋盘 4*8 例
 *  00 01 02 03 04 05 06 07  - 0行
 *  08 09 10 11 12 13 14 15  - 1行
 *  16 17 18 19 20 21 22 23  - 2行
 *  24 25 26 27 28 29 30 31  - 3行
 *
 * creat_user: qinyitao
 * creat_date: 18/3/16 15:00
 **/
@Data
public class Chessboard {
//i.	棋盘格局行、棋盘格局列、 棋子布局数组[]
    private int column;//4列
    private int row;
    private ChessPieces[] board;

    public Chessboard(int row,int column){
        this.column=column;
        this.row=row;
    }

    public void setBoard(ChessPieces[] board)throws ChessException {
        if(board.length != row*column)
            throw new ChessException("棋盘异常");
        this.board = board;
    }
}
