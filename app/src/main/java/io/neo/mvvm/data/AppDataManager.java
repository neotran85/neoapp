package io.neo.mvvm.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.neo.mvvm.AppConstants;
import io.neo.mvvm.data.local.db.DbHelper;
import io.neo.mvvm.data.local.prefs.PreferencesHelper;
import io.neo.mvvm.data.model.api.account.LoginRequest;
import io.neo.mvvm.data.model.api.account.LoginResponse;
import io.neo.mvvm.data.model.api.account.LogoutResponse;
import io.neo.mvvm.data.model.api.account.SignUpRequest;
import io.neo.mvvm.data.model.api.account.SignUpResponse;
import io.neo.mvvm.data.model.db.Article;
import io.neo.mvvm.data.model.db.ArticleCategory;
import io.neo.mvvm.data.model.db.User;
import io.neo.mvvm.data.remote.ApiHeader;
import io.neo.mvvm.data.remote.ApiHelper;
import io.neo.mvvm.utils.helper.CommonUtils;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    protected final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    private ArrayList<Article> mArrayArticles;
    private ArrayList<ArticleCategory> mArrayCategories;

    @Inject
    public AppDataManager(Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public String getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Observable<Boolean> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }

    @Override
    public Single<LoginResponse> doUserLogin(LoginRequest.ServerLoginRequest
                                                     request) {
        return mApiHelper.doUserLogin(request);
    }

    @Override
    public Single<SignUpResponse> doUserSignUp(SignUpRequest request) {
        return mApiHelper.doUserSignUp(request);
    }

    @Override
    public Single<LogoutResponse> doUserLogout() {
        return mApiHelper.doUserLogout();
    }

    @Override
    public String getCurrentUsername() {
        return mPreferencesHelper.getCurrentUsername();
    }

    @Override
    public void setCurrentUsername(String userName) {
        mPreferencesHelper.setCurrentUsername(userName);
    }

    @Override
    public String getCurrentPhoneNumber() {
        return mPreferencesHelper.getCurrentPhoneNumber();
    }

    @Override
    public void setCurrentPhoneNumber(String phoneNumber) {
        mPreferencesHelper.setCurrentPhoneNumber(phoneNumber);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public void updateApiHeader(String token) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setAuthorization(token);
    }

    @Override
    public void updateUserInfo(
            String userId,
            LoggedInMode loggedInMode,
            String username,
            String phoneNumber,
            String email,
            String profilePicPath, String token) {
        setCurrentUsername(username);
        setCurrentUserEmail(email);
        setCurrentUserProfilePicUrl(profilePicPath);
        setCurrentUserId(userId);
        updateApiHeader(token);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                LoggedInMode.LOGGED_OUT,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String token) {
        mPreferencesHelper.setAccessToken(token);
    }

    @Override
    public void logout() {
        mPreferencesHelper.logout();
    }

    @Override
    public boolean isUserLoggedIn() {
        return mPreferencesHelper.isUserLoggedIn();
    }

    @Override
    public String getUserLastName() {
        return mPreferencesHelper.getUserLastName();
    }

    @Override
    public void setUserLastName(String lastName) {
        mPreferencesHelper.setUserLastName(lastName);
    }

    @Override
    public String getUserFirstName() {
        return mPreferencesHelper.getUserFirstName();
    }

    @Override
    public void setUserFirstName(String firstName) {
        mPreferencesHelper.setUserFirstName(firstName);
    }

    @Override
    public Observable<ArrayList<ArticleCategory>> seedDatabaseCategories() {
        return Observable.fromCallable(new Callable<ArrayList<ArticleCategory>>() {
            @Override
            public ArrayList<ArticleCategory> call() throws Exception {
                Type type = new TypeToken<ArrayList<ArticleCategory>>() {}.getType();
                GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                final Gson gson = builder.create();
                ArrayList<ArticleCategory> appyServiceCategories = gson.fromJson(
                        CommonUtils.loadJSONFromAsset(mContext,
                                AppConstants.SEED_DATABASE_CATEGORIES),
                        type);
                return appyServiceCategories;
            }
        });
    }

    @Override
    public Observable<ArrayList<Article>> seedDatabaseArticle() {
        return Observable.fromCallable(new Callable<ArrayList<Article>>() {
            @Override
            public ArrayList<Article> call() throws Exception {
                Type type = new TypeToken<ArrayList<Article>>() {}.getType();
                GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                Gson gson = builder.create();
                ArrayList<Article> arrayList  = gson.fromJson(
                        CommonUtils.loadJSONFromAsset(mContext,
                                AppConstants.SEED_DATABASE_ARTICLES),
                        type);
                return arrayList;
            }
        });
    }

    public ArrayList<Article> getArrayArticles() {
        return mArrayArticles;
    }

    public void setArrayArticles(ArrayList<Article> mArrayArticles) {
        this.mArrayArticles = mArrayArticles;
    }

    public ArrayList<ArticleCategory> getArrayCategories() {
        return mArrayCategories;
    }

    public void setArrayCategories(ArrayList<ArticleCategory> mArrayCategories) {
        this.mArrayCategories = mArrayCategories;
    }

    public Article getArticleByName(String name) {
        for(Article article: this.mArrayArticles) {
            if(article.name.equals(name)) {
                return article;
            }
        }
        return null;
    }
}