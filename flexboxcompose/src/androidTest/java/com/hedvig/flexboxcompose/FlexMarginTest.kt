package com.hedvig.flexboxcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Test

class FlexMarginTest : FlexScreenshotTest() {
    @Test
    fun testMargins() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    flexDirection = FlexDirection.COLUMN
                ) {
                    FlexNode(
                        size = FlexSize(width = auto(), height = constant(1f)),
                        flexGrow = 1f,
                        modifier = Modifier.background(Color.Red),
                        margin = Edges(
                            leading = undefined(),
                            trailing = constant(10f),
                            top = constant(10f),
                            bottom = constant(10f)
                        )
                    )
                    FlexNode(
                        size = FlexSize(width = auto(), height = constant(1f)),
                        flexGrow = 1f,
                        modifier = Modifier.background(Color.Blue)
                    )
                }
            }
        }

        compareScreenshot(composeTestRule)
    }
}
