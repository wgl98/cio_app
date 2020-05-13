package com.cio_app.model;

public enum CHANNEL {
    MANUFACTURE("智能制造", 0x01),

    INTALLIGENCE("人工智能", 0x02),

    FIVEG("5G", 0x03),

    INTERNET("工业互联网", 0x04),

    ALL("全部",0x05),

    MANUFACTURE_INDUSTRY("制造业",0x06),

    RETAIL_INDUSTRY("零售业",0x07),

    BUILDING_INDUSTRY("建筑业",0x08),

    FINANCIAL_INDUSTRY("金融业",0x09),

    UI_ENGINEER("软件UI设计师",0x10),

    SOFTWARE_ENGINEER("软件工程师",0x11),

    SYSTEM_ENGINEER("系统工程师",0x12);

    //所有类型标识
    public static final int MANUFACTURE_ID = 0x01;
    public static final int INTALLIGENCE_ID = 0x02;
    public static final int FIVEG_ID = 0x03;
    public static final int INTERNET_ID = 0x04;
    public static final int ALL_ID = 0x05;
    public static final int MANUFACTURE_INDUSTRY_ID = 0x06;
    public static final int RETAIL_INDUSTRY_ID = 0x07;
    public static final int BUILDING_INDUSTRY_ID = 0x08;
    public static final int FINANCIAL_INDUSTRY_ID = 0x09;
    public static final int UI_ENGINEER_ID = 0x10;
    public static final int SOFTWARE_ENGINEER_ID = 0x11;
    public static final int SYSTEM_ENGINEER_ID = 0x12;


    private final String key;
    private final int value;

    CHANNEL(String key, int value) {
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
