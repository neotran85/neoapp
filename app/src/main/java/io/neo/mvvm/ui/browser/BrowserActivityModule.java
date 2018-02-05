package io.neo.mvvm.ui.browser;

import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class BrowserActivityModule {

    @Provides
    BrowserViewModel provideBrowserViewModel(DataManager dataManager,
                                              SchedulerProvider schedulerProvider) {
        return new BrowserViewModel(dataManager, schedulerProvider);
    }

}
