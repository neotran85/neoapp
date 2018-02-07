package io.neo.mvvm.ui.detail;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import io.neo.mvvm.ViewModelProviderFactory;
import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.utils.rx.SchedulerProvider;

@Module
public class DetailActivityModule {

    @Provides
    DetailViewModel provideMainViewModel(DataManager dataManager,
                                         SchedulerProvider schedulerProvider) {
        return new DetailViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(DetailViewModel detailViewModel) {
        return new ViewModelProviderFactory<>(detailViewModel);
    }

}
