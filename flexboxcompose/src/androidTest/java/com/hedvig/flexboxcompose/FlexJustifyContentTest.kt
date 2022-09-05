package com.hedvig.flexboxcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Test

class FlexJustifyContentTest : FlexScreenshotTest() {
    @Test
    fun testCenter() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    justifyContent = JustifyContent.CENTER
                ) {
                    FlexNode(
                        size = FlexSize(width = percent(20f), height = percent(100f)),
                        modifier = Modifier.background(Color.Red)
                    ) { }
                }
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun testStart() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    justifyContent = JustifyContent.START
                ) {
                    FlexNode(
                        size = FlexSize(width = percent(20f), height = percent(100f)),
                        modifier = Modifier.background(Color.Red)
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun testEnd() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    justifyContent = JustifyContent.END
                ) {
                    FlexNode(
                        size = FlexSize(width = percent(20f), height = percent(100f)),
                        modifier = Modifier.background(Color.Red)
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }
}
