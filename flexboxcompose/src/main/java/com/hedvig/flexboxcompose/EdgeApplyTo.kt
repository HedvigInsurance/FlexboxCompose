package com.hedvig.flexboxcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import com.facebook.yoga.YogaConstants
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaNode

enum class EdgeKind {
    POSITION, PADDING, MARGIN, BORDER
}

@Composable
fun Edges.applyTo(node: YogaNode, kind: EdgeKind) {
    when (kind) {
        EdgeKind.POSITION -> {
            when (leading.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setPositionPercent(YogaEdge.START, leading.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setPosition(YogaEdge.START, leading.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setPosition(YogaEdge.START, YogaConstants.UNDEFINED)
                }
            }

            when (trailing.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setPositionPercent(YogaEdge.END, trailing.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setPosition(YogaEdge.END, trailing.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setPosition(YogaEdge.END, YogaConstants.UNDEFINED)
                }
            }

            when (top.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setPositionPercent(YogaEdge.TOP, top.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setPosition(YogaEdge.TOP, top.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setPosition(YogaEdge.TOP, YogaConstants.UNDEFINED)
                }
            }

            when (bottom.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setPositionPercent(YogaEdge.BOTTOM, bottom.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setPosition(YogaEdge.BOTTOM, bottom.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setPosition(YogaEdge.BOTTOM, YogaConstants.UNDEFINED)
                }
            }
        }

        EdgeKind.PADDING -> {
            when (leading.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setPaddingPercent(YogaEdge.START, leading.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setPadding(YogaEdge.START, leading.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setPadding(YogaEdge.START, YogaConstants.UNDEFINED)
                }
            }

            when (trailing.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setPaddingPercent(YogaEdge.END, trailing.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setPadding(YogaEdge.END, trailing.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setPadding(YogaEdge.END, YogaConstants.UNDEFINED)
                }
            }

            when (top.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setPaddingPercent(YogaEdge.TOP, top.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setPadding(YogaEdge.TOP, top.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setPadding(YogaEdge.TOP, YogaConstants.UNDEFINED)
                }
            }

            when (bottom.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setPaddingPercent(YogaEdge.BOTTOM, bottom.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setPadding(YogaEdge.BOTTOM, bottom.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setPadding(YogaEdge.BOTTOM, YogaConstants.UNDEFINED)
                }
            }
        }

        EdgeKind.MARGIN -> {
            when (leading.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setMarginPercent(YogaEdge.START, leading.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setMargin(YogaEdge.START, leading.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                    node.setMarginAuto(YogaEdge.START)
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setMargin(YogaEdge.START, YogaConstants.UNDEFINED)
                }
            }

            when (trailing.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setMarginPercent(YogaEdge.END, trailing.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setMargin(YogaEdge.END, trailing.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                    node.setMarginAuto(YogaEdge.END)
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setMargin(YogaEdge.END, YogaConstants.UNDEFINED)
                }
            }

            when (top.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setMarginPercent(YogaEdge.TOP, top.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setMargin(YogaEdge.TOP, top.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                    node.setMarginAuto(YogaEdge.TOP)
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setMargin(YogaEdge.TOP, YogaConstants.UNDEFINED)
                }
            }

            when (bottom.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setMarginPercent(YogaEdge.BOTTOM, bottom.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setMargin(YogaEdge.BOTTOM, bottom.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                    node.setMarginAuto(YogaEdge.BOTTOM)
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setMargin(YogaEdge.BOTTOM, YogaConstants.UNDEFINED)
                }
            }
        }

        EdgeKind.BORDER -> {
            when (leading.kind) {
                FlexSizeKind.PERCENT -> {
                }

                FlexSizeKind.CONSTANT -> {
                    node.setBorder(YogaEdge.START, leading.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                }
            }

            when (trailing.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setMarginPercent(YogaEdge.END, trailing.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setBorder(YogaEdge.END, trailing.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                    node.setMarginAuto(YogaEdge.END)
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setMargin(YogaEdge.END, YogaConstants.UNDEFINED)
                }
            }

            when (top.kind) {
                FlexSizeKind.PERCENT -> {
                    node.setMarginPercent(YogaEdge.TOP, top.dpAmount()!!)
                }

                FlexSizeKind.CONSTANT -> {
                    node.setBorder(YogaEdge.TOP, top.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                    node.setMarginAuto(YogaEdge.TOP)
                }

                FlexSizeKind.UNDEFINED -> {
                    node.setMargin(YogaEdge.TOP, YogaConstants.UNDEFINED)
                }
            }

            when (bottom.kind) {
                FlexSizeKind.PERCENT -> {
                }

                FlexSizeKind.CONSTANT -> {
                    node.setBorder(YogaEdge.BOTTOM, bottom.dpAmount()!!)
                }

                FlexSizeKind.AUTO -> {
                }

                FlexSizeKind.UNDEFINED -> {
                }
            }
        }
    }
}
