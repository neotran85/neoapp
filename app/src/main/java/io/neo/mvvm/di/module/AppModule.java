package io.neo.mvvm.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import io.neo.mvvm.AppConstants;
import io.neo.mvvm.BuildConfig;
import io.neo.mvvm.R;
import io.neo.mvvm.data.AppDataManager;
import io.neo.mvvm.data.DataManager;
import io.neo.mvvm.data.local.db.AppDatabase;
import io.neo.mvvm.data.local.db.AppDbHelper;
import io.neo.mvvm.data.local.db.DbHelper;
import io.neo.mvvm.data.local.prefs.AppPreferencesHelper;
import io.neo.mvvm.data.local.prefs.PreferencesHelper;
import io.neo.mvvm.data.remote.ApiHeader;
import io.neo.mvvm.data.remote.ApiHelper;
import io.neo.mvvm.data.remote.AppApiHelper;
import io.neo.mvvm.di.ApiInfo;
import io.neo.mvvm.di.DatabaseInfo;
import io.neo.mvvm.di.PreferenceInfo;
import io.neo.mvvm.utils.rx.AppSchedulerProvider;
import io.neo.mvvm.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/TitilliumWeb-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
