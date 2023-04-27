package com.sophiahchiang.timefighter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun startsWithZeroInitialScore() {
        val controller = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .visible()
        var systemUnderTest = controller.get()
        val scoreTextView = systemUnderTest.findViewById(R.id.gameScoreTextView) as TextView

        assertEquals("Your Score: 0", scoreTextView.text)
    }

    @Test
    fun increasesScore_whenButtonPressed() {
        val controller = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .visible()
        val systemUnderTest = controller.get()
        val scoreTextView = systemUnderTest.findViewById(R.id.gameScoreTextView) as TextView
        val tapMebutton = systemUnderTest.findViewById(R.id.tapMeButton) as Button

        tapMebutton.performClick()

        assertEquals("Your Score: 1", scoreTextView.text)
    }
    @Test
    fun gameRetainsScore_whenDeviceRotates() {
        var controller = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .visible()
        var systemUnderTest = controller.get()

        var scoreTextView = systemUnderTest.findViewById(R.id.gameScoreTextView) as TextView
        val tapMeButton = systemUnderTest.findViewById(R.id.tapMeButton) as Button

        tapMeButton.performClick()
        tapMeButton.performClick()
        tapMeButton.performClick()

        assertEquals("Your Score: 3", scoreTextView.text)

        val bundle = Bundle()
        controller
            .saveInstanceState(bundle)
            .pause()
            .stop()
            .destroy()

        controller = Robolectric.buildActivity(MainActivity::class.java)
            .create(bundle)
            .start()
            .restoreInstanceState(bundle)
            .resume()
            .visible()
        systemUnderTest = controller.get()
        scoreTextView = systemUnderTest.findViewById(R.id.gameScoreTextView) as TextView
        assertEquals("Your Score: 3", scoreTextView.text)

    }

}