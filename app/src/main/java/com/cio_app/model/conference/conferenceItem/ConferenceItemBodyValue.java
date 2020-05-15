package com.cio_app.model.conference.conferenceItem;

import com.cio_app.model.BaseModel;

public class ConferenceItemBodyValue extends BaseModel {
    public int id;
    public String title;
    public String startTime;
    public String endTime;
    public String address;
    public String description;
    public double locationX;
    public double locationY;
    public String coverImage;
    public String contact;
    public String contactDetails;
    public String summary;
    public String eventStatus;
    public String registrationStatus;
    public String questionnaireStatus;
    public String checkinStatus;
    public int questionnaireId;
}
