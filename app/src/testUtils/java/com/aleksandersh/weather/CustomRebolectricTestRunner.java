package com.aleksandersh.weather;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Vladimir Kondenko on 30.07.17.
 */

public class CustomRebolectricTestRunner extends RobolectricTestRunner {

    public CustomRebolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected Config buildGlobalConfig() {
        return new Config.Builder()
                .setManifest("app/src/main/AndroidManifest.xml")
                .setApplication(App.class)
                .build();

    }
}
