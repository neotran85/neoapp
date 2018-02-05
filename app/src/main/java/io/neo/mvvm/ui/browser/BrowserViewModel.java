package io.neo.mvvm.ui.browser;

import android.content.Intent;

import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.ui.base.BaseViewModel;
import io.neo.mvvm.utils.rx.SchedulerProvider;

public class BrowserViewModel extends BaseViewModel<BrowserActivity> {
    public BrowserViewModel(DataManager dataManager,
                            SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setUp(Intent intent) {
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        if (url != null && url.length() > 0) {
            setIsLoading(true);
            getNavigator().setTitle((title != null && title.length() > 0) ? title : "");
            getNavigator().openLink(url);
        }
    }
}
