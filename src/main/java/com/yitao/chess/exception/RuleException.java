package com.yitao.chess.exception;

/**
 * 规则不允许
 */
public class RuleException extends RuntimeException {
    private String msg;
    public RuleException(){}
    public RuleException(String msg){super(msg);}
}
