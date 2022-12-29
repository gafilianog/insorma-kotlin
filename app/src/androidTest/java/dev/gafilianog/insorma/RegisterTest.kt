package dev.gafilianog.insorma

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.gafilianog.insorma.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterTest {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun register() {
        onView(withId(R.id.tv_move_to_register))
            .perform(click())

        onView(withId(R.id.et_email_register))
            .perform(typeText("gafiliano@gmail.com"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.et_username_register))
            .perform(typeText("gafiliano"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.et_phone_register))
            .perform(typeText("08123456789"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.et_password_register))
            .perform(typeText("a2"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.btn_register))
            .perform(click())
    }
}