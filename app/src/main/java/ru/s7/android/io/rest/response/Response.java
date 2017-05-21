package ru.s7.android.io.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Response basic model
 *
 * @author celikindv
 */
public class Response {
    /**
     * The Count.
     */
    @SerializedName("count ")
    Integer count;
    /**
     * The Code.
     */
    @SerializedName("next")
    Integer next;

    @SerializedName("previous")
    Integer previous;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Integer getPrevious() {
        return previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }
}

