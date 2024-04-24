// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Plugins.android_build_gradle)
        classpath(Dependencies.Plugins.kotlin_version)
    }
}

allprojects {

    repositories {
        google()
        mavenCentral()
    }
}