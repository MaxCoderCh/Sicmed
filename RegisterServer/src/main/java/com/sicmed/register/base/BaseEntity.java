package com.sicmed.register.base;

public class BaseEntity {

    public final static int PAGE_SIZE = 100;
    public int offset;   //页码

    public int limit;    //每页数据量

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
