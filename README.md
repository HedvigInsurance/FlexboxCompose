## FlexboxCompose

A library backed by Yoga that allows to compose flexbox layouts in Jetpack Compose

## API

### FlexRoot
Use FlexRoot to start a Flexbox layout, all Yoga properties are available like flexDirection, flexGrow, etc.

flexibleAxies defines if the max size of the container should be used to fill that axis or if the layout should grow as
large as its content can possible become, a flexible axis of Axis.HORIZONTAL for example tells flex root to grow outside of the width of the container.

```kotlin
    FlexRoot(
        flexibleAxies = listOf(Axis.HORIZONTAL, Axis.VERTICAL),
        flexDirection = FlexDirection.ROW,
        modifier = Modifier.background(Color.Yellow)
    )
```

### FlexNode
Use FlexNode to style and setup a node in the layout, all Yoga properties are available like flexDirection, flexGrow, etc.

Should always be the child in a FlexRoot, never use this anywhere else.

```kotlin
    FlexNode(
        alignSelf = AlignSelf.CENTER,
        modifier = Modifier.background(Color.Yellow)
    ) {
        Text("Some text")
    }
```