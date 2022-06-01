package com.haerul.interventions;

import com.google.gson.annotations.SerializedName;

public class Interventions {

    @SerializedName("id")
    private int id;
    @SerializedName("request_info")
    private String request_info;
    @SerializedName("request_id")
    private String request_id;
    @SerializedName("request_desc")
    private String request_desc;
    @SerializedName("requester_name")
    private String requester_name;
    @SerializedName("tech")
    private int tech;
    @SerializedName("assign_date")
    private String assign_date;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("requester_city")
    private String requester_city;
    @SerializedName("requester_add1")
    private String requester_add1;
    @SerializedName("requester_zip")
    private String requester_zip;
    @SerializedName("requester_email")
    private String requester_email;
    @SerializedName("love")
    private Boolean love;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequest_info() {
        return request_info;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public void setRequest_info(String request_info) {
        this.request_info = request_info;
    }

    public String getRequest_desc() {
        return request_desc;
    }

    public void setRequest_desc(String request_desc) {
        this.request_desc = request_desc;
    }

    public String getRequester_name() {
        return requester_name;
    }

    public void setRequester_name(String requester_name) {
        this.requester_name = requester_name;
    }

    public int getTech() {
        return tech;
    }

    public void setTech(int tech) {
        this.tech = tech;
    }

    public String getAssign_date() {
        return assign_date;
    }

    public void setAssign_date(String assign_date) {
        this.assign_date = assign_date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRequester_city() {
        return requester_city;
    }

    public void setRequester_city(String requester_city) {
        this.requester_city = requester_city;
    }

    public String getRequester_add1() {
        return requester_add1;
    }

    public void setRequester_add1(String requester_add1) {
        this.requester_add1 = requester_add1;
    }

    public String getRequester_zip() {
        return requester_zip;
    }

    public void setRequester_zip(String requester_zip) {
        this.requester_zip = requester_zip;
    }

    public String getRequester_email() {
        return requester_email;
    }

    public void setRequester_email(String requester_email) {
        this.requester_email = requester_email;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
