package com.aleksandersh.weather.utils;


import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Vladimir Kondenko on 09.08.17.
 */

public class Utils {

    public static void transaction(Action action) {
        Completable.fromAction(action)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
    }

}
