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

}