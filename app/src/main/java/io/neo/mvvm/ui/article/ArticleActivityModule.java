package io.neo.mvvm.ui.article;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import io.neo.mvvm.ViewModelProviderFactory;
import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.utils.rx.SchedulerProvider;

@Module
public class ArticleActivityModule {

    @Provides
    ArticleViewModel provideMainViewModel(DataManager dataManager,
                                          SchedulerProvider schedulerProvider) {
        return new ArticleViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(ArticleViewModel articleViewModel) {
        return new ViewModelProviderFactory<>(articleViewModel);
    }

}
