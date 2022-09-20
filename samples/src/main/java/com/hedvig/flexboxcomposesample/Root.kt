package com.hedvig.flexboxcomposesample

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hedvig.flexboxcompose.*

@Composable
fun ExpandingContent() {
    var isOpen by remember {
        mutableStateOf(false)
    }

    Column {
        Button(onClick = {
            isOpen = !isOpen
        }) {
            Text("Open")
        }

        if (isOpen) {
            Text("Hellodldlskdskldskldskldskldskldsklsdkldskldskldskldskldksldslkdskldslkdklsdskldskldskldskldskldskldskldslkdslkdskldskldskldskldskldskldskldskldslkdslkdslk")
        }
    }
}

@Composable
fun Root() {
    var modifySize by remember {
        mutableStateOf(false)
    }

    val loremIpsum = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum condimentum eu diam id facilisis. Integer tempus libero nec velit condimentum iaculis. Pellentesque aliquam quam suscipit urna tincidunt egestas. Fusce vitae justo eget est cursus fermentum eget ac turpis. Etiam ut est sed ante consequat aliquam. Etiam ac porta eros. Etiam placerat, mauris nec sagittis tempor, mi enim ornare dolor, in lobortis urna tortor id leo.

    Nunc ligula nisi, gravida ac aliquam quis, blandit at lacus. Nunc posuere, mauris eu viverra eleifend, neque eros pellentesque nibh, non imperdiet ante diam nec ante. Proin a lectus mollis, pellentesque nisl in, feugiat tortor. Nulla luctus facilisis ligula a venenatis. Nullam quis consectetur turpis. Phasellus id elementum nisi. Morbi quis purus porttitor, bibendum justo sit amet, cursus arcu. Nullam porttitor odio in quam suscipit, eget cursus magna egestas. Suspendisse sed sem eget nunc mattis maximus. Morbi interdum, purus id laoreet pharetra, enim lectus ornare lorem, id dignissim orci ipsum eget tellus.
    """

    val height: Float by animateFloatAsState(
        if (modifySize) 100f else 50f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessLow)
    )

    var scrollState = rememberScrollState()

    Column(
        modifier = Modifier.height(300.dp)
    ) {
        FlexRoot(
            flexDirection = FlexDirection.ROW,
            size = FlexSize(height = percent(height), width = percent(100f)),
            modifier = Modifier.background(Color.Black),
            justifyContent = JustifyContent.START,
            padding = all(constant(5f))
        ) {
            FlexNode(
                flexGrow = 1f,
                flexShrink = 0f,
                modifier = Modifier.background(Color.Red)
            ) {
                FlexRoot(
                    flexibleAxies = listOf(Axis.HORIZONTAL, Axis.VERTICAL),
                    flexDirection = FlexDirection.ROW,
                    modifier = Modifier.background(Color.Cyan),
                    size = FlexSize(height = auto(), width = auto()),
                    flexGrow = 1f,
                    flexShrink = 0f
                ) {
                    FlexNode(
                        flexGrow = 1f,
                        flexShrink = 0f
                    ) {
                        Text("Hello")
                    }

                    FlexNode(
                        flexGrow = 1f,
                        flexShrink = 0f
                    ) {
                        Button(onClick = {
                            modifySize = !modifySize
                        }) {
                            Text("Hello")
                        }
                    }
                }
            }
            FlexNode(
                flexGrow = 0f,
                flexShrink = 0f,
                modifier = Modifier.background(Color.Yellow),
                padding = Edges(
                    constant(16f)
                )
            ) {
                Text(
                    "Hello",
                    Modifier.background(Color.Cyan)
                        .fillMaxHeight()
                )
            }
            FlexNode(
                flexGrow = 0f,
                flexShrink = 0f,
                modifier = Modifier.background(Color.Yellow),
                padding = all(constant(15f))
            ) {
                Text(
                    "Hello",
                    Modifier.background(Color.Cyan)
                        .fillMaxHeight()
                )
            }
        }
    }
}
