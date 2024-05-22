plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.gms.google.services)
    alias(libs.plugins.com.google.firebase.crashlytics)
}

android {
    namespace = "io.github.xiaobaicz.rhythm"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.github.xiaobaicz.rhythm"
        minSdk = 24
        targetSdk = 34
        versionCode = 2000
        versionName = "2.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

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

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    implementation(libs.bundles.common)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.test.android)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    implementation(project(":design"))

    implementation(libs.store)
    implementation(libs.store.mmkv)
    implementation(libs.store.serialize.gson)

    implementation(libs.initializer)

    implementation(libs.log)
    debugImplementation(libs.log.android)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.coil.compose)
}