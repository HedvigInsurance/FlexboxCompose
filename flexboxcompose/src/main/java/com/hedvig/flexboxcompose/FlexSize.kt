package com.hedvig.flexboxcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

enum class FlexSizeKind {
    PERCENT,
    CONSTANT,
    AUTO,
    UNDEFINED
}

class FlexSizeDimension internal constructor(
    val kind: FlexSizeKind,
    val amount: Float?
) {
    @Composable
    internal fun dpAmount(): Float? =
        amount?.let {
            it * LocalDensity.current.density
        }
}

fun percent(amount: Float) = FlexSizeDimension(kind = FlexSizeKind.PERCENT, amount = amount)
fun constant(amount: Float) = FlexSizeDimension(kind = FlexSizeKind.CONSTANT, amount = amount)

fun auto() = FlexSizeDimension(kind = FlexSizeKind.AUTO, amount = null)
fun undefined() = FlexSizeDimension(kind = FlexSizeKind.UNDEFINED, amount = null)

data class FlexSize(
    val width: FlexSizeDimension,
    val height: FlexSizeDimension
)
