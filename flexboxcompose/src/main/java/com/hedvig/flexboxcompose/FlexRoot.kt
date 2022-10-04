package com.hedvig.flexboxcompose

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Constraints
import com.facebook.soloader.SoLoader
import com.facebook.yoga.*
import kotlin.math.roundToInt

enum class Axis {
    VERTICAL, HORIZONTAL
}

@Composable
fun FlexRoot(
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

    flexibleAxes: Set<Axis> = setOf(),

    content: @Composable () -> Unit
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

    val context = LocalContext.current

    val rootNodeContainer = remember {
        SoLoader.init(context, false)
        FlexNodeContainer(
            YogaNodeFactory.create()
        )
    }
    style.applyTo(rootNodeContainer.node)

    MultiMeasureLayout(
        content = content,
        modifier = modifier
            .conditional(!flexibleAxes.contains(Axis.VERTICAL)) {
                this.fillMaxHeight()
            }
            .conditional(!flexibleAxes.contains(Axis.HORIZONTAL)) {
                this.fillMaxWidth()
            }
            .height(IntrinsicSize.Min),
        measurePolicy = object : MeasurePolicy {
            override fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): MeasureResult {
                val allNodeContainerMeasurables = measurables.filter {
                    it.layoutId is FlexNodeContainer
                }

                val nodeContainers = allNodeContainerMeasurables.mapNotNull {
                    it.layoutId as? FlexNodeContainer
                }

                if (rootNodeContainer.node.childCount > nodeContainers.count()) {
                    val numberOfChildrenToRemove = rootNodeContainer.node.childCount - nodeContainers.count()

                    for (i in 1..numberOfChildrenToRemove) {
                        rootNodeContainer.node.removeChildAt(
                            rootNodeContainer.node.childCount - 1
                        )
                    }
                }

                nodeContainers.forEachIndexed { index, layoutContainer ->
                    layoutContainer.node.setMeasureFunction { _, suggestedWidth, widthMode, suggestedHeight, heightMode ->
                        val placeable = allNodeContainerMeasurables[index].measure(
                            Constraints(
                                maxWidth = if (suggestedWidth.isNaN()) Constraints.Infinity
                                else suggestedWidth.roundToInt(),
                                maxHeight = if (suggestedHeight.isNaN()) Constraints.Infinity
                                else suggestedHeight.roundToInt()
                            )
                        )

                        fun sanitize(
                            constrainedSize: Float,
                            measuredSize: Float,
                            mode: YogaMeasureMode
                        ): Float {
                            return when (mode) {
                                YogaMeasureMode.UNDEFINED -> measuredSize
                                YogaMeasureMode.EXACTLY -> constrainedSize
                                YogaMeasureMode.AT_MOST -> kotlin.math.min(measuredSize, constrainedSize)
                            }
                        }
                        return@setMeasureFunction YogaMeasureOutput.make(
                            sanitize(suggestedWidth, placeable.width.toFloat(), widthMode),
                            sanitize(suggestedHeight, placeable.height.toFloat(), heightMode)
                        )
                    }

                    if (rootNodeContainer.node.childCount >= index + 1) {
                        if (rootNodeContainer.node.getChildAt(index) != layoutContainer.node) {
                            rootNodeContainer.node.removeChildAt(index)
                            rootNodeContainer.node.addChildAt(layoutContainer.node, index)
                        }
                    } else {
                        rootNodeContainer.node.addChildAt(layoutContainer.node, index)
                    }
                }

                rootNodeContainer.node.calculateLayout(
                    flexibleAxes.contains(Axis.HORIZONTAL).let {
                        if (it) {
                            YogaConstants.UNDEFINED
                        } else {
                            if (constraints.hasBoundedWidth) {
                                constraints.maxWidth.toFloat()
                            } else {
                                YogaConstants.UNDEFINED
                            }
                        }
                    },
                    flexibleAxes.contains(Axis.VERTICAL).let {
                        if (it) {
                            YogaConstants.UNDEFINED
                        } else {
                            if (constraints.hasBoundedHeight) {
                                constraints.maxHeight.toFloat()
                            } else {
                                YogaConstants.UNDEFINED
                            }
                        }
                    }
                )

                val placeables = allNodeContainerMeasurables.mapIndexed { index, measurable ->
                    val node = nodeContainers[index].node

                    val paddingStart = node.getLayoutPadding(YogaEdge.START)
                    val paddingEnd = node.getLayoutPadding(YogaEdge.END)
                    val paddingTop = node.getLayoutPadding(YogaEdge.TOP)
                    val paddingBottom = node.getLayoutPadding(YogaEdge.BOTTOM)

                    measurable.measure(
                        Constraints(
                            maxWidth = node.layoutWidth.roundToInt() - paddingStart.toInt() - paddingEnd.toInt(),
                            maxHeight = node.layoutHeight.roundToInt() - paddingTop.toInt() - paddingBottom.toInt()
                        )
                    )
                }

                return layout(
                    rootNodeContainer.node.layoutWidth.roundToInt(),
                    rootNodeContainer.node.layoutHeight.roundToInt()
                ) {
                    placeables.forEachIndexed { index, placeable ->
                        val node = nodeContainers[index].node

                        val paddingStart = node.getLayoutPadding(YogaEdge.START)
                        val paddingTop = node.getLayoutPadding(YogaEdge.TOP)

                        placeable.place(
                            x = node.layoutX.roundToInt() + paddingStart.toInt(),
                            y = node.layoutY.roundToInt() + paddingTop.toInt()
                        )
                    }
                }
            }
        }
    )
}
