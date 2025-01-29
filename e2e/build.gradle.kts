group = rootProject.group
version = rootProject.version

plugins {
    id("com.adarshr.test-logger") version "4.0.0"
}

dependencies {
    implementation(project(":gwatlin"))
}
