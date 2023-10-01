package com.esmaeel.compo.buildsrc

object Versions {
    const val ktlint = "0.37.2"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.2.0-alpha08"
    const val junit = "junit:junit:4.13"

    object Accompanist {
        private const val version = "0.2.0"
        const val coil = "dev.chrisbanes.accompanist:accompanist-coil:$version"
    }

    object Dex {
        private const val multidex_version = "2.0.1"
        const val multi_dex = "androidx.multidex:multidex:$multidex_version"
    }

    object Kotlin {
        private const val version = "1.4.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.3.9"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Ktx {
        private const val live_data_version = "2.1.0-beta01"
        private const val fragment_ktx_version = "1.2.5"
        const val viewModelScope = "androidx.lifecycle:lifecycle-viewmodel-ktx:$live_data_version"
        const val lifecycleScope = "androidx.lifecycle:lifecycle-runtime-ktx:$live_data_version"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$live_data_version"

        const val fragment_ktx = "androidx.fragment:fragment-ktx:$fragment_ktx_version"

    }

    object Retrofit {
        private const val version = "2.8.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gson_converter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHttp {
        private const val version = "4.3.1"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Gson {
        private const val version = "2.8.6"
        const val gson = "com.google.code.gson:gson:$version"
    }

    object Loggers {
        private const val timber_version = "4.7.1"
        private const val logger_version = "2.2.0"
        const val timber = "com.jakewharton.timber:timber:$timber_version"
        const val logger = "com.orhanobut:logger:$logger_version"

    }


    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
        const val coreKtx = "androidx.core:core-ktx:1.5.0-alpha02"

        object Compose {
            const val snapshot = ""
            const val version = "1.0.0-alpha01"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val foundation = "androidx.compose.foundation:foundation:${version}"
            const val layout = "androidx.compose.foundation:foundation-layout:${version}"
            const val ui = "androidx.compose.ui:ui:${version}"
            const val material = "androidx.compose.material:material:${version}"
            const val animation = "androidx.compose.animation:animation:${version}"
            const val tooling = "androidx.ui:ui-tooling:${version}"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val uiLiveData = "androidx.compose.runtime:runtime-livedata:$version"

        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.5"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }
    }
}