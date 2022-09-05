package com.hedvig.flexboxcompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.junit.Test

class FlexDynamicSizingTest : FlexScreenshotTest() {
    val loremIpsum = """Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum condimentum eu diam id facilisis. Integer tempus libero nec velit condimentum iaculis. Pellentesque aliquam quam suscipit urna tincidunt egestas. Fusce vitae justo eget est cursus fermentum eget ac turpis. Etiam ut est sed ante consequat aliquam. Etiam ac porta eros. Etiam placerat, mauris nec sagittis tempor, mi enim ornare dolor, in lobortis urna tortor id leo.

Nunc ligula nisi, gravida ac aliquam quis, blandit at lacus. Nunc posuere, mauris eu viverra eleifend, neque eros pellentesque nibh, non imperdiet ante diam nec ante. Proin a lectus mollis, pellentesque nisl in, feugiat tortor. Nulla luctus facilisis ligula a venenatis. Nullam quis consectetur turpis. Phasellus id elementum nisi. Morbi quis purus porttitor, bibendum justo sit amet, cursus arcu. Nullam porttitor odio in quam suscipit, eget cursus magna egestas. Suspendisse sed sem eget nunc mattis maximus. Morbi interdum, purus id laoreet pharetra, enim lectus ornare lorem, id dignissim orci ipsum eget tellus."""

    @Test
    fun testCenter() {
        composeTestRule.setContent {
            Box(modifier = Modifier.width(300.dp)) {
                FlexRoot(
                    size = FlexSize(width = percent(100f), height = auto()),
                    flexibleAxies = listOf(Axis.VERTICAL)
                ) {
                    FlexNode(
                        size = FlexSize(width = percent(100f), height = auto())
                    ) {
                        Text(loremIpsum)
                    }
                }
            }
        }

        compareScreenshot(composeTestRule)
    }
}
