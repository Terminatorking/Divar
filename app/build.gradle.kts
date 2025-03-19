plugins {
    id("convention.android.application")
    id("convention.android.application.compose")
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
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:secure-shared-pref"))
    implementation(project(":core:utils"))
}