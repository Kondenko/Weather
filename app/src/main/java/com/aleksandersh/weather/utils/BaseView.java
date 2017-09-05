package com.aleksandersh.weather.utils;


/**
 * Created by Vladimir Kondenko on 11.08.17.
 */

public interface BaseView {

    void showLoading(boolean show);

    void showError(Throwable throwable);

//    void showError(Throwable throwable, String message);

}
