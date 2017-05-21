package ru.s7.android.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by celikindv on 20/05/2017.
 */

@Parcel
public class Task {
    @SerializedName("image")
    public String logo;
    @SerializedName("name")
    public String label;
    @SerializedName("description")
    public String desc;
    @SerializedName("completed")
    public boolean finished;

    public Task() {
    }

    public Task(String logo, String label, String desc) {
        this.logo = logo;
        this.label = label;
        this.desc = desc;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
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

}
