package ru.s7.android.io.rest;

/**
 * AsyncData interface for DataProvider
 *
 * @param <T> the type parameter
 * @author celikindv
 * @see DataProvider
 */
public interface AsyncData<T> {

    /**
     * On success.
     *
     * @param data the data
     */
    void onSuccess(T data);

    /**
     * On error.
     */
    void onError();

}
