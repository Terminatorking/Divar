plugins {
    id("convention.android.application")
    id("convention.android.application.compose")
    id("convention.android.hilt")
}

android {
    namespace = "ghazimoradi.soheil.divar"

    defaultConfig {
        applicationId = "ghazimoradi.soheil.divar"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:secure-shared-pref"))
    implementation(project(":core:database"))
    implementation(project(":core:utils"))
    implementation(project(":feature:category"))
    implementation(project(":feature:home"))
    implementation(project(":feature:location"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:main"))
    implementation(project(":feature:chat"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:search"))
    implementation(project(":feature:ads"))
    implementation(project(":feature:filter"))
    implementation(project(":feature:ads-detail"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:create-ads"))
    implementation(libs.androidx.core.splashscreen)
}