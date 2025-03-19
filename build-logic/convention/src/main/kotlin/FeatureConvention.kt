import modularization.libraryGradle
import modularization.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class FeatureConvention : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            applyDependencies()
            libraryGradle {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply (libs.findLibrary("compose.compiler").get())
            apply("convention.android.library")
            apply("convention.android.library.compose")
            apply("convention.android.hilt")
            apply("convention.android.serialization")
            apply("com.google.devtools.ksp")
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            // TODO: After adding modules, update this section to include [list the specific modules needed here]
        }
    }
}