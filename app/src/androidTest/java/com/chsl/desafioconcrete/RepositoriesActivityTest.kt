package com.chsl.desafioconcrete


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.chsl.desafioconcrete.presentation.view.activities.RepositoriesActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RepositoriesActivityTest {

    @get:Rule
    val rule = ActivityTestRule<RepositoriesActivity>(RepositoriesActivity::class.java)


    @Test
    fun shouldShowItemsFromList(){
        onView(withId(R.id.rvRepositories))
            .perform(click())

    }
}