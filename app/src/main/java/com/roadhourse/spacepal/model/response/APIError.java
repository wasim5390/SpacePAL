package com.roadhourse.spacepal.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidhu on 4/11/2018.
 */

public class APIError {
    @SerializedName("http_code")
    private int httpCode;

    @SerializedName("response_code")
    private String responseCode;

    @SerializedName("response_msg")
    private String responseMsg;

    public int getHttpCode() {
        return httpCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
