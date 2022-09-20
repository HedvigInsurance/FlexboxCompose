package com.hedvig.flexboxcompose

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size

internal fun Modifier.flexPadding(layout: FlexLayout) =
    this.clip(
        GenericShape { _, _ ->
            addRect(
                Rect(
                    offset = Offset(x = layout.paddingStart, y = layout.paddingEnd),
                    size = Size(
                        layout.width - layout.paddingStart - layout.paddingEnd,
                        layout.height - layout.paddingTop - layout.paddingBottom
                    )
                )
            )
        }
    )
