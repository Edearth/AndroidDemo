package com.freenow.android_demo.pages

import android.support.test.espresso.matcher.ViewMatchers.withId
import com.freenow.android_demo.R
import com.freenow.android_demo.utils.UIElement

object AuthPage {
    private val usernameField = UIElement(withId(R.id.edt_username))
    private val passwordField = UIElement(withId(R.id.edt_password))
    private val loginButton = UIElement(withId(R.id.btn_login))

    fun login(username: String, password: String) {
        usernameField.typeText(username)
        passwordField.typeText(password)
        loginButton.click()
    }
}
