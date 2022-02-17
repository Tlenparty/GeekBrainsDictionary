import org.gradle.api.JavaVersion

object Config {
    const val compileSdk = 31
    const val minSkd = 23
    const val targetSdk = 31
     val javaVersion = JavaVersion.VERSION_1_8
}

object Versions {
    const val retrofit = "2.9.0"
    const val okHttpVersion = "4.9.2"
    const val rxJavaAdapterVersion = "1.0.0"
    const val ktx = "1.6.0"
    const val appCompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.1"
    const val swipeRefreshLayout = "1.1.0"
    const val coroutines = "1.5.0"
    const val koin = "3.1.2"
    const val room = "2.3.0"
}

object Retrofit {
    const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val ConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val LoginInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val RxJavaAdapter =
        "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxJavaAdapterVersion}"
}

object Android {
    const val CoreKtx = "androidx.core:core-ktx:${Versions.ktx}"
    const val AppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val Material = "com.google.android.material:material:${Versions.material}"
    const val ConstrainLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val SwipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object Kotlin {
    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val CoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Koin {
    const val Core = "io.insert-koin:koin-core:${Versions.koin}"
    const val Android = "io.insert-koin:koin-android:${Versions.koin}"
    const val AndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koin}"
}

object Room {
    const val Core = "androidx.room:room-runtime:${Versions.room}"
    const val Compiler = "androidx.room:room-compiler:${Versions.room}"
    const val Ktx = "androidx.room:room-ktx:${Versions.room}"
    const val SupportM1 = "org.xerial:sqlite-jdbc:3.34.0"
}

object Tests {
    const val JUnit = "junit:junit:4.13.2"
    const val AndroidJUnit = "androidx.test.ext:junit:1.1.3"
    const val Espresso = "androidx.test.espresso:espresso-core:3.4.0"
}