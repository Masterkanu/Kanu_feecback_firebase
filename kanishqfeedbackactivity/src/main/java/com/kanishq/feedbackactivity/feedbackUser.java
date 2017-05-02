package com.kanishq.feedbackactivity;

/**
 * Created by gupta on 18-04-2017.
 */

public class feedbackUser {
    String getname;
    String getemail;
    String getQuery;
    String getDeviceinfo;
    String getCountry;
    public feedbackUser() {

    }

    public feedbackUser(String id, String getname, String getemail, String getQuery, String getDeviceinfo, String getCountry) {
        this.getname = getname;
        this.getemail = getemail;
        this.getQuery = getQuery;
        this.getDeviceinfo = getDeviceinfo;
        this.getCountry = getCountry;
    }

    public String getGetname() {
        return getname;
    }

    public String getGetemail() {
        return getemail;
    }

    public String getGetQuery() {
        return getQuery;
    }

    public String getGetDeviceinfo() {
        return getDeviceinfo;
    }

    public String getCountry() {
        return getCountry;
    }
}
