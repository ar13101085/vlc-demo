package com.aos.dynamiclib;

public class PrintSomething {
    public static String print(){
        StringBuilder sb=new StringBuilder();
        sb.append("***********34AOS Start************");
        sb.append("\n");
        sb.append(System.currentTimeMillis());
        sb.append("\n");
        sb.append("************AOS****************");
        return sb.toString();
    }
}