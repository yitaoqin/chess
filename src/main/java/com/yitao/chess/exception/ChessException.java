package com.yitao.chess.exception;

/**
 * 棋局异常
 */
public class ChessException extends RuntimeException {
    private String msg;
    public ChessException(){}
    public ChessException(String msg){super(msg);}
}
