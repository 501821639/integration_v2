package com.core.test.other;

/**
 * Created by GSN on 2017/9/1.
 * 审核状态
 */
public enum EnumState {

    CREATE(1),//刚刚创建状态

    SUBMIT(2),//提交状态

    OFFICE(3),//处室审核

    END(4);//审核结束

    private int value;

    EnumState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
