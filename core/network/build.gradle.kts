plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
}

android {
    namespace = "ghazimoradi.soheil.divar.network"
}

dependencies {
    implementation(project(":core:secure-shared-pref"))

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.kotlin.serialization)
}