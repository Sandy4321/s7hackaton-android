package ru.s7.android.io.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ru.s7.android.model.Achievement;

/**
 * Created by celikindv on 21/05/2017.
 */

public class Achievements extends Response {
    @SerializedName("results")
    ArrayList<Achievement> achievements;

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }
}
