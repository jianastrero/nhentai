plugins {
    id("org.jetbrains.compose") version "0.3.1"
    id("com.android.application")
    kotlin("android")
}

group = "com.jianastrero"
version = "1.0"

repositories {
    google()
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.appcompat:appcompat:1.3.0-rc01")
    implementation("androidx.activity:activity-compose:1.3.0-alpha06")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.jianastrero.nhentai"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.1"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}