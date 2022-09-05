package com.hedvig.flexboxcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Test

class FlexGrowTest : FlexScreenshotTest() {
    @Test
    fun testEqual() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    justifyContent = JustifyContent.CENTER
                ) {
                    FlexNode(
                        size = FlexSize(width = constant(10f), height = auto()),
                        flexGrow = 1f,
                        flexShrink = 1f,
                        modifier = Modifier.background(Color.Red)
                    )
                    FlexNode(
                        size = FlexSize(width = constant(10f), height = auto()),
                        flexGrow = 1f,
                        flexShrink = 1f,
                        modifier = Modifier.background(Color.Blue)
                    )
                    FlexNode(
                        size = FlexSize(width = constant(10f), height = auto()),
                        flexGrow = 1f,
                        flexShrink = 1f,
                        modifier = Modifier.background(Color.Green)
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun testNonEqual() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    justifyContent = JustifyContent.CENTER
                ) {
                    FlexNode(
                        size = FlexSize(width = constant(10f), height = auto()),
                        flexGrow = 2f,
                        flexShrink = 1f,
                        modifier = Modifier.background(Color.Red)
                    )
                    FlexNode(
                        size = FlexSize(width = constant(10f), height = auto()),
                        flexGrow = 1f,
                        flexShrink = 1f,
                        modifier = Modifier.background(Color.Blue)
                    )
                    FlexNode(
                        size = FlexSize(width = constant(10f), height = auto()),
                        flexGrow = 1f,
                        flexShrink = 1f,
                        modifier = Modifier.background(Color.Green)
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun testNonEqualTwo() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    justifyContent = JustifyContent.CENTER
                ) {
                    FlexNode(
                        size = FlexSize(width = constant(10f), height = auto()),
                        flexGrow = 2f,
                        flexShrink = 1f,
                        modifier = Modifier.background(Color.Red)
                    )
                    FlexNode(
                        size = FlexSize(width = constant(10f), height = auto()),
                        flexGrow = 1f,
                        flexShrink = 1f,
                        modifier = Modifier.background(Color.Blue)
                    )
                    FlexNode(
                        size = FlexSize(width = constant(10f), height = auto()),
                        flexGrow = 2f,
                        flexShrink = 1f,
                        modifier = Modifier.background(Color.Green)
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }
}