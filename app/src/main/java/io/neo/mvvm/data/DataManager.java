package io.neo.mvvm.data;

import io.neo.mvvm.data.local.db.DbHelper;
import io.neo.mvvm.data.local.prefs.PreferencesHelper;
import io.neo.mvvm.data.model.db.AppyService;
import io.neo.mvvm.data.model.db.AppyServiceCategory;
import io.neo.mvvm.data.model.db.ServiceOrderUserInput;
import io.neo.mvvm.data.remote.ApiHelper;

import java.util.ArrayList;

import io.reactivex.Observable;


public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void updateApiHeader(String token);

    void setUserAsLoggedOut();

    Observable< ArrayList<AppyServiceCategory>> seedDatabaseCategories();

    Observable<ArrayList<AppyService>> seedDatabaseServices();

    void updateUserInfo(
            String accessToken,
            LoggedInMode loggedInMode,
            String userName,
            String phoneNumber,
            String email,
            String profilePicPath,
            String token);

    enum LoggedInMode {
        LOGGED_OUT(0),
        LOGGED_IN(1);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }

    ServiceOrderUserInput getServiceOrderUserInput();
}
