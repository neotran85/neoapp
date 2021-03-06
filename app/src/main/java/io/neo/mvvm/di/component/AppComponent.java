package io.neo.mvvm.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import io.neo.mvvm.NeoProductApp;
import io.neo.mvvm.di.builder.ActivityBuilder;
import io.neo.mvvm.di.module.AppModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(NeoProductApp app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}
