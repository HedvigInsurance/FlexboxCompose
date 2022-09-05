package com.hedvig.flexboxcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Test

class FlexDirectionTest : FlexScreenshotTest() {

    @Test
    fun testColumn() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    flexDirection = FlexDirection.COLUMN,
                    justifyContent = JustifyContent.CENTER
                ) {
                    FlexNode(
                        size = FlexSize(width = percent(100f), height = constant(10f)),
                        modifier = Modifier.background(Color.Red)
                    )

                    FlexNode(
                        size = FlexSize(width = percent(100f), height = constant(40f)),
                        modifier = Modifier.background(Color.Blue)
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun testColumnJustify() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    flexDirection = FlexDirection.COLUMN,
                    justifyContent = JustifyContent.START
                ) {
                    FlexNode(
                        size = FlexSize(width = percent(100f), height = constant(40f)),
                        modifier = Modifier.background(Color.Red)
                    )

                    FlexNode(
                        size = FlexSize(width = percent(100f), height = constant(40f)),
                        modifier = Modifier.background(Color.Blue)
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun testRow() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    flexDirection = FlexDirection.ROW,
                    justifyContent = JustifyContent.START
                ) {
                    FlexNode(
                        size = FlexSize(width = percent(50f), height = constant(40f)),
                        modifier = Modifier.background(Color.Red)
                    )

                    FlexNode(
                        size = FlexSize(width = percent(50f), height = auto()),
                        modifier = Modifier.background(Color.Blue)
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }
}
