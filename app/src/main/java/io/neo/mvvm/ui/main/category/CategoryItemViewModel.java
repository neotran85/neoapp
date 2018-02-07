package io.neo.mvvm.ui.main.category;

import android.databinding.ObservableField;

import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.data.model.db.ArticleCategory;
import io.neo.mvvm.ui.base.BaseViewModel;
import io.neo.mvvm.utils.rx.SchedulerProvider;

public class CategoryItemViewModel extends BaseViewModel <CategoryItemNavigator> {
    public final ObservableField<String> obName = new ObservableField<>();
    public final ObservableField<String> obImage = new ObservableField<>();
    public final ObservableField<String> obDescription = new ObservableField<>();

    private ArticleCategory category;
    public CategoryItemViewModel(DataManager dataManager,
                                SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public CategoryItemViewModel() {
        super();
    }

    public CategoryItemViewModel(ArticleCategory categoryParam) {
        category = categoryParam;
        obImage.set(categoryParam.image);
        obName.set(categoryParam.name);
        obDescription.set(categoryParam.description);
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }
}
