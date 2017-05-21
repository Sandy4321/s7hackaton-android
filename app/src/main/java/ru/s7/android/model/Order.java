
package ru.s7.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("ru")
    @Expose
    private Integer ru;
    @SerializedName("en")
    @Expose
    private Integer en;
    @SerializedName("zh")
    @Expose
    private Integer zh;

    public Integer getRu() {
        return ru;
    }

    public void setRu(Integer ru) {
        this.ru = ru;
    }

    public Integer getEn() {
        return en;
    }

    public void setEn(Integer en) {
        this.en = en;
    }

    public Integer getZh() {
        return zh;
    }

    public void setZh(Integer zh) {
        this.zh = zh;
    }

}
