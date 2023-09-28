import org.gradle.api.JavaVersion

object Config{

    const val namespaceApp = "com.naumov.speechtotext"
    const val namespaceRepo = "com.naumov.repository"
    const val namespaceUtil = "com.naumov.utils"
    const val namespaceCore = "com.naumov.core"
    const val namespaceModel = "com.naumov.model"

    const val application_id = "com.naumov.speechtotext"
    const val compile_sdk = 33
    const val min_sdk  = 29
    const val target_sdk = 33
    var java_version = org.gradle.api.JavaVersion.VERSION_11
    const val jvmTarget = "11"

}

object Releases{
    const val version_code = 1
    const val version_name = "1.0"

}

object Modules{
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
}

object Version{
    //Design
    const val appcompat = "1.6.1"
    const val material = "1.9.0"

    //Kotlin
    const val core = "1.8.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.6.4"
    const val coroutinesAndroid = "1.6.4"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.9.2"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koin = "3.4.3"
    const val koinViewModel = "android.arch.core:core:1.0.0-alpha3.4.0"

    //Room
    const val roomKtx = "2.5.0"
    const val roomCompiler = "2.5.0"
    const val runtime = "2.5.0"

    //test
    const val jUnit = "2.3.0"
    const val runner = "1.2.0"
    const val espressoCore = "3.5.1"
    const val uiAutomator = "2.2.0"
}

object Design{
    const val appcomp = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val material = "com.google.android.material:material:${Version.material}"
}

object Kotlin{
    const val core = "androidx.core:core-ktx:${Version.core}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.stdlib}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Version.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.adapterCoroutines}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.interceptor}"
}

object Koin {
    const val koin_android = "org.koin:koin-android:${Version.koin}"
    const val koin_view_model = "io.insert-koin:koin-android:${Version.koin}"

    //Для совместимости с Java
    const val compat = "io.insert-koin:koin-android-compat:${Version.koin}"

    // При написании юнит-тестов можно создавать модули в рантайме и вызывать функцию startKoin  внутри тестов
    const val test = "io.insert-koin:koin-test:${Version.koin}"

    // Needed JUnit version
    const val junit4Test = "io.insert-koin:koin-test-junit4:${Version.koin}"
    const val junit5Test = "io.insert-koin:koin-test-junit5:${Version.koin}"

}

object Room {
    const val runtime = "androidx.room:room-runtime:${Version.runtime}"
    const val compiler = "androidx.room:room-compiler:${Version.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Version.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Version.jUnit}"
    const val runner = "androidx.test:runner:${Version.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
    const val uiAutomator = "androidx.test.uiautomator:uiautomator:${Version.uiAutomator}"
    const val  testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
}


