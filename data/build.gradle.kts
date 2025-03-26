plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
}

android {
    namespace = "ghazimoradi.soheil.divar.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:secure-shared-pref"))
    implementation(project(":core:utils"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
}