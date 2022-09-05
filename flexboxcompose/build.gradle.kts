import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
    id("shot")
    id("maven-publish") apply true
}

group = "com.hedvig.flexboxcompose"
version = "1.0-SNAPSHOT"

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

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.hedvig.flexboxcompose"
            artifactId = "flexbox-compose"
            version = "1.0.0"
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/HedvigInsurance/FlexboxCompose")
            credentials {
                username = System.getenv("GPR_USER")
                password = System.getenv("GPR_KEY")
            }
        }
    }
}

tasks.named("publish") {
    dependsOn("assemble")
}
