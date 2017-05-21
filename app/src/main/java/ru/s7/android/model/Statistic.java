package ru.s7.android.model;

/**
 * Created by celikindv on 20/05/2017.
 */

public class Statistic {
    int level;
    int currentScoreCount;
    int currentFlightsCount;
    int aeroportCount;
    int cityCount;
    int countryCount;
    int planeCount;

    public Statistic(int level, int currentScoreCount, int currentFlightsCount, int aeroportCount, int cityCount, int countryCount, int planeCount) {
        this.level = level;
        this.currentScoreCount = currentScoreCount;
        this.currentFlightsCount = currentFlightsCount;
        this.aeroportCount = aeroportCount;
        this.cityCount = cityCount;
        this.countryCount = countryCount;
        this.planeCount = planeCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentScoreCount() {
        return currentScoreCount;
    }

    public void setCurrentScoreCount(int currentScoreCount) {
        this.currentScoreCount = currentScoreCount;
    }

    public int getCurrentFlightsCount() {
        return currentFlightsCount;
    }

    public void setCurrentFlightsCount(int currentFlightsCount) {
        this.currentFlightsCount = currentFlightsCount;
    }

    public int getAeroportCount() {
        return aeroportCount;
    }

    public void setAeroportCount(int aeroportCount) {
        this.aeroportCount = aeroportCount;
    }

    public int getCityCount() {
        return cityCount;
    }

    public void setCityCount(int cityCount) {
        this.cityCount = cityCount;
    }

    public int getCountryCount() {
        return countryCount;
    }

    public void setCountryCount(int countryCount) {
        this.countryCount = countryCount;
    }

    public int getPlaneCount() {
        return planeCount;
    }

    public void setPlaneCount(int planeCount) {
        this.planeCount = planeCount;
    }
}
