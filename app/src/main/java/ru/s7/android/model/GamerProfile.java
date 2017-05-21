package ru.s7.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by celikindv on 20/05/2017.
 */

public class GamerProfile {
    @SerializedName("full_name")
    String fullName;

    @SerializedName("email")
    String email;

    @SerializedName("adventures")
    List<Adventure> adventures;

    @SerializedName("achievements")
    List<Achievement> achievements;

    @SerializedName("trips")
    List<Trip> trips;

    @SerializedName("score")
    public Integer score;


    public Integer getScore() {
        return score;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public List<Adventure> getAdventures() {
        return adventures;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }


}
