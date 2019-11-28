package com.freenow.android_demo.tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.freenow.android_demo.activities.MainActivity
import com.freenow.android_demo.pages.AuthPage
import com.freenow.android_demo.pages.MainPage
import com.freenow.android_demo.utils.Responses
import com.squareup.okhttp.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUpHttpResponses() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(Responses.DRIVER_LIST))
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(Responses.VALID_USER))
    }

    @Test
    fun login0() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login1() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login2() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login3() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login4() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login5() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login6() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login7() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login8() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login9() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

}
