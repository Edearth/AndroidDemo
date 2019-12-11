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
import org.junit.Assert.*
import kotlin.random.Random

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
    fun loginFlaky0() {
        assertEquals(1, Random.nextInt(0,2))
        test()
    }

    @Test
    fun loginFlaky1() {
        assertEquals(1, Random.nextInt(0,2))
        test()
    }

    @Test
    fun loginFlaky2() {
        assertEquals(1, Random.nextInt(0,2))
        test()
    }

    @Test
    fun loginFlaky3() {
        assertEquals(1, Random.nextInt(0,2))
        test()
    }

    @Test
    fun loginFailing0() {
        assertEquals(1, 2)
        test()
    }

    @Test
    fun loginFailing1() {
        assertEquals(1, 2)
        test()
    }

}
