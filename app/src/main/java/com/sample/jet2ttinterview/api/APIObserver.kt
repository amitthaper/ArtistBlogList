package com.sample.jet2ttinterview.api

import com.google.gson.JsonParser
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class APIObserver<T>(successMutableLiveData: (T) -> Unit, failureMutableLiveData: (Throwable) -> Unit): MaybeNetworkObserver<T>() {
    val responseSuccess = successMutableLiveData
    val responseError = failureMutableLiveData

    override fun onSuccess(value: T) {
        super.onSuccess(value)
        responseSuccess(value)
    }

    override fun onError(errorThrowable: Throwable) {
        super.onError(errorThrowable)
        if(errorThrowable is ConnectException) {
            responseError(Throwable("Unable to connect with remote server. Please try again later"))
        } else if(errorThrowable is SocketTimeoutException) {
            responseError(Throwable("No internet connection please try again"))
        } else {
            JsonParser().parse((errorThrowable as HttpException).response().errorBody()?.string()).asJsonObject["message"].asString
            responseError(errorThrowable)
        }
    }
}