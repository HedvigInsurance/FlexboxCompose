package com.hedvig.flexboxcompose

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.facebook.soloader.SoLoader
import com.karumi.shot.ScreenshotTest
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class FlexScreenshotTest : ScreenshotTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        SoLoader.init(context, false)
    }

    @After
    fun destroy() {
        SoLoader.deinitForTest()
    }
}