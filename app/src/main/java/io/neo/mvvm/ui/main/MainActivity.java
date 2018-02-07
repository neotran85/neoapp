package io.neo.mvvm.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import io.neo.mvvm.AppConstants;
import io.neo.mvvm.BR;
import io.neo.mvvm.R;
import io.neo.mvvm.data.model.db.ArticleCategory;
import io.neo.mvvm.databinding.ActivityMainBinding;
import io.neo.mvvm.ui.article.ArticleActivity;
import io.neo.mvvm.ui.base.BaseActivity;
import io.neo.mvvm.ui.splash.SplashActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, View.OnClickListener {
    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    ActivityMainBinding mBinder;
    private MainViewModel mMainViewModel;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        AppConstants.initiate(this);
        mBinder = getViewDataBinding();
        mBinder.setViewModel(mMainViewModel);
        mBinder.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinder.categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMainViewModel.setNavigator(this);
        mMainViewModel.setUp(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getTag() instanceof ArticleCategory) {
            ArticleCategory data = (ArticleCategory)view.getTag();
            if(data.name.equals("Theories")) {
                openArticleActivity();
            }
        }
    }

    public void openArticleActivity() {
        Intent intent = ArticleActivity.getStartIntent(MainActivity.this);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }



    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
