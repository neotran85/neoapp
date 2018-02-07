package io.neo.mvvm.data.remote;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.neo.mvvm.data.model.api.account.LoginRequest;
import io.neo.mvvm.data.model.api.account.LoginResponse;
import io.neo.mvvm.data.model.api.account.LogoutResponse;
import io.neo.mvvm.data.model.api.account.SignUpRequest;
import io.neo.mvvm.data.model.api.account.SignUpResponse;
import io.reactivex.Single;


@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<LoginResponse> doUserLogin(LoginRequest.ServerLoginRequest
                                                     request) {
        return Rx2AndroidNetworking.post(ApiUrlConfig.USER_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<SignUpResponse> doUserSignUp(SignUpRequest request) {
        return Rx2AndroidNetworking.post(ApiUrlConfig.USER_SIGN_UP)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(SignUpResponse.class);
    }


    @Override
    public Single<LogoutResponse> doUserLogout() {
        return Rx2AndroidNetworking.post(ApiUrlConfig.USER_LOGOUT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

}