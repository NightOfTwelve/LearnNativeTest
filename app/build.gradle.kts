plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.learn.nativetest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.learn.nativetest"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
//                arguments += "-DANDROID_STL=c++_shared"
//                cppFlags += "-std=c++11"
//                cppFlags += "-std=c++14"
                cppFlags += "-std=c++20"
                //abiFilters = mutableListOf("armeabi", "armeabi-v7a" , "arm64-v8a", "x86", "x86_64", "mips", "mips64")
                //abiFilters = mutableSetOf("arm64-v8a");
            }
        }

        ndk {
            //abiFilters.addAll(arrayOf("armeabi-v7a", "arm64-v8a", "x86_64"))
            abiFilters.addAll(arrayOf("arm64-v8a"))
//            abiFilters.addAll(arrayOf("armeabi-v7a"))
        }

        ndkVersion = "26.1.10909125"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}