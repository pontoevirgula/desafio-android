package com.chsl.desafioconcrete


import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.chsl.desafioconcrete.presentation.view.activities.RepositoriesActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class RepositoriesActivityTest {

    private var mockWebServer = MockWebServer()


    @get:Rule
    val rule = ActivityTestRule<RepositoriesActivity>(RepositoriesActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        setupServer()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Throws(Exception::class)
    private fun setupServer() {
        mockWebServer = MockWebServer()
        val dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                try {
                    if (request.method == "GET" && request.path!!.contains("/search/repositories?q=language:Java&sort=stars&"))
                        return MockResponse().setResponseCode(200)
                } catch (e: IOException) {
                    Timber.e(e)
                    Log.e("ERRO", "falha no teste")
                }

                 return MockResponse().setResponseCode(200).setBody("")
            }
        }
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start(8080)
    }






    @Test
    fun shouldShowItemsFromList(){
        onView(withId(R.id.rvRepositories))
            .perform(click())
    }
}