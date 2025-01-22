plugins {
    id("com.adarshr.test-logger") version "4.0.0"
}

group = "io.github.kryszak"
version = "0.0.1"

dependencies {
    implementation(project(":code"))
}
