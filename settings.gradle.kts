@file:Suppress("UnstableApiUsage")

include(":domain")


include(":data")


include(":feature:shopping")


include(":feature:settings")


include(":feature:home")


pluginManagement {
    repositories {
        google()
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

rootProject.name = "Recipe Book"
include(":app")
