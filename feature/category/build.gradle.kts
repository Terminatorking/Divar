plugins {
    id("convention.android.feature")
}

android {
    namespace = "ghazimoradi.soheil.divar.category"
}

dependencies {
    implementation(project(":domain"))
}
