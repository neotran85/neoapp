package io.neo.mvvm.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.neo.mvvm.ui.article.ArticleActivity;
import io.neo.mvvm.ui.article.ArticleActivityModule;
import io.neo.mvvm.ui.browser.BrowserActivity;
import io.neo.mvvm.ui.browser.BrowserActivityModule;
import io.neo.mvvm.ui.detail.DetailActivity;
import io.neo.mvvm.ui.detail.DetailActivityModule;
import io.neo.mvvm.ui.main.MainActivity;
import io.neo.mvvm.ui.main.MainActivityModule;
import io.neo.mvvm.ui.splash.SplashActivity;
import io.neo.mvvm.ui.splash.SplashActivityModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = BrowserActivityModule.class)
    abstract BrowserActivity bindBrowserActivity();

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {ArticleActivityModule.class})
    abstract ArticleActivity bindArticleActivity();

    @ContributesAndroidInjector(modules = {DetailActivityModule.class})
    abstract DetailActivity bindDetailActivity();
}
