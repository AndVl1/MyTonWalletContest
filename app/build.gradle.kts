plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
//    alias(libs.plugins.vkompose)
//    alias(libs.plugins.vkompose.skip.checker)
//    alias(libs.plugins.vkompose.logger)
//    alias(libs.plugins.vkompose.highlighter)
//    alias(libs.plugins.vkompose.source.cleaner)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.andvl.mytonwallet.contest"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.andvl.mytonwallet.contest"
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
    implementation(libs.koin)
    implementation(libs.koin.compose)
    implementation(libs.androidx.ui.text.google.fonts)

    implementation(libs.kotlinx.collections.immutable)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

//vkompose {
//    skippabilityCheck = true
//    // or
//    skippabilityCheck {
//        strongSkipping {
//            // Fail compilation if there is any problem with strong skipping mode
//            strongSkippingFailFastEnabled = false // false by default
//        }
//    }
//
//    recompose {
//        isHighlighterEnabled = false
//        logger {
//            isEnabled = true
//            // true by default since 0.5
//            // log modifier arguments changes
//            logModifierChanges = true
//            // true by default since 0.5
//            // log when function arguments (like lambdas or function references) of composable function are changed
//            logFunctionChanges = true
//        }
//    }
//
//    testTag {
//        isApplierEnabled = true
//        isDrawerEnabled = false
//        isCleanerEnabled = false
//    }
//
//    sourceInformationClean = true
//}
