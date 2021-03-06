plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "com.example.testdiary"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.uiTooling)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.navigation)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.4.0-beta01")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.3")

    implementation("androidx.compose.runtime:runtime-livedata:1.0.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-rc01")

    implementation (Dependencies.Room.roomRuntime)
    kapt (Dependencies.Room.roomCompiler)
    implementation (Dependencies.Room.roomKtx)

    //Gson
    implementation (Dependencies.Gson.gson)

    implementation (Dependencies.Hilt.hiltAndroid)
    kapt (Dependencies.Hilt.hiltAndroidCompiler)
    implementation (Dependencies.Hilt.hiltLifecycleViewmodel)
    kapt (Dependencies.Hilt.hiltCompiler)
    implementation (Dependencies.Hilt.hiltNavigationCompose)

    //navigation animation
    implementation (Dependencies.Compose.accompanist)
}