package com.hedvig.flexboxcompose

import androidx.compose.runtime.Composable
import com.facebook.yoga.*

enum class AlignItems {
    STRETCH,
    CENTER,
    START,
    END
}

enum class AlignSelf {
    AUTO,
    STRETCH,
    CENTER,
    START,
    END
}

enum class AlignContent {
    AUTO,
    CENTER,
    START,
    END,
    STRETCH
}

enum class FlexDirection {
    COLUMN,
    ROW,
    COLUMN_REVERSE,
    ROW_REVERSE
}

enum class FlexWrap {
    WRAP,
    NO_WRAP
}

enum class JustifyContent {
    CENTER,
    START,
    END,
    SPACE_AROUND,
    SPACE_BETWEEN
}

enum class Direction {
    LTR,
    RTL,
    INHERIT
}

enum class Overflow {
    VISIBLE,
    HIDDEN,
    SCROLL
}

enum class PositionType {
    ABSOLUTE,
    RELATIVE
}

data class FlexStyle(
    val size: FlexSize,
    val minSize: FlexSize,
    val maxSize: FlexSize,

    // Specifies how flex-items are placed in the flex-container (defining the main-axis).
    // - Note: Applies to flex-container.
    val flexDirection: FlexDirection,

    // Specifies whether flex items are forced into a single line
    // or can be wrapped onto multiple lines.
    // - Note: Applies to flex-container.
    val flexWrap: FlexWrap,

    // Distributes space between and around flex-items along the main-axis.
    // - Note: Applies to flex-container.
    val justifyContent: JustifyContent,

    // Distributes space between and around flex-items along the cross-axis.
    // This works like `justifyContent` but in the perpendicular direction.
    // - Note: Applies to flex-container.
    val alignItems: AlignItems,

    // Aligns a flex-container's lines when there is extra space on the cross-axis.
    // - Warning: This property has no effect on single line.
    // - Note: Applies to multi-line flex-container (no `FlexWrap.nowrap`).
    val alignContent: AlignContent,

    // Aligns self (flex-item) by overriding it's parent's (flex-container) `alignItems`.
    // - Note: Applies to flex-item.
    val alignSelf: AlignSelf,

    // Shorthand property specifying the ability of a flex-item
    // to alter its dimensions to fill available space.
    // - Note: Applies to flex-item.
    val flex: Float,

    // Grow factor of a flex-item.
    // - Note: Applies to flex-item.
    val flexGrow: Float,

    // Shrink factor of a flex-item.
    // - Note: Applies to flex-item.
    val flexShrink: Float,

    // Initial main size of a flex item.
    // - Note: Applies to flex-item.
    val flexBasis: Float,

    val direction: Direction,
    val overflow: Overflow,
    val positionType: PositionType,

    // CSS's (top, right, bottom, left) that works with `positionType = .absolute`.
    val position: Edges,

    val margin: Edges,
    val padding: Edges,

    // facebook/yoga implementation that mostly works as same as `padding`.
    val border: Edges
)

@Composable
fun FlexStyle.applyTo(node: YogaNode) {
    when (size.width.kind) {
        FlexSizeKind.PERCENT -> size.width.amount?.let { node.setWidthPercent(it) }
        FlexSizeKind.CONSTANT -> size.width.dpAmount()?.let { node.setWidth(it) }
        FlexSizeKind.AUTO -> node.setWidthAuto()
        FlexSizeKind.UNDEFINED -> {}
    }

    when (size.height.kind) {
        FlexSizeKind.PERCENT -> size.height.amount?.let { node.setHeightPercent(it) }
        FlexSizeKind.CONSTANT -> size.height.dpAmount()?.let { node.setHeight(it) }
        FlexSizeKind.AUTO -> node.setHeightAuto()
        FlexSizeKind.UNDEFINED -> {}
    }

    when (minSize.width.kind) {
        FlexSizeKind.PERCENT -> minSize.width.amount?.let { node.setMinWidthPercent(it) }
        FlexSizeKind.CONSTANT -> minSize.width.dpAmount()?.let { node.setMinWidth(it) }
        FlexSizeKind.AUTO -> {}
        FlexSizeKind.UNDEFINED -> {}
    }

    when (minSize.height.kind) {
        FlexSizeKind.PERCENT -> minSize.height.amount?.let { node.setMinHeightPercent(it) }
        FlexSizeKind.CONSTANT -> minSize.height.dpAmount()?.let { node.setMinHeight(it) }
        FlexSizeKind.AUTO -> {}
        FlexSizeKind.UNDEFINED -> {}
    }

    when (maxSize.width.kind) {
        FlexSizeKind.PERCENT -> maxSize.width.amount?.let { node.setMaxWidthPercent(it) }
        FlexSizeKind.CONSTANT -> maxSize.width.dpAmount()?.let { node.setMaxWidth(it) }
        FlexSizeKind.AUTO -> {}
        FlexSizeKind.UNDEFINED -> {}
    }

    when (maxSize.height.kind) {
        FlexSizeKind.PERCENT -> maxSize.height.amount?.let { node.setMaxHeightPercent(it) }
        FlexSizeKind.CONSTANT -> maxSize.height.dpAmount()?.let { node.setMaxHeight(it) }
        FlexSizeKind.AUTO -> {}
        FlexSizeKind.UNDEFINED -> {}
    }

    when (flexDirection) {
        FlexDirection.COLUMN -> {
            node.flexDirection = YogaFlexDirection.COLUMN
        }

        FlexDirection.ROW -> {
            node.flexDirection = YogaFlexDirection.ROW
        }

        FlexDirection.COLUMN_REVERSE -> {
            node.flexDirection = YogaFlexDirection.COLUMN_REVERSE
        }

        FlexDirection.ROW_REVERSE -> {
            node.flexDirection = YogaFlexDirection.ROW_REVERSE
        }
    }

    when (flexWrap) {
        FlexWrap.WRAP -> {
            node.wrap = YogaWrap.WRAP
        }

        FlexWrap.NO_WRAP -> {
            node.wrap = YogaWrap.NO_WRAP
        }
    }

    when (justifyContent) {
        JustifyContent.CENTER -> {
            node.justifyContent = YogaJustify.CENTER
        }

        JustifyContent.START -> {
            node.justifyContent = YogaJustify.FLEX_START
        }

        JustifyContent.END -> {
            node.justifyContent = YogaJustify.FLEX_END
        }

        JustifyContent.SPACE_AROUND -> {
            node.justifyContent = YogaJustify.SPACE_AROUND
        }

        JustifyContent.SPACE_BETWEEN -> {
            node.justifyContent = YogaJustify.SPACE_BETWEEN
        }
    }

    when (alignItems) {
        AlignItems.STRETCH -> {
            node.alignItems = YogaAlign.STRETCH
        }

        AlignItems.CENTER -> {
            node.alignItems = YogaAlign.CENTER
        }

        AlignItems.START -> {
            node.alignItems = YogaAlign.FLEX_START
        }

        AlignItems.END -> {
            node.alignItems = YogaAlign.FLEX_END
        }
    }

    when (alignContent) {
        AlignContent.AUTO -> {
            node.alignContent = YogaAlign.AUTO
        }

        AlignContent.CENTER -> {
            node.alignContent = YogaAlign.CENTER
        }

        AlignContent.START -> {
            node.alignContent = YogaAlign.FLEX_START
        }

        AlignContent.END -> {
            node.alignContent = YogaAlign.FLEX_END
        }

        AlignContent.STRETCH -> {
            node.alignContent = YogaAlign.STRETCH
        }
    }

    when (alignSelf) {
        AlignSelf.AUTO -> {
            node.alignSelf = YogaAlign.AUTO
        }

        AlignSelf.STRETCH -> {
            node.alignSelf = YogaAlign.STRETCH
        }

        AlignSelf.CENTER -> {
            node.alignSelf = YogaAlign.CENTER
        }

        AlignSelf.START -> {
            node.alignSelf = YogaAlign.FLEX_START
        }

        AlignSelf.END -> {
            node.alignSelf = YogaAlign.FLEX_END
        }
    }

    node.flex = flex
    node.flexGrow = flexGrow
    node.flexShrink = flexShrink
    node.setFlexBasis(flexBasis)

    when (direction) {
        Direction.LTR -> node.setDirection(YogaDirection.LTR)
        Direction.RTL -> node.setDirection(YogaDirection.RTL)
        Direction.INHERIT -> node.setDirection(YogaDirection.INHERIT)
    }

    when (overflow) {
        Overflow.VISIBLE -> {
            node.overflow = YogaOverflow.VISIBLE
        }

        Overflow.HIDDEN -> {
            node.overflow = YogaOverflow.HIDDEN
        }

        Overflow.SCROLL -> {
            node.overflow = YogaOverflow.SCROLL
        }
    }

    when (positionType) {
        PositionType.ABSOLUTE -> {
            node.positionType = YogaPositionType.ABSOLUTE
        }

        PositionType.RELATIVE -> {
            node.positionType = YogaPositionType.RELATIVE
        }
    }

    position.applyTo(node, kind = EdgeKind.POSITION)
    padding.applyTo(node, kind = EdgeKind.PADDING)
    margin.applyTo(node, kind = EdgeKind.MARGIN)
    border.applyTo(node, kind = EdgeKind.BORDER)
}
