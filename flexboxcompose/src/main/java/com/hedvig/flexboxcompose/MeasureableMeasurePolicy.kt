package com.hedvig.flexboxcompose

import androidx.compose.ui.layout.Measurable

internal fun Measurable.applyPaddingMeasurePolicy(): ApplyPaddingMeasurePolicy {
    val field = this::class.java.getDeclaredField("measurePolicy")
    field.isAccessible = true
    return field.get(this) as ApplyPaddingMeasurePolicy
}