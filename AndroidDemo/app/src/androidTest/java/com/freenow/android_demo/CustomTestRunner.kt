package com.freenow.android_demo

import android.os.Bundle
import android.support.test.runner.AndroidJUnitRunner

class CustomTestRunner : AndroidJUnitRunner() {

    /**
     * Espresso sends analytics data to Google after all tests by default. This can cause
     * a delay after the test is completed when it tries to send the data and it can't reach
     * Google's server (specially if you're using the VPN and are working from home). Some
     * times it can take over a minute for it to time out. We have to disable it so tests
     * can finish faster (right after the test finishes).
     *
     * Bear in mind this will only be effective as long as we use this test runner in the
     * App's build.gradle file. Example:
     *
     * android {
     *   defaultConfig {
     *     testInstrumentationRunner 'com.freenow.android_demo.CustomTestRunner'
     *   }
     * }
     */
    override fun onCreate(arguments: Bundle) {
        arguments.putString("disableAnalytics", "true")
        super.onCreate(arguments)
    }
}
