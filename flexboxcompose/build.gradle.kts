import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
    id("shot")
    id("maven-publish") apply true
    id("com.palantir.git-version") version "0.15.0"
}

val gitVersion: groovy.lang.Closure<String> by extra

group = "com.github.HedvigInsurance"
version = gitVersion()

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32
        testApplicationId = "com.hedvig.flexboxcomposetests"
        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-rc02"
    }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    namespace = "com.hedvig.flexboxcompose"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

object AndroidX {
    const val lifecycleRuntimeCompose =
        "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha01"
    const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha01"
}

repositories {
    mavenCentral()
}

shot {
    applicationId = "com.hedvig.flexboxcomposetests"
}

val composeVersion = "1.2.0-rc02"

dependencies {
    with(AndroidX) {
        compileOnly(lifecycleRuntimeCompose)
        compileOnly(lifecycleRuntimeKtx)
        compileOnly(lifecycleViewmodelKtx)
    }

    implementation("com.facebook.soloader:soloader:0.10.4")
    implementation("com.facebook.yoga:yoga:1.19.0")
    compileOnly("androidx.compose.foundation:foundation-layout:$composeVersion")
    compileOnly("androidx.compose.foundation:foundation:$composeVersion")

    androidTestImplementation("com.facebook.soloader:soloader:0.10.1")
    androidTestImplementation("androidx.compose.material:material:$composeVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

afterEvaluate {
    configure<PublishingExtension> {
        publications {
            publishing {
                create<MavenPublication>("release") {
                    groupId = "com.github.HedvigInsurance"
                    artifactId = "FlexboxCompose"
                    version = gitVersion()
                    from(components["release"])
                }
            }
        }
    }
}

tasks.withType<PublishToMavenLocal> {
    dependsOn("assemble")
}