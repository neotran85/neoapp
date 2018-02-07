package io.neo.mvvm.data.remote;

import io.neo.mvvm.data.model.api.account.LoginRequest;
import io.neo.mvvm.data.model.api.account.LoginResponse;
import io.neo.mvvm.data.model.api.account.LogoutResponse;
import io.neo.mvvm.data.model.api.account.SignUpRequest;
import io.neo.mvvm.data.model.api.account.SignUpResponse;
import io.reactivex.Single;


public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<LoginResponse> doUserLogin(LoginRequest.ServerLoginRequest request);

    Single<SignUpResponse> doUserSignUp(SignUpRequest request);

    Single<LogoutResponse> doUserLogout();
}
