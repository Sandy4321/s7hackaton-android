package ru.s7.android.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by celikindv on 20/05/2017.
 */
@Parcel
public class Adventure {
    @SerializedName("id")
    public int id;
    @SerializedName("image")
    public String logo;
    @SerializedName("name")
    public String label;
    @SerializedName("description")
    public String desc;
    @SerializedName("tasks")
    public ArrayList<Task> taskList;
    public int reward;
    public boolean finished;

    public Adventure() {
    }


    public Adventure(String logo, String label, String desc, int reward, boolean finished) {
        this.logo = logo;
        this.label = label;
        this.desc = desc;
        this.reward = reward;
        this.finished = finished;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
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

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
