package com.freenow.android_demo.tests

import android.support.test.espresso.Espresso
import android.support.test.rule.GrantPermissionRule
import com.freenow.android_demo.DaggerTestAppComponent
import com.freenow.android_demo.utils.ClearPreferencesRule
import com.freenow.android_demo.utils.network.HttpClient
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

abstract class BaseTest {

    @Inject
    lateinit var httpClient : HttpClient

    open val mockWebServer = MockWebServer()

    @Rule
    @JvmField
    var grantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )!!

    @Rule
    @JvmField
    var clearPreferencesRule = ClearPreferencesRule()


    @Before
    fun setUpDependencies() {
        DaggerTestAppComponent.builder().build().inject(this)
    }

    @Before
    fun startMockWebServer() {
        Espresso.registerIdlingResources(httpClient.httpIdlingResource)
        mockWebServer.play(8080)
    }

    @After
    fun stopMockWebServer() {
        Espresso.unregisterIdlingResources(httpClient.httpIdlingResource)
        mockWebServer.shutdown()
    }

}
