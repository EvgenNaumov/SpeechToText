plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = Config.namespaceApp
    compileSdk = Config.compile_sdk


    buildFeatures{
        viewBinding = true
    }

    defaultConfig {
        applicationId  = Config.application_id
        minSdk  = Config.min_sdk
        targetSdk  = Config.target_sdk
        versionCode = Releases.version_code
        versionName = Releases.version_name

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

    // КОРУТИНЫ
    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    // Koin for Android
    implementation(Koin.koin_android)
    implementation(Koin.koin_view_model)
    implementation(Koin.compat)
    implementation(Koin.test)
    implementation(Koin.junit4Test)

    // Room
    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.room_ktx)

    implementation("com.android.support.constraint:constraint-layout:2.0.4")
    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    //yandex speech
    implementation("com.yandex.android:speechkit:3.12.2")

    testImplementation(TestImpl.junit)

    // ** TESTS **
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)
    androidTestImplementation(TestImpl.uiAutomator)
}