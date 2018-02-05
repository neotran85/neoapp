package io.neo.mvvm.data.remote;

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

import org.json.JSONObject;

import io.reactivex.Single;


public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<LoginResponse> doUserLogin(LoginRequest.ServerLoginRequest request);

    Single<SignUpResponse> doUserSignUp(SignUpRequest request);

    Single<LogoutResponse> doUserLogout();

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();

    Single<AppointmentCreateResponse> createAppointment(AppointmentCreateRequest dataRequest);

    Single<JSONObject> getAppointment(AppointmentGetRequest request);

    Single<JSONObject> getOrder(OrderGetRequest request);

    Single<JSONObject> getReceipt(ReceiptGetRequest request);

    Single<JSONObject> getOrderAll();

    Single<OrderCompletedResponse> markOrderCompleted(OrderCompletedRequest request);

    Single<JSONObject> getReceiptAll();

    Single<AppointmentDeleteResponse> deleteAppointment(AppointmentDeleteRequest request);

    Single<JSONObject> getAppointmentAll();

    Single<JSONObject> editOrder(OrderEditRequest request);

    Single<JSONObject> getUserProfile();

}
