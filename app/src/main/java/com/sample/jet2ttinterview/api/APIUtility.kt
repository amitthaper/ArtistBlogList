package com.sample.jet2ttinterview.api

import com.google.gson.JsonParser
import retrofit2.HttpException
import java.net.ConnectException

object APIUtility {
    const val API_ERROR_MESSAGE = "Network not available. Please try again"
    const val CONNECT_ERROR = "Unable to connect with server. Please try again"

    fun getErrorMessage(apiError: Throwable): String? {
        when (apiError) {
            is HttpException -> {
                return handleHttpException(apiError)
            }
            is ConnectException -> {
                return CONNECT_ERROR
            }
            else -> {
                apiError.message?.let {
                    return apiError.message
                } ?: kotlin.run {
                    return API_ERROR_MESSAGE
                }
            }
        }
    }

    private fun handleHttpException(httpException: HttpException): String? {
        val errorJsonString = httpException.response().errorBody()?.string()
        val message: String?
        message = try {
            JsonParser().parse(errorJsonString)
                    .asJsonObject["message"]
                    .asString
        } catch (exception: Exception) {
            if (errorJsonString?.trim()?.isNotBlank()!!) {
                JsonParser().parse(errorJsonString)
                    .asJsonObject["message"]
                    .asJsonObject.get("response").toString()
            } else {
                ""
            }
        }
        return message
    }
}