package com.cio_app.model.policy.policyModel;


import com.cio_app.model.BaseModel;

import java.util.List;

/**
 * 政策实体
 */
public class PolicyModel extends BaseModel {
    public int pageNumber;
    public int pageSize;
    public int totalElements;
    public int totalPages;
    public List<PolicyBodyValue> content;
}
