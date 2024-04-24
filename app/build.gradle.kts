plugins {
    id(Dependencies.Plugins.android_application)
    id(Dependencies.Plugins.kotlin_android)
}

android {
    namespace = Dependencies.Application.id
    compileSdk = Dependencies.Versions.compileSDK

    defaultConfig {
        applicationId = Dependencies.Application.id
        minSdk = Dependencies.Versions.minsdk
        targetSdk = Dependencies.Versions.targetsdk
        versionCode = Dependencies.Application.version_code
        versionName = Dependencies.Application.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = Dependencies.Application.releaseMinify
            isShrinkResources = Dependencies.Application.releaseMinify

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = Dependencies.Application.debugMinify
            isShrinkResources = Dependencies.Application.debugMinify

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Dependencies.Java.java_compile
        targetCompatibility = Dependencies.Java.java_compile
    }
    kotlinOptions {
        jvmTarget = Dependencies.Java.java_versions
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.kotlinCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.Dependencies.orbit_compose)
    implementation(Dependencies.Dependencies.orbit_viewModel)

    //android-core
    implementation(Dependencies.Dependencies.android_core_ktx)
    implementation(Dependencies.Dependencies.lifecycle_runtime)
    implementation(Dependencies.Dependencies.material)

    implementation(Dependencies.Compose.activityCompose)
    implementation(platform(Dependencies.Compose.composeBoom))
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeUiGraphics)
    implementation(Dependencies.Compose.composeFoundation)
    implementation(Dependencies.Compose.systemUiController)
    implementation(Dependencies.Compose.iconsCompose)
    implementation(Dependencies.Dependencies.coroutines)

    //material
    implementation(Dependencies.Compose.material3)

    //preview
    implementation(Dependencies.Compose.toolingPreview)
    debugImplementation(Dependencies.Compose.debugUiTooling)
    debugImplementation(Dependencies.Compose.debugAndroidCompose)

    //koin di
    implementation(Dependencies.Dependencies.koin_android)
    implementation(Dependencies.Dependencies.koin_compose)

    // Screen Size
    implementation(Dependencies.Dependencies.window)
}