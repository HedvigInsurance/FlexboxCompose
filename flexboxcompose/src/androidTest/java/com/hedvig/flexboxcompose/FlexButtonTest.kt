package com.hedvig.flexboxcompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.junit.Test

class FlexButtonTest : FlexScreenshotTest() {
    @Test
    fun testButtonContent() {
        composeTestRule.setContent {
            Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = percent(100f))
                ) {
                    FlexNode(
                        flexGrow = 1f
                    ) {
                        Button(onClick = {}) {
                            Text("Some button text")
                        }
                    }

                    FlexNode(
                        flexGrow = 1f
                    ) {
                        Button(onClick = {}) {
                            Text("Some other text")
                        }
                    }
                }
            }
        }

        compareScreenshot(composeTestRule)
    }
}
