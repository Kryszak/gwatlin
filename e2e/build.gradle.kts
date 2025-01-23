plugins {
    id("com.adarshr.test-logger") version "4.0.0"
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(project(":gwatlin"))
}
