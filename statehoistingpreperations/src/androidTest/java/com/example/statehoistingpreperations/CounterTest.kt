package com.example.statehoistingpreperations

import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.statehoistingpreperations.staterestorationtester.Counter

import org.junit.Rule
import org.junit.Test

class CounterTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counter_stateRestoration(){

        val stateRestorationTester = StateRestorationTester(composeTestRule)

        // Set the content to be tested
        stateRestorationTester.setContent {
            Counter()
        }

        // Perform actions
        composeTestRule.onNodeWithText("Increment").performClick()
        composeTestRule.onNodeWithText("Count: 1").assertExists()

        // Emulate activity recreation
        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithText("Count: 1").assertExists()


    }
}