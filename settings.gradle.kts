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

rootProject.name = "CompoosePreperations"
include(":app")
include(":basiclayoutpractice")
include(":jetnewsvariablesize")
include(":navigationgraphpracticew")
include(":hiltwithcleannavgraph")
