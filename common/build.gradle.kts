
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.4.0"
    id("com.android.library")
}

group = "es.niadecode"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop") {
        jvmToolchain(11)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.animation)
                api(compose.material)

                val precompose_version = "1.4.3"
                api("moe.tlaster:precompose:$precompose_version")
                api("moe.tlaster:precompose-viewmodel:$precompose_version")

                api("com.github.Gikkman:Java-Twirk:0.7.1")

            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")
            }
        }

        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }

        //val jsMain by getting

    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}