package com.hedvig.flexboxcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaNode

data class FlexLayout(
    val paddingStart: Float,
    val paddingEnd: Float,
    val paddingTop: Float,
    val paddingBottom: Float
)

data class FlexLayoutContainer(
    val node: YogaNode
) {
    var layout: FlexLayout? by mutableStateOf(null)

    fun updateLayout() {
        layout = FlexLayout(
            paddingStart = node.getLayoutPadding(YogaEdge.START),
            paddingEnd = node.getLayoutPadding(YogaEdge.END),
            paddingTop = node.getLayoutPadding(YogaEdge.TOP),
            paddingBottom = node.getLayoutPadding(YogaEdge.BOTTOM)
        )
    }
}