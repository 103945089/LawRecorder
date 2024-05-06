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
        maven {
            url = uri("https://jitpack.io")
        };
        maven{
            isAllowInsecureProtocol=true
            url = uri("http://172.16.247.8:18081/repository/maven-releases/")
        }

    }
}

rootProject.name = "LawRecorder"
include(":app")
