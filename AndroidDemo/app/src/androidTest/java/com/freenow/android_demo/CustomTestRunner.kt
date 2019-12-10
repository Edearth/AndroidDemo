package com.freenow.android_demo

import android.os.Bundle
import android.support.test.runner.AndroidJUnitRunner

class CustomTestRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle) {
        arguments.putString("disableAnalytics", "true")
        super.onCreate(arguments)
    }
}
