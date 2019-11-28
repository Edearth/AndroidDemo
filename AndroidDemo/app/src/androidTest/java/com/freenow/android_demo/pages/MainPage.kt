package com.freenow.android_demo.pages

import android.support.test.espresso.matcher.ViewMatchers.withId
import com.freenow.android_demo.R
import com.freenow.android_demo.utils.UIElement

object MainPage {
    private val searchBar = UIElement(withId(R.id.textSearch))

    fun at() {
        searchBar.isDisplayed()
    }
}
