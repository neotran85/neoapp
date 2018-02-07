package io.neo.mvvm.ui.detail;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.davemorrissey.labs.subscaleview.ImageSource;

import javax.inject.Inject;

import io.neo.mvvm.BR;
import io.neo.mvvm.R;
import io.neo.mvvm.data.model.db.Article;
import io.neo.mvvm.databinding.ActivityDetailBinding;
import io.neo.mvvm.ui.base.BaseActivity;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailViewModel> implements DetailNavigator {
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    ActivityDetailBinding mBinder;
    private DetailViewModel mDetailViewModel;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = getViewDataBinding();
        mBinder.setViewModel(mDetailViewModel);
        mDetailViewModel.setNavigator(this);
        mDetailViewModel.setUp(getIntent());
    }

    @Override
    public void openContentDescription(Article article) {
        mBinder.ivContent.setImage(ImageSource.asset(article.detail));
    }

    @Override
    public DetailViewModel getViewModel() {
        mDetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailViewModel.class);
        return mDetailViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

}
