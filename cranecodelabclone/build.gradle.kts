import org.jetbrains.kotlin.kapt3.base.Kapt
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.cranecodelabclone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cranecodelabclone"
        minSdk = 26
        targetSdk = 34
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    // Hilt Core
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.44")
//    Kapt("com.google.dagger:hilt-compiler:2.46.1")
//
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation ("androidx.navigation:navigation-compose:2.7.6")

    //window size class
    implementation ("androidx.compose.material3:material3-window-size-class:1.2.1")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0")
    //coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Google Maps dependencies
    implementation ("com.google.android.gms:play-services-maps:18.0.0")
    implementation ("com.google.maps.android:maps-compose:2.0.0")

    // Add the foundation library
    implementation ("androidx.compose.foundation:foundation:1.3.2")

    // Add the necessary Compose dependencies
    implementation ("androidx.compose.material3:material3-window-size-class:1.2.1")
    implementation ("androidx.compose.runtime:runtime:1.6.8")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.8")

    // Add the animation library
    implementation ("androidx.compose.animation:animation:1.6.8")

    // Add the material-icons-extended library
    implementation ("androidx.compose.material:material-icons-extended:1.6.8")
    // Add the coil-compose library
    implementation ("io.coil-kt:coil-compose:2.6.0")

    // Add the AppCompat library
    implementation ("androidx.appcompat:appcompat:1.6.1")


}