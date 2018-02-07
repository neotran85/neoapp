package io.neo.mvvm.ui.main;

import android.databinding.ObservableField;
import android.view.View;

import java.util.ArrayList;

import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.data.model.db.ArticleCategory;
import io.neo.mvvm.ui.base.BaseViewModel;
import io.neo.mvvm.ui.main.category.CategoryAdapter;
import io.neo.mvvm.ui.main.category.CategoryItemViewModel;
import io.neo.mvvm.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {
    public final ObservableField<CategoryAdapter> obAdapter = new ObservableField<>();
    public MainViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void setUp(View.OnClickListener target) {
        ArrayList<CategoryItemViewModel> arrayList = new ArrayList<>();
        for(ArticleCategory category: getDataManager().getArrayCategories()) {
            CategoryItemViewModel item = new CategoryItemViewModel(category);
            arrayList.add(item);
        }
        CategoryAdapter newAdapter = new CategoryAdapter(arrayList);
        newAdapter.setOnClickListener(target);
        obAdapter.set(newAdapter);
    }
}
