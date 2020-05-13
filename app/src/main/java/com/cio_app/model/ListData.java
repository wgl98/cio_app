package com.cio_app.model;

import java.util.ArrayList;
import java.util.List;

public class ListData {

    public List getAmountData(){
        List<String> list = new ArrayList<>();
        list.add("1人");
        list.add("2人");
        list.add("3人");
        list.add("4人");
        return list;
    }

    public List getTypeData(){
        List<String> list = new ArrayList<>();
        list.add("全职");
        list.add("兼职");
        return list;
    }

    public List getSexData(){
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        return list;
    }

    public List getPublicData(){
        List<String> list = new ArrayList<>();
        list.add("是");
        list.add("否");
        return list;
    }

    public List getBackgroundData(){
        List<String> list = new ArrayList<>();
        list.add("小学");
        list.add("初中");
        list.add("高中");
        list.add("大专");
        list.add("本科");
        list.add("研究生及以上");
        return list;
    }

    public List getCompanyCharacterData(){
        List<String> list = new ArrayList<>();
        list.add("国有企业");
        list.add("集体企业");
        list.add("联营企业");
        list.add("股份合作制企业");
        list.add("私营企业");
        list.add("个体户");
        list.add("合伙企业");
        list.add("有限责任公司");
        list.add("股份有限公司");
        return list;
    }

    public List getCompanyBusinessData(){
        List<String> list = new ArrayList<>();
        list.add("金融业");
        list.add("信息业");
        list.add("农业");
        return list;
    }

    public List getUnitCharacterData(){
        List<String> list = new ArrayList<>();
        list.add("国家行政企业");
        list.add("公私合作企业");
        list.add("中外合资企业");
        list.add("社会组织机构");
        list.add("国际组织机构");
        list.add("外资企业");
        list.add("私营企业");
        list.add("集体企业");
        list.add("国防军事企业");
        return list;
    }
}
