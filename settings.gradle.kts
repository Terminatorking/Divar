pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Divar"
include(":app")
include(":data")
include(":domain")
include(":core:ui")
include(":core:database")
include(":core:network")
include(":core:secure-shared-pref")
include(":core:utils")
include(":feature:category")
include(":feature:home")
include(":feature:ads")
include(":feature:ads-detail")
include(":feature:auth")
include(":feature:chat")
include(":feature:create-ads")
include(":feature:filter")
include(":feature:location")
include(":feature:main")
include(":feature:profile")
include(":feature:search")
include(":feature:splash")
