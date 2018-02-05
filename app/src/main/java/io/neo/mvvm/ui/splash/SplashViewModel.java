package io.neo.mvvm.ui.splash;

import java.util.ArrayList;

import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.data.model.db.AppyService;
import io.neo.mvvm.data.model.db.AppyServiceCategory;
import io.neo.mvvm.ui.base.BaseViewModel;
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
                .seedDatabaseServices()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ArrayList<AppyService>>() {
                    @Override
                    public void accept(ArrayList<AppyService> services) throws Exception {
                        if (services != null) {
                            getDataManager().getServiceOrderUserInput().setArrayAppyService(services);
                            getNavigator().openMainActivity();
                        }
                    }
                }));
    }
    public void loadCategories() {
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseCategories()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ArrayList<AppyServiceCategory>>() {
                    @Override
                    public void accept(ArrayList<AppyServiceCategory> categories) throws Exception {
                        if (categories != null) {
                            getDataManager().getServiceOrderUserInput().setArrayAppyServiceCategory(categories);
                            loadServices();
                        }
                    }
                }));
    }

}
