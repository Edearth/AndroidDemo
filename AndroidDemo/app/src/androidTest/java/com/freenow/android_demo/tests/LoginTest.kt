package com.freenow.android_demo.tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.freenow.android_demo.activities.MainActivity
import com.freenow.android_demo.pages.AuthPage
import com.freenow.android_demo.pages.MainPage
import com.freenow.android_demo.utils.CustomDispatcher
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
        mockWebServer.setDispatcher(CustomDispatcher())
    }

    private fun test() {
        AuthPage.login("crazydog335", "venture")
        MainPage.at()
    }

    @Test
    fun login0() {
        test()
    }

    @Test
    fun login1() {
        test()
    }

    @Test
    fun login2() {
        test()
    }

    @Test
    fun login3() {
        test()
    }

    @Test
    fun login4() {
        test()
    }

    @Test
    fun login5() {
        test()
    }

    @Test
    fun login6() {
        test()
    }

    @Test
    fun login7() {
        test()
    }

    @Test
    fun login8() {
        test()
    }

    @Test
    fun login9() {
        test()
    }

}
