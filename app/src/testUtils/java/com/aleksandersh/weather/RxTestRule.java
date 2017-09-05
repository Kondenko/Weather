package com.aleksandersh.weather;

/**
 * Created by Vladimir Kondenko on 30.07.17.
 */
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Always subscribeOn and observeOn Schedulers.trampoline()
 * for immediate execution.
 */
public class RxTestRule implements TestRule {

    private final Scheduler trampoline = Schedulers.trampoline();

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setSingleSchedulerHandler(__ -> trampoline);
                RxJavaPlugins.setComputationSchedulerHandler(__ -> trampoline);
                RxJavaPlugins.setIoSchedulerHandler(__ -> trampoline);
                RxJavaPlugins.setNewThreadSchedulerHandler(__ -> trampoline);
                RxAndroidPlugins.setMainThreadSchedulerHandler(__ -> trampoline);
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> trampoline);
                try {
                    base.evaluate();
                } finally {
                    RxAndroidPlugins.reset();
                    RxJavaPlugins.reset();
                }
            }
        };
    }

}