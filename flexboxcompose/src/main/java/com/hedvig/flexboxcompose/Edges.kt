package com.hedvig.flexboxcompose

data class Edges(
    val leading: FlexSizeDimension = undefined(),
    val trailing: FlexSizeDimension = undefined(),
    val top: FlexSizeDimension = undefined(),
    val bottom: FlexSizeDimension = undefined()
)

fun all(dimension: FlexSizeDimension): Edges =
    Edges(
        leading = dimension,
        trailing = dimension,
        top = dimension,
        bottom = dimension
    )