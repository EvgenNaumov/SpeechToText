plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = Config.namespaceUtil
    compileSdk = Config.compile_sdk


    buildFeatures{
        viewBinding = true
    }

    defaultConfig {
        minSdk  = Config.min_sdk

        testInstrumentationRunner = TestImpl.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility  = Config.java_version
        targetCompatibility  = Config.java_version
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

dependencies {

    // AndroidX
    implementation(Design.appcomp)
    // Design
    implementation(Design.material)

    // Kotlin
    implementation(Kotlin.core)
    implementation(Kotlin.stdlib)

}