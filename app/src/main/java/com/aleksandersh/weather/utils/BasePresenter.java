package com.aleksandersh.weather.utils;


import android.support.annotation.CallSuper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by Vladimir Kondenko on 11.08.17.
 */

public abstract class BasePresenter<V extends BaseView> {

    protected V view;

    protected CompositeDisposable disposables;

    public BasePresenter() {
        this.disposables = new CompositeDisposable();
    }

    @CallSuper
    public void attach(V view) {
        this.view = view;
    }

    @CallSuper
    public void detach() {
        Utils.dispose(disposables);
        this.view = null;
    }

    protected void add(Disposable disposable) {
        disposables.add(disposable);
    }

}
