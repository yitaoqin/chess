package com.yitao.chess.util;

/**
 * 规则工厂
 */
public class RuleFactory {

    public static Rule chizi(){ return new ChiziRule(); }
    public static Rule duizi(){
        return new DuiziRule();
    }
    public static Rule fanzi(){
        return new FanziRule();
    }
    public static Rule yidong(){
        return new Yidong();
    }
}
