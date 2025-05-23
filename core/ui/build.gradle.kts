plugins {
    id("org.jetbrains.kotlin.plugin.compose")
    id("convention.android.library")
    id("convention.android.library.compose")
    id("convention.android.hilt")
    id("convention.android.serialization")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ghazimoradi.soheil.divar.ui"
}

dependencies {
    implementation(project(":core:utils"))
    implementation(project(":domain"))
    implementation(libs.coil.svg)
}