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
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

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

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiUrlConfig.ENDPOINT_BLOG)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(BlogResponse.class);
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiUrlConfig.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(OpenSourceResponse.class);
    }

    @Override
    public Single<AppointmentCreateResponse> createAppointment(AppointmentCreateRequest dataRequest) {
        return Rx2AndroidNetworking.post(ApiUrlConfig.APPOINTMENT_CREATE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(dataRequest)
                .build()
                .getObjectSingle(AppointmentCreateResponse.class);
    }

    @Override
    public Single<JSONObject> getAppointment(AppointmentGetRequest request) {
        return Rx2AndroidNetworking.post(ApiUrlConfig.APPOINTMENT_GET)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getJSONObjectSingle();
    }

    @Override
    public Single<JSONObject> getAppointmentAll() {
        return Rx2AndroidNetworking.post(ApiUrlConfig.APPOINTMENT_GET)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getJSONObjectSingle();
    }

    @Override
    public Single<AppointmentDeleteResponse> deleteAppointment(AppointmentDeleteRequest request) {
        return Rx2AndroidNetworking.post(ApiUrlConfig.APPOINTMENT_DELETE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(request)
                .build()
                .getObjectSingle(AppointmentDeleteResponse.class);
    }

    @Override
    public Single<JSONObject> getOrder(OrderGetRequest request) {
        return Rx2AndroidNetworking.post(ApiUrlConfig.ORDER_GET)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getJSONObjectSingle();
    }

    @Override
    public Single<JSONObject> getOrderAll() {
        return Rx2AndroidNetworking.post(ApiUrlConfig.ORDER_GET)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getJSONObjectSingle();
    }

    @Override
    public Single<JSONObject> getReceiptAll() {
        return Rx2AndroidNetworking.post(ApiUrlConfig.RECEIPT_GET)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getJSONObjectSingle();
    }

    @Override
    public Single<OrderCompletedResponse> markOrderCompleted(OrderCompletedRequest request) {
        return Rx2AndroidNetworking.post(ApiUrlConfig.ORDER_COMPLETED)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(request)
                .build()
                .getObjectSingle(OrderCompletedResponse.class);
    }
    @Override
    public Single<JSONObject> getUserProfile() {
        return Rx2AndroidNetworking.post(ApiUrlConfig.USER_GET)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getJSONObjectSingle();
    }
    @Override
    public Single<JSONObject> getReceipt(ReceiptGetRequest request) {
        return Rx2AndroidNetworking.post(ApiUrlConfig.RECEIPT_GET)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getJSONObjectSingle();
    }

    @Override
    public Single<JSONObject> editOrder(OrderEditRequest request) {
        return Rx2AndroidNetworking.post(ApiUrlConfig.ORDER_EDIT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getJSONObjectSingle();
    }
}