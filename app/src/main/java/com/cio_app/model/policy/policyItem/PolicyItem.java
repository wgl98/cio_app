package com.cio_app.model.policy.policyItem;

import com.cio_app.model.BaseModel;

import java.util.List;

public class PolicyItem extends BaseModel {
    public int id;
    public String categoryName;
    public String cover;
    public String title;
    public String content;
    public String link;
    public String createTime;
    public List<PolicyItemValue > policyAnnexSet;
}
