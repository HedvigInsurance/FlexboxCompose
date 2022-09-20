package com.hedvig.flexboxcompose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import com.facebook.yoga.YogaNodeFactory

interface ApplyPaddingMeasurePolicy {
    var applyPadding: Boolean
}

@Composable
fun FlexNode(
    modifier: Modifier = Modifier,

    size: FlexSize = FlexSize(width = undefined(), height = undefined()),
    minSize: FlexSize = FlexSize(width = undefined(), height = undefined()),
    maxSize: FlexSize = FlexSize(width = undefined(), height = undefined()),

    // Specifies how flex-items are placed in the flex-container (defining the main-axis).
    // - Note: Applies to flex-container.
    flexDirection: FlexDirection = FlexDirection.ROW,

    // Specifies whether flex items are forced into a single line
    // or can be wrapped onto multiple lines.
    // - Note: Applies to flex-container.
    flexWrap: FlexWrap = FlexWrap.NO_WRAP,

    // Distributes space between and around flex-items along the main-axis.
    // - Note: Applies to flex-container.
    justifyContent: JustifyContent = JustifyContent.START,

    // Distributes space between and around flex-items along the cross-axis.
    // This works like `justifyContent` but in the perpendicular direction.
    // - Note: Applies to flex-container.
    alignItems: AlignItems = AlignItems.STRETCH,

    // Aligns a flex-container's lines when there is extra space on the cross-axis.
    // - Warning: This property has no effect on single line.
    // - Note: Applies to multi-line flex-container (no `FlexWrap.nowrap`).
    alignContent: AlignContent = AlignContent.STRETCH,

    // Aligns self (flex-item) by overriding it's parent's (flex-container) `alignItems`.
    // - Note: Applies to flex-item.
    alignSelf: AlignSelf = AlignSelf.AUTO,

    // Shorthand property specifying the ability of a flex-item
    // to alter its dimensions to fill available space.
    // - Note: Applies to flex-item.
    flex: Float = Float.NaN,

    // Grow factor of a flex-item.
    // - Note: Applies to flex-item.
    flexGrow: Float = Float.NaN,

    // Shrink factor of a flex-item.
    // - Note: Applies to flex-item.
    flexShrink: Float = Float.NaN,

    // Initial main size of a flex item.
    // - Note: Applies to flex-item.
    flexBasis: Float = Float.NaN,

    direction: Direction = Direction.INHERIT,
    overflow: Overflow = Overflow.VISIBLE,
    positionType: PositionType = PositionType.RELATIVE,

    // CSS's (top, right, bottom, left) that works with `positionType = .absolute`.
    position: Edges = Edges(),

    margin: Edges = Edges(),
    padding: Edges = Edges(),

    // facebook/yoga implementation that mostly works as same as `padding`.
    border: Edges = Edges(),

    content: @Composable () -> Unit = {}
) {
    val style = FlexStyle(
        size,
        minSize,
        maxSize,
        flexDirection,
        flexWrap,
        justifyContent,
        alignItems,
        alignContent,
        alignSelf,
        flex,
        flexGrow,
        flexShrink,
        flexBasis,
        direction,
        overflow,
        positionType,
        position,
        margin,
        padding,
        border
    )

    val layoutContainer = remember {
        FlexLayoutContainer(
            YogaNodeFactory.create()
        )
    }

    style.applyTo(layoutContainer.node)

    val layout = layoutContainer.layout

    val density = LocalDensity.current

    val paddingStart = with(density) {
        layout.paddingStart.toDp()
    }

    val paddingEnd = with(density) {
        layout.paddingEnd.toDp()
    }

    val paddingTop = with(density) {
        layout.paddingTop.toDp()
    }

    val paddingBottom = with(density) {
        layout.paddingBottom.toDp()
    }

    val localFlexRootForceRecomposition = LocalFlexRootForceRecomposition.current

    var minimumIntrinsicSize: IntSize? by remember {
        mutableStateOf(null)
    }

    Layout(
        content = {
            Box(
                modifier = modifier.layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)

                    val minimumWidth = measurable.minIntrinsicWidth(0)
                    val minimumHeight = measurable.minIntrinsicHeight(0)

                    val minimumSize = IntSize(minimumWidth, minimumHeight)

                    if (minimumIntrinsicSize == null) {
                        minimumIntrinsicSize = minimumSize
                    } else if (minimumSize != minimumIntrinsicSize) {
                        layoutContainer.node.dirty()
                        minimumIntrinsicSize = minimumSize
                        localFlexRootForceRecomposition()
                    }

                    layout(placeable.width, placeable.height) {
                        placeable.place(x = 0, y = 0)
                    }
                }
            ) {
                content()
            }
        },
        modifier = Modifier.layoutId(layoutContainer),
        measurePolicy = object : MeasurePolicy, ApplyPaddingMeasurePolicy {
            override var applyPadding = true

            override fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): MeasureResult {
                val measurable = measurables[0]

                if (applyPadding) {
                    val placeable = measurable.measure(
                        Constraints(
                            maxWidth = layout.width.toInt() - layout.paddingStart.toInt() - layout.paddingEnd.toInt(),
                            maxHeight = layout.height.toInt() - layout.paddingTop.toInt() - layout.paddingBottom.toInt()
                        )
                    )
                    return layout(
                        layout.width.toInt(),
                        layout.height.toInt()
                    ) {
                        placeable.place(
                            paddingStart.toPx().toInt(),
                            paddingTop.toPx().toInt()
                        )
                    }
                } else {
                    val placeable = measurable.measure(
                        constraints
                    )

                    return layout(placeable.width, placeable.height) {
                        placeable.place(0, 0)
                    }
                }
            }
        }
    )
}
