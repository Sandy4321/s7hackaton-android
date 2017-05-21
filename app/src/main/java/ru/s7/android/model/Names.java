
package ru.s7.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Names {

    @SerializedName("ru")
    @Expose
    private String ru;
    @SerializedName("en")
    @Expose
    private String en;
    @SerializedName("zh")
    @Expose
    private String zh;

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

}
