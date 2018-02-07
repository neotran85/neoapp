package io.neo.mvvm.ui.splash;

import java.util.ArrayList;

import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.data.model.db.Article;
import io.neo.mvvm.data.model.db.ArticleCategory;
import io.neo.mvvm.ui.base.BaseViewModel;
import io.neo.mvvm.utils.helper.AppLogger;
import io.neo.mvvm.utils.manager.AlertManager;
import io.neo.mvvm.utils.rx.SchedulerProvider;
import io.reactivex.functions.Consumer;

public class SplashViewModel extends BaseViewModel<SplashActivity> {

    public SplashViewModel(DataManager dataManager,
                           SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setUp() {
        loadCategories();
    }

    public void loadServices() {
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseArticle()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ArrayList<Article>>() {
                    @Override
                    public void accept(ArrayList<Article> articles) throws Exception {
                        if (articles != null) {
                            getNavigator().openMainActivity();
                            getDataManager().setArrayArticles(articles);
                        }
                    }
                }));
    }
    public void loadCategories() {
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseCategories()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ArrayList<ArticleCategory>>() {
                    @Override
                    public void accept(ArrayList<ArticleCategory> categories) throws Exception {
                        if (categories != null) {
                            loadServices();
                            getDataManager().setArrayCategories(categories);
                        }
                    }
                }));
    }

}
