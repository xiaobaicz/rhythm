plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "io.github.xiaobaicz.rhythm"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.github.xiaobaicz.rhythm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.bundles.common)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.test.android)

    implementation(libs.common)
    implementation(libs.store)
    implementation(libs.store.mmkv)
    implementation(libs.store.serialize.gson)
}