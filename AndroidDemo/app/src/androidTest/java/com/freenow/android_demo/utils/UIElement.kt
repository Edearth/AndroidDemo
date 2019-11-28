package com.freenow.android_demo.utils

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matcher

class UIElement(val locator: Matcher<View>) {

    private val element: ViewInteraction = onView(locator)

    fun click() {
        element.perform(ViewActions.click())
    }

    fun typeText(text: String) {
        element.perform(ViewActions.clearText(), ViewActions.typeText(text), ViewActions.closeSoftKeyboard())
    }

    fun withText(text: String) {
        element.check(matches(ViewMatchers.withText(text)))
    }

    fun contains(text: String) {
        element.check(matches(ViewMatchers.withText(containsString(text))))
    }

    fun isDisplayed() {
        element.check(matches(ViewMatchers.isDisplayed()))
    }

    fun perform(viewAction: ViewAction) {
        element.perform(viewAction)
    }

    fun check(viewAssertion: ViewAssertion) {
        element.check(viewAssertion)
    }
}
