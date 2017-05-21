package ru.s7.android.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by celikindv on 20/05/2017.
 */

public class Achievement {
    @SerializedName("id")
    int id;
    @SerializedName("image")
    String logo;
    @SerializedName("name")
    String label;
    @SerializedName("description")
    String desc;
    @SerializedName("count")
    int count;

    long time;
    boolean unlocked;
    int progress;

    public Achievement(String logo, String label, String desc, long time) {
        this.logo = logo;
        this.label = label;
        this.desc = desc;
        this.time = time;
    }

    public Achievement(String logo, String label, String desc, int progress, int count, long time, boolean unlocked) {
        this.logo = logo;
        this.label = label;
        this.unlocked = unlocked;
        this.desc = desc;
        this.progress = progress;
        this.count = count;
        this.time = time;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Achievement that = (Achievement) o;

        if (progress != that.progress) return false;
        if (count != that.count) return false;
        if (time != that.time) return false;
        if (unlocked != that.unlocked) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return desc != null ? desc.equals(that.desc) : that.desc == null;

    }

    @Override
    public int hashCode() {
        int result = logo != null ? logo.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + progress;
        result = 31 * result + count;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (unlocked ? 1 : 0);
        return result;
    }
}
