package com.example.onmars

import android.view.View
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.onmars.mvp.ui.adapter.RoversRVAdapter
import com.example.onmars.mvp.ui.fragments.RoversFragment
import kotlinx.android.synthetic.main.fragment_rovers.*
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoversFragmentTest {

    private lateinit var scenario: FragmentScenario<RoversFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_Visible_TextView_Test() {
        scenario.onFragment {
            val tv = it.tv_rovers
            Assert.assertEquals(tv.isVisible, true)
        }
    }

    @Test
    fun fragment_isDisplayed_TextView_Test() {
        onView(withId(R.id.tv_rovers)).check(matches(isDisplayed()))
    }

    @Test
    fun fragment_isCompletelyDisplayed_TextView_Test() {
        onView(withId(R.id.tv_rovers)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun test_EqualsButton_text1() {
        onView(withId(R.id.rv_rovers)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RoversRVAdapter.ViewHolder>(
                0,
                equalsText(R.id.button_rover, "curiosity")
            )
        )
    }

    @Test
    fun test_EqualsButton_text2() {
        onView(withId(R.id.rv_rovers)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RoversRVAdapter.ViewHolder>(
                0,
                equalsText(R.id.button_rover, "spirit")
            )
        )
    }

    @Test
    fun test_EqualsButton_text3() {
        onView(withId(R.id.rv_rovers)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RoversRVAdapter.ViewHolder>(
                0,
                equalsText(R.id.button_rover, "opportunity")
            )
        )
    }

    @Test
    fun test_EqualsButton_text4() {
        onView(withId(R.id.rv_rovers)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RoversRVAdapter.ViewHolder>(
                0,
                equalsText(R.id.button_rover, "perseverance")
            )
        )
    }

    private fun equalsText(id: Int, text: String) = object : ViewAction {
        override fun getConstraints(): Matcher<View>? {
            return null
        }

        override fun getDescription(): String {
            return "Нажимаем на view с указанным id"
        }

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById(id) as Button
            Assert.assertEquals(v.text.toString().toLowerCase(), text)
        }
    }
}