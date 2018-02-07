package io.neo.mvvm.ui.article;

import android.databinding.ObservableField;
import android.view.View;

import java.util.ArrayList;

import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.data.model.db.Article;
import io.neo.mvvm.data.model.db.ArticleCategory;
import io.neo.mvvm.ui.base.BaseViewModel;
import io.neo.mvvm.ui.main.category.CategoryAdapter;
import io.neo.mvvm.ui.main.category.CategoryItemViewModel;
import io.neo.mvvm.utils.rx.SchedulerProvider;

public class ArticleViewModel extends BaseViewModel<ArticleNavigator> {
    public final ObservableField<CategoryAdapter> obAdapter = new ObservableField<>();
    public ArticleViewModel(DataManager dataManager,
                            SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void setUp(View.OnClickListener target) {
        ArrayList<CategoryItemViewModel> arrayList = new ArrayList<>();
        for(Article article: getDataManager().getArrayArticles()) {
            ArticleCategory category = new ArticleCategory();
            category.name = article.name;
            category.description = article.description;
            category.image = article.image;
            CategoryItemViewModel item = new CategoryItemViewModel(category);
            arrayList.add(item);
        }
        CategoryAdapter newAdapter = new CategoryAdapter(arrayList);
        newAdapter.setOnClickListener(target);
        obAdapter.set(newAdapter);
    }
}
