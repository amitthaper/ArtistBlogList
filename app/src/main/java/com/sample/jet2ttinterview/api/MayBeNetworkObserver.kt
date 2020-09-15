package com.sample.jet2ttinterview.api

import android.util.Log
import io.reactivex.observers.DisposableMaybeObserver
import retrofit2.HttpException
import java.net.ConnectException


open class MaybeNetworkObserver<T> : DisposableMaybeObserver<T>() {

    override fun onSuccess(value: T) {
        Log.d("Success Message", "API Call Success")
    }

    override fun onError(errorThrowable: Throwable) {
        when(errorThrowable) {
            is HttpException -> {
                Log.e("Error Message", errorThrowable.message)
            }
            is ConnectException -> {
                Log.e("Error Message", "Unable to connect with the server")
            }
        }

    }

    override fun onComplete() {
        Log.d("Success Message", "API Call Completed")
    }
}