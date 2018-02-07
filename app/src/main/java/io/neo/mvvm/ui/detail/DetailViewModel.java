package io.neo.mvvm.ui.detail;

import android.content.Intent;

import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.data.model.db.Article;
import io.neo.mvvm.ui.base.BaseViewModel;
import io.neo.mvvm.utils.rx.SchedulerProvider;

public class DetailViewModel extends BaseViewModel<DetailNavigator> {
    public DetailViewModel(DataManager dataManager,
                           SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setUp(Intent data) {
        String nameArticle = data.getStringExtra("name");
        Article article = getDataManager().getArticleByName(nameArticle);
        if (article != null)
            getNavigator().openContentDescription(article);
    }
}
