group = "io.github.kryszak"
version = "3.6.2"

plugins {
    val kotlinVersion = "2.1.21"
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    kotlin("jvm") version kotlinVersion
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            val sonatypeUsername = providers
                .environmentVariable("SONATYPE_USERNAME")
            val sonatypePassword = providers
                .environmentVariable("SONATYPE_PASSWORD")
            if (sonatypeUsername.isPresent && sonatypePassword.isPresent) {
                username.set(sonatypeUsername.get())
                password.set(sonatypePassword.get())
            }
        }
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks {
        test {
            useJUnitPlatform()
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        val kotestVersion = "5.9.1"
        // testing
        testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
        testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
        testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")
    }
}

