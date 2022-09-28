package com.hedvig.flexboxcomposesample

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
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

    val loremIpsum =
        "Lorem ipsum dolor sit amet, Integer tempus libero nec velit condimentum iaculis. lentesque nisl in, feugiat tortor. Nulla luctus facilisis ligula a venenatis. Nullam quis consectetur turpis. Phasellus id elementum nisi. Morbi quis purus porttitor, bibendum justo sit amet, cursus arcu. Nullam porttitor odio in quam suscipit, eget cursus magna egestas. Suspendisse sed sem eget nunc mattis maximus. Morbi interdum, purus id laoreet pharetra, enim lectus ornare lorem, id dignissim orci ipsum eget tellus."

    val height: Float by animateFloatAsState(
        if (modifySize) 10f else 0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessLow)
    )

    var scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        FlexRoot(
            flexDirection = FlexDirection.COLUMN,
            flexibleAxies = listOf(Axis.VERTICAL),
            modifier = Modifier.background(Color.Red),
            justifyContent = JustifyContent.START
        ) {
            FlexNode {
                Button(onClick = {
                    modifySize = !modifySize
                }) {
                    Text("Test")
                }
            }

            FlexNode {
                FlexRoot(
                    flexibleAxies = listOf(Axis.VERTICAL)
                ) {
                    FlexNode(
                        flexGrow = 0f,
                        flexShrink = 0f,
                        padding = Edges(
                            leading = constant(80f),
                            bottom = constant(20f),
                            trailing = constant(80f),
                            top = constant(if (modifySize) 20f else 0f)
                        )
                    ) {
                        Text(loremIpsum, modifier = Modifier.padding(max(height.dp, 0.dp)).background(Color.White))
                    }
                }
            }

            FlexNode(
                flexGrow = 1f,
                flexShrink = 0f
            ) {
                FlexRoot(
                    flexDirection = FlexDirection.COLUMN,
                    flexibleAxies = listOf(Axis.VERTICAL),
                    modifier = Modifier.background(Color.Yellow),
                    justifyContent = JustifyContent.START
                ) {
                    FlexNode {
                        ExpandingContent()
                    }
                }
            }
        }
    }
}
