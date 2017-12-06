package pl.thewalkingcode.beans;

import java.io.Serializable;

public class InfoBean implements Serializable {

    private String appVersion;
    private String appName;

    public InfoBean() {
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
