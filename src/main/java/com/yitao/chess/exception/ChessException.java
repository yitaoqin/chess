package com.yitao.chess.exception;

public class ChessException extends RuntimeException {
    private String msg;
    public ChessException(){}
    public ChessException(String msg){super(msg);}
}
