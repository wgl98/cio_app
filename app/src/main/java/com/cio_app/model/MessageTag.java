package com.cio_app.model;

public enum MessageTag {
    TAG_RESUME("简历通知",0x01),
    TAG_RECRUIT("招聘通知",0x02),
    TAG_DEMAND("需求通知",0x03),
    TAG_ACTIVITY("活动通知",0x04),
    TAG_PROJECT("方案通知",0x05);

    public static final int TAG_RESUME_ID = 0x01;
    public static final int TAG_RECRUIT_ID = 0x02;
    public static final int TAG_DEMAND_ID = 0x03;
    public static final int TAG_ACTIVITY_ID = 0x04;
    public static final int TAG_PROJECT_ID = 0x05;


    private final String key;
    private final int value;

    MessageTag(String key,int value){
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
