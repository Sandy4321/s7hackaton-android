package ru.s7.android.model;

/**
 * Created by celikindv on 21/05/2017.
 */

public class NotificationPush {
    private String logo;
    private String label;
    private String desc;
    private long time;

    public NotificationPush(String logo, String label, String desc, long time) {
        this.logo = logo;
        this.label = label;
        this.desc = desc;
        this.time = time;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
