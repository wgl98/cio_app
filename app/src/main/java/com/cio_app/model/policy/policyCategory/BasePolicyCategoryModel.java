package com.cio_app.model.policy.policyCategory;

import com.cio_app.model.BaseModel;

import java.util.List;

public class BasePolicyCategoryModel extends BaseModel {
    public String code;
    public String msg;
    public List<PolicyFirstCategoryModelBodyValue> data;
}
