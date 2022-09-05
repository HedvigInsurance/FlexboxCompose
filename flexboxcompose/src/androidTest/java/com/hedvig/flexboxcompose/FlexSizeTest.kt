package com.hedvig.flexboxcompose

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Test

class FlexSizeTest : FlexScreenshotTest() {
    @Test
    fun testHalfHeight() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(50f))
                ) {
                    FlexNode(
                        size = FlexSize(width = auto(), height = auto()),
                        modifier = Modifier.background(Color.Red),
                        flexGrow = 1f
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun testHalfWidth() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(800.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(50f), height = percent(100f))
                ) {
                    FlexNode(
                        size = FlexSize(width = auto(), height = auto()),
                        modifier = Modifier.background(Color.Red),
                        flexGrow = 1f
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }
}
