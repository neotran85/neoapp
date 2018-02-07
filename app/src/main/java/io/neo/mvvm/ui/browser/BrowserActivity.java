package io.neo.mvvm.ui.browser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import javax.inject.Inject;

import io.neo.mvvm.R;
import io.neo.mvvm.ui.base.BaseActivity;
import io.neo.mvvm.BR;
import io.neo.mvvm.databinding.ActivityBrowserBinding;

public class BrowserActivity extends BaseActivity<ActivityBrowserBinding, BrowserViewModel> {

    @Inject
    BrowserViewModel mBrowserViewModel;

    ActivityBrowserBinding mBinder;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, BrowserActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = getViewDataBinding();
        mBinder.setViewModel(mBrowserViewModel);
        mBrowserViewModel.setNavigator(this);
        mBrowserViewModel.setUp(getIntent());
    }

    public void openLink(String url) {
        mBinder.webView.getSettings().setJavaScriptEnabled(true);
        mBinder.webView.loadUrl(url);
        mBinder.webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (mBrowserViewModel != null)
                    mBrowserViewModel.setIsLoading(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public BrowserViewModel getViewModel() {
        return mBrowserViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_browser;
    }

}
