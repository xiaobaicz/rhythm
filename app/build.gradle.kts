plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.gms.google.services)
    alias(libs.plugins.com.google.firebase.crashlytics)
}

android {
    signingConfigs {
        val jksFile = System.getenv("XB_JKS_FILE")
        val jksPassword = System.getenv("XB_JKS_PASSWORD")
        getByName("debug") {
            storeFile = file(jksFile)
            storePassword = jksPassword
            keyPassword = jksPassword
            keyAlias = "debug"
        }
        create("release") {
            storeFile = file(jksFile)
            storePassword = jksPassword
            keyPassword = jksPassword
            keyAlias = "rhythm"
        }
    }
    namespace = "io.github.xiaobaicz.rhythm"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.github.xiaobaicz.rhythm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1000
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
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

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    implementation(libs.mmkv)

    implementation(libs.common)
    implementation(libs.store)
    implementation(libs.store.mmkv)
    implementation(libs.store.serialize.gson)
    implementation(libs.text)
}