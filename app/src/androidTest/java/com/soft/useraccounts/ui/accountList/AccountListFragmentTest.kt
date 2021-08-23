package com.soft.useraccounts.ui.accountList

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.soft.useraccounts.MainActivity
import com.soft.useraccounts.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccountListFragmentTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testSearchLogin() {
        onView(ViewMatchers.withId(R.id.appSearchBar)).perform(typeText("App"))
        onView(ViewMatchers.withId(R.id.appSearchBar))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun testTogglePassword() {
//       onView(ViewMatchers.withId(R.id.recycler_accounts))
//           .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()))
//
//        val middleElementText = rule.activity.resources
//            .getString(R.id.imgPassOff)
//        onView(withText(middleElementText)).check(matches(isDisplayed()))
    }

    @Test
    fun testMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
    }
}