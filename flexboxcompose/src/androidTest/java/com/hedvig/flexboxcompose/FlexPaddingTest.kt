package com.hedvig.flexboxcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Test

class FlexPaddingTest : FlexScreenshotTest() {
    @Test
    fun testPadding() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f)),
                    flexDirection = FlexDirection.ROW
                ) {
                    FlexNode(
                        size = FlexSize(width = auto(), height = auto()),
                        flexGrow = 0f,
                        flexShrink = 0f,
                        padding = Edges(
                            leading = constant(50f),
                            trailing = constant(50f)
                        )
                    ) {
                        Text("Padding", modifier = Modifier.background(Color.Red))
                    }
                    FlexNode(
                        size = FlexSize(width = auto(), height = auto()),
                        flexGrow = 0f,
                        flexShrink = 0f,
                        padding = Edges(
                            leading = constant(50f),
                            top = constant(50f)
                        )
                    ) {
                        Text("Padding", modifier = Modifier.background(Color.Red))
                    }
                }
            }
        }

        compareScreenshot(composeTestRule)
    }
}