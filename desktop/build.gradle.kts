import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "0.3.1"
}

group = "com.jianastrero"
version = "0.1.1"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}


compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {

            packageName = "NHentai Desktop"
            description = "This project is dedicated into making a Desktop and Android version of NHentai. It utilizes HTML scalping from the main NHentai Website. It will provide the ability to browse and read manga from NHentai."
            copyright = "Copyright (c) 2021 Jian James Astrero"

            targetFormats(TargetFormat.Msi)

            windows {
                iconFile.set(project.file("icon.ico"))
                menuGroup = "NHentai"
            }
            // Mac and Linux will be added on first major update
        }
    }
}