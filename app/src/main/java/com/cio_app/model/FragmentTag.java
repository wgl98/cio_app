package com.cio_app.model;

public enum  FragmentTag {
    CONFERENCE_SIGN("报名中",0x01),
    CONFERENCE_HANDLE("进行中",0x02),
    CONFERENCE_FINISH("已结束",0x03),
    CONFERENCE_NO_PASS("未通过",0x04);

    public static final int CONFERENCE_SIGN_ID = 0x01;
    public static final int CONFERENCE_HANDLE_ID = 0x02;
    public static final int CONFERENCE_FINISH_ID = 0x03;
    public static final int CONFERENCE_NO_PASS_ID = 0x04;

    private final String key;
    private final int value;

    FragmentTag(String key,int value){
        this.key = key;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
