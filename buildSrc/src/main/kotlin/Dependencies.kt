import org.gradle.api.JavaVersion

object Dependencies {

    object Application {
        const val id = "com.zekony.music"
        const val version_code = 1
        const val version_name = "1.0.$version_code"

        const val releaseMinify = true
        const val releaseEditable = false

        const val debugMinify = false
        const val debugEditable = true
    }

        object AnnotationProcessing {
        const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
        const val dagger_hilt_kapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Compose {

        const val kotlinCompiler = "1.4.8"
        private const val accompanist = "0.33.0-alpha"
        private const val coil = "2.2.2"

        private const val composeBoomVersion = "2023.08.00"

        const val composeBoom = "androidx.compose:compose-bom:$composeBoomVersion"

        /**
         * choice one
         */
        const val material3 = "androidx.compose.material3:material3"
        const val material2 = "androidx.compose.material:material"
        const val composeFoundation = "androidx.compose.foundation:foundation"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUiGraphics = "androidx.compose.ui:ui-graphics"

        /**
         * Android Studio Preview support
         */
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val debugUiTooling = "androidx.compose.ui:ui-tooling"

        /**
         * ui tests
         */
        const val debugAndroidCompose = "androidx.compose.ui:ui-test-manifest"

        const val iconsCompose = "androidx.compose.material:material-icons-core"

        // Optional - Integration with activities
        const val activityCompose = "androidx.activity:activity-compose:1.7.2"

        const val serviceView = "com.google.accompanist:accompanist-webview:$accompanist"

        const val navigation = "com.google.accompanist:accompanist-navigation-animation:$accompanist"
        const val permission = "com.google.accompanist:accompanist-permissions:$accompanist"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$accompanist"
    }

    object Dependencies {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"
        const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"

        const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttp_login_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

        const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
        const val room_ktx = "androidx.room:room-ktx:${Versions.room}"

        const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"

        const val nav_compose = "androidx.navigation:navigation-compose:${Versions.nav_version}"
        const val hilt_nav_compose = "androidx.hilt:hilt-navigation-compose:${Versions.compose_hilt_nav}"

        const val coil_compose = "io.coil-kt:coil-compose:${Versions.coil}"
        const val coil_svg = "io.coil-kt:coil-svg:${Versions.coil}"

        const val android_core_ktx = "androidx.core:core-ktx:${Versions.ktx}"
        const val android_appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"

        const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
        const val dataStoreProto = "androidx.datastore:datastore:${Versions.datastore}"


        const val koin_android = "io.insert-koin:koin-android:${Versions.koin_version}"
        const val koin_compose = "io.insert-koin:koin-androidx-compose:${Versions.koin_version}"

        const val orbit_viewModel = "org.orbit-mvi:orbit-viewmodel:${Versions.orbit_version}"
        const val orbit_compose = "org.orbit-mvi:orbit-compose:${Versions.orbit_version}"

        const val window = "androidx.window:window:${Versions.window}"

        const val kotlinJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1"
        const val kotlinCollectionsImmutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5"
    }

    object Java {
        const val java_versions = "17"
        const val javaLibraryPlugin = "java-library"
        const val kotlinJmv = "org.jetbrains.kotlin.jvm"

        val java_compile = JavaVersion.VERSION_17

    }

    object Modules {
        const val app = ":app"
    }

    object Plugins {
        //projects plugins
        const val dagger_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        const val android_build_gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin_version = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val serialization = "org.jetbrains.kotlin.plugin.serialization"


        //app plugins
        const val android_application = "com.android.application"
        const val android_library = "com.android.library"
        const val kotlin_android = "org.jetbrains.kotlin.android"
        const val kapt = "kotlin-kapt"
        const val dagger_hilt = "dagger.hilt.android.plugin"
        const val ksp = "com.google.devtools.ksp"
    }

    object Versions {
        const val gradle = "8.1.0"
        const val compileSDK = 34
        const val minsdk = 24
        const val targetsdk = 34
        const val kotlin = "1.8.22"
        const val ktx = "1.10.1"
        const val kotlin_coroutines = "1.7.1"

        const val lifecycle = "2.6.1"
        const val fragment_ktx = "1.5.6"
        const val appcompat = "1.6.1"
        const val material = "1.8.0"

        const val coil = "2.4.0"

        const val retrofit = "2.9.0"
        const val okhttp = "4.10.0"
        const val room = "2.5.1"

        const val hilt = "2.46.1"

        const val nav_version = "2.5.3"
        const val compose_hilt_nav = "1.0.0"

        const val datastore = "1.0.0"

        const val koin_version = "3.4.0"

        const val orbit_version = "6.0.0"

        const val window = "1.1.0"
    }
}
