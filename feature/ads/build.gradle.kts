plugins {
    id("convention.android.feature")
}
android {
    namespace = "ghazimoradi.soheil.divar.ads"
}

dependencies {
    implementation(project(":feature:category"))
}