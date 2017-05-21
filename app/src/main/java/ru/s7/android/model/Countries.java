package ru.s7.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by celikindv on 21/05/2017.
 */

public class Countries {
    @SerializedName("version")
    int version;

    @SerializedName("countries")
    List<Country> countries;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
