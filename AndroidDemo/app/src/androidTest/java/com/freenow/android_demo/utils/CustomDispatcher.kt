package com.freenow.android_demo.utils

import android.util.Log
import com.squareup.okhttp.mockwebserver.Dispatcher
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.RecordedRequest

class CustomDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        val path = request.path
        Log.d("Test", "Got this path: $path")
        return when {
            DRIVER_LIST_MATCHER.matches(path) -> {
                Log.d("Test", "Responded with Driver list")
                MockResponse().setResponseCode(200).setBody(Responses.DRIVER_LIST)
            }
            USER_MATCHER.matches(path) -> {
                Log.d("Test", "Responded with User")
                MockResponse().setResponseCode(200).setBody(Responses.VALID_USER)
            }
            else -> MockResponse().setResponseCode(404)
        }
    }

    companion object {
        private val DRIVER_LIST_MATCHER = Regex("^/\\?results=[0-9]+&seed=.*$")
        private val USER_MATCHER = Regex("^/\\?seed=.*$")
    }
}
