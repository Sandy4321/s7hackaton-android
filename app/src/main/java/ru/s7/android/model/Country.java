
package ru.s7.android.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("names")
    @Expose
    private Names names;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("timeZone")
    @Expose
    private Object timeZone;
    @SerializedName("order ")
    @Expose
    private Order order;
    @SerializedName("callingCode")
    @Expose
    private String callingCode;
    @SerializedName("iso")
    @Expose
    private String iso;
    @SerializedName("childrens")
    @Expose
    private List<Childs> childrens = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Names getNames() {
        return names;
    }

    public void setNames(Names names) {
        this.names = names;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Object getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Object timeZone) {
        this.timeZone = timeZone;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public void setCallingCode(String callingCode) {
        this.callingCode = callingCode;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public List<Childs> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Childs> childrens) {
        this.childrens = childrens;
    }

}
