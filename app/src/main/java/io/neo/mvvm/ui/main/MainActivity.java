package io.neo.mvvm.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import io.neo.mvvm.AppConstants;
import io.neo.mvvm.BR;
import io.neo.mvvm.R;
import io.neo.mvvm.databinding.ActivityMainBinding;
import io.neo.mvvm.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    public final static int TAB_HOME = 0;
    public final static int TAB_NOTIFICATION = 1;
    public final static int TAB_REQUEST = 2;
    public final static int TAB_WISH_LIST = 3;
    public final static int TAB_MY_PROFILE = 4;
    public final static String TAB = "tab";

    public final static int REQUEST_LOGIN_FOR_REQUEST_TRACKING= 1111;
    public final static int REQUEST_LOGIN_FOR_MY_PROFILE = 1112;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    ActivityMainBinding mBinder;
    private MainViewModel mMainViewModel;
    private View currentTab;

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
        mMainViewModel.setNavigator(this);
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
