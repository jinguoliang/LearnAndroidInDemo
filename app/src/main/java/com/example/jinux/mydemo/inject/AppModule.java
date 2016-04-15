package com.example.jinux.mydemo.inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by baidu on 16/4/14.
 */
@Module(injects = TestDagger.class, library = true)
public class AppModule {
    @Provides
    Coder provideCoder(Boss boss) {
        return Coder.provideCoder(boss);
    }

    @Provides
    Test provideTTT() {
        return new Test();
    }
}
