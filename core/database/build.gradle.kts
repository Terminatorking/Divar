plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
    id("convention.android.room")
    id("androidx.room") version ("2.6.1")
}

android {
    namespace = "ghazimoradi.soheil.divar.database"
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(project(":core:utils"))
    testImplementation(kotlin("test"))
}