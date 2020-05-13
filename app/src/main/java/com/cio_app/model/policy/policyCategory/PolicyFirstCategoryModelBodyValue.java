package com.cio_app.model.policy.policyCategory;

import com.cio_app.model.BaseModel;

import java.util.List;

public class PolicyFirstCategoryModelBodyValue extends BaseModel {
    public int id;
    public String categoryName;
    public List<PolicySecondCategoryModelBodyValue> policyCategoryVOList;
}
