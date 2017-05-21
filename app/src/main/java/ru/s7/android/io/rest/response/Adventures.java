package ru.s7.android.io.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ru.s7.android.model.Adventure;

/**
 * Created by celikindv on 21/05/2017.
 */

public class Adventures extends Response {
    @SerializedName("results")
    ArrayList<Adventure> adventures;

    public ArrayList<Adventure> getAdventures() {
        return adventures;
    }
}
