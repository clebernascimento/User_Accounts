package com.soft.useraccounts.ui.accountList

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.soft.useraccounts.MainActivity
import com.soft.useraccounts.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccountFragmentTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testButtonSave() {
        onView(withId(R.id.fabAddAccounts)).perform(click())

        onView(withId(R.id.input_name)).perform(typeText("Teste"))
        onView(withId(R.id.input_user)).perform(typeText("Teste"))
        onView(withId(R.id.input_pass)).perform(typeText("123456789"))
        onView(withId(R.id.input_description)).perform(typeText("Teste"), closeSoftKeyboard())

        onView(withId(R.id.bt_account)).perform(click())
    }

    @Test
    fun testButtonUpdate() {
        onView(withId(R.id.recycler_accounts)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AccountAdapter.AccountViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.input_name)).perform(clearText(), typeText("Teste1"))
        onView(withId(R.id.input_user)).perform(clearText(), typeText("Teste1"))
        onView(withId(R.id.input_pass)).perform(clearText(), typeText("123456"))
        onView(withId(R.id.input_description)).perform(
            clearText(),
            typeText("Teste1"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.bt_account)).perform(click())
    }

    @Test
    fun testButtonDelete() {
        onView(withId(R.id.recycler_accounts)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AccountAdapter.AccountViewHolder>(
                1,
                click()
            )
        )

        onView(withId(R.id.bt_delete)).perform(click())
    }
}