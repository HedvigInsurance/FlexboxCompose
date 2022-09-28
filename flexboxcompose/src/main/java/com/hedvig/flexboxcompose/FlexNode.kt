package com.hedvig.flexboxcompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import com.facebook.yoga.YogaNodeFactory

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

    content: @Composable (modifier: Modifier) -> Unit = {}
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

    val nodeContainer = remember {
        FlexNodeContainer(
            YogaNodeFactory.create()
        )
    }

    style.applyTo(nodeContainer.node)

    content(modifier.layoutId(nodeContainer))
}
