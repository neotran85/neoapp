package io.neo.mvvm.ui.main;

import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.ui.base.BaseViewModel;
import io.neo.mvvm.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {
   public MainViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isUserLoggedIn() {
        return getDataManager().isUserLoggedIn();
    }
}
