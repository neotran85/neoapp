package io.neo.mvvm.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.neo.mvvm.ui.browser.BrowserActivity;
import io.neo.mvvm.ui.browser.BrowserActivityModule;
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

}
