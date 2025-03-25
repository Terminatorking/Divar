plugins {
    id("convention.android.feature")
}
android {
    namespace = "ghazimoradi.soheil.divar.splash"
}

dependencies {
    implementation(libs.androidx.core.splashscreen)
}