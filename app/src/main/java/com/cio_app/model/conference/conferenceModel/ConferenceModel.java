package com.cio_app.model.conference.conferenceModel;

import com.cio_app.model.BaseModel;

import java.util.List;

public class ConferenceModel extends BaseModel {
    public int pageNum;
    public int pageSize;
    public int pageTotal;
    public int total;
    public List<ConferenceBodyValue> data;
}
