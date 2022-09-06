package com.hedvig.flexboxcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.MultiMeasureLayout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.facebook.soloader.SoLoader
import com.facebook.yoga.*
import java.lang.Integer.min

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

    flexibleAxies: List<Axis> = listOf(),

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

    val rootLayoutContainer = remember {
        SoLoader.init(context, false)
        FlexLayoutContainer(
            YogaNodeFactory.create()
        )
    }
    style.applyTo(rootLayoutContainer.node)

    MultiMeasureLayout(
        content = content,
        modifier = modifier.padding(
            start = rootLayoutContainer.layout?.paddingStart?.dp ?: 0.dp,
            end = rootLayoutContainer.layout?.paddingEnd?.dp ?: 0.dp,
            top = rootLayoutContainer.layout?.paddingTop?.dp ?: 0.dp,
            bottom = rootLayoutContainer.layout?.paddingBottom?.dp ?: 0.dp
        )
    ) { measurables, constraints ->
        val allLayoutContainerMeasurables = measurables.filter {
            it.layoutId is FlexLayoutContainer
        }

        val layoutContainers = allLayoutContainerMeasurables.mapNotNull {
            it.layoutId as? FlexLayoutContainer
        }

        if (rootLayoutContainer.node.childCount > layoutContainers.count()) {
            val numberOfChildrenToRemove = rootLayoutContainer.node.childCount - layoutContainers.count()

            for (i in 1..numberOfChildrenToRemove) {
                rootLayoutContainer.node.removeChildAt(
                    rootLayoutContainer.node.childCount - 1
                )
            }
        }

        layoutContainers.forEachIndexed { index, layoutContainer ->
            layoutContainer.node.setMeasureFunction { _, suggestedWidth, widthMode, suggestedHeight, heightMode ->
                val placeable = allLayoutContainerMeasurables[index].measure(
                    Constraints(
                        maxWidth = if (suggestedWidth.isNaN()) Constraints.Infinity else suggestedWidth.toInt(),
                        maxHeight = if (suggestedHeight.isNaN()) Constraints.Infinity else suggestedHeight.toInt()
                    )
                )

                fun sanitize(
                    constrainedSize: Int,
                    measuredSize: Int,
                    mode: YogaMeasureMode
                ): Int {
                    return when (mode) {
                        YogaMeasureMode.UNDEFINED -> measuredSize
                        YogaMeasureMode.EXACTLY -> constrainedSize
                        YogaMeasureMode.AT_MOST -> min(measuredSize, constrainedSize)
                    }
                }

                return@setMeasureFunction YogaMeasureOutput.make(
                    sanitize(suggestedWidth.toInt(), placeable.width, widthMode),
                    sanitize(suggestedHeight.toInt(), placeable.height, heightMode)
                )
            }

            if (rootLayoutContainer.node.childCount >= index + 1) {
                if (rootLayoutContainer.node.getChildAt(index) != layoutContainer.node) {
                    rootLayoutContainer.node.removeChildAt(index)
                    rootLayoutContainer.node.addChildAt(layoutContainer.node, index)
                }
            } else {
                rootLayoutContainer.node.addChildAt(layoutContainer.node, index)
            }
        }

        rootLayoutContainer.node.calculateLayout(
            flexibleAxies.contains(Axis.HORIZONTAL).let {
                if (it) {
                    YogaConstants.UNDEFINED
                } else {
                    constraints.maxWidth.toFloat()
                }
            },
            flexibleAxies.contains(Axis.VERTICAL).let {
                if (it) {
                    YogaConstants.UNDEFINED
                } else {
                    constraints.maxHeight.toFloat()
                }
            }
        )

        val placeables = allLayoutContainerMeasurables.mapIndexed { index, measurable ->
            val node = layoutContainers[index].node

            measurable.measure(
                Constraints.fixed(
                    width = node.layoutWidth.toInt(),
                    height = node.layoutHeight.toInt()
                )
            )
        }

        rootLayoutContainer.updateLayout()

        layout(
            rootLayoutContainer.node.layoutWidth.toInt(),
            rootLayoutContainer.node.layoutHeight.toInt()
        ) {
            placeables.forEachIndexed { index, placeable ->
                val node = layoutContainers[index].node
                layoutContainers[index].updateLayout()

                placeable.placeRelative(
                    x = node.layoutX.toInt(),
                    y = node.layoutY.toInt()
                )
            }
        }
    }
}
