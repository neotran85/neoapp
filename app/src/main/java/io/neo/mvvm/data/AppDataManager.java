package io.neo.mvvm.data;

import android.content.Context;

import io.neo.mvvm.AppConstants;
import io.neo.mvvm.data.local.db.DbHelper;
import io.neo.mvvm.data.local.prefs.PreferencesHelper;
import io.neo.mvvm.data.model.api.BlogResponse;
import io.neo.mvvm.data.model.api.OpenSourceResponse;
import io.neo.mvvm.data.model.api.account.LoginRequest;
import io.neo.mvvm.data.model.api.account.LoginResponse;
import io.neo.mvvm.data.model.api.account.LogoutResponse;
import io.neo.mvvm.data.model.api.account.SignUpRequest;
import io.neo.mvvm.data.model.api.account.SignUpResponse;
import io.neo.mvvm.data.model.api.service.AppointmentCreateRequest;
import io.neo.mvvm.data.model.api.service.AppointmentCreateResponse;
import io.neo.mvvm.data.model.api.service.AppointmentDeleteRequest;
import io.neo.mvvm.data.model.api.service.AppointmentDeleteResponse;
import io.neo.mvvm.data.model.api.service.AppointmentGetRequest;
import io.neo.mvvm.data.model.api.service.OrderCompletedRequest;
import io.neo.mvvm.data.model.api.service.OrderCompletedResponse;
import io.neo.mvvm.data.model.api.service.OrderEditRequest;
import io.neo.mvvm.data.model.api.service.OrderGetRequest;
import io.neo.mvvm.data.model.api.service.ReceiptGetRequest;
import io.neo.mvvm.data.model.db.AppyService;
import io.neo.mvvm.data.model.db.AppyServiceCategory;
import io.neo.mvvm.data.model.db.ServiceAddress;
import io.neo.mvvm.data.model.db.ServiceOrderUserInput;
import io.neo.mvvm.data.model.db.User;
import io.neo.mvvm.data.remote.ApiHeader;
import io.neo.mvvm.data.remote.ApiHelper;
import io.neo.mvvm.utils.helper.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

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
    public Single<BlogResponse> getBlogApiCall() {
        return mApiHelper.getBlogApiCall();
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return mApiHelper.getOpenSourceApiCall();
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
    public ServiceAddress loadServiceAddress() {
        return mPreferencesHelper.loadServiceAddress();
    }

    @Override
    public void saveServiceAddress(ServiceAddress address) {
        mPreferencesHelper.saveServiceAddress(address);
    }

    @Override
    public Single<AppointmentCreateResponse> createAppointment(AppointmentCreateRequest dataRequest) {
        return mApiHelper.createAppointment(dataRequest);
    }

    @Override
    public Single<JSONObject> getAppointmentAll() {
        return mApiHelper.getAppointmentAll();
    }

    @Override
    public Single<JSONObject> getAppointment(AppointmentGetRequest request) {
        return mApiHelper.getAppointment(request);
    }

    @Override
    public Single<AppointmentDeleteResponse> deleteAppointment(AppointmentDeleteRequest request) {
        return mApiHelper.deleteAppointment(request);
    }

    @Override
    public Single<JSONObject> getOrder(OrderGetRequest request) {
        return mApiHelper.getOrder(request);
    }

    @Override
    public Single<JSONObject> getOrderAll() {
        return mApiHelper.getOrderAll();
    }

    @Override
    public Single<JSONObject> getReceipt(ReceiptGetRequest request) {
        return mApiHelper.getReceipt(request);
    }

    @Override
    public Single<JSONObject> getReceiptAll() {
        return mApiHelper.getReceiptAll();
    }

    @Override
    public Single<OrderCompletedResponse> markOrderCompleted(OrderCompletedRequest request) {
        return mApiHelper.markOrderCompleted(request);
    }

    @Override
    public Single<JSONObject> getUserProfile() {
        return mApiHelper.getUserProfile();
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
    public Single<JSONObject> editOrder(OrderEditRequest request) {
        return mApiHelper.editOrder(request);
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
    public Observable<ArrayList<AppyServiceCategory>> seedDatabaseCategories() {
        return Observable.fromCallable(new Callable<ArrayList<AppyServiceCategory>>() {
            @Override
            public ArrayList<AppyServiceCategory> call() throws Exception {
                Type type = new TypeToken<ArrayList<AppyServiceCategory>>() {}.getType();
                GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                final Gson gson = builder.create();
                ArrayList<AppyServiceCategory> appyServiceCategories = gson.fromJson(
                        CommonUtils.loadJSONFromAsset(mContext,
                                AppConstants.SEED_DATABASE_CATEGORIES),
                        type);
                return appyServiceCategories;
            }
        });
    }

    @Override
    public Observable<ArrayList<AppyService>> seedDatabaseServices() {
        return Observable.fromCallable(new Callable<ArrayList<AppyService>>() {
            @Override
            public ArrayList<AppyService> call() throws Exception {
                Type type = new TypeToken<ArrayList<AppyService>>() {}.getType();
                GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                Gson gson = builder.create();
                ArrayList<AppyService> arrayList  = gson.fromJson(
                        CommonUtils.loadJSONFromAsset(mContext,
                                AppConstants.SEED_DATABASE_SERVICES),
                        type);
                return arrayList;
            }
        });
    }

    @Override
    public ServiceOrderUserInput getServiceOrderUserInput() {
        return ServiceOrderUserInput.getInstance();
    }
}