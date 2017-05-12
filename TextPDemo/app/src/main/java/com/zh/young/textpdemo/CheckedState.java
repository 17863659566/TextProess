package com.zh.young.textpdemo;


public class CheckedState {
    /**
     * 记录是不是关键字
     */
    private boolean isKeyword;
    /**
     * 记录关键字的开始位置
     */
    private int start;
    /**
     * 记录关键字的结束位置
     */
    private int end;

    public boolean isKeyword() {
        return isKeyword;
    }

    public void setKeyword(boolean keyword) {
        isKeyword = keyword;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
