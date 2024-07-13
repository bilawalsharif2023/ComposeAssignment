// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    kotlin("kapt") version libs.versions.kotlin.get()
    alias(libs.plugins.hiltAndroid) apply false
}

/*
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
*/
