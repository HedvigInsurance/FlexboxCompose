package com.hedvig.flexboxcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaNode

data class FlexLayout(
    val paddingStart: Float = 0f,
    val paddingEnd: Float = 0f,
    val paddingTop: Float = 0f,
    val paddingBottom: Float = 0f,
    val width: Float = 0f,
    val height: Float = 0f,
)

data class FlexLayoutContainer(
    val node: YogaNode
) {
    var layout: FlexLayout by mutableStateOf(FlexLayout())
    var applyPaddingMeasurePolicy: ApplyLayoutMeasurePolicy? = null

    fun updateLayout() {
        layout = FlexLayout(
            paddingStart = node.getLayoutPadding(YogaEdge.START),
            paddingEnd = node.getLayoutPadding(YogaEdge.END),
            paddingTop = node.getLayoutPadding(YogaEdge.TOP),
            paddingBottom = node.getLayoutPadding(YogaEdge.BOTTOM),
            width = node.layoutWidth,
            height = node.layoutHeight
        )
    }
}