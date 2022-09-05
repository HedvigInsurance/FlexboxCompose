pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "FlexboxCompose"

if (System.getenv()["JITPACK"] == null) {
    include(":samples")
}

include(":flexboxcompose")