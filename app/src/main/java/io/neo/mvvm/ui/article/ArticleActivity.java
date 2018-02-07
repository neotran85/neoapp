package io.neo.mvvm.ui.article;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import io.neo.mvvm.BR;
import io.neo.mvvm.R;
import io.neo.mvvm.data.model.db.ArticleCategory;
import io.neo.mvvm.databinding.ActivityArticleBinding;
import io.neo.mvvm.ui.base.BaseActivity;
import io.neo.mvvm.ui.detail.DetailActivity;

public class ArticleActivity extends BaseActivity<ActivityArticleBinding, ArticleViewModel> implements ArticleNavigator, View.OnClickListener {
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    ActivityArticleBinding mBinder;
    private ArticleViewModel mArticleViewModel;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ArticleActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = getViewDataBinding();
        mBinder.setViewModel(mArticleViewModel);
        mBinder.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinder.categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mArticleViewModel.setNavigator(this);
        mArticleViewModel.setUp(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getTag() instanceof ArticleCategory) {
            ArticleCategory data = (ArticleCategory)view.getTag();
            Intent intent = DetailActivity.getStartIntent(ArticleActivity.this);
            intent.putExtra("name", data.name);
            startActivity(intent);
        }
    }

    @Override
    public ArticleViewModel getViewModel() {
        mArticleViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ArticleViewModel.class);
        return mArticleViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_article;
    }

}
